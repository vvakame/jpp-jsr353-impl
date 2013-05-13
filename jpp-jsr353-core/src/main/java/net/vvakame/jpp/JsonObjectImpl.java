package net.vvakame.jpp;

import java.util.Collection;
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

	Map<String, JsonValue> values = new LinkedHashMap<String, JsonValue>();


	@Override
	public ValueType getValueType() {
		return ValueType.OBJECT;
	}

	@Override
	public void clear() {
		values.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		return values.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return values.containsValue(value);
	}

	@Override
	public Set<java.util.Map.Entry<String, JsonValue>> entrySet() {
		return values.entrySet();
	}

	@Override
	public JsonValue get(Object key) {
		return values.get(key);
	}

	@Override
	public boolean isEmpty() {
		return values.isEmpty();
	}

	@Override
	public Set<String> keySet() {
		return values.keySet();
	}

	@Override
	public JsonValue put(String key, JsonValue value) {
		return values.put(key, value);
	}

	@Override
	public void putAll(Map<? extends String, ? extends JsonValue> jsonObject) {
		values.putAll(jsonObject);
	}

	@Override
	public JsonValue remove(Object key) {
		return values.remove(key);
	}

	@Override
	public int size() {
		return values.size();
	}

	@Override
	public Collection<JsonValue> values() {
		return values.values();
	}

	@Override
	public JsonArray getJsonArray(String name) {
		// TODO verify spec
		return (JsonArray) values.get(name);
	}

	@Override
	public JsonObject getJsonObject(String name) {
		// TODO verify spec
		return (JsonObject) values.get(name);
	}

	@Override
	public JsonNumber getJsonNumber(String name) {
		// TODO verify spec
		return (JsonNumber) values.get(name);
	}

	@Override
	public JsonString getJsonString(String name) {
		// TODO verify spec
		return (JsonString) values.get(name);
	}

	@Override
	public String getString(String name) {
		// TODO verify spec
		return ((JsonString) values.get(name)).getString();
	}

	@Override
	public String getString(String name, String defaultValue) {
		// TODO verify spec
		if (values.containsKey(name)) {
			return ((JsonString) values.get(name)).getString();
		} else {
			return defaultValue;
		}
	}

	@Override
	public int getInt(String name) {
		// TODO verify spec
		return ((JsonNumber) values.get(name)).intValueExact();
	}

	@Override
	public int getInt(String name, int defaultValue) {
		// TODO verify spec
		if (values.containsKey(name)) {
			return ((JsonNumber) values.get(name)).intValueExact();
		} else {
			return defaultValue;
		}
	}

	@Override
	public boolean getBoolean(String name) {
		// TODO verify spec
		if (values.get(name).getValueType() == ValueType.TRUE) {
			return true;
		} else if (values.get(name).getValueType() == ValueType.FALSE) {
			return false;
		} else {
			// TODO
			throw new IllegalStateException();
		}
	}

	@Override
	public boolean getBoolean(String name, boolean defaultValue) {
		// TODO verify spec
		if (!values.containsKey(name)) {
			return defaultValue;
		} else if (values.get(name).getValueType() == ValueType.TRUE) {
			return true;
		} else if (values.get(name).getValueType() == ValueType.FALSE) {
			return false;
		} else {
			return defaultValue;
		}
	}

	@Override
	public boolean isNull(String name) {
		// TODO verify spec
		return !values.containsKey(name) || values.get(name).getValueType() == ValueType.NULL;
	}

	// TODO equals, hashCode, toString
}
