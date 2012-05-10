package movq.core;

import java.net.URL;

public interface IMovie {
	public String getName();
	String getImdbID();
	URL getUrl();
	String getLanguage();
	int getVotes();
	double getRating();
}
