package edu.escuelaing.arep.picosparkweb;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

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
