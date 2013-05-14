package net.vvakame.jpp;

import java.io.Reader;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.json.stream.JsonParser.Event;

import net.vvakame.stream.JsonParserImpl;

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
	public JsonReaderImpl(Reader reader) {
		parser = new JsonParserImpl(reader);
	}

	@Override
	public JsonStructure read() {
		Event first = parser.next();
		if (first == Event.START_OBJECT) {
			return readObjectHelper();
		} else if (first == Event.START_ARRAY) {
			return readArrayHelper();
		} else {
			// TODO verify spec
			throw new IllegalStateException();
		}
	}

	@Override
	public JsonObject readObject() {
		Event first = parser.next();
		if (first == Event.START_OBJECT) {
			return readObjectHelper();
		} else {
			// TODO verify spec
			throw new IllegalStateException();
		}
	}

	@Override
	public JsonArray readArray() {
		Event first = parser.next();
		if (first == Event.START_ARRAY) {
			return readArrayHelper();
		} else {
			// TODO verify spec
			throw new IllegalStateException();
		}
	}

	JsonObject readObjectHelper() {
		// already read START_OBJECT

		JsonObjectImpl jsonObject = new JsonObjectImpl();
		Event next;
		while (true) {
			next = parser.next();
			String key;
			if (next == Event.KEY_NAME) {
				key = parser.getString();
			} else if (next == Event.END_OBJECT) {
				return jsonObject;
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

		JsonArrayImpl jsonArray = new JsonArrayImpl();
		while (true) {
			switch (parser.next()) {
				case VALUE_STRING:
					jsonArray.add(new JsonStringImpl(parser.getString()));
					break;
				case VALUE_NUMBER:
					jsonArray.add(new JsonNumberImpl(parser.getBigDecimal()));
					break;
				case VALUE_TRUE:
					jsonArray.add(JsonValue.TRUE);
					break;
				case VALUE_FALSE:
					jsonArray.add(JsonValue.FALSE);
					break;
				case VALUE_NULL:
					jsonArray.add(JsonValue.NULL);
					break;

				case END_ARRAY:
					return jsonArray;

				case START_OBJECT:
					jsonArray.add(readObjectHelper());
					break;
				case START_ARRAY:
					jsonArray.add(readArrayHelper());
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
