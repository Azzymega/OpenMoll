package org.OpenMoll.Semantic.Constructions.Expressions;

import org.OpenMoll.Parsing.Token;
import org.OpenMoll.Parsing.TokenTypes;
import org.OpenMoll.Semantic.Part;

import java.util.Stack;

public class AssignmentExpression extends Part {
    @Override
    public Token Analyze(Stack<Token> tokens) {
        Stack<Token> clone = (Stack<Token>) tokens.clone();
        Token token = new Token();
        token.setType(TokenTypes.AssignmentExpression);
        try {
            if (tokens.peek().getType() == TokenTypes.ConditionalExpression || tokens.peek().getType() == TokenTypes.Assignment) {
                token.getTokens().add(tokens.peek());
                token.setValue(tokens.peek().getValue());
                tokens.pop();
                return token;
            }
        }
        catch (Exception ex) {
            tokens = (Stack<Token>) clone.clone();
            return null;
        }
        tokens = (Stack<Token>) clone.clone();
        return null;
    }
}
