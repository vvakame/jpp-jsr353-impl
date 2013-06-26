package net.vvakame.jpp.jsr353;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;

/**
 * Implementation for {@link JsonObject}.
 * @author vvakame
 */
public class JsonObjectImpl implements JsonObject {

	Map<String, JsonValue> object = new LinkedHashMap<String, JsonValue>();


	/**
	 * the constructor.
	 * @param object
	 * @category constructor
	 */
	public JsonObjectImpl(Map<String, JsonValue> object) {
		this.object = Collections.unmodifiableMap(object);
	}

	@Override
	public ValueType getValueType() {
		return ValueType.OBJECT;
	}

	@Override
	public boolean containsKey(Object key) {
		return object.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return object.containsValue(value);
	}

	@Override
	public Set<Map.Entry<String, JsonValue>> entrySet() {
		return object.entrySet();
	}

	@Override
	public JsonValue get(Object key) {
		return object.get(key);
	}

	@Override
	public boolean isEmpty() {
		return object.isEmpty();
	}

	@Override
	public Set<String> keySet() {
		return object.keySet();
	}

	@Override
	public int size() {
		return object.size();
	}

	@Override
	public Collection<JsonValue> values() {
		return object.values();
	}

	@Override
	public JsonArray getJsonArray(String name) {
		return (JsonArray) object.get(name);
	}

	@Override
	public JsonObject getJsonObject(String name) {
		return (JsonObject) object.get(name);
	}

	@Override
	public JsonNumber getJsonNumber(String name) {
		return (JsonNumber) object.get(name);
	}

	@Override
	public JsonString getJsonString(String name) {
		return (JsonString) object.get(name);
	}

	@Override
	public String getString(String name) {
		if (!object.containsKey(name)) {
			throw new NullPointerException("JsonObject not has a key=" + name);
		}
		return ((JsonString) object.get(name)).getString();
	}

	@Override
	public String getString(String name, String defaultValue) {
		JsonString jsonString = (JsonString) object.get(name);
		if (jsonString != null) {
			return jsonString.getString();
		} else {
			return defaultValue;
		}
	}

	@Override
	public int getInt(String name) {
		if (!object.containsKey(name)) {
			throw new NullPointerException("JsonObject not has a key=" + name);
		}
		return ((JsonNumber) object.get(name)).intValue();
	}

	@Override
	public int getInt(String name, int defaultValue) {
		JsonNumber jsonNumber = (JsonNumber) object.get(name);
		if (jsonNumber != null) {
			return jsonNumber.intValue();
		} else {
			return defaultValue;
		}
	}

	@Override
	public boolean getBoolean(String name) {
		if (!object.containsKey(name)) {
			throw new NullPointerException("JsonObject not has a key=" + name);
		}
		if (object.get(name).getValueType() == ValueType.TRUE) {
			return true;
		} else if (object.get(name).getValueType() == ValueType.FALSE) {
			return false;
		} else {
			// TODO check spec
			throw new IllegalStateException();
		}
	}

	@Override
	public boolean getBoolean(String name, boolean defaultValue) {
		if (!object.containsKey(name)) {
			return defaultValue;
		} else if (object.get(name).getValueType() == ValueType.TRUE) {
			return true;
		} else if (object.get(name).getValueType() == ValueType.FALSE) {
			return false;
		} else {
			return defaultValue;
		}
	}

	@Override
	public boolean isNull(String name) {
		if (!object.containsKey(name)) {
			throw new NullPointerException("JsonObject not has a key=" + name);
		}
		return object.get(name).getValueType() == ValueType.NULL;
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException("JsonObject is immutable. use JsonObjectBuilder");
	}

	@Override
	public JsonValue put(String key, JsonValue value) {
		throw new UnsupportedOperationException("JsonObject is immutable. use JsonObjectBuilder");
	}

	@Override
	public void putAll(Map<? extends String, ? extends JsonValue> jsonObject) {
		throw new UnsupportedOperationException("JsonObject is immutable. use JsonObjectBuilder");
	}

	@Override
	public JsonValue remove(Object key) {
		throw new UnsupportedOperationException("JsonObject is immutable. use JsonObjectBuilder");
	}
}
