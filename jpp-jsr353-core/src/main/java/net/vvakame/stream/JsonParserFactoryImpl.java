package net.vvakame.stream;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Map;

import javax.json.JsonArray;
import javax.json.JsonObject;
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
		this.config = config;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonParser createParser(JsonArray jsonArray) {
		// TODO Auto-generated method stub
		return null;
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
