package net.vvakame.jpp.helper;

/**
 * Callback class which allows peeking of various intermittent instances as parsing goes.
 *
 * @author vvakame
 */
public interface OnJsonObjectAddListener {

	/**
	 * A new instance is read.
	 * @param obj Newly read instance
	 * @author vvakame
	 */
	public void onAdd(Object obj);
}
