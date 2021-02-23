package edu.escuelaing.arep.httpserver;

import edu.escuelaing.arep.picosparkweb.Processor;
import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class HttpServer {

    private int port;

    Map<String, Processor> routesToprocessors = new HashMap<>();

    public void startServer(int port) throws IOException {
        this.port = port;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(getPort());
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + this.port);
            System.exit(1);
        }
        boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir en el puerto " +
                        "" + this.port + "...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            boolean isFirstLine = true;
            String route = "";
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Recibí: " + inputLine);
                if (isFirstLine){
                    System.out.println(inputLine);
                    route = inputLine.split(" ")[1];
                    isFirstLine = false;
                }
                if (!in.ready()) {
                    break;
                }
            }
            String response = "";
            for (String key: routesToprocessors.keySet()){
                if ( route.startsWith(key) ) {
                    System.out.println(route.substring(key.length()));
                    response = routesToprocessors.get(key).handle(route.substring(key.length()), null, null);
                }
            }

            if (response != "" ){
                outputLine = response;
            }else{
                outputLine = validOkHttpResponse();
            }
            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort(){
        return port;
    }

    public void registerProcessor(String s, Processor processor) {
        routesToprocessors.put(s, processor);
    }

    private String validOkHttpResponse(){
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>Default response</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Default response</h1>\n"
                + "</body>\n"
                + "</html>\n";
    }
}