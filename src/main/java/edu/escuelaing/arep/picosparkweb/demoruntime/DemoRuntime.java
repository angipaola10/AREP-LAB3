package edu.escuelaing.arep.picosparkweb.demoruntime;

import edu.escuelaing.arep.picosparkweb.PicoSparkServer;

import static edu.escuelaing.arep.picosparkweb.PicoSpark.*;

public class DemoRuntime
{
    public static void main( String[] args ){
        port(getPort());
        get("/hello", (req, resp) -> "Hello world LAMBDA!");
        startServer();
    }

    public static void startServer(){
        PicoSparkServer pserver = PicoSparkServer.getInstance();
        pserver.startServer();
    }

    public static int getPort() {
        if(System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 3344;
    }

}
