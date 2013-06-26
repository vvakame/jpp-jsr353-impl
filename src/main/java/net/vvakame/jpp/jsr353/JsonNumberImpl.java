package net.vvakame.jpp.jsr353;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.json.JsonNumber;

/**
 * Implementation for {@link JsonNumber}.
 * @author vvakame
 */
public class JsonNumberImpl implements JsonNumber {

	final BigDecimal bigDecimal;


	/**
	 * the constructor.
	 * @param bigDecimal
	 * @category constructor
	 */
	// TODO to package private?
	public JsonNumberImpl(BigDecimal bigDecimal) {
		this.bigDecimal = bigDecimal;
	}

	@Override
	public ValueType getValueType() {
		return ValueType.NUMBER;
	}

	@Override
	public boolean isIntegral() {
		return bigDecimal.scale() == 0;
	}

	@Override
	public int intValue() {
		return bigDecimal.intValue();
	}

	@Override
	public int intValueExact() {
		return bigDecimal.intValueExact();
	}

	@Override
	public long longValue() {
		return bigDecimal.longValue();
	}

	@Override
	public long longValueExact() {
		return bigDecimal.longValueExact();
	}

	@Override
	public BigInteger bigIntegerValue() {
		return bigDecimal.toBigInteger();
	}

	@Override
	public BigInteger bigIntegerValueExact() {
		return bigDecimal.toBigIntegerExact();
	}

	@Override
	public double doubleValue() {
		return bigDecimal.doubleValue();
	}

	@Override
	public BigDecimal bigDecimalValue() {
		return bigDecimal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bigDecimal == null) ? 0 : bigDecimal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JsonNumberImpl other = (JsonNumberImpl) obj;
		if (bigDecimal == null) {
			if (other.bigDecimal != null)
				return false;
		} else if (!bigDecimal.equals(other.bigDecimal))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return bigDecimal.toString();
	}
}
