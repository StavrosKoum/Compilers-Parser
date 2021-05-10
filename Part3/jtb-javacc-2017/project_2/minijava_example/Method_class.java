public class Method_class
{
    private String method_name;

    //type
    //return type
    //arguments
    //etc
    //offset
    private int offset;

    public Method_class(String name)
    {
        this.method_name = name;
    }

    public void give_offset(int num)
    {
        offset = num;
    }

    public void print_meth_info()
    {
        System.out.println(method_name +"."+ offset);
    }
}