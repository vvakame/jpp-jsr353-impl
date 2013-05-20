package net.vvakame.jpp.helper;

import java.math.BigDecimal;

import javax.json.stream.JsonLocation;
import javax.json.stream.JsonParser;

/**
 * Wrapper for {@link JsonParser}.<br>
 * This class can use {@link #lookAhead()} method, {@link #discardValue()}, {@link #discardArray()} and {@link #discardObject()} method.
 * @author vvakame
 */
// TODO class name
public class JsonParserExtender implements JsonParser {

	JsonParser parser;


	/**
	 * Get {@link JsonParserExtender} instance. 
	 * @param parser
	 * @return {@link JsonParserExtender}
	 * @author vvakame
	 */
	public static JsonParserExtender getInstance(JsonParser parser) {
		if (parser instanceof JsonParserExtender) {
			return (JsonParserExtender) parser;
		} else {
			return new JsonParserExtender(parser);
		}
	}

	/**
	 * the constructor.
	 * @param parser
	 * @category constructor
	 */
	private JsonParserExtender(JsonParser parser) {
		this.parser = parser;
	}


	Event lookAhead;


	/**
	 * Advances by one elements.
	 * @return The next event type.
	 * @author vvakame
	 */
	public Event lookAhead() {
		if (lookAhead != null) {
			lookAhead = parser.next();
		}
		return lookAhead;
	}

	/**
	 * Reads and discards the next value.<br>
	 *
	 * @throws IllegalStateException when it stumbles upon an unexpected {@link javax.json.stream.JsonParser.Event}.
	 */
	public void discardValue() {
		Event event = lookAhead();
		switch (event) {
			case START_ARRAY:
				discardArray();
				break;
			case START_OBJECT:
				discardObject();
				break;
			case KEY_NAME:
				next();
				discardValue();
				break;
			case VALUE_TRUE:
			case VALUE_FALSE:
			case VALUE_NULL:
			case VALUE_NUMBER:
			case VALUE_STRING:
				next();
				break;
			default:
				throw new IllegalStateException("unexpected event. event=" + event);
		}
	}

	/**
	 * Reads and discards the next array or null.
	 * @throws IllegalStateException when it stumbles upon an unexpected {@link javax.json.stream.JsonParser.Event}.
	 */
	public void discardArray() {
		Event event = lookAhead();
		switch (event) {
			case START_ARRAY:
				next();
				while ((event = lookAhead()) != Event.END_ARRAY) {
					switch (event) {
						case START_ARRAY:
							discardArray();
							break;
						case START_OBJECT:
							discardObject();
							break;
						case VALUE_TRUE:
						case VALUE_FALSE:
						case VALUE_NULL:
						case VALUE_NUMBER:
						case VALUE_STRING:
							next();
							break;
						default:
							throw new IllegalStateException("unexpected event. event=" + event);
					}
				}
				next();
				break;
			case VALUE_NULL:
				next();
				break;

			default:
				throw new IllegalStateException("unexpected event. event=" + event);
		}
	}

	/**
	 * Reads and discards the next object or null.<br>
	 * @throws IllegalStateException when it stumbles upon an unexpected {@link javax.json.stream.JsonParser.Event}.
	 */
	public void discardObject() {
		Event event = lookAhead();
		switch (event) {
			case START_OBJECT:
				next();
				while ((event = lookAhead()) != Event.END_OBJECT) {
					switch (event) {
						case START_ARRAY:
							discardArray();
							break;
						case START_OBJECT:
							discardObject();
							break;
						case KEY_NAME:
						case VALUE_TRUE:
						case VALUE_FALSE:
						case VALUE_NULL:
						case VALUE_NUMBER:
						case VALUE_STRING:
							next();
							break;
						default:
							throw new IllegalStateException("unexpected event. event=" + event);
					}
				}
				next();
				break;
			case VALUE_NULL:
				next();
				break;
			default:
				throw new IllegalStateException("unexpected event. event=" + event);
		}
	}

	@Override
	public boolean hasNext() {
		if (lookAhead != null) {
			return true;
		} else {
			return parser.hasNext();
		}
	}

	@Override
	public Event next() {
		if (lookAhead != null) {
			Event next = lookAhead;
			lookAhead = null;
			return next;
		} else {
			return parser.next();
		}
	}

	void checkExecutedLookAhead() {
		if (lookAhead != null) {
			throw new IllegalStateException("already called lookAhead method.");
		}
	}

	@Override
	public String getString() {
		checkExecutedLookAhead();
		return parser.getString();
	}

	@Override
	public boolean isIntegralNumber() {
		checkExecutedLookAhead();
		return parser.isIntegralNumber();
	}

	@Override
	public int getInt() {
		checkExecutedLookAhead();
		return parser.getInt();
	}

	@Override
	public long getLong() {
		checkExecutedLookAhead();
		return parser.getLong();
	}

	@Override
	public BigDecimal getBigDecimal() {
		checkExecutedLookAhead();
		return parser.getBigDecimal();
	}

	@Override
	public JsonLocation getLocation() {
		checkExecutedLookAhead();
		return parser.getLocation();
	}

	@Override
	public void close() {
		parser.close();
	}
}
