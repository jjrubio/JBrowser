package gui;

import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import jbrowser.Page;

/**
 *
 * @author Sanny Florencia <sbflorenc@gmail.com>
 */
public class PageView extends JEditorPane {
	
	private Page page;
	
	public PageView() {
		page = new Page();
		setEditable(false);
        setContentType("text/html;charset=UTF-8");
		addHyperlinkListener( new HyperlinkListener() {

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
			setText(page.getContent());
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
			setText(page.getContent());
	}
	
	public void forward() {
		if ( page.forward() )
			setText(page.getContent());
	}

}
