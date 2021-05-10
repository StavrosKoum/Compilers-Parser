public class Method_class
{
    private String method_name;

    //type
    public String type;
    //return type
    //arguments
    public String args;
    //etc
    //offset
    private int offset;

    public Method_class(String name,String type,String argumentList)
    {
        this.method_name = name;
        this.type = type;
        args = argumentList;
    }

    public void give_offset(int num)
    {
        offset = num;
    }

    public void print_meth_info()
    {
        System.out.println(type +" - "+method_name +"."+ offset+"  ("+ args+ ")");
    }
    
}