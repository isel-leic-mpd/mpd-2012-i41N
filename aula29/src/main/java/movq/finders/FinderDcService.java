package movq.finders;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import movq.core.IMovie;
import movq.core.IMovieFinder;

import org.json.JSONException;
import org.json.JSONObject;

import com.jtmdb.GeneralSettings.Utilities;

public class FinderDcService implements IMovieFinder{
	
	public static void main(String [] args) throws Exception{
		
		for (IMovie m : new FinderDcService().search("black")) {
			System.out.println(m.getName());
		}
	}
	
	private static final String HTTP_API_URL = "http://www.deanclatworthy.com/imdb/";

	
	public Iterable<IMovie> search(String title) throws MalformedURLException, IOException, JSONException{
		//Forming a complete url ready to send (type parameter can be JSON also)  
		String dataurl = HTTP_API_URL + "?q="+ title + "&type=json";  

		String jsonString = Utilities.readUrlResponse(new URL(dataurl)).trim();
		final JSONObject obj = new JSONObject(jsonString);
		IMovie mov = new IMovie() {
			URL url = new URL(obj.getString("imdburl")); 
			public int getVotes() {return obj.getInt("votes");}
			public URL getUrl() {return url;}
			public double getRating() {return obj.getDouble("rating");}
			public String getName() {return obj.getString("title");}
			public String getLanguage() {return obj.getString("languages");}
			public String getImdbID() {return obj.getString("tt0120689");}
			@Override
			public String toString() {
				return getName();
			}
		};
		return Arrays.asList(mov);
	}


	/**
	 * This method will open a connection to the provided url and return its
	 * response.
	 * 
	 * @param url
	 *            The url to open a connection to.
	 * @return The respone.
	 * @throws IOException
	 * @author Savvas Dalkitsis
	 */
	private static String readUrlResponse(URL url) throws IOException {
		URLConnection yc = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(yc
				.getInputStream(),"UTF-8"));
		String inputLine;
		StringBuffer responce = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			responce.append(inputLine);
		}
		in.close();
		return responce.toString();
	}	
}
