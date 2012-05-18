package controllers;

import play.mvc.Controller;

public class TagTestController extends Controller {

    public static void showTagTestPage(){
        render("TagTest/tagTestPage.html");
    }
}
