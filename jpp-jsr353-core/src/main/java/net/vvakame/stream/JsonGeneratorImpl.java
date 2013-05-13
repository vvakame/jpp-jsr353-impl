package net.vvakame.stream;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.NoSuchElementException;

import javax.json.JsonValue;
import javax.json.stream.JsonGenerationException;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser.Event;

/**
 * Implementation for {@link JsonGenerator}.
 * @author vvakame
 */
public class JsonGeneratorImpl implements JsonGenerator {

	final Writer writer;

	Stack<Event> eventStack = new Stack<Event>();

	Stack<Boolean> firstStack = new Stack<Boolean>();


	/**
	 * the constructor.
	 * @param writer
	 * @category constructor
	 */
	// TODO to package private?
	public JsonGeneratorImpl(Writer writer) {
		this.writer = writer;
	}

	void checkFirstElement() throws IOException {
		if (firstStack.size() == 0) {
			return;
		}
		boolean first = firstStack.peek();
		if (!first) {
			writer.write(",");
		} else {
			firstStack.pop();
			firstStack.push(false);
		}
	}

	void checkInArray() {
		if (eventStack.peek() != Event.START_ARRAY) {
			throw new JsonGenerationException("Now in the JsonObject, not JsonArray.");
		}
	}

	void checkInObject() {
		if (eventStack.peek() != Event.START_OBJECT) {
			throw new JsonGenerationException("Now in the JsonArray, not JsonObject.");
		}
	}

