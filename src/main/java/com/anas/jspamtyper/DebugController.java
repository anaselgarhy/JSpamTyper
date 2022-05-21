package com.anas.jspamtyper;

public class DebugController {
    private boolean debug;

    private static DebugController instance;
    private DebugController() {
        this.debug = false; // Disable debug by default
    }

    public static DebugController getInstance() {
        if (instance == null) {
            instance = new DebugController();
        }
        return instance;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean isDebug() {
        return debug;
    }

    public void printDebug(String message) {
        if (debug) {
            System.out.println(message);
        }
    }
}
