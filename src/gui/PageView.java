package gui;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import jbrowser.Page;

/**
 *
 * @author Sanny Florencia <sbflorenc@gmail.com>
 */
public class PageView extends JScrollPane {
	
	private Page page;
	private JEditorPane editor;
	private String url;
	private PageListener pageListener;
	
	/**
     * 
     */
    public PageView() {
		page = new Page();
		
		editor = new JEditorPane();
		editor.setEditable(false);
                editor.setContentType( "txt/html;charset=UTF-8" );	
		setViewportView(editor);
		
		setAutoscrolls(true);
		
		editor.addHyperlinkListener( new HyperlinkListener() {

			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (HyperlinkEvent.EventType.ACTIVATED == e.getEventType()) {
					go( e.getURL().toString() );
				}
			}
		});
	}
	
	/**
     * Go to the url
     * @param url
     */
    public void go(String url) {
		if (page.go(url)) {
			if( page.getContentType() == null)
				editor.setContentType( "txt/html; charset=UTF-8" );
			else
				editor.setContentType( page.getContentType() );
			
			editor.setText(page.getContent());
			this.url = url;

			if (pageListener != null)
			    pageListener.pageLoaded();
		}
	}
	
	/**
     * Reload the current page.
     */
    public void reload() {
		page.reload();
	}
	
	/**
     *
     */
    public void stop(){
		// TODO
	}
	
	/**
     * Go back to the previous page.
     */
    public void back() {
		if ( page.back() )
			editor.setText(page.getContent());
	}
	
	/**
     * Go forward to the next page.
     */
    public void forward() {
		if ( page.forward() )
			editor.setText(page.getContent());
	}
     
	/**
     * Get the url page
     * @return
     */
    public String getUrl() {
		return url;
	}
	
	/**
     * Get the history size
     * @return
     */
    public int getHistorySize(){
		return page.getHistorySize();
	}
	
	/**
     * Get the current page
     * @return
     */
    public int getCurrentPage(){
		return page.getCurrentPage();
	}
	
	/**
     * Get the status page
     * @return
     */
	public int getStatus(){
		return page.getStatus();
	}

	public void addPageListener(PageListener pageListener) {
		this.pageListener = pageListener;
	}

	interface PageListener {

	    void pageLoaded();
	}
}
