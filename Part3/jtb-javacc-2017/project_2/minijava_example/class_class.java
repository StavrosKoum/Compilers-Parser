
import java.util.HashMap;

public class class_class
{
    public String class_name;
    //hashmap methods
    private HashMap <String,Method_class> Methods_Table = new HashMap <String,Method_class>();
    //hashmap var
    private HashMap <String,Variable_class> Variables_Table = new HashMap <String,Variable_class>();
    //method offset
    private int meth_offset = -8;

    public class_class(String name)
    {
        this.class_name = name;
    }

    //more to insert here
    public void Insert_Method_MethTable(String method_name,String method_type,String argumentList) throws Exception
    {
        if(Methods_Table.get(method_name) != null)
        {
            throw new Exception("Function" + method_name + " has already been declared");
        }
        else
        {
            //System.out.println("Insert method->" + method_name + " at class " + class_name);
            //create method_class here to instert other declarations later
            Method_class test = new Method_class(method_name,method_type,argumentList);
            this.meth_offset = meth_offset+8;
            test.give_offset(meth_offset);
            Methods_Table.put(method_name,test);
            //System.out.println( test.method_name);
            test = Methods_Table.get(method_name);
            test.print_meth_info();
            
        }
    }

    //same with the above but does not update offset, it takes it as arg
    public void Ex_Insert_Method_MethTable(String method_name,String method_type,String argumentList,int off) throws Exception
    {
        if(Methods_Table.get(method_name) != null)
        {
            throw new Exception("Function" + method_name + " has already been declared");
        }
        else
        {
            //System.out.println("Insert method->" + method_name + " at class " + class_name);
            //create method_class here to instert other declarations later
            Method_class test = new Method_class(method_name,method_type,argumentList);
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
    
    public boolean Search_for_override_meth(String method_name,String method_type,String argumenString) throws Exception
    {
        if(Methods_Table.get(method_name) != null)
        {
            //check if type and args are the same
            Method_class temp = Methods_Table.get(method_name);
            if((temp.type.equals(method_type)) && temp.args.equals(argumenString))
            {
                System.out.println("Found same method");
            
                //temp = Methods_Table.get(method_name);
                System.out.print(class_name +"->");
                temp.print_meth_info();
                return true;
                
            }
            throw new Exception("Function " + method_name + " has different type or args");
            
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