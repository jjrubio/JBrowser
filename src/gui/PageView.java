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
	
	public PageView() {
		page = new Page();
		
		editor = new JEditorPane();
		editor.setEditable(false);
                editor.setContentType("text/html;charset=UTF-8");	
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
	
	public void go(String url) {
		if (page.go(url)) {
			editor.setText(page.getContent());
			this.url = url;
		}
	}
	
	public void reload() {
		page.reload();
	}
	
	public void stop(){
		// TODO
	}
	
	public void back() {
		if ( page.back() )
			editor.setText(page.getContent());
	}
	
	public void forward() {
		if ( page.forward() )
			editor.setText(page.getContent());
	}
     
	public String getUrl() {
		return url;
	}
	
	public int getHistorySize(){
		return page.getHistorySize();
	}
	
	public int getCurrentPage(){
		return page.getCurrentPage();
	}
}
