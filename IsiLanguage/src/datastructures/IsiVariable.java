package datastructures;


public class IsiVariable extends IsiSymbol {
    
    public static final int NUMBER  = 0;
    public static final int TEXT    = 1;
    
    private int type;
    private String value;
    private int usage;
    
    
    public IsiVariable (String name, int type, String value) {
        super(name);
        this.type = type;
        this.value = value;
        this.usage = 0;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    public void registerUsage() {
        this.usage++;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public int getUsage() {
        return usage;
    }

    @Override
    public String toString() {
        return "IsiVariable{" +  "name=" + name  + ", type=" + type + ", value=" + value + '}';
    }
    
    public String generateJavaCode() {
        String str;
        if (type == NUMBER) {
            str = "double ";
        } else {
            str = "String ";
        }
        
        return str + " " + super.name + ';';
    }
    
    public String generateCCode() {
        String str;
        String extra = "";
        if (type == NUMBER) {
            str = "float ";
        } else {
            str = "char *";
            extra = "[1000]"; //Adicionado uma string de tamanho fixo para simplificar.
        }
        
        return str + super.name + ';';
    }
}
