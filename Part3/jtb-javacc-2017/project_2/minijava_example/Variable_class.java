

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
    public boolean to_be_printed;
    

    public Variable_class(String name,String type,int var_offset,int count,String method,boolean print)
    {
        this.num_id = count;
        this.var_name = name;
        this.type = type;
        this.offset = var_offset;
        this.method = method;
        this.to_be_printed = print;
        //System.out.println("variable -> "+type+var_name+"."+offset+"id-->"+num_id);
    }

    public void print_var_info()
    {
        if(to_be_printed)
        System.out.println(var_name+"."+offset );
    }
    
}
