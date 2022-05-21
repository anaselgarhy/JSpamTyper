package com.anas.jspamtyper;

import com.anas.jspamtyper.arguments.ArgumentsProcessor;

import java.awt.*;

public class MainController {
    public static final String VERSION = "JSpamTyper v1.0";
    private TypeController typeController;

    public MainController(String... args) {
        init();
        new ArgumentsProcessor(this).process(args);
        try {
            Thread.sleep(5000); // Wait for 5 seconds to put the focus on the window
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        typeController.start();
    }

    private void init() {
        try {
            typeController = new TypeController();
        } catch (AWTException e) {
            e.printStackTrace();
            System.exit(1); // Exit with error code 1
        }
    }


    public TypeController getTyperController() {
        return typeController;
    }
}
