package org.OpenMoll.Semantic.Constructions.Expressions;

import org.OpenMoll.Parsing.Token;
import org.OpenMoll.Parsing.TokenTypes;
import org.OpenMoll.Semantic.Part;

import java.util.Stack;

public class ArrayAccess extends Part {
    @Override
    public Token Analyze(Stack<Token> tokens) {
        Stack<Token> clone = (Stack<Token>) tokens.clone();
        Token token = new Token();
        try {
            if (tokens.peek().getType() == TokenTypes.Name) {
                token.getTokens().add(tokens.peek());
                tokens.pop();
            }
            else {
                tokens = (Stack<Token>) clone.clone();
                return null;
            }
            if (tokens.peek().getType() == TokenTypes.ArrayStart) {
                token.getTokens().add(tokens.peek());
                tokens.pop();
            }
            else {
                tokens = (Stack<Token>) clone.clone();
                return null;
            }
            if (tokens.peek().getType() == TokenTypes.Expression) {
                token.getTokens().add(tokens.peek());
                tokens.pop();
            }
            else {
                tokens = (Stack<Token>) clone.clone();
                return null;
            }
            if (tokens.peek().getType() == TokenTypes.ArrayEnd) {
                token.getTokens().add(tokens.peek());
                //token.setValue(tokens.peek().getValue());
                token.setType(TokenTypes.QualifiedName);
                tokens.pop();
                return token;
            }
            else {
                tokens = (Stack<Token>) clone.clone();
                return null;
            }
        }
        catch (Exception ex) {
            tokens = (Stack<Token>) clone.clone();
            return null;
        }
    }
}
