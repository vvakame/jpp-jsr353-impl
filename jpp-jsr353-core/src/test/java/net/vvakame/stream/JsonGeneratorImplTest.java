package net.vvakame.stream;

import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.json.spi.JsonProvider;
import javax.json.stream.JsonGenerationException;
import javax.json.stream.JsonGenerator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.*;

/**
 * Test for {@link JsonGeneratorImpl}.
 * @author vvakame
 */
public class JsonGeneratorImplTest {

	protected Class<? extends JsonGenerator> getJsonGeneratorImplClass() {
		return JsonGeneratorImpl.class;
	}

	protected JsonGenerator getJsonGeneratorImpl(Writer writer) {
		JsonGenerator generator = JsonProvider.provider().createGenerator(writer);
		assertThat(generator, instanceOf(getJsonGeneratorImplClass()));
		return generator;
	}

	/**
	 * Write simple JsonObject test.
	 * @author vvakame
	 */
	@Test
	public void generateSimpleJsonObject() {
		StringWriter writer = new StringWriter();
		JsonGenerator generator = getJsonGeneratorImpl(writer);
		generator.writeStartObject().writeEnd().flush();

		assertThat(writer.toString(), is("{}"));
	}

	/**
	 * Write simple JsonArray test.
	 * @author vvakame
	 */
	@Test
	public void generateSimpleJsonArray() {
		StringWriter writer = new StringWriter();
		JsonGenerator generator = getJsonGeneratorImpl(writer);
		generator.writeStartArray().writeEnd().flush();

		assertThat(writer.toString(), is("[]"));
	}

	/**
	 * Test by javadoc sample code.
	 * @author vvakame
	 */
	@Test
	public void generateJavadocSample() {
		// from here https://json-processing-spec.java.net/nonav/releases/1.0/pfd-draft/javadocs/javax/json/stream/JsonGenerator.html
		StringWriter writer = new StringWriter();
		JsonGenerator generator = getJsonGeneratorImpl(writer);
		generator.writeStartObject().write("firstName", "John").write("lastName", "Smith")
			.write("age", 25).writeStartObject("address").write("streetAddress", "21 2nd Street")
			.write("city", "New York").write("state", "NY").write("postalCode", "10021").writeEnd()
			.writeStartArray("phoneNumber").writeStartObject().write("type", "home")
			.write("number", "212 555-1234").writeEnd().writeStartObject().write("type", "fax")
			.write("number", "646 555-4567").writeEnd().writeEnd().writeEnd();
		generator.close();

		JSONObject jsonObject = JSONObject.fromObject(writer.toString());

		assertThat("top level has 5 keys", jsonObject.size(), is(5));
		{
			JSONArray jsonArray = jsonObject.getJSONArray("phoneNumber");
			assertThat("phoneNumber has 2 child", jsonArray.size(), is(2));
			{
				JSONObject data = jsonArray.getJSONObject(0);
				assertThat(data.getString("number"), is("212 555-1234"));
				assertThat(data.getString("type"), is("home"));
			}
			{
				JSONObject data = jsonArray.getJSONObject(1);
				assertThat(data.getString("number"), is("646 555-4567"));
				assertThat(data.getString("type"), is("fax"));
			}
		}
		{
			JSONObject data = jsonObject.getJSONObject("address");

			assertThat(data.getString("postalCode"), is("10021"));
			assertThat(data.getString("state"), is("NY"));
			assertThat(data.getString("city"), is("New York"));
			assertThat(data.getString("streetAddress"), is("21 2nd Street"));
		}
		{
			assertThat(jsonObject.getInt("age"), is(25));
		}
		{
			assertThat(jsonObject.getString("lastName"), is("Smith"));
		}
		{
			assertThat(jsonObject.getString("firstName"), is("John"));
		}
	}

