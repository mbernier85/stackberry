package com.stackoverflow.json;
/**
 * The <code>Json</code> interface allows a <code>toJSONString()</code>
 * method so that a class can change the behavior of 
 * <code>JSONObject.toString()</code>, <code>JSONArray.toString()</code>,
 * and <code>JSONWriter.value(</code>Object<code>)</code>. The 
 * <code>toJSONString</code> method will be used instead of the default behavior 
 * of using the Object's <code>toString()</code> method and quoting the result.
 * @author drubin <drubin [at] smartcube dot co.za>
 */
public interface Json {
	/**
	 * The <code>toJSONString</code> method allows a class to produce its own JSON 
	 * serialization. 
	 * 
	 * @return A strictly syntactically correct JSON text.
	 */
	public String toJSONString();

    //public void toJSON(String json OR JSONObject);
    //The idea is to populate this object from the JSONObject
}
