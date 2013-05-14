package net.vvakame.jpp;

import java.io.Reader;
import java.io.StringReader;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.spi.JsonProvider;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.*;

/**
 * Test for {@link JsonReaderImpl}.
 * @author vvakame
 */
public class JsonReaderImplTest {

	protected Class<? extends JsonReader> getJsonReaderImplClass() {
		return JsonReaderImpl.class;
	}

	protected JsonReader getJsonReaderImpl(Reader reader) {
		JsonReader jsonReader = JsonProvider.provider().createReader(reader);
		assertThat(jsonReader, instanceOf(getJsonReaderImplClass()));
		return jsonReader;
	}

	/**
	 * Read JsonObject test.
	 * @author vvakame
	 */
	@Test
	public void readJsonObject() {
		StringReader reader = new StringReader("{\"array\":[1,2,3],\"after\":true}");
		JsonReader jsonReader = getJsonReaderImpl(reader);
		JsonObject jsonObject = jsonReader.readObject();

		assertThat(jsonObject.size(), is(2));
		JsonArray jsonArray = jsonObject.getJsonArray("array");
		assertThat(jsonArray.size(), is(3));
	}

	/**
	 * Read JsonObject test.
	 * @author vvakame
	 */
	@Test
	public void readJsonArray() {
		StringReader reader = new StringReader("[1,2,3,{}]");
		JsonReader jsonReader = getJsonReaderImpl(reader);
		JsonArray jsonArray = jsonReader.readArray();

		assertThat(jsonArray.size(), is(4));
	}
}
