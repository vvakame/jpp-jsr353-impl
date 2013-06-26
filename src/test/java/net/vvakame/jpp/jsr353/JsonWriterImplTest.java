package net.vvakame.jpp.jsr353;

import java.io.StringWriter;
import java.io.Writer;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.json.spi.JsonProvider;

import net.vvakame.jpp.jsr353.JsonWriterImpl;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.*;

/**
 * Test for {@link JsonWriterImpl}.
 * @author vvakame
 */
public class JsonWriterImplTest {

	protected Class<? extends JsonWriter> getJsonWriterImplClass() {
		return JsonWriterImpl.class;
	}

	protected JsonWriter getJsonWriterImpl(Writer writer) {
		JsonWriter jsonWriter = JsonProvider.provider().createWriter(writer);
		assertThat(jsonWriter, instanceOf(getJsonWriterImplClass()));
		return jsonWriter;
	}

	/**
	 * Write JsonObject test.
	 * @author vvakame
	 */
	@Test
	public void writeJsonObject() {
		JsonObjectBuilder builder = JsonProvider.provider().createObjectBuilder();
		builder.add("key", "value");

		StringWriter writer = new StringWriter();
		JsonWriter jsonWriter = getJsonWriterImpl(writer);
		jsonWriter.write(builder.build());

		assertThat(writer.toString(), is("{\"key\":\"value\"}"));
	}

	/**
	 * Write JsonArray test.
	 * @author vvakame
	 */
	@Test
	public void writeJsonArray() {
		JsonArrayBuilder builder = JsonProvider.provider().createArrayBuilder();
		builder.add(1);
		builder.add("array");

		StringWriter writer = new StringWriter();
		JsonWriter jsonWriter = getJsonWriterImpl(writer);
		jsonWriter.write(builder.build());

		assertThat(writer.toString(), is("[1,\"array\"]"));
	}
}
