package com.stackberry.json;

/*
Copyright (c) 2002 JSON.org

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

The Software shall be used for Good, not Evil.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
/**
 * A JSONTokener takes a source string and extracts characters and tokens from
 * it. It is used by the JSONObject and JSONArray constructors to parse
 * JSON source strings.
 * @author JSON.org
 * @author drubin <drubin [at] smartcube dot co.za>
 * @version 2
 *
 * NOTES:
 * * drubin - Maybe add functionality to allow input from streams of data
 */
public class JSONTokener {

    /**
     * The index of the next character.
     */
    private int myIndex;
    /**
     * The source string being tokenized.
     */
    private byte[] mySource;

    /**
     * Construct a JSONTokener from a string.
     *
     * @param s     A source string.
     */
    public JSONTokener(final byte[] s) {
        this.myIndex = 0;
        this.mySource = s;
    }

    /**
     * Back up one character. This provides a sort of lookahead capability,
     * so that you can test for a digit or letter before attempting to parse
     * the next number or identifier.
     */
    public void back() {
        if (this.myIndex > 0) {
            --this.myIndex;
        }
    }

    /**
     * Get the hex value of a character (base16).
     * @param c A character between '0' and '9' or between 'A' and 'F' or
     * between 'a' and 'f'.
     * @return  An int between 0 and 15, or -1 if c was not a hex digit.
     */
    public static int dehexchar(final char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'F') {
            return c - ('A' - 10);
        }
        if (c >= 'a' && c <= 'f') {
            return c - ('a' - 10);
        }
        return -1;
    }

    /**
     * Determine if the source string still contains characters that next()
     * can consume.
     * @return true if not yet at the end of the source.
     */
    public boolean isMore() {
        return this.myIndex < this.mySource.length;
    }

    /**
     * Get the next character in the source string.
     *
     * @return The next character, or 0 if past the end of the source string.
     */
    public char next() {
        if (isMore()) {
            char c = (char) this.mySource[this.myIndex];
            this.myIndex += 1;
            return c;
        }
        return 0;
    }

    /**
     * Consume the next character, and check that it matches a specified
     * character.
     * @param c The character to match.
     * @return The character.
     * @throws Exception if the character does not match.
     */
    public char next(final char c) throws JSONException {
        char n = next();
        if (n != c) {
            throw syntaxError("Expected '" + c + "' and instead saw '" +
                    n + "'.");
        }
        return n;
    }

    /**
     * Get the next n characters.
     *
     * @param n     The number of characters to take.
     * @return      A string of n characters.
     * @throws Exception
     *   Substring bounds error if there are not
     *   n characters remaining in the source string.
     */
    public String next(final int n) throws JSONException {
        int i = this.myIndex;
        int j = i + n;
        if (j >= this.mySource.length) {
            throw syntaxError("Substring bounds error");
        }
        this.myIndex += n;
        String next = "";
        for (int k = i ; k < j  ; k ++) {
        	next += mySource[k];
        }
        return next;
    }

    /**
     * Get the next char in the string, skipping whitespace
     * and comments (slashslash, slashstar, and hash).
     * @throws Exception
     * @return  A character, or 0 if there are no isMore characters.
     */
    public char nextClean() throws JSONException {
        for (;;) {
            char c = next();
            if (c == '/') {
                switch (next()) {
                    case '/':
                        do {
                            c = next();
                        } while (c != '\n' && c != '\r' && c != 0);
                        break;
                    case '*':
                        for (;;) {
                            c = next();
                            if (c == 0) {
                                throw syntaxError("Unclosed comment.");
                            }
                            if (c == '*') {
                                if (next() == '/') {
                                    break;
                                }
                                back();
                            }
                        }
                        break;
                    default:
                        back();
                        return '/';
                }
            } else if (c == '#') {
                do {
                    c = next();
                } while (c != '\n' && c != '\r' && c != 0);
            } else if (c == 0 || c > ' ') {
                return c;
            }
        }
    }

    /**
     * Return the characters up to the next close quote character.
     * Backslash processing is done. The formal JSON format does not
     * allow strings in single quotes, but an implementation is allowed to
     * accept them.
     * @param quote The quoting character, either
     *      <code>"</code>&nbsp;<small>(double quote)</small> or
     *      <code>'</code>&nbsp;<small>(single quote)</small>.
     * @return      A String.
     * @throws Exception Unterminated string.
     */
    public String nextString(final char quote) throws JSONException {
        char c;
        StringBuffer sb = new StringBuffer();
        for (;;) {
            c = next();
            switch (c) {
                case 0:
                case '\n':
                case '\r':
                    throw syntaxError("Unterminated string");
                case '\\':
                    c = next();
                    switch (c) {
                        case 'b':
                            sb.append('\b');
                            break;
                        case 't':
                            sb.append('\t');
                            break;
                        case 'n':
                            sb.append('\n');
                            break;
                        case 'f':
                            sb.append('\f');
                            break;
                        case 'r':
                            sb.append('\r');
                            break;
                        case 'u':
                            sb.append((char) Integer.parseInt(next(4), 16));
                            break;
                        case 'x':
                            sb.append((char) Integer.parseInt(next(2), 16));
                            break;
                        default:
                            sb.append(c);
                    }
                    break;
                default:
                    if (c == quote) {
                        return sb.toString();
                    }
                    sb.append(c);
            }
        }
    }

    /**
     * Get the text up but not including the specified character or the
     * end of line, whichever comes first.
     * @param  d A delimiter character.
     * @return   A string.
     */
    public String nextTo(final char d) {
        StringBuffer sb = new StringBuffer();
        for (;;) {
            char c = next();
            if (c == d || c == 0 || c == '\n' || c == '\r') {
                if (c != 0) {
                    back();
                }
                return sb.toString().trim();
            }
            sb.append(c);
        }
    }

    /**
     * Get the text up but not including one of the specified delimeter
     * characters or the end of line, whichever comes first.
     * @param delimiters A set of delimiter characters.
     * @return A string, trimmed.
     */
    public String nextTo(final String delimiters) {
        char c;
        StringBuffer sb = new StringBuffer();
        for (;;) {
            c = next();
            if (delimiters.indexOf(c) >= 0 || c == 0 ||
                    c == '\n' || c == '\r') {
                if (c != 0) {
                    back();
                }
                return sb.toString().trim();
            }
            sb.append(c);
        }
    }

    /**
     * Get the next value. The value can be a Boolean, Double, Integer,
     * JSONArray, JSONObject, Long, or String, or the JSONObject.NULL object.
     * @throws Exception If syntax error.
     *
     * @return An object.
     */
    public Object nextValue() throws JSONException {
        char c = nextClean();
        String s;

        switch (c) {
            case '"':
            case '\'':
                return nextString(c);
            case '{':
                back();
                return new JSONObject(this);
            case '[':
                back();
                return new JSONArray(this);
        }

        /*
         * Handle unquoted text. This could be the values true, false, or
         * null, or it can be a number. An implementation (such as this one)
         * is allowed to also accept non-standard forms.
         *
         * Accumulate characters until we reach the end of the text or a
         * formatting character.
         */

        StringBuffer sb = new StringBuffer();
        int state = 0;
        //0=string, 1 = int, 2 =double, 3, boolean;
        char b = c;
        while (c >= ' ' && ",:]}\"[{".indexOf(c) < 0) {
            sb.append(c);
            c = next();
        }
        back();
        /*
         * If it is true, false, or null, return the proper value.
         */
        s = sb.toString().trim();
        return s;
    }

    /**
     * Skip characters until the next character is the requested character.
     * If the requested character is not found, no characters are skipped.
     * @param to A character to skip to.
     * @return The requested character, or zero if the requested character
     * is not found.
     */
    public char skipTo(final char to) {
        char c;
        int index = this.myIndex;
        do {
            c = next();
            if (c == 0) {
                this.myIndex = index;
                return c;
            }
        } while (c != to);
        back();
        return c;
    }

    /**
     * Skip characters until past the requested string.
     * If it is not found, we are left at the end of the source.
     * @param to A string to skip past.
     */
    public void skipPast(final String to) {
        String source = new String(mySource);
    	this.myIndex = source.indexOf(to, this.myIndex);
        if (this.myIndex < 0) {
            this.myIndex = this.mySource.length;
        } else {
            this.myIndex += to.length();
        }
    }

    /**
     * Make a Exception to signal a syntax error.
     *
     * @param message The error message.
     * @return  A Exception object, suitable for throwing
     */
    public JSONException syntaxError(final String message) {
        return new JSONException(message + toString(),"");
    }

    /**
     * Make a printable string of this JSONTokener.
     * @return " at character [this.myIndex] of [this.mySource]"
     */
    public String toString() {
        return " at character " + this.myIndex + " of " + this.mySource;
    }
}