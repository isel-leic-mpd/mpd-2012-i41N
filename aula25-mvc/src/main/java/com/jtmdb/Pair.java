package com.jtmdb;

import java.io.Serializable;

/**
 * This class represents a pair of objects.
 * @author  Savvas Dalkitsis
 */
public class Pair<K, V> implements Serializable {

	private static final long serialVersionUID = -3592615574714099889L;

	/**
	 * The first object.
	 * @uml.property  name="first"
	 */
	private K first = null;
	/**
	 * The second object.
	 * @uml.property  name="second"
	 */
	private V second = null;

	/**
	 * Constructs a Pair with the provided parameters.
	 * 
	 * @param first
	 *            The first object of the pair.
	 * @param second
	 *            The second object of the pair.
	 */
	public Pair(K first, V second) {
		this.first = first;
		this.second = second;
	}

	/**
	 * Constructs an empty Pair with both the objects set to null.
	 */
	public Pair() {
	}

	/**
	 * Gets the first object of the Pair.
	 * @return  The first object of the Pair.
	 * @uml.property  name="first"
	 */
	public K getFirst() {
		return first;
	}

	/**
	 * Sets the first object of the Pair.
	 * @param first  The first object of the Pair.
	 * @uml.property  name="first"
	 */
	public void setFirst(K first) {
		this.first = first;
	}

	/**
	 * Gets the second object of the Pair.
	 * @return  The second object of the Pair.
	 * @uml.property  name="second"
	 */
	public V getSecond() {
		return second;
	}

	/**
	 * Set the second object of the pair.
	 * @param second  The second object of the pair.
	 * @uml.property  name="second"
	 */
	public void setSecond(V second) {
		this.second = second;
	}

}
