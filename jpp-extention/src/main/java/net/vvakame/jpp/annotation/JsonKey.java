package net.vvakame.jpp.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.vvakame.jpp.helper.TokenConverter;

/**
 * Annotation to decorate fields should be mapped to JSON.
 * 
 * @see JsonModel
 * @author vvakame
 */
@Retention(RetentionPolicy.SOURCE)
@Target({
	ElementType.FIELD
})
public @interface JsonKey {

	/**
	 * Mock converter!! (ΦωΦ)ｶｯ 
	 * @author vvakame
	 */
	static class MockConverter extends TokenConverter<Void> {

		public static MockConverter getInstance() {
			throw new UnsupportedOperationException("if you use this method. override it.");
		}
	}


	/**
	 * The string value that should be used as JSON key.<br>
	 * The field name itself is assumed by default.
	 * @return The value should be used as JSON key
	 * @author vvakame
	 */
	public String value() default "";

	/**
	 * You can pass {@link TokenConverter}-alike classes to perform conversion if you have fields of unsupported type (nor don't have {@link JsonModel} decorate them.)
	 * (e.g. to define some conversion between {@link java.util.Date} and {@code long})
	 *
	 * @return A {@link TokenConverter}-alike class defines conversion
	 * @author vvakame
	 */
	public Class<? extends TokenConverter<?>> converter() default MockConverter.class;

	/**
	 * Inbound (i.e. JSON to POJO) conversion should be performed or not.
	 * @return True if JSON de-serialization should be performed on the field, false otherwise.
	 * @author vvakame
	 */
	public boolean in() default true;

	/**
	 * Outbound (i.e. POJO to JSON) conversion should be performed or not.
	 * @return True if JSON serialization should be performed on the field, false otherwise.
	 * @author vvakame
	 */
	public boolean out() default true;

	/**
	 * De-camelize the name of field on mapping (i.e. "source_id" becomes "sourceId", and vice versa) or not.
	 * @return True if de-camelization should be performed, false otherwise.
	 * @author vvakame
	 */
	public boolean decamelize() default false;

	/**
	 * Ignore this property at mapping.
	 * @return True if ignore, False otherwise.
	 * @author vvakame
	 */
	public boolean ignore() default false;

	/**
	 * sort order for json member.
	 * @return sort order
	 * @author vvakame
	 */
	public int sortOrder() default Integer.MAX_VALUE;
}
