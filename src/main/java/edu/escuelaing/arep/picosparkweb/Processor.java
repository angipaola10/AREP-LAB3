package edu.escuelaing.arep.picosparkweb;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

public interface Processor {

    String handle(String route, HttpRequest req, HttpResponse resp);
}
