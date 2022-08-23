package ast;

import java.util.ArrayList;

public class CommandCase extends AbstractCommand {
    private String condition;
    private ArrayList<AbstractCommand> listaTrue;
    
    public CommandCase(String condition, ArrayList<AbstractCommand> lt) {
        this.condition = condition;
        this.listaTrue = lt;
    }

    @Override
    public String generateJavaCode() {
        StringBuilder str = new StringBuilder();
        str.append("case " + condition + ":\n");
        for (AbstractCommand cmd: listaTrue) {
            str.append("        " + cmd.generateJavaCode() + "\n");
        }
        str.append("        break;\n");
        
        return str.toString();
    }

    @Override
    public String toString() {
        return "CommandLoop{" + "condition=" + condition + ", listaTrue=" + listaTrue + '}';
    }

    @Override
    public String generateCCode() {
        StringBuilder str = new StringBuilder();
        str.append("case (" + condition + "):\n");
        for (AbstractCommand cmd: listaTrue) {
            str.append("        " + cmd.generateCCode() + "\n");
        }
        str.append("        break;\n");
        
        return str.toString();
    }
}
