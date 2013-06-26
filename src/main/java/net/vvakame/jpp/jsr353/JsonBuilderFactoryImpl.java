package net.vvakame.jpp.jsr353;

import java.util.Collections;
import java.util.Map;

import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;

/**
 * Implementation for {@link JsonBuilderFactory}.
 * @author vvakame
 */
public class JsonBuilderFactoryImpl implements JsonBuilderFactory {

	final Map<String, ?> config;


	/**
	 * the constructor.
	 * @param config
	 * @category constructor
	 */
	public JsonBuilderFactoryImpl(Map<String, ?> config) {
		if (config != null) {
			this.config = Collections.unmodifiableMap(config);
		} else {
			this.config = Collections.emptyMap();
		}
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
	public Map<String, ?> getConfigInUse() {
		return config;
	}
}
