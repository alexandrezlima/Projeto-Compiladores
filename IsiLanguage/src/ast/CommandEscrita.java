package ast;

import datastructures.IsiVariable;


public class CommandEscrita extends AbstractCommand{

    private String id;
    private IsiVariable var; 
    
    public CommandEscrita(String id, IsiVariable var) {
        this.id = id;
        this.var = var;
    }
    
    @Override
    public String generateJavaCode() {
        return "System.out.println(" + id + ");";
    }

    @Override
    public String toString() {
        return "CommandEscrita{" + "id=" + id + '}';
    }

    @Override
    public String generateCCode() {
        if (var.getType()==IsiVariable.NUMBER) {
            return ("printf(\"%f\", " + id +");");
        } else {
            return ("printf(\"%s\", " + id + ");");
        }
    }
    
    
}
