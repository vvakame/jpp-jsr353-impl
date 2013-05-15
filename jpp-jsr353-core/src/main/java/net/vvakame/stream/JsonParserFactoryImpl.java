package net.vvakame.stream;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParserFactory;

/**
 * Implentation for {@link JsonParserFactory}.
 * @author vvakame
 */
public class JsonParserFactoryImpl implements JsonParserFactory {

	final Map<String, ?> config;


	/**
	 * the constructor.
	 * @param config
	 * @category constructor
	 */
	public JsonParserFactoryImpl(Map<String, ?> config) {
		if (config != null) {
			this.config = Collections.unmodifiableMap(config);
		} else {
			this.config = Collections.emptyMap();
		}
	}

	@Override
	public JsonParser createParser(Reader reader) {
		return new JsonParserImpl(reader);
	}

	@Override
	public JsonParser createParser(InputStream is) {
		return new JsonParserImpl(new InputStreamReader(is));
	}

	@Override
	public JsonParser createParser(JsonObject jsonObject) {
		StringWriter writer = new StringWriter();
		JsonGenerator generator = Json.createGenerator(writer);
		generator.write(jsonObject);
		String json = writer.toString();
		StringReader reader = new StringReader(json);
		return createParser(reader);
	}

	@Override
	public JsonParser createParser(JsonArray jsonArray) {
		StringWriter writer = new StringWriter();
		JsonGenerator generator = Json.createGenerator(writer);
		generator.write(jsonArray);
		String json = writer.toString();
		StringReader reader = new StringReader(json);
		return createParser(reader);
	}

	@Override
	public JsonParser createParser(InputStream is, Charset charset) {
		return new JsonParserImpl(new InputStreamReader(is, charset));
	}

	@Override
	public Map<String, ?> getConfigInUse() {
		return config;
	}
}
