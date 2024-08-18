package com.smith.model;

public class FileComparisonResult {
    private boolean areSimilar;
    private String message;

    public FileComparisonResult(boolean areSimilar, String message) {
        this.areSimilar = areSimilar;
        this.message = message;
    }

    public boolean isAreSimilar() {
        return areSimilar;
    }

    public void setAreSimilar(boolean areSimilar) {
        this.areSimilar = areSimilar;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
