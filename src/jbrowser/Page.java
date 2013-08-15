package jbrowser;

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
	
	public Page() {
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
	
	public  int getCurrentPage() {
		return currentPage;
	}
	
	public boolean go( String url ) {
		//If the url is correct add to history 
		if( load(url) ){
			history.add(url);
			currentPage = history.size() - 1;
			System.out.println(history);
			return true;
		}
		
		return false;
	}
	
	private boolean load(String url) {
		http = new Http(url);
		
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
		currentPage = getCurrentPage() - 1;
		load( history.get( currentPage ) );
		
		return true;
	}
	
	public boolean forward() {
		currentPage = getCurrentPage() + 1;
		load( history.get( currentPage ) );
		
		return true;
	}

	public boolean reload() {
		load( history.get(currentPage) );
		
		return true;
	}
	
}
