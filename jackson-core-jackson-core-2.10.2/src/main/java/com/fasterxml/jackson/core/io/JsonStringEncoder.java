package com.fasterxml.jackson.core.io;

import java.util.Arrays;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;

/**
 * Helper class used for efficient encoding of JSON String values (including
 * JSON field names) into Strings or UTF-8 byte arrays.
 *<p>
 * Note that methods in here are somewhat optimized, but not ridiculously so.
 * Reason is that conversion method results are expected to be cached so that
 * these methods will not be hot spots during normal operation.
 */
public final class JsonStringEncoder
{
    /*
    /**********************************************************************
    /* Constants
    /**********************************************************************
     */

    private final static char[] HC = CharTypes.copyHexChars();

    private final static byte[] HB = CharTypes.copyHexBytes();

    private final static int SURR1_FIRST = 0xD800;
    private final static int SURR1_LAST = 0xDBFF;
    private final static int SURR2_FIRST = 0xDC00;
    private final static int SURR2_LAST = 0xDFFF;

    private final static int INITIAL_CHAR_BUFFER_SIZE = 120;
    private final static int INITIAL_BYTE_BUFFER_SIZE = 200;

    /*
    /**********************************************************************
    /* Construction, instance access
    /**********************************************************************
     */

    // Since 2.10 we have stateless singleton and NO fancy ThreadLocal/SofRef caching!!!
    private final static JsonStringEncoder instance = new JsonStringEncoder();
    
    public JsonStringEncoder() { }

    /**
     * Factory method for getting an instance; this is either recycled per-thread instance,
     * or a newly constructed one.
     */
    public static JsonStringEncoder getInstance() {
        return instance;
    }

    /*
    /**********************************************************************
    /* Public API
    /**********************************************************************
     */

    /**
     * Method that will quote text contents using JSON standard quoting,
     * and return results as a character array
     */
    public char[] quoteAsString(String input)
    {
        char[] outputBuffer = new char[INITIAL_CHAR_BUFFER_SIZE];
        final int[] escCodes = CharTypes.get7BitOutputEscapes();
        final int escCodeCount = escCodes.length;
        int inPtr = 0;
        final int inputLen = input.length();
        TextBuffer textBuffer = null;
        int outPtr = 0;
        char[] qbuf = null;

        outer:
        while (inPtr < inputLen) {
            tight_loop:
            while (true) {
                char c = input.charAt(inPtr);
                if (c < escCodeCount && escCodes[c] != 0) {
                    break tight_loop;
                }
                if (outPtr >= outputBuffer.length) {
                    if (textBuffer == null) {
                        textBuffer = TextBuffer.fromInitial(outputBuffer);
                    }
                    outputBuffer = textBuffer.finishCurrentSegment();
                    outPtr = 0;
                }
                outputBuffer[outPtr++] = c;
                if (++inPtr >= inputLen) {
                    break outer;
                }
            }
            // something to escape; 2 or 6-char variant? 
            if (qbuf == null) {
                qbuf = _qbuf();
            }
            char d = input.charAt(inPtr++);
            int escCode = escCodes[d];
            int length = (escCode < 0)
                    ? _appendNumeric(d, qbuf)
                    : _appendNamed(escCode, qbuf);
                    ;
            if ((outPtr + length) > outputBuffer.length) {
                int first = outputBuffer.length - outPtr;
                if (first > 0) {
                    System.arraycopy(qbuf, 0, outputBuffer, outPtr, first);
                }
                if (textBuffer == null) {
                    textBuffer = TextBuffer.fromInitial(outputBuffer);
                }
                outputBuffer = textBuffer.finishCurrentSegment();
                int second = length - first;
                System.arraycopy(qbuf, first, outputBuffer, 0, second);
                outPtr = second;
            } else {
                System.arraycopy(qbuf, 0, outputBuffer, outPtr, length);
                outPtr += length;
            }
        }

        if (textBuffer == null) {
            return Arrays.copyOfRange(outputBuffer, 0, outPtr);
        }
        textBuffer.setCurrentLength(outPtr);
        return textBuffer.contentsAsArray();
    }

