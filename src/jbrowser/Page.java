package jbrowser;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Sanny Florencia <sbflorenc@gmail.com>
 */
public class Page {
	private ArrayList<String> history = new ArrayList<String>();
	private int currentPage;
	
	private Http http;
	private String url;
	private String content;
	
	public Page( String url ) {
		setUrl( url );
	}
	
	public void setUrl( String url ) {
		this.url = url;
	}
	
	public  String getUrl() {
		return url;
	}

	public String getContent() {
		return content;
	}
	
	public void setCurrentPage() {
		currentPage = history.size();
	}
	
	public  int getCurrentPage() {
		return currentPage;
	}
	
	public boolean go() {
		
		//If the url is correct add to history 
		if( load() ){
			history.add( getUrl() );
			return true;
		}
		
		return false;
	}
	
	public boolean load() {
		http = new Http( getUrl() );
		
		/* Call function to create socket connection request()
		*	if it returns - http status 200 - call getContent function
		*	else show an error message
		*/
		
		if ( http.request() ){
			System.out.println("" + http.getStatus());
			//if ( http.getStatus() >= 200 && http.getStatus() <= 208 )
				content = http.getContent();
		} else {
			return false;
		}
		
		return true;
	} 
	
	public boolean back() {
		// Set url - history 
		currentPage = getCurrentPage() -1;
		setUrl( history.get( currentPage ) );
		load();
		
		return true;
	}
	
	public boolean forward() {
		currentPage = getCurrentPage() -1;
		setUrl( history.get( currentPage ) );
		load();
		
		return true;
	}

	public boolean reload() {
		load();
		
		return true;
	}
	
}
