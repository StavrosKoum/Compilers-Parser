

public class Variable_class {
    int offset;
    //name
    public String var_name;
    //type
    public String type;
    //initialized
    public boolean init = false;
    public int num_id;
    public String method;

    public Variable_class(String name,String type,int var_offset,int count,String method)
    {
        this.num_id = count;
        this.var_name = name;
        this.type = type;
        this.offset = var_offset;
        this.method = method;
        System.out.println("variable -> "+type+var_name+"."+offset+"id-->"+num_id);
    }

    public void print_var_info()
    {
        System.out.println(type + " " +var_name+"."+offset );
    }
    
}
