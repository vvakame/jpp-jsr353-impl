package net.vvakame.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;

import javax.json.stream.JsonLocation;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParsingException;

/**
 * Implementations for {@link JsonParser}.
 * @author vvakame
 */
public class JsonParserImpl implements JsonParser {

	Reader reader;

	final Stack<Event> stack = new Stack<Event>();

	boolean readFuture = false;

	boolean finished = false;

	String futureValueStr;

	BigDecimal futureBigDecimal;

	boolean futureIntegralNumber;

	Event futureEvent;

	String currentValueStr;

	BigDecimal currentBigDecimal;

	boolean currentIntegralNumber;

	Event currentEvent;


	/**
	 * the constructor.
	 * @param reader
	 * @category constructor
	 */
	public JsonParserImpl(Reader reader) {
		if (!reader.markSupported()) {
			this.reader = new BufferedReader(reader);
		} else {
			this.reader = reader;
		}
	}

	@Override
	public void close() {
		try {
			reader.close();
		} catch (IOException e) {
			throw new JsonParsingException("raise IOException", e, getLocation());
		}
	}

	@Override
	public JsonLocation getLocation() {
		return new JsonLocationImpl(columnNumber, lineNumber, streamOffset);
	}

	@Override
	public boolean hasNext() {
		parseFuture();
		return !finished;
	}

	@Override
	public Event next() {
		parseFuture();

		currentEvent = futureEvent;
		currentValueStr = futureValueStr;
		currentIntegralNumber = futureIntegralNumber;
		currentBigDecimal = futureBigDecimal;
		readFuture = false;

		return currentEvent;
	}

	void parseFuture() {
		if (readFuture) {
			return;
		}
		try {
			char c = getNextChar();
			// TODO 65536 は終端じゃないのでは…
			if (c == -1 || c == 65535) {
				finished = true;
				return;
			}
			if (stack.size() == 0) {
				switch (c) {
					case '{':
						stack.push(Event.START_OBJECT);
						break;
					case '[':
						stack.push(Event.START_ARRAY);
						break;
					default:
						throw new JsonParsingException("unexpected token. token=" + c,
								getLocation());
				}
			} else {
				switch (stack.pop()) {
					case START_ARRAY:
						stack.push(Event.START_ARRAY);
						switch (c) {
							case '{':
								stack.push(Event.START_OBJECT);
								break;
							case '[':
								stack.push(Event.START_ARRAY);
								break;
							case '"':
								stack.push(Event.VALUE_STRING);
								futureValueStr = getNextString();
								break;
							case ']':
								stack.push(Event.END_ARRAY);
								break;
							case 't':
								expectNextChar('r');
								expectNextChar('u');
								expectNextChar('e');

								stack.push(Event.VALUE_TRUE);
								break;
							case 'f':
								expectNextChar('a');
								expectNextChar('l');
								expectNextChar('s');
								expectNextChar('e');

								stack.push(Event.VALUE_FALSE);
								break;
							case 'n':
								expectNextChar('u');
								expectNextChar('l');
								expectNextChar('l');

								stack.push(Event.VALUE_NULL);
								break;
							default:
								// 数字
								try {
									fetchNextNumeric();
									break;
								} catch (NumberFormatException e) {
									throw new JsonParsingException("raise NumberFormatException",
											e, getLocation());
								}
						}
						break;

					case START_OBJECT:
						stack.push(Event.START_OBJECT);
						switch (c) {
							case '{':
								stack.push(Event.START_OBJECT);
								break;
							case '[':
								stack.push(Event.START_ARRAY);
								break;
							case '}':
								stack.push(Event.END_OBJECT);
								break;
							case '"':
								stack.push(Event.KEY_NAME);
								futureValueStr = getNextString();
								c = getNextChar();
								if (c != ':') {
									throw new JsonParsingException("unexpected token. token=" + c,
											getLocation());
								}
								break;
							default:
								throw new JsonParsingException("unexpected token. token=" + c,
										getLocation());
						}
						break;

					case END_ARRAY:
						if (!Event.START_ARRAY.equals(stack.pop())) {
							throw new JsonParsingException("unexpected token.", getLocation());
						}
						switch (c) {
							case ',':
								next();
								break;
							case ']':
								stack.push(Event.END_ARRAY);
								break;
							case '}':
								stack.push(Event.END_OBJECT);
								break;
							default:
								throw new JsonParsingException("unexpected token. token=" + c,
										getLocation());
						}
						break;
					case END_OBJECT:
						if (!Event.START_OBJECT.equals(stack.pop())) {
							throw new JsonParsingException("unexpected token.", getLocation());
						}
						switch (c) {
							case ',':
								next();
								break;
							case ']':
								stack.push(Event.END_ARRAY);
								break;
							case '}':
								stack.push(Event.END_OBJECT);
								break;
							default:
								throw new JsonParsingException("unexpected token. token=" + c,
										getLocation());
						}
						break;
					case KEY_NAME:
						switch (c) {
							case '"':
								stack.push(Event.VALUE_STRING);
								futureValueStr = getNextString();
								break;
							case '[':
								stack.push(Event.START_ARRAY);
								break;
							case '{':
								stack.push(Event.START_OBJECT);
								break;
							case 't':
								expectNextChar('r');
								expectNextChar('u');
								expectNextChar('e');

								stack.push(Event.VALUE_TRUE);
								break;
							case 'f':
								expectNextChar('a');
								expectNextChar('l');
								expectNextChar('s');
								expectNextChar('e');

								stack.push(Event.VALUE_FALSE);
								break;
							case 'n':
								expectNextChar('u');
								expectNextChar('l');
								expectNextChar('l');

								stack.push(Event.VALUE_NULL);
								break;
							default:
								// 数字
								try {
									fetchNextNumeric();
									break;
								} catch (NumberFormatException e) {
									throw new JsonParsingException("raise NumberFormatException",
											e, getLocation());
								}
						}
						break;
					case VALUE_STRING:
					case VALUE_NUMBER:
					case VALUE_NULL:
					case VALUE_TRUE:
					case VALUE_FALSE:
						switch (c) {
							case ',':
								next();
								break;
							case '}':
								stack.push(Event.END_OBJECT);
								break;
							case ']':
								stack.push(Event.END_ARRAY);
								break;
							default:
								throw new JsonParsingException("unexpected token. token=" + c,
										getLocation());
						}
						break;
					default:
						throw new JsonParsingException("unexpected token.", getLocation());
				}
			}
			futureEvent = stack.peek();
			readFuture = true;

		} catch (IOException e) {
			throw new JsonParsingException("raise IOException", e, getLocation());
		}
	}

