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
     * Class constructor.
     */
    public Page() {
	}
	
	/**
     * Get the url to the page.
     * @param url
     */
    public void setUrl( String url ) {
		this.url = url;
	}
	
	/**
     * Get the url to the page.
     * @return
     */
    public  String getUrl() {
		return url;
	}

	/**
     * Get the content to the page.
     * @return
     */
    public String getContent() {
		return content;
	}
	
	/**
     * Get the current page.
     * @return
     */
    public  int getCurrentPage() {
		return currentPage;
	}
	
	/**
     * Get the size of the history
     * @return
     */
    public int getHistorySize(){
		return history.size();
	}
	
	/**
     * go to the page indicated in the url if its ok add url to history.
     * @param url
     * @return
     */
    public boolean go( String url ) {
		if( load(url) ){
			history.add(url);
			currentPage = history.size() - 1;
			System.out.println(history);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Call function to create socket connection request()
	 * if it returns - http status 200 - call getContent function
	 * @param url
	 * @return 
	 */
	private boolean load(String url) {
		http = new Http(url);
		
		
		
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
     * Get content type of the page
     * @return
     */
    public String getContentType(){
		return http.getHeader( "Content-Type" );
	}
	
	/**
     * Go back to the previous page.
     * @return
     */
    public boolean back() {
		// Set url - history 
		currentPage = getCurrentPage() - 1;
		load( history.get( currentPage ) );
		
		return true;
	}
	
	/**
     * Go forward to the next page.
     * @return
     */
    public boolean forward() {
		currentPage = getCurrentPage() + 1;
		load( history.get( currentPage ) );
		
		return true;
	}

	/**
     * Reload the current page.
     * @return
     */
    public boolean reload() {
		load( history.get(currentPage) );
		
		return true;
	}
	
	public int getStatus(){
		return http.getStatus();
	}
	
}
