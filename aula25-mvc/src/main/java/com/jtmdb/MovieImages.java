package com.jtmdb;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * This class represents the images of a Movie. Contains posters and backdrops.
 * @author  Savvas Dalkitsis
 */
public class MovieImages implements Serializable {

	private static final long serialVersionUID = 1087926973625501506L;

	public Set<MoviePoster> posters = new LinkedHashSet<MoviePoster>();
	public Set<MovieBackdrop> backdrops = new LinkedHashSet<MovieBackdrop>();

	/**
	 * @uml.property  name="name"
	 */
	private String name;
	/**
	 * @uml.property  name="iD"
	 */
	private int ID;

	public MovieImages(int ID, String name) {
		this.ID = ID;
		this.name = name;
	}

	/**
	 * @return
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 * @uml.property  name="name"
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 * @uml.property  name="iD"
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param iD
	 * @uml.property  name="iD"
	 */
	public void setID(int iD) {
		ID = iD;
	}

}
