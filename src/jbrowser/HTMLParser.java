package jbrowser;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Parser code HTML in JAVA
 * @author Jefferson Rubio <jefferson.jrubio@gmail.com>
 */
public class HTMLParser {
    
    Document doc;
    
    /**
     * Creates an object Document
     * @param url String Url address to parser
     */
    public HTMLParser(String url){
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException ex) {
            Logger.getLogger(HTMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Get the title of a website
     * @return String title
     */
    public String getTitle(){
        String title = doc.title();
        
        if(doc.title().equals("")){
            title = doc.baseUri().split("http://")[1];
        }
        
        return title;
    }
    
}
