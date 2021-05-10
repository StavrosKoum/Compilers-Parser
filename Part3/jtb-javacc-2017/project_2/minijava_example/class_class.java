
import java.util.HashMap;

public class class_class
{
    public String class_name;
    //hashmap methods
    private HashMap <String,Method_class> Methods_Table = new HashMap <String,Method_class>();
    //hashmap metavlites
    //method offset
    private int meth_offset = -8;

    public class_class(String name)
    {
        this.class_name = name;
    }

    //more to insert here
    public void Insert_Method_MethTable(String method_name) throws Exception
    {
        if(Methods_Table.get(method_name) != null)
        {
            throw new Exception("Function" + method_name + " has already been declared");
        }
        else
        {
            //System.out.println("Insert method->" + method_name + " at class " + class_name);
            //create method_class here to instert other declarations later
            Method_class test = new Method_class(method_name);
            this.meth_offset = meth_offset+8;
            test.give_offset(meth_offset);
            Methods_Table.put(method_name,test);
            //System.out.println( test.method_name);
            test = Methods_Table.get(method_name);
            test.print_meth_info();
            
        }
    }

    public void Ex_Insert_Method_MethTable(String method_name,int off) throws Exception
    {
        if(Methods_Table.get(method_name) != null)
        {
            throw new Exception("Function" + method_name + " has already been declared");
        }
        else
        {
            //System.out.println("Insert method->" + method_name + " at class " + class_name);
            //create method_class here to instert other declarations later
            Method_class test = new Method_class(method_name);
            test.give_offset(off);
            Methods_Table.put(method_name,test);
            //System.out.println( test.method_name);
            test = Methods_Table.get(method_name);
            test.print_meth_info();
            
        }
    }

    public  void Print_MethKeys()
    {
        System.out.println(Methods_Table.keySet());
    }
    
    public boolean Search_for_override_meth(String method_name)
    {
        if(Methods_Table.get(method_name) != null)
        {
            System.out.println("method updated");
            //Methods_Table.replace(method_name,);
            return true;
        }
        else
        {
            return false;
        }

    }

    public int give_and_update_meth_offset()
    {
        meth_offset = meth_offset + 8;
        return meth_offset;
    }
}