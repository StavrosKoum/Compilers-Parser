
import java.util.*;
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
    private String[] args_array;
    private List<String> args_list;

    public Method_class(String name,String type,String argumentList)
    {
        this.method_name = name;
        this.type = type;
        args = argumentList;
        args_array = args.split(",");
        args_array = args.split(" ");
        args_list = new ArrayList<String>(Arrays.asList(args_array));
        int l = args_array.length;
        if(l == 1)
        {
            //System.out.println("empty arg list");
            l= 0;
        }
       
        if(l ==2)
        {
            l=1;
            args_list.remove(args_list.size()-1);
        }
        else
        {
            l = l/4;
            for(int i = 0; i <l; i ++)
            {
                args_list.remove(args_list.size()-1);
                args_list.remove(args_list.size()-2);
            }
        }
        
        for(int i = 0; i <args_list.size(); i ++)
        {
            //System.out.println(args_list.get(i));
        }
       




      
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