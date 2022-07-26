package ast;

import java.util.ArrayList;


public class CommandTrocar extends AbstractCommand {
    private String condition;
    private ArrayList<AbstractCommand> listaTrue;
    private ArrayList<AbstractCommand> listaFalse;
    
    public CommandTrocar(String condition, ArrayList<AbstractCommand> lt, ArrayList<AbstractCommand> lf) {
        this.condition = condition;
        this.listaTrue = lt;
        this.listaFalse = lf;
    }

    public CommandTrocar(String condition, ArrayList<AbstractCommand> lt) {
        this.condition = condition;
        this.listaTrue = lt;
    }

    @Override
    public String generateJavaCode() {
        StringBuilder str = new StringBuilder();
        str.append("switch (" + condition + ") {\n        ");
        for (AbstractCommand cmd: listaTrue) {
            str.append("        " + cmd.generateJavaCode() + "\n");
        }
        if (listaFalse!= null){
            if (listaFalse.size() >= 0) {
                str.append("        default:\n        ");
                for (AbstractCommand cmd: listaFalse) {
                str.append("        " + cmd.generateJavaCode());
                }
            }
        }
        str.append("        }\n");
        return str.toString();
    }

    @Override
    public String toString() {
        return "CommandTrocar{" + "condition=" + condition + ", listaTrue=" + /*listaTrue*/ + '}';
    }

    
    @Override
    public String generateCCode() {
        StringBuilder str = new StringBuilder();
        str.append("switch (" + condition + ") {\n        ");
        for (AbstractCommand cmd: listaTrue) {
            str.append("        " + cmd.generateCCode() + "\n");
        }
        if (listaFalse!= null){
            if (listaFalse.size() >= 0) {
                str.append("        default:\n        ");
                for (AbstractCommand cmd: listaFalse) {
                str.append("        " + cmd.generateCCode());
                }
            }
        }
        str.append("        }\n");
        return str.toString();
    }
    
}
