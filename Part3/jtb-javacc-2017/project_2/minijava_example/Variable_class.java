

public class Variable_class {
    int offset;
    //name
    private String var_name;
    //type
    public String type;
    //initialized
    public boolean init = false;

    public Variable_class(String name,String type,int var_offset)
    {
        this.var_name = name;
        this.type = type;
        this.offset = var_offset;
        System.out.println("variable -> "+type+var_name+"."+offset);
    }

    public void print_var_info()
    {
        System.out.println(type + " " +var_name+"."+offset );
    }
    
}
