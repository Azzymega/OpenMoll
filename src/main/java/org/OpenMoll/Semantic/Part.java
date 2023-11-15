package org.OpenMoll.Semantic;

import org.OpenMoll.Parsing.Token;

import java.util.Stack;

public abstract class Part {
    public abstract Token Analyze(Stack<Token> tokens);
}
