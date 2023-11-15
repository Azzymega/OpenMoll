package org.OpenMoll.Parsing;

import org.OpenMoll.Assembly.Assembly;

import java.util.Stack;

public class Tokenizer implements IAnalyzeConflict<Tokenizer, Assembly> {
    private final Stack<TokenizerState> state = new Stack<>();
    @Override
    public Tokenizer AnalyzeResolve(Assembly Object) {
        state.push(TokenizerState.UsingState);
        for (String value : Object.getStrings()) {
            switch (state.peek()) {
                case UsingState -> {
                    switch (value) {
                        case "using" -> {
                            Token token = new Token();
                            token.setValue(value);
                            token.setType(TokenTypes.Using);
                            Object.getTokens().add(token);
                        }
                        default -> {
                            Token token = new Token();
                            token.setValue(value);
                            token.setType(TokenTypes.UsingIdentifier);
                            Object.getTokens().add(token);
                        }
                        case "namespace" -> {
                            Token token = new Token();
                            token.setValue(value);
                            token.setType(TokenTypes.Namespace);
                            state.push(TokenizerState.NamespaceState);
                            Object.getTokens().add(token);
                        }
                        case ";" -> {
                            Token token = new Token();
                            token.setValue(value);
                            token.setType(TokenTypes.Semicolon);
                            Object.getTokens().add(token);
                        }
                    }
                }
                case ClassState -> {
                    switch (value) {
                        default -> {
                            Token token = new Token();
                            token.setValue(value);
                            token.setType(TokenTypes.ClassIdentifier);
                            Object.getTokens().add(token);
                        }
                        case "{" -> {
                            Token token = new Token();
                            token.setValue(value);
                            token.setType(TokenTypes.InternalStart);
                            state.push(TokenizerState.ClassMemberState);
                            Object.getTokens().add(token);
                        }
                        case "}" -> {
                            Token token = new Token();
                            token.setValue(value);
                            token.setType(TokenTypes.InternalEnd);
                            state.pop();
                            Object.getTokens().add(token);
                        }
                        case "[" -> {
                            Token token = new Token();
                            token.setValue(value);
                            token.setType(TokenTypes.ArrayStart);
                            Object.getTokens().add(token);
                        }
                        case "]" -> {
                            Token token = new Token();
                            token.setValue(value);
                            token.setType(TokenTypes.ArrayEnd);
                            Object.getTokens().add(token);
                        }
                    }
                }
                case NamespaceState -> {
                    switch (value) {
                        default -> {
                            Token token = new Token();
                            token.setValue(value);
                            token.setType(TokenTypes.NamespaceIdentifier);
                            Object.getTokens().add(token);
                        }
                        case "}" -> {
                            Token token = new Token();
                            token.setValue(value);
                            token.setType(TokenTypes.InternalEnd);
                            state.pop();
                            Object.getTokens().add(token);
                        }
                        case "{" -> {
                            Token token = new Token();
                            token.setValue(value);
                            token.setType(TokenTypes.Class);
                            state.push(TokenizerState.ClassState);
                            Object.getTokens().add(token);
                        }
                    }
                }
                case ClassMemberState -> {
                    switch (value) {
                        case "}" -> {
                            Token token = new Token();
                            token.setValue(value);
                            token.setType(TokenTypes.InternalEnd);
                            state.pop();
                            Object.getTokens().add(token);
                        }
                        case "." -> {
                            Token token = new Token();
                            token.setValue(value);
                            token.setType(TokenTypes.Qualifier);
                            Object.getTokens().add(token);
                        }
                        case "public" -> {
                            Token token = new Token();
                            token.setValue(value);
                            token.setType(TokenTypes.PublicFlag);
                            Object.getTokens().add(token);
                        }
                        case "private" -> {
                            Token token = new Token();
                            token.setValue(value);
                            token.setType(TokenTypes.PrivateFlag);
                            Object.getTokens().add(token);
                        }
                        case "protected" -> {
                            Token token = new Token();
                            token.setValue(value);
                            token.setType(TokenTypes.ProtectedFlag);
                            Object.getTokens().add(token);
                        }
                        case "[" -> {
                            Token token = new Token();
                            token.setValue(value);
                            token.setType(TokenTypes.ArrayStart);
                            Object.getTokens().add(token);
                        }
                        case "]" -> {
                            Token token = new Token();
                            token.setValue(value);
                            token.setType(TokenTypes.ArrayEnd);
                            Object.getTokens().add(token);
                        }
                        case "=", "&=", ">>>=", ">>=", "<<=", "-=", "*=", "/=", "%=", "+=", "^=", "|=" -> {
                            Token token = new Token();
                            token.setValue(value);
                            token.setType(TokenTypes.AssignmentOperator);
                            Object.getTokens().add(token);
                        }
                        default -> {
                            boolean isWord = false;
                            for (char c : value.toCharArray()) {
                                if(!Character.isUpperCase(Character.toUpperCase(c))) {
                                    isWord = false;
                                    break;
                                }
                                else {
                                    isWord = true;
                                }
                            }

                            if (isWord) {
                                Token token = new Token();
                                token.setValue(value);
                                token.setType(TokenTypes.Identifier);
                                Object.getTokens().add(token);
                            }
                            else {
                                Token token = new Token();
                                token.setValue(value);
                                token.setType(TokenTypes.Undef);
                                Object.getTokens().add(token);
                            }
                        }
                    }
                }
                case FunctionState -> {
                    switch (value) {
                        case "this" -> {
                            Token token = new Token();
                            token.setValue(value);
                            token.setType(TokenTypes.This);
                            Object.getTokens().add(token);
                        }
                    }
                }
            }
        }
        return null;
    }
}

