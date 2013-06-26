package net.vvakame.jpp;

import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.json.stream.JsonParser.Event;
import javax.json.stream.JsonParsingException;

import net.vvakame.jpp.stream.JsonParserImpl;

/**
 * Implementation for {@link JsonReader}.
 * @author vvakame
 */
public class JsonReaderImpl implements JsonReader {

	final JsonParserImpl parser;


	/**
	 * the constructor.
	 * @param reader
	 * @category constructor
	 */
	// TODO to package private?
	public JsonReaderImpl(Reader reader) {
		parser = new JsonParserImpl(reader);
	}

	@Override
	public JsonStructure read() {
		// TODO raise JsonException by raise IOException
		// TODO raise IllegalStateException
		Event first = parser.next();
		if (first == Event.START_OBJECT) {
			return readObjectHelper();
		} else if (first == Event.START_ARRAY) {
			return readArrayHelper();
		} else {
			throw new JsonParsingException("unexpected event. event=" + first, parser.getLocation());
		}
	}

	@Override
	public JsonObject readObject() {
		// TODO same as read() method.
		Event first = parser.next();
		if (first == Event.START_OBJECT) {
			return readObjectHelper();
		} else {
			throw new JsonParsingException("unexpected event. event=" + first, parser.getLocation());
		}
	}

	@Override
	public JsonArray readArray() {
		// TODO same as read() method.
		Event first = parser.next();
		if (first == Event.START_ARRAY) {
			return readArrayHelper();
		} else {
			throw new JsonParsingException("unexpected event. event=" + first, parser.getLocation());
		}
	}

	JsonObject readObjectHelper() {
		// already read START_OBJECT

		Map<String, JsonValue> jsonObject = new LinkedHashMap<String, JsonValue>();
		Event next;
		while (true) {
			next = parser.next();
			String key;
			if (next == Event.KEY_NAME) {
				key = parser.getString();
			} else if (next == Event.END_OBJECT) {
				return new JsonObjectImpl(jsonObject);
			} else {
				// TODO verify spec
				throw new IllegalStateException();
			}
			next = parser.next();
			switch (next) {
				case VALUE_STRING:
					jsonObject.put(key, new JsonStringImpl(parser.getString()));
					break;
				case VALUE_NUMBER:
					jsonObject.put(key, new JsonNumberImpl(parser.getBigDecimal()));
					break;
				case VALUE_TRUE:
					jsonObject.put(key, JsonValue.TRUE);
					break;
				case VALUE_FALSE:
					jsonObject.put(key, JsonValue.FALSE);
					break;
				case VALUE_NULL:
					jsonObject.put(key, JsonValue.NULL);
					break;

				case START_OBJECT:
					jsonObject.put(key, readObjectHelper());
					break;
				case START_ARRAY:
					jsonObject.put(key, readArrayHelper());
					break;

				default:
					// TODO verify spec
					throw new IllegalStateException();
			}
		}
	}

	JsonArray readArrayHelper() {
		// already read START_ARRAY

		List<JsonValue> list = new ArrayList<JsonValue>();

		while (true) {
			switch (parser.next()) {
				case VALUE_STRING:
					list.add(new JsonStringImpl(parser.getString()));
					break;
				case VALUE_NUMBER:
					list.add(new JsonNumberImpl(parser.getBigDecimal()));
					break;
				case VALUE_TRUE:
					list.add(JsonValue.TRUE);
					break;
				case VALUE_FALSE:
					list.add(JsonValue.FALSE);
					break;
				case VALUE_NULL:
					list.add(JsonValue.NULL);
					break;

				case END_ARRAY:
					return new JsonArrayImpl(list);

				case START_OBJECT:
					list.add(readObjectHelper());
					break;
				case START_ARRAY:
					list.add(readArrayHelper());
					break;

				default:
					// TODO verify spec
					throw new IllegalStateException();
			}
		}
	}

	@Override
	public void close() {
		parser.close();
	}
}
