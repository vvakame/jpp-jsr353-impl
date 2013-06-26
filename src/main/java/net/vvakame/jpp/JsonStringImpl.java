package net.vvakame.jpp;

import javax.json.JsonString;

/**
 * Implementation for {@link JsonString}.
 * @author vvakame
 */
public class JsonStringImpl implements JsonString {

	final String str;


	/**
	 * the constructor.
	 * @param str
	 * @category constructor
	 */
	public JsonStringImpl(String str) {
		this.str = str;
	}

	@Override
	public ValueType getValueType() {
		return ValueType.STRING;
	}

	@Override
	public String getString() {
		return str;
	}

	@Override
	public CharSequence getChars() {
		return str;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((str == null) ? 0 : str.hashCode());
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
		JsonStringImpl other = (JsonStringImpl) obj;
		if (str == null) {
			if (other.str != null)
				return false;
		} else if (!str.equals(other.str))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return str;
	}
}
