package net.vvakame.jpp.sample;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import javax.json.spi.JsonProvider;
import javax.json.stream.JsonParser;

import net.vvakame.jpp.helper.JsonParserExtender;

public class SampleJson {

	static JsonProvider provider;


	static JsonProvider getProvider() {
		if (provider != null) {
			return provider;
		} else {
			provider = JsonProvider.provider();
			return provider;
		}
	}

	public static List<Sample> getList(String json) {
		StringReader reader = new StringReader(json);
		return getList(reader);
	}

	public static List<Sample> getList(InputStream stream) {
		JsonParser parser = getProvider().createParser(stream);
		return getList(parser);
	}

	public static List<Sample> getList(Reader reader) {
		JsonParser parser = getProvider().createParser(reader);
		return getList(parser);
	}

	public static List<Sample> getList(JsonParser parser) {
		JsonParserExtender extender = JsonParserExtender.getInstance(parser);
		// TODO Auto-generated method stub
		return null;
	}
}
