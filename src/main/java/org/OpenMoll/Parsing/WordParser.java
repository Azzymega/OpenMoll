package org.OpenMoll.Parsing;

import org.OpenMoll.Assembly.Assembly;

public class WordParser implements IAnalyzeConflict<WordParser, Assembly> {
    @Override
    public WordParser AnalyzeResolve(Assembly Object) {
        String buffer = "";
        for (Character data : Object.getLetters()) {
            if (Character.isWhitespace(data)) {
                if (buffer.isEmpty()) {
                    continue;
                }
                Object.getStrings().add(buffer);
                buffer = "";
            }
            else if (Character.isISOControl(data)) {
            }
            else if (data == ';' || data == '(' || data == ')' || data == '+' || data == '*' || data == '/' || data == '%' || data == '.' || data == '[' || data == ']') {
                if (!buffer.isEmpty()) {
                    Object.getStrings().add(buffer);
                }
                buffer = "";
                Object.getStrings().add(data.toString());
            }
            else {
                buffer+=data;
            }
        }
        if (!buffer.isEmpty()) {
            Object.getStrings().add(buffer);
        }
        return null;
    }
}
