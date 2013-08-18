package jbrowser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
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

    private String errorMsg;

    // Cookies
    private LinkedHashMap<String, String> cookies;
    
    Http(String url) {
        host = url.split("://")[1].split("/")[0];
        port = 80;
        method = "GET";
        uri = url.split("://")[1].split("/", 2)[1];
   }
    
    /**
     * Make an HTTP request through a socket.
     *
     * @return true if the request was successful, false otherwise
     */
    public boolean request() {
        
        try {

            socket = new Socket(host, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            loadCookies();

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
                    errorMsg = ex.getMessage();
                    return false;
                }
            }

            if (!ready) {
                errorMsg = "Timeout reached.";
                return false;
            }

            String[] header;

            // Status
            header = in.readLine().split(" ");
            if (header.length != 3) {
                errorMsg = "Bad-formed HTTP response: First chunk is wrong.";
                return false;
            }
            status = Integer.parseInt(header[1]);

            // Headers
            String read;
            headers = new LinkedHashMap<String, String>();
            while ( (ready = in.ready()) && !(read = in.readLine()).equals("") ) {
                if ( (header = read.split(": ", 2)).length != 2) {
                    errorMsg = "Bad-formed HTTP response: Header is wrong.";
                    return false;
                }
                processHeader(header[0], header[1]);
                headers.put(header[0], header[1]);
            }

            if (!ready) {
                errorMsg = "Bad-formed HTTP response: Headers are incomplete. ";
                return false;
            }

            saveCookies();

            // Content
            content = "";
            while (in.ready())
                content += in.readLine() + "\n";

        } catch (Exception e) {
	    errorMsg = e.getMessage();

            return false;
        }

        return true;
    }

    /**
     * If the HTTP was unsuccessful, then this error message describe the error 
     *
     * @return Message
     */
    public String getErrorMsg() {
        return errorMsg;
    }
    
    /**
     * Build the HTTP request to send.
     *
     * @return HTTP request
     */
    private String httpRequest() {
        return method + " /" + uri + " HTTP/1.1\r\n" +
                "Host: " + host + "\r\n" +
                "User-Agent: JBrower 0.0.1" + "\r\n" +
                "Accept-Encoding: deflate" + "\r\n" +
                cookies() +
                "\r\n";
    }

    /**
     * The body of the HTTP response
     *
     * @return body
     */
    public String getContent() {
        return content;
    }

    /**
     * The status of the HTTP response
     *
     * @return status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Query the value of one header of the HTTP response
     *
     * @param header name
     * @return header value
     */
    public String getHeader(String header) {
	return headers.get(header);
    }

    /**
     * Make additional operations with certain headers.
     *
     * For now, the only header being processed is "Set-Cookie"
     *
     * @param header name
     * @param header value
     */
    private void processHeader(String header, String value) {

        if (header.equals("Set-Cookie")) {
            String[] cookie = value.split(";")[0].split("=");

            // Consider empty cookies
            if (cookie.length == 1)
                cookies.put(cookie[0], "");
            else if (cookie.length == 2)
                cookies.put(cookie[0], cookie[1]);

        }
    }

    /**
     * Build the cookies part of the HTTP request
     *
     * @return cookies part of the HTTP request
     */
    private String cookies() {
        String cookiesToSend = "";

        if ( cookies.size() == 0 )
	    return "";

        cookiesToSend = "Cookie: ";

        int i = 0;
        for(String cookie : cookies.keySet()) {

            if (i != 0)
                cookiesToSend += "; ";

            cookiesToSend += cookie + "=" + cookies.get(cookie);

            i++;
        }

        cookiesToSend += "\r\n";

        return cookiesToSend;
    }

    /**
     * Save the cookies received in HTTP response in a file
     */
    private void saveCookies() {
        File cookiesDir = new File("cookies");

        if ( !cookiesDir.isDirectory() )
            cookiesDir.mkdir();

        BufferedWriter out = null;
        try {
            out = new BufferedWriter( new FileWriter("cookies/" + host, false) );

            for(String cookie : cookies.keySet()) {
                out.write(cookie + "=" + cookies.get(cookie) + " \n");
            }

            out.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Load any previous cookies saved in the cookies file.
     */
    private void loadCookies() {
	cookies = new LinkedHashMap<String, String>();
        FileReader f = null;

	if ( !( new File("cookies/" + host) ).isFile() )
	    return;

        try {
            f = new FileReader("cookies/" + host);

            BufferedReader in = new BufferedReader(f);
            String line;
            while ( ( line = in.readLine() ) != null ) {
                String[] cookie = line.split("=", 2);
                if (cookie.length == 2)
                    cookies.put(cookie[0], cookie[1]);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