	@Override
	public void flush() {
		try {
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public JsonGenerator writeStartObject() {
		try {
			checkFirstElement();
			writer.write("{");

			eventStack.push(Event.START_OBJECT);
			firstStack.push(true);
		} catch (IOException e) {
			throw new JsonGenerationException("raise IOException", e);
		}
		return this;
	}

	@Override
	public JsonGenerator writeStartObject(String key) {
		try {
			checkFirstElement();
			writeString(key);
			writer.write(":");
			writer.write("{");

			eventStack.push(Event.START_OBJECT);
			firstStack.push(true);
		} catch (IOException e) {
			throw new JsonGenerationException("raise IOException", e);
		}
		return this;
	}

	@Override
	public JsonGenerator writeStartArray() {
		try {
			checkFirstElement();
			writer.write("[");

			eventStack.push(Event.START_ARRAY);
			firstStack.push(true);
		} catch (IOException e) {
			throw new JsonGenerationException("raise IOException", e);
		}
		return this;
	}

	@Override
	public JsonGenerator writeStartArray(String key) {
		try {
			checkFirstElement();
			writeString(key);
			writer.write(":");
			writer.write("[");

			eventStack.push(Event.START_ARRAY);
			firstStack.push(true);
		} catch (IOException e) {
			throw new JsonGenerationException("raise IOException", e);
		}
		return this;
	}

	@Override
	public JsonGenerator writeEnd() {
		try {
			Event event = eventStack.pop();
			firstStack.pop();

			switch (event) {
				case START_OBJECT:
					writer.write("}");
					break;
				case START_ARRAY:
					writer.write("]");
					break;
			}
		} catch (NoSuchElementException e) {
			throw new JsonGenerationException("raise NoSuchElementException", e);
		} catch (IOException e) {
			throw new JsonGenerationException("raise IOException", e);
		}
		return this;
	}

	@Override
	public JsonGenerator write(JsonValue arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonGenerator write(String value) {
		try {
			checkInArray();
			checkFirstElement();
			writeString(value);
		} catch (IOException e) {
			throw new JsonGenerationException("raise IOException", e);
		}
		return this;
	}

	@Override
	public JsonGenerator write(BigDecimal value) {
		try {
			checkInArray();
			checkFirstElement();
			writer.write(value.toString());
		} catch (IOException e) {
			throw new JsonGenerationException("raise IOException", e);
		}
		return this;
	}

	@Override
	public JsonGenerator write(BigInteger value) {
		try {
			checkInArray();
			checkFirstElement();
			writer.write(value.toString());
		} catch (IOException e) {
			throw new JsonGenerationException("raise IOException", e);
		}
		return this;
	}

	@Override
	public JsonGenerator write(int value) {
		try {
			checkInArray();
			checkFirstElement();
			writer.write(String.valueOf(value));
		} catch (IOException e) {
			throw new JsonGenerationException("raise IOException", e);
		}
		return this;
	}

	@Override
	public JsonGenerator write(long value) {
		try {
			checkInArray();
			checkFirstElement();
			writer.write(String.valueOf(value));
		} catch (IOException e) {
			throw new JsonGenerationException("raise IOException", e);
		}
		return this;
	}

	@Override
	public JsonGenerator write(double value) {
		try {
			checkInArray();
			checkFirstElement();
			writer.write(String.valueOf(value));
		} catch (IOException e) {
			throw new JsonGenerationException("raise IOException", e);
		}
		return this;
	}

	@Override
	public JsonGenerator write(boolean value) {
		try {
			checkInArray();
			checkFirstElement();
			writer.write(String.valueOf(value));
		} catch (IOException e) {
			throw new JsonGenerationException("raise IOException", e);
		}
		return this;
	}

	@Override
	public JsonGenerator write(String key, JsonValue value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonGenerator write(String key, String value) {
		try {
			checkInObject();
			checkFirstElement();

			Event event = eventStack.peek();
			if (event != Event.START_OBJECT) {
				throw new JsonGenerationException("Illegal state, currently in " + event);
			}
			writeString(key);
			writer.write(":");
			writeString(value);
		} catch (IOException e) {
			throw new JsonGenerationException("raise IOException", e);
		}
		return this;
	}

	@Override
	public JsonGenerator write(String key, BigInteger value) {
		try {
			checkInObject();
			checkFirstElement();

			Event event = eventStack.peek();
			if (event != Event.START_OBJECT) {
				throw new JsonGenerationException("Illegal state, currently in " + event);
			}
			writeString(key);
			writer.write(":");
			writer.write(value.toString());
		} catch (IOException e) {
			throw new JsonGenerationException("raise IOException", e);
		}
		return this;
	}

	@Override
	public JsonGenerator write(String key, BigDecimal value) {
		try {
			checkInObject();
			checkFirstElement();

			Event event = eventStack.peek();
			if (event != Event.START_OBJECT) {
				throw new JsonGenerationException("Illegal state, currently in " + event);
			}
			writeString(key);
			writer.write(":");
			writer.write(value.toString());
		} catch (IOException e) {
			throw new JsonGenerationException("raise IOException", e);
		}
		return this;
	}

	@Override
	public JsonGenerator write(String key, int value) {
		try {
			checkInObject();
			checkFirstElement();

			Event event = eventStack.peek();
			if (event != Event.START_OBJECT) {
				throw new JsonGenerationException("Illegal state, currently in " + event);
			}
			writeString(key);
			writer.write(":");
			writer.write(String.valueOf(value));
		} catch (IOException e) {
			throw new JsonGenerationException("raise IOException", e);
		}
		return this;
	}

	@Override
	public JsonGenerator write(String key, long value) {
		try {
			checkInObject();
			checkFirstElement();

			Event event = eventStack.peek();
			if (event != Event.START_OBJECT) {
				throw new JsonGenerationException("Illegal state, currently in " + event);
			}
			writeString(key);
			writer.write(":");
			writer.write(String.valueOf(value));
		} catch (IOException e) {
			throw new JsonGenerationException("raise IOException", e);
		}
		return this;
	}

	@Override
	public JsonGenerator write(String key, double value) {
		try {
			checkInObject();
			checkFirstElement();

			Event event = eventStack.peek();
			if (event != Event.START_OBJECT) {
				throw new JsonGenerationException("Illegal state, currently in " + event);
			}
			writeString(key);
			writer.write(":");
			writer.write(String.valueOf(value));
		} catch (IOException e) {
			throw new JsonGenerationException("raise IOException", e);
		}
		return this;
	}

	@Override
	public JsonGenerator write(String key, boolean value) {
		try {
			checkInObject();
			checkFirstElement();

			Event event = eventStack.peek();
			if (event != Event.START_OBJECT) {
				throw new JsonGenerationException("Illegal state, currently in " + event);
			}
			writeString(key);
			writer.write(":");
			writer.write(String.valueOf(value));
		} catch (IOException e) {
			throw new JsonGenerationException("raise IOException", e);
		}
		return this;
	}

	@Override
	public JsonGenerator writeNull() {
		try {
			checkFirstElement();
			checkInArray();
			writer.write("null");
		} catch (IOException e) {
			throw new JsonGenerationException("raise IOException", e);
		}
		return this;
	}

	@Override
	public JsonGenerator writeNull(String key) {
		try {
			checkInObject();
			checkFirstElement();

			Event event = eventStack.peek();
			if (event != Event.START_OBJECT) {
				throw new JsonGenerationException("Illegal state, currently in " + event);
			}
			writeString(key);
			writer.write(":null");
		} catch (IOException e) {
			throw new JsonGenerationException("raise IOException", e);
		}
		return this;
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
