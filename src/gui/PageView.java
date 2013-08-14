package gui;

import java.io.IOException;
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
		setEditable(false);
        setContentType("text/html;charset=UTF-8");
		addHyperlinkListener( new HyperlinkListener() {

			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (HyperlinkEvent.EventType.ACTIVATED == e.getEventType()) {
					try {
						go( e.getURL().toString() );
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}
	
	public void go(String url) throws IOException{
		page = new Page(url);
		if (page.go()) {
			setText(page.getContent());
		}
	}
	
	public void reload() throws IOException{
		page.reload();
	}
	
	public void stop(){
		// TODO
	}
	
	public void back() throws IOException{
		page.back();
	}
	
	public void forward() throws IOException{
		page.forward();
	}

}
