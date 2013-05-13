package net.vvakame.stream;

import java.io.Reader;
import java.io.StringReader;

import javax.json.spi.JsonProvider;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.*;

/**
 * Test for {@link JsonParserImpl}.
 * @author vvakame
 */
public class JsonParserImplTest {

	protected Class<? extends JsonParser> getJsonParserImplClass() {
		return JsonParserImpl.class;
	}

	protected JsonParser getJsonParserImpl(Reader reader) {
		JsonParser parser = JsonProvider.provider().createParser(reader);
		assertThat(parser, instanceOf(getJsonParserImplClass()));
		return parser;
	}

	/**
	 * Parse simple JsonObject test.
	 * @author vvakame
	 */
	@Test
	public void parseSimpleJsonObject() {
		StringReader reader = new StringReader("{}");
		JsonParser parser = getJsonParserImpl(reader);

		{
			assertThat(parser.hasNext(), is(true));
			assertThat(parser.next(), is(Event.START_OBJECT));
		}
		{
			assertThat(parser.hasNext(), is(true));
			assertThat(parser.next(), is(Event.END_OBJECT));
		}
		{
			assertThat(parser.hasNext(), is(false));
		}
	}

	/**
	 * Parse simple JsonArray test.
	 * @author vvakame
	 */
	@Test
	public void parseSimpleJsonArray() {
		StringReader reader = new StringReader("[]");
		JsonParser parser = getJsonParserImpl(reader);

		{
			assertThat(parser.hasNext(), is(true));
			assertThat(parser.next(), is(Event.START_ARRAY));
		}
		{
			assertThat(parser.hasNext(), is(true));
			assertThat(parser.next(), is(Event.END_ARRAY));
		}
		{
			assertThat(parser.hasNext(), is(false));
		}
	}

	/**
	 * Parse simple JsonArray test.
	 * @author vvakame
	 */
	@Test
	public void parseJavadocSample() {
		// from https://json-processing-spec.java.net/nonav/releases/1.0/pfd-draft/javadocs/javax/json/stream/JsonParser.html
		StringReader reader =
				new StringReader(
						"{\"firstName\": \"John\", \"lastName\": \"Smith\", \"age\": 25,\"phoneNumber\": [{ \"type\": \"home\", \"number\": \"212 555-1234\" },{ \"type\": \"fax\", \"number\": \"646 555-4567\" }]}");
		JsonParser parser = getJsonParserImpl(reader);

		{ // {
			assertThat(parser.hasNext(), is(true));
			assertThat(parser.next(), is(Event.START_OBJECT));
		}
		{ // key firstName
			assertThat(parser.hasNext(), is(true));
			assertThat(parser.next(), is(Event.KEY_NAME));
			assertThat(parser.getString(), is("firstName"));
		}
		{ // value John
			assertThat(parser.hasNext(), is(true));
			assertThat(parser.next(), is(Event.VALUE_STRING));
			assertThat(parser.getString(), is("John"));
		}
		{ // key lastName
			assertThat(parser.hasNext(), is(true));
			assertThat(parser.next(), is(Event.KEY_NAME));
			assertThat(parser.getString(), is("lastName"));
		}
		{ // value Smith
			assertThat(parser.hasNext(), is(true));
			assertThat(parser.next(), is(Event.VALUE_STRING));
			assertThat(parser.getString(), is("Smith"));
		}
		{ // key age
			assertThat(parser.hasNext(), is(true));
			assertThat(parser.next(), is(Event.KEY_NAME));
			assertThat(parser.getString(), is("age"));
		}
		{ // value 25
			assertThat(parser.hasNext(), is(true));
			assertThat(parser.next(), is(Event.VALUE_NUMBER));
			assertThat(parser.isIntegralNumber(), is(true));
			assertThat(parser.getInt(), is(25));
		}
		{ // key phoneNumber
			assertThat(parser.hasNext(), is(true));
			assertThat(parser.next(), is(Event.KEY_NAME));
			assertThat(parser.getString(), is("phoneNumber"));
		}
		{ // [
			assertThat(parser.hasNext(), is(true));
			assertThat(parser.next(), is(Event.START_ARRAY));
		}
		{ // {
			assertThat(parser.hasNext(), is(true));
			assertThat(parser.next(), is(Event.START_OBJECT));
		}
		{ // key type
			assertThat(parser.hasNext(), is(true));
			assertThat(parser.next(), is(Event.KEY_NAME));
			assertThat(parser.getString(), is("type"));
		}
		{ // value Smith
			assertThat(parser.hasNext(), is(true));
			assertThat(parser.next(), is(Event.VALUE_STRING));
			assertThat(parser.getString(), is("home"));
		}
		{ // key number
			assertThat(parser.hasNext(), is(true));
			assertThat(parser.next(), is(Event.KEY_NAME));
			assertThat(parser.getString(), is("number"));
		}
		{ // value 212 555-1234
			assertThat(parser.hasNext(), is(true));
			assertThat(parser.next(), is(Event.VALUE_STRING));
			assertThat(parser.getString(), is("212 555-1234"));
		}
		{ // }
			assertThat(parser.hasNext(), is(true));
			assertThat(parser.next(), is(Event.END_OBJECT));
		}
		{ // {
			assertThat(parser.hasNext(), is(true));
			assertThat(parser.next(), is(Event.START_OBJECT));
		}
		{ // key type
			assertThat(parser.hasNext(), is(true));
			assertThat(parser.next(), is(Event.KEY_NAME));
			assertThat(parser.getString(), is("type"));
		}
		{ // value fax
			assertThat(parser.hasNext(), is(true));
			assertThat(parser.next(), is(Event.VALUE_STRING));
			assertThat(parser.getString(), is("fax"));
		}
		{ // key type
			assertThat(parser.hasNext(), is(true));
			assertThat(parser.next(), is(Event.KEY_NAME));
			assertThat(parser.getString(), is("number"));
		}
		{ // value fax
			assertThat(parser.hasNext(), is(true));
			assertThat(parser.next(), is(Event.VALUE_STRING));
			assertThat(parser.getString(), is("646 555-4567"));
		}
		{ // }
			assertThat(parser.hasNext(), is(true));
			assertThat(parser.next(), is(Event.END_OBJECT));
		}
		{ // ]
			assertThat(parser.hasNext(), is(true));
			assertThat(parser.next(), is(Event.END_ARRAY));
		}
		{ // }
			assertThat(parser.hasNext(), is(true));
			assertThat(parser.next(), is(Event.END_OBJECT));
		}
		{
			assertThat(parser.hasNext(), is(false));
		}
	}

