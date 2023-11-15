package org.OpenMoll.Assembly;

import org.OpenMoll.Parsing.Token;

import java.util.ArrayList;

public class Assembly {
    private String diskLocation;

    private ArrayList<Character> letters;

    private ArrayList<String> strings;

    private ArrayList<Token> tokens;
    private ArrayList<Token> constructions;
    public Assembly(String diskLocation) {
        this.diskLocation = diskLocation;
        this.letters = new ArrayList<>();
        this.strings = new ArrayList<>();
        this.tokens = new ArrayList<>();
        this.constructions = new ArrayList<>();
    }

    public String getDiskLocation() {
        return diskLocation;
    }

    public void setDiskLocation(String diskLocation) {
        this.diskLocation = diskLocation;
    }

    public ArrayList<Character> getLetters() {
        return letters;
    }

    public void setLetters(ArrayList<Character> letters) {
        this.letters = letters;
    }

    public ArrayList<String> getStrings() {
        return strings;
    }

    public void setStrings(ArrayList<String> strings) {
        this.strings = strings;
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public ArrayList<Token> getConstructions() {
        return constructions;
    }

    public void setConstructions(ArrayList<Token> constructions) {
        this.constructions = constructions;
    }
}
