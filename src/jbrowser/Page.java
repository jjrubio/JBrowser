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
	
	/**
     *
     */
    public Page() {
	}
	
	/**
     *
     * @param url
     */
    public void setUrl( String url ) {
		this.url = url;
	}
	
	/**
     *
     * @return
     */
    public  String getUrl() {
		return url;
	}

	/**
     *
     * @return
     */
    public String getContent() {
		return content;
	}
	
	/**
     *
     * @return
     */
    public  int getCurrentPage() {
		return currentPage;
	}
	
	/**
     *
     * @return
     */
    public int getHistorySize(){
		return history.size();
	}

        /**
     *
     * @return
     */
    public Http getHttp() {
            return http;
        }

        /**
     *
     * @param http
     */
    public void setHttp(Http http) {
            this.http = http;
        }
	
	/**
     *
     * @param url
     * @return
     */
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
			int statusHttp = http.getStatus(); 
			System.out.println("" + http.getStatus());
			
			if ( statusHttp >= 200 && statusHttp <= 208 )
				content = http.getContent();
			else if( statusHttp >= 300 && statusHttp <= 307 )
				go( http.getHeader( "Location" ) );
			
		} else {
			return false;
		}
		
		return true;
	} 
	
	/**
     *
     * @return
     */
    public String getContentType(){
		return http.getHeader( "Content-Type" );
	}
	
	/**
     *
     * @return
     */
    public boolean back() {
		// Set url - history 
		currentPage = getCurrentPage() - 1;
		load( history.get( currentPage ) );
		
		return true;
	}
	
	/**
     *
     * @return
     */
    public boolean forward() {
		currentPage = getCurrentPage() + 1;
		load( history.get( currentPage ) );
		
		return true;
	}

	/**
     *
     * @return
     */
    public boolean reload() {
		load( history.get(currentPage) );
		
		return true;
	}
	
}
