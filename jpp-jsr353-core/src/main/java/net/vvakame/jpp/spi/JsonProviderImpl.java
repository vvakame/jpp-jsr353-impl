package net.vvakame.jpp.spi;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;

import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.spi.JsonProvider;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParserFactory;

import net.vvakame.jpp.JsonArrayBuilderImpl;
import net.vvakame.jpp.JsonBuilderFactoryImpl;
import net.vvakame.jpp.JsonObjectBuilderImpl;
import net.vvakame.jpp.JsonReaderFactoryImpl;
import net.vvakame.jpp.JsonReaderImpl;
import net.vvakame.jpp.JsonWriterFactoryImpl;
import net.vvakame.jpp.JsonWriterImpl;
import net.vvakame.stream.JsonGeneratorFactoryImpl;
import net.vvakame.stream.JsonGeneratorImpl;
import net.vvakame.stream.JsonParserFactoryImpl;
import net.vvakame.stream.JsonParserImpl;

/**
 * Implementation for {@link JsonProvider}.
 * @author vvakame
 */
public class JsonProviderImpl extends JsonProvider {

	@Override
	public JsonGenerator createGenerator(Writer writer) {
		return new JsonGeneratorImpl(writer);
	}

	@Override
	public JsonGenerator createGenerator(OutputStream os) {
		OutputStreamWriter writer = new OutputStreamWriter(os);
		return new JsonGeneratorImpl(writer);
	}

	@Override
	public JsonGeneratorFactory createGeneratorFactory(Map<String, ?> config) {
		return new JsonGeneratorFactoryImpl(config);
	}

	@Override
	public JsonParser createParser(Reader reader) {
		return new JsonParserImpl(reader);
	}

	@Override
	public JsonParser createParser(InputStream is) {
		InputStreamReader reader = new InputStreamReader(is);
		return new JsonParserImpl(reader);
	}

	@Override
	public JsonParserFactory createParserFactory(Map<String, ?> config) {
		return new JsonParserFactoryImpl(config);
	}

	@Override
	public JsonBuilderFactory createBuilderFactory(Map<String, ?> config) {
		return new JsonBuilderFactoryImpl(config);
	}

	@Override
	public JsonObjectBuilder createObjectBuilder() {
		return new JsonObjectBuilderImpl();
	}

	@Override
	public JsonArrayBuilder createArrayBuilder() {
		return new JsonArrayBuilderImpl();
	}

	@Override
	public JsonReader createReader(Reader reader) {
		return new JsonReaderImpl(reader);
	}

	@Override
	public JsonReader createReader(InputStream is) {
		InputStreamReader reader = new InputStreamReader(is);
		return new JsonReaderImpl(reader);
	}

	@Override
	public JsonReaderFactory createReaderFactory(Map<String, ?> config) {
		return new JsonReaderFactoryImpl(config);
	}

	@Override
	public JsonWriter createWriter(Writer writer) {
		return new JsonWriterImpl(writer);
	}

	@Override
	public JsonWriter createWriter(OutputStream os) {
		OutputStreamWriter writger = new OutputStreamWriter(os);
		return new JsonWriterImpl(writger);
	}

	@Override
	public JsonWriterFactory createWriterFactory(Map<String, ?> config) {
		return new JsonWriterFactoryImpl(config);
	}
}
