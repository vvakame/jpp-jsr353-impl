package net.vvakame.jpp.jsr353;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

/**
 * Implementation for {@link JsonArrayBuilder}.
 * @author vvakame
 */
public class JsonArrayBuilderImpl implements JsonArrayBuilder {

	List<JsonValue> list = new ArrayList<JsonValue>();


	@Override
	public JsonArrayBuilder add(JsonValue value) {
		if (value == null) {
			throw new NullPointerException("value is required. You can use addNull() method.");
		}
		list.add(value);
		return this;
	}

	@Override
	public JsonArrayBuilder add(String value) {
		if (value == null) {
			throw new NullPointerException("value is required. You can use addNull() method.");
		}
		list.add(new JsonStringImpl(value));
		return this;
	}

	@Override
	public JsonArrayBuilder add(BigDecimal value) {
		if (value == null) {
			throw new NullPointerException("value is required. You can use addNull() method.");
		}
		list.add(new JsonNumberImpl(value));
		return this;
	}

	@Override
	public JsonArrayBuilder add(BigInteger value) {
		if (value == null) {
			throw new NullPointerException("value is required. You can use addNull() method.");
		}
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
		if (Double.isNaN(value)) {
			throw new NumberFormatException("NaN is not allowed.");
		} else if (Double.isInfinite(value)) {
			throw new NumberFormatException("Intifite is not allowed.");
		}
		return add(new BigDecimal(value));
	}

	@Override
	public JsonArrayBuilder add(boolean value) {
		if (value) {
			list.add(JsonValue.TRUE);
		} else {
			list.add(JsonValue.FALSE);
		}
		return this;
	}

	@Override
	public JsonArrayBuilder addNull() {
		list.add(JsonValue.NULL);
		return this;
	}

	@Override
	public JsonArrayBuilder add(JsonObjectBuilder builder) {
		if (builder == null) {
			throw new NullPointerException("builder is required. You can use addNull() method.");
		}
		list.add(builder.build());
		return this;
	}

	@Override
	public JsonArrayBuilder add(JsonArrayBuilder builder) {
		if (builder == null) {
			throw new NullPointerException("builder is required. You can use addNull() method.");
		}
		list.add(builder.build());
		return this;
	}

	@Override
	public JsonArray build() {
		return new JsonArrayImpl(list);
	}
}
