package net.vvakame.jpp;

import java.util.ArrayList;
import java.util.Collection;
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

	List<JsonValue> list = new ArrayList<JsonValue>();


	@Override
	public ValueType getValueType() {
		return ValueType.ARRAY;
	}

	@Override
	public boolean add(JsonValue value) {
		return list.add(value);
	}

	@Override
	public void add(int index, JsonValue value) {
		list.add(index, value);
	}

	@Override
	public boolean addAll(Collection<? extends JsonValue> collection) {
		return list.addAll(collection);
	}

	@Override
	public boolean addAll(int index, Collection<? extends JsonValue> collection) {
		return list.addAll(index, collection);
	}

	@Override
	public void clear() {
		list.clear();
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
	public boolean remove(Object value) {
		return list.remove(value);
	}

	@Override
	public JsonValue remove(int index) {
		return list.remove(index);
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		return list.removeAll(collection);
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		return list.removeAll(collection);
	}

	@Override
	public JsonValue set(int index, JsonValue value) {
		return list.set(index, value);
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
		// TODO verify spec
		return (JsonObject) list.get(index);
	}

	@Override
	public JsonArray getJsonArray(int index) {
		// TODO verify spec
		return (JsonArray) list.get(index);
	}

	@Override
	public JsonNumber getJsonNumber(int index) {
		// TODO verify spec
		return (JsonNumber) list.get(index);
	}

	@Override
	public JsonString getJsonString(int index) {
		// TODO verify spec
		return (JsonString) list.get(index);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends JsonValue>List<T> getValuesAs(Class<T> clazz) {
		// TODO verify spec
		return (List<T>) list;
	}

	@Override
	public String getString(int index) {
		// TODO verify spec
		return ((JsonString) list.get(index)).getString();
	}

	@Override
	public String getString(int index, String defaultValue) {
		// TODO verify spec
		JsonValue value = list.get(index);
		if (value != null) {
			return ((JsonString) value).getString();
		} else {
			return defaultValue;
		}
	}

	@Override
	public int getInt(int index) {
		// TODO verify spec
		return ((JsonNumber) list.get(index)).intValue();
	}

	@Override
	public int getInt(int index, int defaultValue) {
		// TODO verify spec
		JsonValue value = list.get(index);
		if (value != null) {
			return ((JsonNumber) list.get(index)).intValue();
		} else {
			return defaultValue;
		}
	}

	@Override
	public boolean getBoolean(int index) {
		// TODO Auto-generated method stub
		JsonValue value = list.get(index);
		if (value.getValueType() == ValueType.TRUE) {
			return true;
		} else if (value.getValueType() == ValueType.FALSE) {
			return false;
		} else {
			// TODO
			throw new IllegalStateException();
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
			return defaultValue;
		}
	}

	@Override
	public boolean isNull(int index) {
		// TODO verify spec
		return list.get(index) == null || list.get(index).getValueType() == ValueType.NULL;
	}

	// TODO equals, hashCode, toString
}
