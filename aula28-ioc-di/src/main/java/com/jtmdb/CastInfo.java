package com.jtmdb;

import java.io.Serializable;
import java.net.URL;


import org.json.JSONException;
import org.json.JSONObject;

import com.jtmdb.Log.Verbosity;

/**
 * This class represents an entry in the cast list of a Movie. It contains info about a member of cast and its ID is the same ID with that of the Person in the cast.
 * @author  Savvas Dalkitsis
 */
public class CastInfo implements Serializable {

	private static final long serialVersionUID = 8623103045084363150L;

	/**
	 * The Url of the cast.
	 * @uml.property  name="url"
	 */
	private URL url;
	/**
	 * The name of the cast.
	 * @uml.property  name="name"
	 */
	private String name;
	/**
	 * The name of the character of the cast.
	 * @uml.property  name="characterName"
	 */
	private String characterName;
	/**
	 * The job description of the cast.
	 * @uml.property  name="job"
	 */
	private String job;
	/**
	 * The ID of the person.
	 * @uml.property  name="iD"
	 */
	private int ID;
	/**
	 * The ID of the cast.
	 * @uml.property  name="castID"
	 */
	private int castID;
	/**
	 * The thumbnail Url of the cast.
	 * @uml.property  name="thumb"
	 */
	private URL thumb;
	/**
	 * The department of the job of the cast.
	 * @uml.property  name="department"
	 */
	private String department;
	/**
	 * The json string that created this CastInfo object.
	 * @uml.property  name="jsonOrigin"
	 */
	private String jsonOrigin;

	/**
	 * Constructs a new CastInfo object.
	 * 
	 * @param url
	 *            The Url of the cast.
	 * @param name
	 *            The name of the cast.
	 * @param characterName
	 *            The name of the character of the cast.
	 * @param job
	 *            The job description of the cast.
	 * @param ID
	 *            The ID of the person.
	 * @param castID
	 *            The ID of the cast.
	 * @param thumb
	 *            The thumbnail Url of the cast.
	 * @param department
	 *            The department of the job of the cast.
	 * @param jsonOrigin
	 *            The json string that created this CastInfo object.
	 */
	public CastInfo(URL url, String name, String characterName, String job,
			int ID, int castID, URL thumb, String department, String jsonOrigin) {
		Log.log("Creating CastInfo object with url: "
				+ ((url == null) ? "NULL" : url.toString())
				+ ",character name: " + characterName + ", job: " + job
				+ "id: " + ID + ", castID: +" + castID + ", thumb URL: "
				+ ((thumb == null) ? "NULL" : thumb.toString())
				+ ", department: " + department + " and name: " + name,
				Verbosity.VERBOSE);
		this.jsonOrigin = jsonOrigin;
		setUrl(url);
		setName(name);
		setCharacterName(characterName);
		setJob(job);
		setID(ID);
		setCastID(castID);
		setThumb(thumb);
		setDepartment(department);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj instanceof CastInfo) {
			if (((CastInfo) obj).getCastID() == getCastID()
					&& ((CastInfo) obj).getID() == getID()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		// We will use the algorithm used in the hash code
		// calculation for the Point2D class;
		int x = getID();
		int y = getCastID();
		long bits = java.lang.Double.doubleToLongBits(x);
		bits ^= java.lang.Double.doubleToLongBits(y) * 31;
		return (((int) bits) ^ ((int) (bits >> 32)));
	}

	/**
	 * The json string that created this CastInfo object.
	 * @return  The json string that created this CastInfo object.
	 * @uml.property  name="jsonOrigin"
	 */
	public String getJsonOrigin() {
		return jsonOrigin;
	}

	/**
	 * The prettyprinted json string that created this CastInfo object.
	 * 
	 * @param indentFactor
	 *            The number of spaces to add to each level of indentation.
	 * @return The json string that created this CastInfo object.
	 */
	public String getJsonOrigin(int indentFactor) {
		try {
			return new JSONObject(jsonOrigin).toString(indentFactor);
		} catch (JSONException e) {
			Log.log(e, Verbosity.ERROR);
			return null;
		}
	}

	/**
	 * The Url of the cast.
	 * @return  The Url of the cast.
	 * @uml.property  name="url"
	 */
	public URL getUrl() {
		return url;
	}

	/**
	 * Sets the Url of the cast.
	 * @param url  The Url of the cast.
	 * @uml.property  name="url"
	 */
	public void setUrl(URL url) {
		this.url = url;
	}

	/**
	 * The name of the cast.
	 * @return  The name of the cast.
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the cast.
	 * @param name  The name of the cast.
	 * @uml.property  name="name"
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * The name of the character of the cast.
	 * @return  The name of the character of the cast.
	 * @uml.property  name="characterName"
	 */
	public String getCharacterName() {
		return characterName;
	}

	/**
	 * Sets the name of the character of the cast.
	 * @param characterName  The name of the character of the cast.
	 * @uml.property  name="characterName"
	 */
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	/**
	 * The job description of the cast.
	 * @return  The job description of the cast.
	 * @uml.property  name="job"
	 */
	public String getJob() {
		return job;
	}

	/**
	 * Sets the job description of the cast.
	 * @param job  The job description of the cast.
	 * @uml.property  name="job"
	 */
	public void setJob(String job) {
		this.job = job;
	}

	/**
	 * The ID of the person.
	 * @return  The ID of the person.
	 * @uml.property  name="iD"
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Sets the ID of the person.
	 * @param iD  The ID of the person.
	 * @uml.property  name="iD"
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * The ID of the cast.
	 * @return  The ID of the cast.
	 * @uml.property  name="castID"
	 */
	public int getCastID() {
		return castID;
	}

	/**
	 * Sets the ID of the cast.
	 * @param castID  The ID of the cast.
	 * @uml.property  name="castID"
	 */
	public void setCastID(int castID) {
		this.castID = castID;
	}

	/**
	 * The thumbnail Url of the cast.
	 * @return  The thumbnail Url of the cast.
	 * @uml.property  name="thumb"
	 */
	public URL getThumb() {
		return thumb;
	}

	/**
	 * Sets the thumbnail Url of the cast.
	 * @param thumb  The thumbnail Url of the cast.
	 * @uml.property  name="thumb"
	 */
	public void setThumb(URL thumb) {
		this.thumb = thumb;
	}

	/**
	 * Sets the department of the job of the cast.
	 * @param department  The department of the job of the cast.
	 * @uml.property  name="department"
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * The department of the job of the cast.
	 * @return  The department of the job of the cast.
	 * @uml.property  name="department"
	 */
	public String getDepartment() {
		return department;
	}

}
