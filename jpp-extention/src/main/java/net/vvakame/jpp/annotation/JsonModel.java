package net.vvakame.jpp.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to decorate classes should be mapped to JSON.
 * 
 * @see JsonKey
 * @author vvakame
 */
@Retention(RetentionPolicy.CLASS)
@Target({
	ElementType.TYPE
})
public @interface JsonModel {

	/**
	 * Specifies whether parsers should error out on detection of an unknown key in JSON describling this class or not.<br>
	 * They should throw {@link IllegalStateException} if true, or discard it and continue otherwise.
	 * @return Whether parsers should throw error upon detection of unknown key about this class or not.
	 * @author vvakame
	 */
	public boolean treatUnknownKeyAsError() default false;

	/**
	 * Include the all property for mapping. If you want to ignore the property, use {@link JsonKey#ignore()}.
	 * @return True if include all property for mapping, false otherwise.
	 * @author vvakame
	 */
	public boolean includeAll() default true;

	/**
	 * De-camelize the name of class on mapping (i.e. "source_id" becomes "sourceId", and vice versa) or not.
	 * @return True if de-camelization should be performed, false otherwise.
	 * @see JsonKey#decamelize()
	 * @author vvakame
	 */
	public boolean decamelize() default false;

	/**
	 * Modifier of generated class change to "package private" from "public". 
	 * @return True if modifier change to package private, false otherwise.
	 * @author vvakame
	 */
	public boolean genToPackagePrivate() default false;

	/**
	 * Modifier of JsonMeta class change to "package private" from "public". 
	 * @return True if modifier change to package private, false otherwise.
	 * @author vvakame
	 */
	public boolean jsonMetaToPackagePrivate() default false;

	/**
	 * Whether a dynamic JSON builder should be generated for this class or not.
	 * @return True if dynamic JSON builder should be generated, false otherwise.
	 * @author vvakame
	 */
	public boolean builder() default false;
}
