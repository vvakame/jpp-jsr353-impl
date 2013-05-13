package net.vvakame.stream;

import javax.json.stream.JsonLocation;

/**
 * Implementation for {@link JsonLocation}.
 * @author vvakame
 */
public class JsonLocationImpl implements JsonLocation {

	final long columnNumber;

	final long lineNumber;

	final long streamOffset;


	/**
	 * the constructor.
	 * @param columnNumber
	 * @param lineNumber
	 * @param streamOffset
	 * @category constructor
	 */
	public JsonLocationImpl(long columnNumber, long lineNumber, long streamOffset) {
		this.columnNumber = columnNumber;
		this.lineNumber = lineNumber;
		this.streamOffset = streamOffset;
	}

	@Override
	public long getColumnNumber() {
		return columnNumber;
	}

	@Override
	public long getLineNumber() {
		return lineNumber;
	}

	@Override
	public long getStreamOffset() {
		return streamOffset;
	}

	@Override
	public String toString() {
		return "JsonLocation [columnNumber=" + columnNumber + ", lineNumber=" + lineNumber
				+ ", streamOffset=" + streamOffset + "]";
	}
}
