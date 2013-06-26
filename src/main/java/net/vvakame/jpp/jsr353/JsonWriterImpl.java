package net.vvakame.jpp.jsr353;

import java.io.IOException;
import java.io.Writer;

import javax.json.JsonArray;
import javax.json.JsonException;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.json.JsonWriter;

/**
 * Implementation for {@link JsonWriter}.
 * @author vvakame
 */
public class JsonWriterImpl implements JsonWriter {

	Writer writer;


	/**
	 * the constructor.
	 * @param writer
	 * @category constructor
	 */
	public JsonWriterImpl(Writer writer) {
		this.writer = writer;
	}

	@Override
	public void writeArray(JsonArray array) {
		// TODO IllegalStateException
		try {
			writer.write("[");
			for (int i = 0; i < array.size(); i++) {
				JsonValue jsonValue = array.get(i);

				switch (jsonValue.getValueType()) {
					case ARRAY:
						writeArray((JsonArray) jsonValue);
						break;
					case OBJECT:
						writeObject((JsonObject) jsonValue);
						break;
					case TRUE:
						writer.write("true");
						break;
					case FALSE:
						writer.write("false");
						break;
					case NULL:
						writer.write("null");
						break;
					case STRING:
						writeString(((JsonString) jsonValue).getString());
						break;
					case NUMBER:
						writer.write(((JsonNumber) jsonValue).bigDecimalValue().toString());
						break;
					default:
						break;
				}
				if (array.size() - 1 != i) {
					writer.write(",");
				}
			}
			writer.write("]");
		} catch (IOException e) {
			throw new JsonException("raise IOException", e);
		}
	}

	@Override
	public void writeObject(JsonObject object) {
		// TODO IllegalStateException
		try {
			writer.write("{");
			String[] keys = object.keySet().toArray(new String[object.size()]);
			for (int i = 0; i < keys.length; i++) {
				String key = keys[i];

				writeString(key);
				writer.write(":");
				JsonValue jsonValue = object.get(key);
				switch (jsonValue.getValueType()) {
					case ARRAY:
						writeArray((JsonArray) jsonValue);
						break;
					case OBJECT:
						writeObject((JsonObject) jsonValue);
						break;
					case TRUE:
						writer.write("true");
						break;
					case FALSE:
						writer.write("false");
						break;
					case NULL:
						writer.write("null");
						break;
					case STRING:
						writeString(((JsonString) jsonValue).getString());
						break;
					case NUMBER:
						writer.write(((JsonNumber) jsonValue).bigDecimalValue().toString());
					default:
						break;
				}
				if (keys.length - 1 != i) {
					writer.write(",");
				}
			}
			writer.write("}");
		} catch (IOException e) {
			throw new JsonException("raise IOException", e);
		}
	}

	@Override
	public void write(JsonStructure value) {
		// TODO IllegalStateException
		switch (value.getValueType()) {
			case OBJECT:
				writeObject((JsonObject) value);
				break;
			case ARRAY:
				writeArray((JsonArray) value);
				break;
			default:
				// TODO verify spec
				throw new IllegalStateException();
		}
	}

	@Override
	public void close() {
		try {
			writer.close();
		} catch (IOException e) {
			throw new JsonException("raise IOException", e);
		}
	}

	static String sanitize(String orig) {
		if (orig == null) {
			return null;
		}
		// TODO Increase Process Speed
		return orig.replace("\\", "\\\\").replace("\"", "\\\"").replace("/", "\\/")
			.replace("\b", "\\b").replace("\f", "\\f").replace("\n", "\\n").replace("\r", "\\r")
			.replace("\t", "\\t");
	}

	void writeString(String value) throws IOException {
		writer.write("\"");
		writer.write(sanitize(value));
		writer.write("\"");
	}
}
