package net.vvakame.jpp;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

/**
 * Implementation for {@link JsonObjectBuilder}.
 * @author vvakame
 */
public class JsonObjectBuilderImpl implements JsonObjectBuilder {

	JsonObjectImpl object = new JsonObjectImpl();


	@Override
	public JsonObjectBuilder add(String name, JsonValue value) {
		object.add(name, value);
		return this;
	}

	@Override
	public JsonObjectBuilder add(String name, String value) {
		object.add(name, new JsonStringImpl(value));
		return this;
	}

	@Override
	public JsonObjectBuilder add(String name, BigDecimal value) {
		object.add(name, new JsonNumberImpl(value));
		return this;
	}

	@Override
	public JsonObjectBuilder add(String name, BigInteger value) {
		add(name, new BigDecimal(value));
		return this;
	}

	@Override
	public JsonObjectBuilder add(String name, int value) {
		add(name, new BigDecimal(value));
		return this;
	}

	@Override
	public JsonObjectBuilder add(String name, long value) {
		add(name, new BigDecimal(value));
		return this;
	}

	@Override
	public JsonObjectBuilder add(String name, double value) {
		add(name, new BigDecimal(value));
		return this;
	}

	@Override
	public JsonObjectBuilder add(String name, boolean value) {
		if (value) {
			add(name, JsonValue.TRUE);
		} else {
			add(name, JsonValue.FALSE);
		}
		return this;
	}

	@Override
	public JsonObjectBuilder addNull(String name) {
		add(name, JsonValue.NULL);
		return this;
	}

	@Override
	public JsonObjectBuilder add(String name, JsonObjectBuilder builder) {
		add(name, builder.build());
		return this;
	}

	@Override
	public JsonObjectBuilder add(String name, JsonArrayBuilder builder) {
		add(name, builder.build());
		return this;
	}

	@Override
	public JsonObject build() {
		return object;
	}
}