    /**
     * Overloaded variant of {@link #quoteAsString(String)}.
     *
     * @since 2.10
     */
    public char[] quoteAsString(CharSequence input)
    {
        // 15-Aug-2019, tatu: Optimize common case as JIT can't get rid of overhead otherwise
        if (input instanceof String) {
            return quoteAsString((String) input);
        }

        TextBuffer textBuffer = null;

        char[] outputBuffer = new char[INITIAL_CHAR_BUFFER_SIZE];
        final int[] escCodes = CharTypes.get7BitOutputEscapes();
        final int escCodeCount = escCodes.length;
        int inPtr = 0;
        final int inputLen = input.length();
        int outPtr = 0;
        char[] qbuf = null;
 
        outer:
        while (inPtr < inputLen) {
            tight_loop:
            while (true) {
                char c = input.charAt(inPtr);
                if (c < escCodeCount && escCodes[c] != 0) {
                    break tight_loop;
                }
                if (outPtr >= outputBuffer.length) {
                    if (textBuffer == null) {
                        textBuffer = TextBuffer.fromInitial(outputBuffer);
                    }
                    outputBuffer = textBuffer.finishCurrentSegment();
                    outPtr = 0;
                }
                outputBuffer[outPtr++] = c;
                if (++inPtr >= inputLen) {
                    break outer;
                }
            }
            // something to escape; 2 or 6-char variant? 
            if (qbuf == null) {
                qbuf = _qbuf();
            }
            char d = input.charAt(inPtr++);
            int escCode = escCodes[d];
            int length = (escCode < 0)
                    ? _appendNumeric(d, qbuf)
                    : _appendNamed(escCode, qbuf);
                    ;
            if ((outPtr + length) > outputBuffer.length) {
                int first = outputBuffer.length - outPtr;
                if (first > 0) {
                    System.arraycopy(qbuf, 0, outputBuffer, outPtr, first);
                }
                if (textBuffer == null) {
                    textBuffer = TextBuffer.fromInitial(outputBuffer);
                }
                outputBuffer = textBuffer.finishCurrentSegment();
                int second = length - first;
                System.arraycopy(qbuf, first, outputBuffer, 0, second);
                outPtr = second;
            } else {
                System.arraycopy(qbuf, 0, outputBuffer, outPtr, length);
                outPtr += length;
            }
        }

        if (textBuffer == null) {
            return Arrays.copyOfRange(outputBuffer, 0, outPtr);
        }
        textBuffer.setCurrentLength(outPtr);
        return textBuffer.contentsAsArray();
    }

    /**
     * Method that will quote text contents using JSON standard quoting,
     * and append results to a supplied {@link StringBuilder}.
     * Use this variant if you have e.g. a {@link StringBuilder} and want to avoid superfluous copying of it.
     *
     * @since 2.8
     */
    public void quoteAsString(CharSequence input, StringBuilder output)
    {
        final int[] escCodes = CharTypes.get7BitOutputEscapes();
        final int escCodeCount = escCodes.length;
        int inPtr = 0;
        final int inputLen = input.length();
        char[] qbuf = null;

        outer:
        while (inPtr < inputLen) {
            tight_loop:
            while (true) {
                char c = input.charAt(inPtr);
                if (c < escCodeCount && escCodes[c] != 0) {
                    break tight_loop;
                }
                output.append(c);
                if (++inPtr >= inputLen) {
                    break outer;
                }
            }
            // something to escape; 2 or 6-char variant?
            if (qbuf == null) {
                qbuf = _qbuf();
            }
            char d = input.charAt(inPtr++);
            int escCode = escCodes[d];
            int length = (escCode < 0)
                    ? _appendNumeric(d, qbuf)
                    : _appendNamed(escCode, qbuf);
            output.append(qbuf, 0, length);
        }
    }

