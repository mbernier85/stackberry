package com.stackoverflow.json;

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
import java.util.Vector;

/**
 * A JSONArray is an ordered sequence of values. Its external text form is a
 * string wrapped in square brackets with commas separating the values. The
 * internal form is an object having <code>get</code> and <code>get</code>
 * methods for accessing the values by index, and <code>put</code> methods for
 * adding or replacing values. The values can be any of these types:
 * <code>Boolean</code>, <code>JSONArray</code>, <code>JSONObject</code>,
 * <code>Number</code>, <code>String</code>, or the
 * <code>JSONObject.NULL object</code>.
 * <p>
 * The constructor can convert a JSON text into a Java object. The
 * <code>toString</code> method converts to JSON text.
 * <p>
 * A <code>get</code> method returns a value if one can be found, and throws an
 * exception if one cannot be found. An <code>get</code> method returns a
 * default value instead of throwing an exception, and so is useful for
 * obtaining optional values.
 * <p>
 * The generic <code>get()</code> and <code>get()</code> methods return an
 * object which you can cast or query for type. There are also typed
 * <code>get</code> and <code>get</code> methods that do type checking and type
 * coersion for you.
 * <p>
 * The texts produced by the <code>toString</code> methods strictly conform to
 * JSON syntax rules. The constructors are isMore forgiving in the texts they will
 * accept:
 * <ul>
 * <li>An extra <code>,</code>&nbsp;<small>(comma)</small> may appear just
 *     before the closing bracket.</li>
 * <li>The <code>null</code> value will be inserted when there
 *     is <code>,</code>&nbsp;<small>(comma)</small> elision.</li>
 * <li>Strings may be quoted with <code>'</code>&nbsp;<small>(single
 *     quote)</small>.</li>
 * <li>Strings do not need to be quoted at all if they do not begin with a quote
 *     or single quote, and if they do not contain leading or trailing spaces,
 *     and if they do not contain any of these characters:
 *     <code>{ } [ ] / \ : , = ; #</code> and if they do not look like numbers
 *     and if they are not the reserved words <code>true</code>,
 *     <code>false</code>, or <code>null</code>.</li>
 * <li>Values can be separated by <code>;</code> <small>(semicolon)</small> as
 *     well as by <code>,</code> <small>(comma)</small>.</li>
 * <li>Numbers may have the <code>0-</code> <small>(octal)</small> or
 *     <code>0x-</code> <small>(hex)</small> prefix.</li>
 * <li>Comments written in the slashshlash, slashstar, and hash conventions
 *     will be ignored.</li>
 * </ul>

 * @author JSON.org
 * @author drubin <drubin [at] smartcube dot co.za>
 * @version 2
 *
 */
public class JSONArray extends Vector implements Json {

    /**
     * Construct an empty JSONArray.
     */
    public JSONArray() {
    }

    public JSONArray(final byte[] x) throws Exception {
        this(new JSONTokener(x));
    }

    /**
     * Construct a JSONArray from a JSONTokener.
     * @param x A JSONTokener
     * @throws Exception If there is a syntax error.
     */
    public JSONArray(final JSONTokener x) throws Exception {
        this();
        if (x.nextClean() != '[') {
            throw x.syntaxError("A JSONArray text must start with '['");
        }
        if (x.nextClean() == ']') {
            return;
        }
        x.back();
        for (;;) {
            if (x.nextClean() == ',') {
                x.back();
                this.addElement(null);
            } else {
                x.back();
                this.addElement(x.nextValue());
            }
            switch (x.nextClean()) {
                case ',':
                    if (x.nextClean() == ']') {
                        return;
                    }
                    x.back();
                    break;
                case ']':
                    return;
                default:
                    throw x.syntaxError("Expected a ',' or ']'");
            }
        }
    }

    /**
     * Contructs a JSONArry object from the Vector.
     * @param v
     * @return
     */
    public static JSONArray createFromVector(final Vector v) {
        JSONArray arr = new JSONArray();
        for (int i = 0; i < v.size(); i++) {
            arr.put(v.elementAt(i));
        }
        return arr;
    }

    /**
     * Get the JSONObject associated with an index.
     * @param index subscript
     * @return      A JSONObject value.
     * @throws Exception If there is no value for the index or if the
     * value is not a JSONObject
     */
    public JSONObject getJSONObject(final int index) {
        Object o = elementAt(index);
        return o instanceof JSONObject ? (JSONObject) o : null;
    }

    /**
     * Determine if the value is null.
     * @param index The index must be between 0 and size() - 1.
     * @return true if the value at the index is null, or if there is no value.
     */
    public boolean isNull(final int index) {
        return JSONObject.NULL.equals(elementAt(index));
    }

    /**
     * Make a string from the contents of this JSONArray. The
     * <code>separator</code> string is inserted between each element.
     * Warning: This method assumes that the data structure is acyclical.
     * @param separator A string that will be inserted between the elements.
     * @return a string.
     * @throws Exception If the array contains an invalid number.
     */
    public String join(final String separator) throws Exception {
        int len = size();
        StringBuffer sb = new StringBuffer(len * separator.length() + len);

        for (int i = 0; i < len; i += 1) {
            if (i > 0) {
                sb.append(separator);
            }
            sb.append(JSONObject.valueToString(this.elementAt(i)));
        }
        return sb.toString();
    }

    /**
     * Get the optional object value associated with an index.
     * @param index The index must be between 0 and size() - 1.
     * @return      An object value, or null if there is no
     *              object at that index.
     */
    public Object elementAt(final int index) {
        return (index < 0 || index >= size()) ? null : super.elementAt(index);
    }

    /**
     * Append an object value. This increases the array's size by one.
     * @param value An object value.  The value should be a
     *  Boolean, Double, Integer, JSONArray, JSONObject, Long, or String, or the
     *  JSONObject.NULL object.
     * @return this.
     */
    public JSONArray put(final Object value) {
        this.addElement(value);
        return this;
    }

    /**
     * Put or replace an object value in the JSONArray. If the index is greater
     *  than the size of the JSONArray, then null elements will be added as
     *  necessary to pad it out.
     * @param index The subscript.
     * @param value The value to put into the array. The value should be a
     *  Boolean, Double, Integer, JSONArray, JSONObject, Long, or String, or the
     *  JSONObject.NULL object.
     * @return this.
     * @throws Exception If the index is negative or if the the value is
     *  an invalid number.
     */
    public JSONArray put(final int index, final Object value) throws Exception {
        if (index < 0) {
            throw new Exception("JSONArray[" + index + "] not found.");
        }
        if (index < size()) {
            this.setElementAt(value, index);
        } else {
            while (index != size()) {
                put(JSONObject.NULL);
            }
            put(value);
        }
        return this;
    }

    /**
     * Make a JSON text of this JSONArray. For compactness, no
     * unnecessary whitespace is added. If it is not possible to produce a
     * syntactically correct JSON text then null will be returned instead. This
     * could occur if the array contains an invalid number.
     * <p>
     * Warning: This method assumes that the data structure is acyclical.
     *
     * @return a printable, displayable, transmittable
     *  representation of the array.
     */
    public String toString() {
        try {
            return '[' + join(",") + ']';
        } catch (Exception e) {
            return null;
        }
    }

    /**
     *
     * @return Returns the toString method.
     */
    public String toJSONString() {
        return toString();
    }
}