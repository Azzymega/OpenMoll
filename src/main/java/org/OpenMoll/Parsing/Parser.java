package org.OpenMoll.Parsing;

import org.OpenMoll.Assembly.Assembly;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Parser implements ILoadConflict<Parser, Assembly> {
    public Parser LoadResolve(Assembly Object) {
        FileReader reader;
        try {
            reader = new FileReader(Object.getDiskLocation());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int c;
        while (true) {
            try {
                if ((c = reader.read()) == -1) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Object.getLetters().add(new Character((char) c));
        }
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this;
    }
}