    /**
     * Will quote given JSON String value using standard quoting, encode
     * results as UTF-8, and return result as a byte array.
     */
    @SuppressWarnings("resource")
    public byte[] quoteAsUTF8(String text)
    {
        int inputPtr = 0;
        int inputEnd = text.length();
        int outputPtr = 0;
        byte[] outputBuffer = new byte[INITIAL_BYTE_BUFFER_SIZE];
        ByteArrayBuilder bb = null;
        
        main:
        while (inputPtr < inputEnd) {
            final int[] escCodes = CharTypes.get7BitOutputEscapes();

            inner_loop: // ASCII and escapes
            while (true) {
                int ch = text.charAt(inputPtr);
                if (ch > 0x7F || escCodes[ch] != 0) {
                    break inner_loop;
                }
                if (outputPtr >= outputBuffer.length) {
                    if (bb == null) {
                        bb = ByteArrayBuilder.fromInitial(outputBuffer, outputPtr);
                    }
                    outputBuffer = bb.finishCurrentSegment();
                    outputPtr = 0;
                }
                outputBuffer[outputPtr++] = (byte) ch;
                if (++inputPtr >= inputEnd) {
                    break main;
                }
            }
            if (bb == null) {
                bb = ByteArrayBuilder.fromInitial(outputBuffer, outputPtr);
            }
            if (outputPtr >= outputBuffer.length) {
                outputBuffer = bb.finishCurrentSegment();
                outputPtr = 0;
            }
            // Ok, so what did we hit?
            int ch = (int) text.charAt(inputPtr++);
            if (ch <= 0x7F) { // needs quoting
                int escape = escCodes[ch];
                // ctrl-char, 6-byte escape...
                outputPtr = _appendByte(ch, escape, bb, outputPtr);
                outputBuffer = bb.getCurrentSegment();
                continue main;
            }
            if (ch <= 0x7FF) { // fine, just needs 2 byte output
                outputBuffer[outputPtr++] = (byte) (0xc0 | (ch >> 6));
                ch = (0x80 | (ch & 0x3f));
            } else { // 3 or 4 bytes
                // Surrogates?
                if (ch < SURR1_FIRST || ch > SURR2_LAST) { // nope
                    outputBuffer[outputPtr++] = (byte) (0xe0 | (ch >> 12));
                    if (outputPtr >= outputBuffer.length) {
                        outputBuffer = bb.finishCurrentSegment();
                        outputPtr = 0;
                    }
                    outputBuffer[outputPtr++] = (byte) (0x80 | ((ch >> 6) & 0x3f));
                    ch = (0x80 | (ch & 0x3f));
                } else { // yes, surrogate pair
                    if (ch > SURR1_LAST) { // must be from first range
                        _illegal(ch);
                    }
                    // and if so, followed by another from next range
                    if (inputPtr >= inputEnd) {
                        _illegal(ch);
                    }
                    ch = _convert(ch, text.charAt(inputPtr++));
                    if (ch > 0x10FFFF) { // illegal, as per RFC 4627
                        _illegal(ch);
                    }
                    outputBuffer[outputPtr++] = (byte) (0xf0 | (ch >> 18));
                    if (outputPtr >= outputBuffer.length) {
                        outputBuffer = bb.finishCurrentSegment();
                        outputPtr = 0;
                    }
                    outputBuffer[outputPtr++] = (byte) (0x80 | ((ch >> 12) & 0x3f));
                    if (outputPtr >= outputBuffer.length) {
                        outputBuffer = bb.finishCurrentSegment();
                        outputPtr = 0;
                    }
                    outputBuffer[outputPtr++] = (byte) (0x80 | ((ch >> 6) & 0x3f));
                    ch = (0x80 | (ch & 0x3f));
                }
            }
            if (outputPtr >= outputBuffer.length) {
                outputBuffer = bb.finishCurrentSegment();
                outputPtr = 0;
            }
            outputBuffer[outputPtr++] = (byte) ch;
        }
        if (bb == null) {
            return Arrays.copyOfRange(outputBuffer, 0, outputPtr);
        }
        return bb.completeAndCoalesce(outputPtr);
    }

