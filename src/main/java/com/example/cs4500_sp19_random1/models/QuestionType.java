package com.example.cs4500_sp19_random1.models;

public enum QuestionType {

    TRUE_FALSE("TRUE_FALSE"), MULTIPLE("MULTIPLE"), RANGE("RANGE");

    private String type;

    QuestionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}