package jbrowser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
    
    public String request() throws IOException {
        
        try {
            socket = new Socket(host, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());

            return "";
        }

        out.print(httpRequest());
        out.flush();

        String read, response = "";
        while ( (read = in.readLine()) != null )
            response += read + "\n";

	    return response;

    }
    
    private String httpRequest() {
        return method + " /" + uri + " HTTP/1.1\r\n" +
                "Host: " + host + "\r\n" +
                "User-Agent: JBrower 0.0.1" + "\r\n" +
                "Accept-Encoding: deflate" + "\r\n" +
                "\r\n";
    }
}
