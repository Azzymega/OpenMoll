package org.OpenMoll.Semantic;

import org.OpenMoll.Assembly.Assembly;
import org.OpenMoll.Parsing.IAnalyzeConflict;
import org.OpenMoll.Parsing.Token;
import org.OpenMoll.Semantic.Constructions.Expressions.*;

import java.util.ArrayList;
import java.util.Stack;

public class SemanticGenerator implements IAnalyzeConflict<SemanticGenerator, Assembly> {
    private ArrayList<Part> evaluations = new ArrayList<>(); // разделить на несколько массивов по состояниям (парсинг поля, функции и т.д.)
    public SemanticGenerator() {
        evaluations.add(new SimpleName());
        evaluations.add(new Name());
        evaluations.add(new QualifiedName());
        evaluations.add(new ArrayAccess());
        evaluations.add(new Assignment());
        evaluations.add(new AssignmentExpression());
        evaluations.add(new ConditionalExpression());
        evaluations.add(new Expression());
        evaluations.add(new LeftHandSide());
    }

    public Stack<Token> Analyze(Stack<Token> tokens) {
        Stack<Token> clone = (Stack<Token>) tokens.clone();
        for (Part part : evaluations) {
            Token token = part.Analyze(tokens);
            if (token != null) {
                tokens.add(token);
                tokens = Analyze(tokens);
            }
            else {
                tokens = (Stack<Token>) clone.clone();
            }
            clone = (Stack<Token>) tokens.clone();
        }
        return tokens;
    }
    @Override
    public SemanticGenerator AnalyzeResolve(Assembly Object) {
        Stack<Token> tokens = new Stack<>();
        for (Token tkn : Object.getTokens()) {
            tokens.add(tkn);
            tokens = Analyze(tokens);
        }
        Object.getConstructions().addAll(new ArrayList<Token>(tokens));
        return null;
    }
}