    /**
     * Will encode given String as UTF-8 (without any quoting), return
     * resulting byte array.
     */
    @SuppressWarnings("resource")
    public byte[] encodeAsUTF8(String text)
    {
        int inputPtr = 0;
        int inputEnd = text.length();
        int outputPtr = 0;
        byte[] outputBuffer = new byte[INITIAL_BYTE_BUFFER_SIZE];
        int outputEnd = outputBuffer.length;
        ByteArrayBuilder bb = null;

        main_loop:
        while (inputPtr < inputEnd) {
            int c = text.charAt(inputPtr++);

            // first tight loop for ascii
            while (c <= 0x7F) {
                if (outputPtr >= outputEnd) {
                    if (bb == null) {
                        bb = ByteArrayBuilder.fromInitial(outputBuffer, outputPtr);
                    }
                    outputBuffer = bb.finishCurrentSegment();
                    outputEnd = outputBuffer.length;
                    outputPtr = 0;
                }
                outputBuffer[outputPtr++] = (byte) c;
                if (inputPtr >= inputEnd) {
                    break main_loop;
                }
                c = text.charAt(inputPtr++);
            }

            // then multi-byte...
            if (bb == null) {
                bb = ByteArrayBuilder.fromInitial(outputBuffer, outputPtr);
            }
            if (outputPtr >= outputEnd) {
                outputBuffer = bb.finishCurrentSegment();
                outputEnd = outputBuffer.length;
                outputPtr = 0;
            }
            if (c < 0x800) { // 2-byte
                outputBuffer[outputPtr++] = (byte) (0xc0 | (c >> 6));
            } else { // 3 or 4 bytes
                // Surrogates?
                if (c < SURR1_FIRST || c > SURR2_LAST) { // nope
                    outputBuffer[outputPtr++] = (byte) (0xe0 | (c >> 12));
                    if (outputPtr >= outputEnd) {
                        outputBuffer = bb.finishCurrentSegment();
                        outputEnd = outputBuffer.length;
                        outputPtr = 0;
                    }
                    outputBuffer[outputPtr++] = (byte) (0x80 | ((c >> 6) & 0x3f));
                } else { // yes, surrogate pair
                    if (c > SURR1_LAST) { // must be from first range
                        _illegal(c);
                    }
                    // and if so, followed by another from next range
                    if (inputPtr >= inputEnd) {
                        _illegal(c);
                    }
                    c = _convert(c, text.charAt(inputPtr++));
                    if (c > 0x10FFFF) { // illegal, as per RFC 4627
                        _illegal(c);
                    }
                    outputBuffer[outputPtr++] = (byte) (0xf0 | (c >> 18));
                    if (outputPtr >= outputEnd) {
                        outputBuffer = bb.finishCurrentSegment();
                        outputEnd = outputBuffer.length;
                        outputPtr = 0;
                    }
                    outputBuffer[outputPtr++] = (byte) (0x80 | ((c >> 12) & 0x3f));
                    if (outputPtr >= outputEnd) {
                        outputBuffer = bb.finishCurrentSegment();
                        outputEnd = outputBuffer.length;
                        outputPtr = 0;
                    }
                    outputBuffer[outputPtr++] = (byte) (0x80 | ((c >> 6) & 0x3f));
                }
            }
            if (outputPtr >= outputEnd) {
                outputBuffer = bb.finishCurrentSegment();
                outputEnd = outputBuffer.length;
                outputPtr = 0;
            }
            outputBuffer[outputPtr++] = (byte) (0x80 | (c & 0x3f));
        }
        if (bb == null) {
            return Arrays.copyOfRange(outputBuffer, 0, outputPtr);
        }
        return bb.completeAndCoalesce(outputPtr);
    }

    /*
    /**********************************************************************
    /* Internal methods
    /**********************************************************************
     */

    private char[] _qbuf() {
        char[] qbuf = new char[6];
        qbuf[0] = '\\';
        qbuf[2] = '0';
        qbuf[3] = '0';
        return qbuf;
    }

    private int _appendNumeric(int value, char[] qbuf) {
        qbuf[1] = 'u';
        // We know it's a control char, so only the last 2 chars are non-0
        qbuf[4] = HC[value >> 4];
        qbuf[5] = HC[value & 0xF];
        return 6;
    }

    private int _appendNamed(int esc, char[] qbuf) {
        qbuf[1] = (char) esc;
        return 2;
    }

    private int _appendByte(int ch, int esc, ByteArrayBuilder bb, int ptr)
    {
        bb.setCurrentSegmentLength(ptr);
        bb.append('\\');
        if (esc < 0) { // standard escape
            bb.append('u');
            if (ch > 0xFF) {
                int hi = (ch >> 8);
                bb.append(HB[hi >> 4]);
                bb.append(HB[hi & 0xF]);
                ch &= 0xFF;
            } else {
                bb.append('0');
                bb.append('0');
            }
            bb.append(HB[ch >> 4]);
            bb.append(HB[ch & 0xF]);
        } else { // 2-char simple escape
            bb.append((byte) esc);
        }
        return bb.getCurrentSegmentLength();
    }

    private static int _convert(int p1, int p2) {
        // Ok, then, is the second part valid?
        if (p2 < SURR2_FIRST || p2 > SURR2_LAST) {
            throw new IllegalArgumentException("Broken surrogate pair: first char 0x"+Integer.toHexString(p1)+", second 0x"+Integer.toHexString(p2)+"; illegal combination");
        }
        return 0x10000 + ((p1 - SURR1_FIRST) << 10) + (p2 - SURR2_FIRST);
    }

    private static void _illegal(int c) {
        throw new IllegalArgumentException(UTF8Writer.illegalSurrogateDesc(c));
    }
}
