package org.OpenMoll;

import org.OpenMoll.Assembly.Assembly;
import org.OpenMoll.Parsing.Parser;
import org.OpenMoll.Parsing.Tokenizer;
import org.OpenMoll.Parsing.WordParser;
import org.OpenMoll.Semantic.SemanticGenerator;

public class Main {
    public static void main(String[] args) {
        Assembly assembly = new Assembly("D:\\IntelliJ IDEA Community Edition 2022.3.1\\projects\\OpenMoll\\example");
        Parser parser = new Parser();
        WordParser wordParser = new WordParser();
        Tokenizer tokenizer = new Tokenizer();
        SemanticGenerator semanticGenerator = new SemanticGenerator();
        parser.LoadResolve(assembly);
        wordParser.AnalyzeResolve(assembly);
        tokenizer.AnalyzeResolve(assembly);
        semanticGenerator.AnalyzeResolve(assembly);
    }
}