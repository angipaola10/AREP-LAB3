package edu.escuelaing.arep.picosparkweb;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public interface Processor {

    String handle(String route, HttpRequest req, HttpResponse resp);
}