	/**
	 * Generate general JsonArray values.
	 * @author vvakame
	 */
	@Test
	public void generalJsonArrayValue() {
		StringWriter writer = new StringWriter();
		JsonGenerator generator = getJsonGeneratorImpl(writer);

		generator.writeStartArray();
		generator.write("string");
		generator.write(BigDecimal.TEN);
		generator.write(BigInteger.TEN);
		generator.write(8);
		generator.write(11L);
		generator.write(0.125);
		generator.write(true);
		generator.write(false);
		generator.writeNull();
		generator.writeEnd();

		assertThat(writer.toString(), is("[\"string\",10,10,8,11,0.125,true,false,null]"));
	}

	/**
	 * Generate general JsonArray values.
	 * @author vvakame
	 */
	@Test
	public void generalJsonArrayValue_checkState() {
		StringWriter writer = new StringWriter();
		JsonGenerator generator = getJsonGeneratorImpl(writer);

		generator.writeStartObject();
		try {
			generator.write("string");
			fail("Now in the JsonObject.");
		} catch (JsonGenerationException e) {
		}
		try {
			generator.write(BigDecimal.TEN);
			fail("Now in the JsonObject.");
		} catch (JsonGenerationException e) {
		}
		try {
			generator.write(BigInteger.TEN);
			fail("Now in the JsonObject.");
		} catch (JsonGenerationException e) {
		}
		try {
			generator.write(8);
			fail("Now in the JsonObject.");
		} catch (JsonGenerationException e) {
		}
		try {
			generator.write(11L);
			fail("Now in the JsonObject.");
		} catch (JsonGenerationException e) {
		}
		try {
			generator.write(0.125);
			fail("Now in the JsonObject.");
		} catch (JsonGenerationException e) {
		}
		try {
			generator.write(true);
			fail("Now in the JsonObject.");
		} catch (JsonGenerationException e) {
		}
		try {
			generator.write(false);
			fail("Now in the JsonObject.");
		} catch (JsonGenerationException e) {
		}
		try {
			generator.writeNull();
			fail("Now in the JsonObject.");
		} catch (JsonGenerationException e) {
		}
		generator.writeEnd();
	}

	/**
	 * Generate general JsonArray values.
	 * @author vvakame
	 */
	@Test
	public void generalJsonObjectValue() {
		StringWriter writer = new StringWriter();
		JsonGenerator generator = getJsonGeneratorImpl(writer);

		generator.writeStartObject();
		generator.write("str", "string");
		generator.write("bigInteger", BigInteger.TEN);
		generator.write("bigDecimal", BigDecimal.TEN);
		generator.write("int", 8);
		generator.write("long", 11L);
		generator.write("double", 0.125);
		generator.write("booleanTrue", true);
		generator.write("booleanFalse", false);
		generator.writeNull("null");
		generator.writeEnd();

		System.out.println(writer.toString());
		assertThat(
				writer.toString(),
				is("{\"str\":\"string\",\"bigInteger\":10,\"bigDecimal\":10,\"int\":8,\"long\":11,\"double\":0.125,\"booleanTrue\":true,\"booleanFalse\":false,\"null\":null}"));
	}

	/**
	 * Generate general JsonArray values.
	 * @author vvakame
	 */
	@Test
	public void generalJsonObjectValue_checkState() {
		StringWriter writer = new StringWriter();
		JsonGenerator generator = getJsonGeneratorImpl(writer);

		generator.writeStartArray();
		try {
			generator.write("str", "string");
			fail("Now in the JsonObject.");
		} catch (JsonGenerationException e) {
		}
		try {
			generator.write("bigInteger", BigInteger.TEN);
		} catch (JsonGenerationException e) {
		}
		try {
			generator.write("bigDecimal", BigDecimal.TEN);
		} catch (JsonGenerationException e) {
		}
		try {
			generator.write("int", 8);
		} catch (JsonGenerationException e) {
		}
		try {
			generator.write("long", 11L);
		} catch (JsonGenerationException e) {
		}
		try {
			generator.write("double", 0.125);
		} catch (JsonGenerationException e) {
		}
		try {
			generator.write("booleanTrue", true);
		} catch (JsonGenerationException e) {
		}
		try {
			generator.write("booleanFalse", false);
		} catch (JsonGenerationException e) {
		}
		try {
			generator.writeNull("null");
		} catch (JsonGenerationException e) {
		}
		generator.writeEnd();
	}
}
