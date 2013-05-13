package net.vvakame.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * {@link java.util.Stack} equivalent.<br> We won't use {@link java.util.Stack}
 * because we don't have to synchronize.<br> And we cannot use {@link java.util.Deque}
 * because it requires an API level of 9 or better on the Android platform.
 * 
 * @param <T>
 * @author vvakame
 * 
 */
final public class Stack<T> {

	final List<T> stack = new ArrayList<T>();


	/**
	 * Pushes the given value to the stack.
	 * @param arg The value to be pushed
	 * @author vvakame
	 */
	public void push(T arg) {
		stack.add(arg);
	}

	/**
	 * Pops the value from the top of stack and returns it.
	 * @return The value has been popped.
	 * @author vvakame
	 */
	public T pop() {
		final int max = stack.size() - 1;
		if (max < 0) {
			throw new NoSuchElementException();
		}
		return stack.remove(max);
	}

	/**
	 * Returns the value currently on the top of the stack.
	 * @return The value on the top.
	 * @author vvakame
	 */
	public T peek() {
		final int top = stack.size() - 1;
		if (top < 0) {
			throw new NoSuchElementException();
		}
		return stack.get(top);
	}

	/**
	 * Returns stack size.
	 * @return size
	 * @author vvakame
	 */
	public int size() {
		return stack.size();
	}
}
