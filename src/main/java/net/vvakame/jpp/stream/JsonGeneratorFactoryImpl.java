package net.vvakame.jpp.stream;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Map;

import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;

/**
 * Implementation for {@link JsonGeneratorFactory}.
 * @author vvakame
 */
public class JsonGeneratorFactoryImpl implements JsonGeneratorFactory {

	final Map<String, ?> config;


	/**
	 * the constructor.
	 * @param config
	 * @category constructor
	 */
	// TODO to package private?
	public JsonGeneratorFactoryImpl(Map<String, ?> config) {
		if (config != null) {
			this.config = Collections.unmodifiableMap(config);
		} else {
			this.config = Collections.emptyMap();
		}
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
	public JsonGenerator createGenerator(OutputStream os, Charset charset) {
		OutputStreamWriter writer = new OutputStreamWriter(os, charset);
		return new JsonGeneratorImpl(writer);
	}

	@Override
	public Map<String, ?> getConfigInUse() {
		return config;
	}
}