	/**
	 * Parse simple JsonArray test.
	 * @author vvakame
	 */
	@Test
	public void parseJavadocSample_withoutHasNext() {
		// from https://json-processing-spec.java.net/nonav/releases/1.0/pfd-draft/javadocs/javax/json/stream/JsonParser.html
		StringReader reader =
				new StringReader(
						"{\"firstName\": \"John\", \"lastName\": \"Smith\", \"age\": 25,\"phoneNumber\": [{ \"type\": \"home\", \"number\": \"212 555-1234\" },{ \"type\": \"fax\", \"number\": \"646 555-4567\" }]}");
		JsonParser parser = getJsonParserImpl(reader);

		{ // {
			assertThat(parser.next(), is(Event.START_OBJECT));
		}
		{ // key firstName
			assertThat(parser.next(), is(Event.KEY_NAME));
			assertThat(parser.getString(), is("firstName"));
		}
		{ // value John
			assertThat(parser.next(), is(Event.VALUE_STRING));
			assertThat(parser.getString(), is("John"));
		}
		{ // key lastName
			assertThat(parser.next(), is(Event.KEY_NAME));
			assertThat(parser.getString(), is("lastName"));
		}
		{ // value Smith
			assertThat(parser.next(), is(Event.VALUE_STRING));
			assertThat(parser.getString(), is("Smith"));
		}
		{ // key age
			assertThat(parser.next(), is(Event.KEY_NAME));
			assertThat(parser.getString(), is("age"));
		}
		{ // value 25
			assertThat(parser.next(), is(Event.VALUE_NUMBER));
			assertThat(parser.isIntegralNumber(), is(true));
			assertThat(parser.getInt(), is(25));
		}
		{ // key phoneNumber
			assertThat(parser.next(), is(Event.KEY_NAME));
			assertThat(parser.getString(), is("phoneNumber"));
		}
		{ // [
			assertThat(parser.next(), is(Event.START_ARRAY));
		}
		{ // {
			assertThat(parser.next(), is(Event.START_OBJECT));
		}
		{ // key type
			assertThat(parser.next(), is(Event.KEY_NAME));
			assertThat(parser.getString(), is("type"));
		}
		{ // value Smith
			assertThat(parser.next(), is(Event.VALUE_STRING));
			assertThat(parser.getString(), is("home"));
		}
		{ // key number
			assertThat(parser.next(), is(Event.KEY_NAME));
			assertThat(parser.getString(), is("number"));
		}
		{ // value 212 555-1234
			assertThat(parser.next(), is(Event.VALUE_STRING));
			assertThat(parser.getString(), is("212 555-1234"));
		}
		{ // }
			assertThat(parser.next(), is(Event.END_OBJECT));
		}
		{ // {
			assertThat(parser.next(), is(Event.START_OBJECT));
		}
		{ // key type
			assertThat(parser.next(), is(Event.KEY_NAME));
			assertThat(parser.getString(), is("type"));
		}
		{ // value fax
			assertThat(parser.next(), is(Event.VALUE_STRING));
			assertThat(parser.getString(), is("fax"));
		}
		{ // key type
			assertThat(parser.next(), is(Event.KEY_NAME));
			assertThat(parser.getString(), is("number"));
		}
		{ // value fax
			assertThat(parser.next(), is(Event.VALUE_STRING));
			assertThat(parser.getString(), is("646 555-4567"));
		}
		{ // }
			assertThat(parser.next(), is(Event.END_OBJECT));
		}
		{ // ]
			assertThat(parser.next(), is(Event.END_ARRAY));
		}
		{ // }
			assertThat(parser.next(), is(Event.END_OBJECT));
		}
		{
			assertThat(parser.hasNext(), is(false));
		}
	}
}
