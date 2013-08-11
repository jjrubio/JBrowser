package jbrowser;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Sanny Florencia <sbflorenc@gmail.com>
 */
public class Page {
	private ArrayList<String> history= new ArrayList<String>();
	private int currentPage;
	
	private Http http;
	private String url;
	
	public Page( String url ) {
		setUrl( url );
	}
	
	public void setUrl( String url ) {
		this.url = url;
	}
	
	public  String getUrl() {
		return url;
	}
	
	public void setCurrentPage() {
		this.currentPage = history.size();
	}
	
	public  int getCurrentPage() {
		return this.currentPage;
	}
	
	public boolean go() throws IOException {
		load();
		
		//If the url is correct add to history 
		history.add( getUrl() );
		
		return true;
	}
	
	public boolean load() throws IOException {
		http = new Http( getUrl() );
		
		/* Call function to create socket connection request()
		*	if it returns - http status 200 - call getContent function
		*	else show an error message
		*/
		
		
		/*if ( http.request() ){
			if ( http.getStatus() )
				http.getContent();
		} else {
			return false;
		}*/
		
		
		return true;
	} 
	
	public boolean back() throws IOException {
		// Set url - history 
		setUrl( history.get( getCurrentPage() -1 ) );
		load();
		
		return true;
	}
	
	public boolean forward() throws IOException {
		setUrl( history.get( getCurrentPage() + 1 ) );
		load();
		
		return true;
	}

	public boolean reload() throws IOException {
		load();
		
		return true;
	}
	
}