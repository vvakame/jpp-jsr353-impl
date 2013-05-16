package net.vvakame.jpp.helper;

import java.io.Writer;

import javax.json.JsonException;
import javax.json.stream.JsonGenerationException;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParsingException;

import net.vvakame.jpp.annotation.JsonKey;

/**
 * Class to customize the serialization and deserialization.<br>
 * Use with {@link JsonKey#converter()}.<br>
 * If extend this class, when subclass must implement {@code public static TokenConverter getInstance()}.
 * 
 * @param <T> 
 * @author vvakame
 */
public class TokenConverter<T> {

	/**
	 * For JSON deserialize.
	 * @param parser {@link JsonParser}
	 * @param listener {@link OnJsonObjectAddListener} for sequential processing
	 * @return deserialized instance
	 * @throws JsonException if an i/o error occurs (IOException
	 * would be cause of JsonException)
	 * @throws JsonParsingException if the parser encounters invalid JSON
	 * when advancing to next state.
	 * @author vvakame
	 */
	public T parse(JsonParser parser, OnJsonObjectAddListener listener) throws JsonException,
			JsonParsingException {
		throw new UnsupportedOperationException("if you use this method. override it.");
	}

	/**
	 * For JSON serialize.<br>
	 * If null is passed to object, write null to writer.
	 * @param writer
	 * @param obj
	 * @throws JsonException if an i/o error occurs (IOException
	 * would be cause of JsonException)
	 * @throws JsonGenerationException if this method is called within an 
	 *      object context or if called more than once in no context
	 * @author vvakame
	 */
	public void write(Writer writer, T obj) throws JsonException, JsonGenerationException {
		throw new UnsupportedOperationException("if you use this method. override it.");
	}
}
