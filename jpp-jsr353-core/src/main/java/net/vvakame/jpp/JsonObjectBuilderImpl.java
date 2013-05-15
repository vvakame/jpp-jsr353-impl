package net.vvakame.jpp;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

/**
 * Implementation for {@link JsonObjectBuilder}.
 * @author vvakame
 */
public class JsonObjectBuilderImpl implements JsonObjectBuilder {

	Map<String, JsonValue> object = new LinkedHashMap<String, JsonValue>();


	@Override
	public JsonObjectBuilder add(String name, JsonValue value) {
		if (name == null) {
			throw new NullPointerException("name is required. You can use addNull(String) method.");
		} else if (value == null) {
			throw new NullPointerException("value is required. You can use addNull(String) method.");
		}
		object.put(name, value);
		return this;
	}

	@Override
	public JsonObjectBuilder add(String name, String value) {
		if (name == null) {
			throw new NullPointerException("name is required. You can use addNull(String) method.");
		} else if (value == null) {
			throw new NullPointerException("value is required. You can use addNull(String) method.");
		}
		object.put(name, new JsonStringImpl(value));
		return this;
	}

	@Override
	public JsonObjectBuilder add(String name, BigDecimal value) {
		if (name == null) {
			throw new NullPointerException("name is required. You can use addNull(String) method.");
		} else if (value == null) {
			throw new NullPointerException("value is required. You can use addNull(String) method.");
		}
		object.put(name, new JsonNumberImpl(value));
		return this;
	}

	@Override
	public JsonObjectBuilder add(String name, BigInteger value) {
		if (name == null) {
			throw new NullPointerException("name is required. You can use addNull(String) method.");
		} else if (value == null) {
			throw new NullPointerException("value is required. You can use addNull(String) method.");
		}
		add(name, new BigDecimal(value));
		return this;
	}

	@Override
	public JsonObjectBuilder add(String name, int value) {
		if (name == null) {
			throw new NullPointerException("name is required. You can use addNull(String) method.");
		}
		add(name, new BigDecimal(value));
		return this;
	}

	@Override
	public JsonObjectBuilder add(String name, long value) {
		if (name == null) {
			throw new NullPointerException("name is required. You can use addNull(String) method.");
		}
		add(name, new BigDecimal(value));
		return this;
	}

	@Override
	public JsonObjectBuilder add(String name, double value) {
		if (name == null) {
			throw new NullPointerException("name is required. You can use addNull(String) method.");
		}
		add(name, BigDecimal.valueOf(value));
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
		if (name == null) {
			throw new NullPointerException("name is required. You can use addNull(String) method.");
		}
		add(name, JsonValue.NULL);
		return this;
	}

	@Override
	public JsonObjectBuilder add(String name, JsonObjectBuilder builder) {
		if (name == null) {
			throw new NullPointerException("name is required. You can use addNull(String) method.");
		} else if (builder == null) {
			throw new NullPointerException(
					"builder is required. You can use addNull(String) method.");
		}
		add(name, builder.build());
		return this;
	}

	@Override
	public JsonObjectBuilder add(String name, JsonArrayBuilder builder) {
		if (name == null) {
			throw new NullPointerException("name is required. You can use addNull(String) method.");
		} else if (builder == null) {
			throw new NullPointerException(
					"builder is required. You can use addNull(String) method.");
		}
		add(name, builder.build());
		return this;
	}

	@Override
	public JsonObject build() {
		return new JsonObjectImpl(object);
	}
}
