grammar IsiLang;

@header {
    import datastructures.*;
    import exceptions.*;
    import ast.*;
    import java.util.ArrayList;
    import java.util.Stack;
    
}

@members{
    private int _tipo;
    private String _varName;
    private String _varValue;
    private IsiSymbolTable symbolTable = new IsiSymbolTable();
    private IsiSymbol symbol;
    private IsiProgram program = new IsiProgram();
    private ArrayList<AbstractCommand> currentThread;
    private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
    private String _readID;
    private String _writeID;
    private String _exprID;
    private String _exprContent;
    private String _exprDecision;
    private ArrayList<AbstractCommand> listaTrue;
    private ArrayList<AbstractCommand> listaFalse;


    public void verificaID(String id) {
        if (!symbolTable.exists(id)) {
            throw new IsiException("Symbol \"" + id + "\" not declared.");
        }
    }

   public void exibeComandos () {
        for (AbstractCommand c: program.getComandos()) {
            System.out.println(c);
        }
   }

    public void generateCode() {
        program.generateTarget();
    }
}


prog    : 'programa' decl bloco 'fimprog.'
            { 
                program.setVarTable(symbolTable);
                program.setComandos(stack.pop());
            }
        ;

decl    : (declaravar)+
        ;

declaravar  :   tipo ID {   _varName = _input.LT(-1).getText();
                            _varValue = null;
                            symbol = new IsiVariable(_varName, _tipo, _varValue);
                            if (!symbolTable.exists(_varName)) {
                                symbolTable.add(symbol);
                            } else {
                                throw new IsiException("Symbol " + symbol + " already declared.");
                            }
                        }
                (   VIR 
                    ID  {   _varName = _input.LT(-1).getText();
                            _varValue = null;
                            symbol = new IsiVariable(_varName, _tipo, _varValue);
                            if (!symbolTable.exists(_varName)) {
                                symbolTable.add(symbol);
                            } else {
                                throw new IsiException("Symbol " + symbol + " already declared.");
                            }
                        }
                )* SC
            ;

tipo    : 'numero'  { _tipo = IsiVariable.NUMBER; }
        | 'texto'   { _tipo = IsiVariable.TEXT; }
        ;

bloco   :   {   currentThread = new ArrayList<AbstractCommand>();
                stack.push(currentThread);
            }
            (cmd)+
        ;

cmd     : cmdleitura 
        | cmdescrita 
        | cmdattrib
        | cmdselecao
        ;


cmdleitura  : 'leia'    AP
                        ID { 
                            verificaID(_input.LT(-1).getText());
                            _readID = _input.LT(-1).getText();
                           }
                        FP
                        SC
                {
                    IsiVariable var = (IsiVariable) symbolTable.get(_readID);
                    CommandLeitura cmd = new CommandLeitura(_readID, var);
                    stack.peek().add(cmd);
                }
            ;

cmdescrita  : 'escreva' AP
                        ID {
                                verificaID(_input.LT(-1).getText());
                                _writeID =_input.LT(-1).getText();
                           }
                        FP
                        SC
                {
                    CommandEscrita cmd = new CommandEscrita(_writeID);
                    stack.peek().add(cmd);
                }
            ;


cmdattrib   :   ID { 
                        verificaID(_input.LT(-1).getText());
                        _exprID = _input.LT(-1).getText();
                   }
                ATTR { _exprContent = ""; }
                expr
                SC  {
                        CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
                        stack.peek().add(cmd);
                    }
            ;

cmdselecao  :   'se'    AP 
                        ID {_exprDecision = _input.LT(-1).getText(); }
                        OPREL {_exprDecision += _input.LT(-1).getText(); }
                        (ID | NUMBER) {_exprDecision += _input.LT(-1).getText(); }
                        FP 
                        ACH 
                        {   currentThread = new ArrayList<AbstractCommand>();
                            stack.push(currentThread);
                        }
                        (cmd)+ 
                        FCH
                        {
                            listaTrue = stack.pop();
                        }
                ('senao'    ACH 
                            {
                                currentThread = new ArrayList<AbstractCommand>();
                                stack.push(currentThread);
                            }
                            (cmd+) 
                            FCH
                            {
                                listaFalse = stack.pop();
                                CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
                                stack.peek().add(cmd);
                            }
                            )?
            ;

expr    :   termo (
            OP { _exprContent += _input.LT(-1).getText(); }
            termo
            )*
        ;


termo   : ID    {
                    verificaID(_input.LT(-1).getText());
                    _exprContent += _input.LT(-1).getText();
                }
        | (NUMBER | TEXT) {
                    _exprContent += _input.LT(-1).getText();
                 }
        ;

AP  :   '('
    ;

FP  :   ')'
    ;

SC  :   '.'
    ;

OP  : '+' | '-' | '*' | '/'
    ;

ATTR    : ':='
        ;

VIR     : ','
        ;

ACH     : '{'
        ;

FCH     : '}'
        ;

OPREL   : '>' | '<' | '>=' | '<=' | '==' | '!='
        ;

ID  : [a-z] ([a-z] | [A-Z] | [0-9])*
    ;

NUMBER  : [0-9]+ ('.' [0-9]+)?
        ;

TEXT   : ['"'] ([a-z] | [A-Z] | [0-9] | ' ' | '\t' | '!' | [#-/])* ['"']
       ;

WS  : (' ' | '\t' | '\n' | '\r') -> skip
    ;