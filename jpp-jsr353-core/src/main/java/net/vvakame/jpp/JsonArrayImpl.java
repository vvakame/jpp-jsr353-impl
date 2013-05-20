package net.vvakame.jpp;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;

/**
 * Implementation for {@link JsonArray}.
 * @author vvakame
 */
public class JsonArrayImpl implements JsonArray {

	List<JsonValue> list;


	/**
	 * the constructor.
	 * @param list
	 * @category constructor
	 */
	// TODO to package private?
	public JsonArrayImpl(List<JsonValue> list) {
		this.list = Collections.unmodifiableList(list);
	}

	@Override
	public ValueType getValueType() {
		return ValueType.ARRAY;
	}

	@Override
	public boolean contains(Object value) {
		return list.contains(value);
	}

	@Override
	public boolean containsAll(Collection<?> collection) {
		return list.containsAll(collection);
	}

	@Override
	public JsonValue get(int index) {
		return list.get(index);
	}

	@Override
	public int indexOf(Object value) {
		return list.indexOf(value);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Iterator<JsonValue> iterator() {
		return list.iterator();
	}

	@Override
	public int lastIndexOf(Object value) {
		return list.lastIndexOf(value);
	}

	@Override
	public ListIterator<JsonValue> listIterator() {
		return list.listIterator();
	}

	@Override
	public ListIterator<JsonValue> listIterator(int index) {
		return list.listIterator(index);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public List<JsonValue> subList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public <T>T[] toArray(T[] a) {
		return list.toArray(a);
	}

	@Override
	public JsonObject getJsonObject(int index) {
		return (JsonObject) list.get(index);
	}

	@Override
	public JsonArray getJsonArray(int index) {
		return (JsonArray) list.get(index);
	}

	@Override
	public JsonNumber getJsonNumber(int index) {
		return (JsonNumber) list.get(index);
	}

	@Override
	public JsonString getJsonString(int index) {
		return (JsonString) list.get(index);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends JsonValue>List<T> getValuesAs(Class<T> clazz) {
		return (List<T>) list;
	}

	@Override
	public String getString(int index) {
		JsonString value = (JsonString) list.get(index);
		if (value != null) {
			return value.getString();
		} else {
			return null;
		}
	}

	@Override
	public String getString(int index, String defaultValue) {
		JsonString value = (JsonString) list.get(index);
		if (value != null) {
			return value.getString();
		} else {
			return defaultValue;
		}
	}

	@Override
	public int getInt(int index) {
		return ((JsonNumber) list.get(index)).intValue();
	}

	@Override
	public int getInt(int index, int defaultValue) {
		JsonNumber value = (JsonNumber) list.get(index);
		if (value != null) {
			return value.intValue();
		} else {
			return defaultValue;
		}
	}

	@Override
	public boolean getBoolean(int index) {
		JsonValue value = list.get(index);
		if (value == null) {
			// TODO check spec
			throw new NullPointerException("index " + index + " is null");
		} else if (value.getValueType() == ValueType.TRUE) {
			return true;
		} else if (value.getValueType() == ValueType.FALSE) {
			return false;
		} else {
			// TODO check spec
			throw new IllegalStateException("index" + index + " is " + value.getValueType());
		}
	}

	@Override
	public boolean getBoolean(int index, boolean defaultValue) {
		JsonValue value = list.get(index);
		if (value == null) {
			return defaultValue;
		} else if (value.getValueType() == ValueType.TRUE) {
			return true;
		} else if (value.getValueType() == ValueType.FALSE) {
			return false;
		} else {
			// TODO check spec
			throw new IllegalStateException("index" + index + " is " + value.getValueType());
		}
	}

	@Override
	public boolean isNull(int index) {
		return list.get(index) == null || list.get(index).getValueType() == ValueType.NULL;
	}

	@Override
	public boolean add(JsonValue value) {
		throw new UnsupportedOperationException("JsonArray is immutable. use JsonArrayBuilder");
	}

	@Override
	public void add(int index, JsonValue value) {
		throw new UnsupportedOperationException("JsonArray is immutable. use JsonArrayBuilder");
	}

	@Override
	public boolean addAll(Collection<? extends JsonValue> collection) {
		throw new UnsupportedOperationException("JsonArray is immutable. use JsonArrayBuilder");
	}

	@Override
	public boolean addAll(int index, Collection<? extends JsonValue> collection) {
		throw new UnsupportedOperationException("JsonArray is immutable. use JsonArrayBuilder");
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException("JsonArray is immutable. use JsonArrayBuilder");
	}

	@Override
	public boolean remove(Object value) {
		throw new UnsupportedOperationException("JsonArray is immutable. use JsonArrayBuilder");
	}

	@Override
	public JsonValue remove(int index) {
		throw new UnsupportedOperationException("JsonArray is immutable. use JsonArrayBuilder");
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		throw new UnsupportedOperationException("JsonArray is immutable. use JsonArrayBuilder");
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		throw new UnsupportedOperationException("JsonArray is immutable. use JsonArrayBuilder");
	}

	@Override
	public JsonValue set(int index, JsonValue value) {
		throw new UnsupportedOperationException("JsonArray is immutable. use JsonArrayBuilder");
	}
}
