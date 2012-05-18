package controllers;

import play.mvc.Controller;

public class Demo extends Controller {

    public static void index() {
        render("index.html");
    }

}