	@Override
	public int getInt() {
		return currentBigDecimal.intValueExact();
	}

	@Override
	public long getLong() {
		return currentBigDecimal.longValueExact();
	}

	@Override
	public BigDecimal getBigDecimal() {
		return currentBigDecimal;
	}

	@Override
	public boolean isIntegralNumber() {
		return currentIntegralNumber;
	}

	@Override
	public String getString() {
		return currentValueStr;
	}


	long columnNumber = 0;

	long lineNumber = 0;

	long streamOffset = 0;

	JsonLocation markedLocation;


	char readChar() throws IOException {
		char c = (char) reader.read();
		columnNumber++;
		streamOffset++;
		return c;
	}

	void encountLineDelimiter() {
		columnNumber = 0;
		lineNumber++;
	}

	void mark(int readAheadLimit) throws IOException {
		reader.mark(readAheadLimit);
		markedLocation = getLocation();
	}

	void resetToMark() throws IOException {
		reader.reset();
		columnNumber = markedLocation.getColumnNumber();
		lineNumber = markedLocation.getLineNumber();
		streamOffset = markedLocation.getStreamOffset();
	}

	char getNextChar() throws IOException {
		mark(1);

		char c = readChar();

		while (c == ' ' || c == '\r' || c == '\n' || c == '\t') {
			encountLineDelimiter();

			mark(1);
			c = readChar();
		}
		return c;
	}

	void expectNextChar(char expect) throws IOException {
		char c = getNextChar();
		if (c != expect) {
			throw new JsonParsingException("unexpected char. expected=" + expect + ", char=" + c,
					getLocation());
		}
	}


	StringBuilder stb = new StringBuilder();


	private void fetchNextNumeric() throws IOException {
		stb.setLength(0);
		resetToMark();
		char c;
		futureIntegralNumber = true;
		loop: while (true) {
			c = readChar();
			switch (c) {
				case '.':
				case 'e':
				case 'E':
					futureIntegralNumber = false;
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
				case '-':
					break;
				default:
					resetToMark();
					break loop;
			}
			mark(1);
			stb.append(c);
		}
		futureBigDecimal = new BigDecimal(stb.toString());
		stack.push(Event.VALUE_NUMBER);
	}

	private String getNextString() throws IOException {
		stb.setLength(0);
		char c;
		loop: while (true) {
			c = readChar();
			switch (c) {
				case '\\':
					mark(5);
					c = readChar();
					switch (c) {
						case '/':
						case '"':
						case '\\':
							break;
						case 'n':
							c = '\n';
							break;
						case 'r':
							c = '\r';
							break;
						case 't':
							c = '\t';
							break;
						case 'b':
							c = '\b';
							break;
						case 'f':
							c = '\f';
							break;
						case 'u':
							c = 0;
							int r;
							for (int i = 0; i < 4; i++) {
								r = getNextStringHelper(readChar());
								if (r == -1) {
									c = '\\';
									resetToMark();
									break;
								}
								c <<= 4;
								c += r;
							}
							break;
						default:
							c = '\\';
							resetToMark();
							break;
					}
					break;
				case '"':
					break loop;
				default:
					break;
			}
			stb.append(c);
		}
		return stb.toString();
	}

	private int getNextStringHelper(int c) {
		if ('0' <= c && c <= '9') {
			return c - '0';
		} else if ('a' <= c && c <= 'f') {
			return c - 'a' + 10;
		} else if ('A' <= c && c <= 'F') {
			return c - 'A' + 10;
		} else {
			return -1;
		}
	}
}
