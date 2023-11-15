package org.OpenMoll.Parsing;

import java.util.ArrayList;

public class Token {
    private String value = "";
    private TokenTypes type;
    private ArrayList<Token> tokens = new ArrayList<>();

    @Override
    public String toString() {
        return type.toString() + ":                         "+value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TokenTypes getType() {
        return type;
    }

    public void setType(TokenTypes type) {
        this.type = type;
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }
}
