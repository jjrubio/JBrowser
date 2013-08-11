package jbrowser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedHashMap;

/**
 *
 * @author Ramón Carrillo <racarrillo91@gmail.com>
 */
public class Http {
    
    // Http request
    private String method;
    private String uri;
    private String host;
    private int port;

    // Http response
    private int status;
    private String content;
    private LinkedHashMap<String, String> headers;
    
    // Socket connection
    private Socket socket;
    private PrintWriter out = null;
    private BufferedReader in = null;
    
    Http(String url) {
        host = url.split("://")[1].split("/")[0];
        port = 80;
        method = "GET";
        uri = url.split("://")[1].split("/", 2)[1];
   }
    
    public boolean request() throws IOException {
        
        try {
            socket = new Socket(host, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());

            return false;
        }

        out.print(httpRequest());
        out.flush();
        boolean ready = false;

        // Look if there is response
        // Try six times every 250 miliseconds
        for( int tries = 1; tries <= 6; tries++) {
            if (ready = in.ready())
                break;
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                return false;
            }
        }

        if (!ready) return false;

        String[] header;

        // Status
        header = in.readLine().split(" ");
        if (header.length != 3) return false;
        status = Integer.parseInt(header[1]);

        // Headers
        String read;
        headers = new LinkedHashMap<String, String>();
        while ( (ready = in.ready()) && !(read = in.readLine()).equals("") ) {
            if ( (header = read.split(": ", 2)).length != 2) return false;
            headers.put(header[0], header[1]);
        }

        if (!ready) return false;

        // Content
        content = "";
        while (in.ready())
            content += in.readLine() + "\n";

        return true;
    }
    
    private String httpRequest() {
        return method + " /" + uri + " HTTP/1.1\r\n" +
                "Host: " + host + "\r\n" +
                "User-Agent: JBrower 0.0.1" + "\r\n" +
                "Accept-Encoding: deflate" + "\r\n" +
                "\r\n";
    }

    public String getContent() {
        return content;
    }

    public int getStatus() {
        return status;
    }
}
