package org.OpenMoll.Semantic.Constructions.Expressions;

import org.OpenMoll.Parsing.Token;
import org.OpenMoll.Parsing.TokenTypes;
import org.OpenMoll.Semantic.Part;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Name extends Part {
    @Override
    public Token Analyze(Stack<Token> tokens) {
        Stack<Token> clone = (Stack<Token>) tokens.clone();
        Token token = new Token();
        token.setType(TokenTypes.Name);
        try {
            if (tokens.peek().getType() == TokenTypes.SimpleName || tokens.peek().getType() == TokenTypes.QualifiedName) {
                token.getTokens().addAll(tokens.peek().getTokens());
                Collections.reverse(token.getTokens());
                for (Token tkn : token.getTokens()) {
                    token.setValue(token.getValue()+tkn.getValue());
                }
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
