
import java.util.HashMap;

public class class_class
{
    public String class_name;
    //hashmap methods
    private HashMap <String,Method_class> Methods_Table = new HashMap <String,Method_class>();
    //hashmap metavlites

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
            System.out.println("Insert " + method_name + " and create method_class");
            //create method_class here to instert other declarations later
            Method_class test = new Method_class(method_name);
            
            Methods_Table.put(method_name,test);
            System.out.println("alalalalala" + Methods_Table.get(method_name));
            
        }
    }

    public  void Print_MethKeys()
    {
        System.out.println(Methods_Table.keySet());
    }



}