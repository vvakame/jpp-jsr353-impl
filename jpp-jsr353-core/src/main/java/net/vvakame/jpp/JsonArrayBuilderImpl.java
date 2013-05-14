package net.vvakame.jpp;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

/**
 * Implementation for {@link JsonArrayBuilder}.
 * @author vvakame
 */
public class JsonArrayBuilderImpl implements JsonArrayBuilder {

	JsonArrayImpl array = new JsonArrayImpl();


	@Override
	public JsonArrayBuilder add(JsonValue value) {
		array.add(value);
		return this;
	}

	@Override
	public JsonArrayBuilder add(String value) {
		array.add(new JsonStringImpl(value));
		return this;
	}

	@Override
	public JsonArrayBuilder add(BigDecimal value) {
		array.add(new JsonNumberImpl(value));
		return this;
	}

	@Override
	public JsonArrayBuilder add(BigInteger value) {
		return add(new BigDecimal(value));
	}

	@Override
	public JsonArrayBuilder add(int value) {
		return add(new BigDecimal(value));
	}

	@Override
	public JsonArrayBuilder add(long value) {
		return add(new BigDecimal(value));
	}

	@Override
	public JsonArrayBuilder add(double value) {
		return add(new BigDecimal(value));
	}

	@Override
	public JsonArrayBuilder add(boolean value) {
		if (value) {
			array.add(JsonValue.TRUE);
		} else {
			array.add(JsonValue.FALSE);
		}
		return this;
	}

	@Override
	public JsonArrayBuilder addNull() {
		array.add(JsonValue.NULL);
		return this;
	}

	@Override
	public JsonArrayBuilder add(JsonObjectBuilder builder) {
		array.add(builder.build());
		return this;
	}

	@Override
	public JsonArrayBuilder add(JsonArrayBuilder builder) {
		array.add(builder.build());
		return this;
	}

	@Override
	public JsonArray build() {
		return array;
	}
}
