package com.jtmdb;

import java.io.Serializable;

/**
 * This class holds a width and a height (integer values).
 * @author  Savvas Dalkitsis
 */
public class Dimension implements Serializable {

	private static final long serialVersionUID = 3579129609109585922L;

	/**
	 * @uml.property  name="width"
	 */
	private int width;
	/**
	 * @uml.property  name="height"
	 */
	private int height;

	/**
	 * Constructs a Dimension object with the specified dimensions.
	 * 
	 * @param width
	 *            The width.
	 * @param height
	 *            The height.
	 */
	public Dimension(int width, int height) {
		setWidth(width);
		setHeight(height);
	}

	/**
	 * Constructs a Dimension object and sets its width and height to 0.
	 */
	public Dimension() {
		this(0, 0);
	}

	/**
	 * Sets the height of the dimension.
	 * @param height  The height to set
	 * @uml.property  name="height"
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Returns the height of the dimension.
	 * @return  The height
	 * @uml.property  name="height"
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the width of the dimension.
	 * @param width  The width to set
	 * @uml.property  name="width"
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Returns the width of the dimension.
	 * @return  The width
	 * @uml.property  name="width"
	 */
	public int getWidth() {
		return width;
	}

}
