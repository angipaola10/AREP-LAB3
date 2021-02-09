package edu.escuelaing.arep.picosparkweb;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class PicoSpark {

    public static void get(String route, BiFunction<HttpRequest, HttpResponse, String> biFunction){
        PicoSparkServer pserver = PicoSparkServer.getInstance();
        pserver.get(route, biFunction);
    }

    public static void port(int port){
        PicoSparkServer pserver = PicoSparkServer.getInstance();
        pserver.setPort(port);
    }

}
