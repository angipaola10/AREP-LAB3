package edu.escuelaing.arep.picosparkweb;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import edu.escuelaing.arep.httpserver.HttpServer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class PicoSparkServer implements Processor{

    private static PicoSparkServer instance = new PicoSparkServer();
    private int port = 36000;
    Map< String, BiFunction<HttpRequest, HttpResponse, String> > functions = new HashMap<>();
    HttpServer hserver = new HttpServer();

    private PicoSparkServer(){
        hserver.registerProcessor("/Apps", this);
    }

    public static PicoSparkServer getInstance(){
        return instance;
    }

    public void get(String route, BiFunction<HttpRequest, HttpResponse, String> biFunction){
        functions.put(route, biFunction);
    }

    public void startServer(){
        try {
            hserver.startServer(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String handle(String route, HttpRequest req, HttpResponse resp) {
        if (functions.containsKey(route)){
            return validOkHttpHeader() + functions.get(route).apply(req, resp);
        }
        return validErrorHttpHeader();
    }

    private String validOkHttpHeader(){
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n";
    }

    private String validErrorHttpHeader(){
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>Not Found</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>404 Not Found</h1>\n"
                + "</body>\n"
                + "</html>\n";

    }



}
