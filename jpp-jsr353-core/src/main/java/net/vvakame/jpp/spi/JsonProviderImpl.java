package net.vvakame.jpp.spi;

import java.io.InputStream;
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

import net.vvakame.stream.JsonGeneratorImpl;

public class JsonProviderImpl extends JsonProvider {

	@Override
	public JsonArrayBuilder createArrayBuilder() {
		// TODO
		return null;
	}

	@Override
	public JsonBuilderFactory createBuilderFactory(Map<String, ?> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

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
	public JsonGeneratorFactory createGeneratorFactory(Map<String, ?> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonObjectBuilder createObjectBuilder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonParser createParser(Reader arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonParser createParser(InputStream arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonParserFactory createParserFactory(Map<String, ?> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonReader createReader(Reader arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonReader createReader(InputStream arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonReaderFactory createReaderFactory(Map<String, ?> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonWriter createWriter(Writer arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonWriter createWriter(OutputStream arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonWriterFactory createWriterFactory(Map<String, ?> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
