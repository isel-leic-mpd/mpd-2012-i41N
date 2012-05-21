package com.jtmdb;

import java.io.Serializable;
import java.net.URL;
import java.util.Date;


import org.json.JSONException;
import org.json.JSONObject;

import com.jtmdb.Log.Verbosity;

/**
 * This class represents an entry in the filmography list of a Person.
 * @author  Savvas Dalkitsis
 */
public class FilmographyInfo implements Serializable {

	private static final long serialVersionUID = -5487789524164209239L;

	/**
	 * The name of the Movie.
	 * @uml.property  name="name"
	 */
	private String name;
	/**
	 * The name of the character in the Movie.
	 * @uml.property  name="characterName"
	 */
	private String characterName;
	/**
	 * The url of the Movie.
	 * @uml.property  name="url"
	 */
	private URL url;
	/**
	 * The ID of the Movie.
	 * @uml.property  name="iD"
	 */
	private int ID;
	/**
	 * The ID of the cast.
	 * @uml.property  name="castID"
	 */
	private int castID;
	/**
	 * The job description in the Movie.
	 * @uml.property  name="job"
	 */
	private String job;
	/**
	 * The department of the job for the Movie.
	 * @uml.property  name="department"
	 */
	private String department;
	/**
	 * The json string that created this FilmographyInfo object.
	 * @uml.property  name="jsonOrigin"
	 */
	private String jsonOrigin;
	/**
	 * The poster of the movie.
	 * @uml.property  name="moviePoster"
	 */
	private URL moviePoster;
	/**
	 * Is the movie for adult audiences only.
	 * @uml.property  name="adult"
	 */
	private boolean adult;
	/**
	 * The release date of the movie.
	 * @uml.property  name="releasedDate"
	 */
	private Date releasedDate;

	/**
	 * Creates a new FilmographyInfo object.
	 * 
	 * @param name
	 *            The name of the Movie.
	 * @param characterName
	 *            The name of the character in the Movie.
	 * @param url
	 *            The url of the Movie.
	 * @param ID
	 *            The ID of the Movie.
	 * @param castID
	 *            The ID of the cast.
	 * @param job
	 *            The job description in the Movie.
	 * @param department
	 *            The department of the job for the Movie.
	 * @param jsonOrigin
	 *            The json string that created this FilmographyInfo object.
	 * @param moviePoster
	 *            The poster of the movie.
	 * @param adult
	 *            Is the movie for adult audiences only.
	 * @param releasedDate
	 *            The release date of the movie.
	 */
	public FilmographyInfo(String name, String characterName, URL url, int ID,
			int castID, String job, String department, String jsonOrigin,
			URL moviePoster, boolean adult, Date releasedDate) {
		Log.log("Creating FilmographyInfo object with url: "
				+ ((url == null) ? "NULL" : url.toString())
				+ ",character name: " + characterName + ", job: " + job
				+ "id: " + ID + ", castID: " + castID + ", department: "
				+ department + ", movie poster: " + moviePoster
				+ ", adult flag: " + adult + " and name: " + name,
				Verbosity.VERBOSE);
		this.jsonOrigin = jsonOrigin;
		setName(name);
		setCharacterName(characterName);
		setUrl(url);
		setID(ID);
		setCastID(castID);
		setJob(job);
		setDepartment(department);
		setMoviePoster(moviePoster);
		setAdult(adult);
		setReleasedDate(releasedDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj instanceof FilmographyInfo) {
			if (((FilmographyInfo) obj).getCastID() == getCastID()
					&& ((FilmographyInfo) obj).getID() == getID()) {
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
	 * The json string that created this FilmographyInfo object.
	 * @return  The json string that created this FilmographyInfo object.
	 * @uml.property  name="jsonOrigin"
	 */
	public String getJsonOrigin() {
		return jsonOrigin;
	}

	/**
	 * The prettyprinted json string that created this FilmographyInfo object.
	 * 
	 * @param indentFactor
	 *            The number of spaces to add to each level of indentation.
	 * @return The json string that created this FilmographyInfo object.
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
	 * The name of the Movie.
	 * @return  The name of the Movie.
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the Movie.
	 * @param name  The name of the Movie.
	 * @uml.property  name="name"
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * The name of the character in the Movie.
	 * @return  The name of the character in the Movie.
	 * @uml.property  name="characterName"
	 */
	public String getCharacterName() {
		return characterName;
	}

	/**
	 * Sets the name of the character in the Movie.
	 * @param characterName  The name of the character in the Movie.
	 * @uml.property  name="characterName"
	 */
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	/**
	 * The url of the Movie.
	 * @return  The url of the Movie.
	 * @uml.property  name="url"
	 */
	public URL getUrl() {
		return url;
	}

	/**
	 * Sets the url of the Movie.
	 * @param url  The url of the Movie.
	 * @uml.property  name="url"
	 */
	public void setUrl(URL url) {
		this.url = url;
	}

	/**
	 * The url of the Movie poster.
	 * @return  The url of the Movie poster.
	 * @uml.property  name="moviePoster"
	 */
	public URL getMoviePoster() {
		return moviePoster;
	}

	/**
	 * Sets the url of the Movie poster.
	 * @param moviePoster  The url of the Movie poster.
	 * @uml.property  name="moviePoster"
	 */
	public void setMoviePoster(URL moviePoster) {
		this.moviePoster = moviePoster;
	}

	/**
	 * The ID of the Movie.
	 * @return  The ID of the Movie.
	 * @uml.property  name="iD"
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Sets the ID of the Movie.
	 * @param iD  The ID of the Movie.
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
	 * The job description in the Movie.
	 * @return  The job description in the Movie.
	 * @uml.property  name="job"
	 */
	public String getJob() {
		return job;
	}

	/**
	 * Sets the job description in the Movie.
	 * @param job  The job description in the Movie.
	 * @uml.property  name="job"
	 */
	public void setJob(String job) {
		this.job = job;
	}

	/**
	 * Returns true if the Movie is adult only.
	 * @return  True if the Movie is adult only.
	 * @uml.property  name="adult"
	 */
	public boolean isAdult() {
		return adult;
	}

	/**
	 * Sets whether the Movie is adult only.
	 * @param adult  The adult flag of the Movie.
	 * @uml.property  name="adult"
	 */
	public void setAdult(boolean adult) {
		this.adult = adult;
	}

	/**
	 * Sets the department of the job for the Movie.
	 * @param department  The department of the job for the Movie.
	 * @uml.property  name="department"
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * The department of the job for the Movie. for the Movieof the cast.
	 * @uml.property  name="department"
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * The Movie release Date.
	 * @return  The Movie release Date.
	 * @uml.property  name="releasedDate"
	 */
	public Date getReleasedDate() {
		return releasedDate;
	}

	/**
	 * Sets the release date of the Movie.
	 * @param releasedDate  The release date of the Movie.
	 * @uml.property  name="releasedDate"
	 */
	public void setReleasedDate(Date releasedDate) {
		this.releasedDate = releasedDate;
	}

}
