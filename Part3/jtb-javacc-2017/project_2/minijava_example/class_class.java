
import java.util.HashMap;

public class class_class
{
    public String class_name;
    //hashmap methods
    public HashMap <String,Method_class> Methods_Table = new HashMap <String,Method_class>();
    //hashmap var
    public HashMap <String,Variable_class> Variables_Table = new HashMap <String,Variable_class>();
    //method offset
    private int meth_offset = -8;
    private int var_offset = 0;
    public int num_id;
    private int var_count= 0;
    private int meth_count = 0;
    public String ex_class;


    public class_class(String name,int counter)
    {
        this.class_name = name;
        this.num_id = counter;
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
            Method_class test = new Method_class(method_name,method_type,argumentList,meth_count);
            this.meth_count ++;
            this.meth_offset = meth_offset+8;
            test.give_offset(meth_offset);
            Methods_Table.put(method_name,test);

            //System.out.println( test.method_name);
            test = Methods_Table.get(method_name);
            //test.print_meth_info();
            
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
            Method_class test = new Method_class(method_name,method_type,argumentList,meth_count);
            this.meth_count ++;
            test.give_offset(off);
            Methods_Table.put(method_name,test);
            //System.out.println( test.method_name);
            test = Methods_Table.get(method_name);
            //test.print_meth_info();
            
        }
    }

    public  void Print_MethKeys()
    {
        //System.out.println(Methods_Table.keySet());
    }
    
    public boolean Search_for_override_meth(String method_name,String method_type,String argumenString) throws Exception
    {
        if(Methods_Table.get(method_name) != null)
        {
            //check if type and args are the same
            Method_class temp = Methods_Table.get(method_name);
            if((temp.type.equals(method_type)) && temp.args.equals(argumenString))
            {
                //System.out.println("Found same method");
            
                //temp = Methods_Table.get(method_name);
                //System.out.print(class_name +"->");
                //temp.print_meth_info();
                return true;
                
            }
            throw new Exception("Function " + method_name + " has different type or args");
            
        }
        else
        {
            return false;
        }

    }

    public  void  Insert_Variable_VarTable(String id,String var_type,String method) throws Exception
    {
        //System.out.print(class_name+"____________________________________________________________________-");
        Variable_class temp;
        temp = Variables_Table.get(id+method);
        if(temp  != null  )
        {
            throw new Exception("Var " + id + " has already been declared");
        }
        //System.out.print(class_name+"-");

        boolean prnt = false;
        if(method.equals("class"))
        prnt = true;
        temp = new Variable_class(id,var_type,var_offset,var_count,method,prnt);
        this.var_count ++;
        Variables_Table.put(id+method,temp);
        
        if(var_type.equals("int"))
        {
            var_offset = var_offset + 4;
        }
        else if(var_type.equals("boolean"))
        {
            var_offset = var_offset + 1;
        }
        else
        {
            var_offset = var_offset + 8;
        }
    }




    
    public  void  Ex_Insert_Variable_VarTable(String id,String var_type,int offset,String method) throws Exception
    {
        Variable_class temp;
        temp = Variables_Table.get(id+method);
        if(temp  != null  )
        {
            throw new Exception("Var " + id + " has already been declared");
        }

        //System.out.print(class_name+"--------------");

        boolean prnt = false;
        if(method.equals("class"))
        prnt = true;
        temp = new Variable_class(id,var_type,offset,var_count,method,prnt);
        this.var_count ++;
        Variables_Table.put(id+method,temp);
    }

    public  int Mother_offset(String var_type)
    {
        int ret = var_offset;
        if(var_type.equals("int"))
        {
            var_offset = var_offset + 4;
        }
        else if(var_type.equals("boolean"))
        {
            var_offset = var_offset + 1;
        }
        else
        {
            var_offset = var_offset + 8;
        }
        return ret;

    }






    public int give_and_update_meth_offset()
    {
        meth_offset = meth_offset + 8;
        return meth_offset;
    }
    
    public void print_all()
    {
        Variable_class var_value= null;
        
        Method_class  meth_value = null;

        int num = 0;

        
        while(num < var_count)
        {
            
            for(String key: Variables_Table.keySet())
            {
                var_value = Variables_Table.get(key);
                //System.out.print(var_value.num_id);
                //var_value.print_var_info();
                // value.print_all();
                if(var_value.num_id == num)
                {
                    if(var_value.to_be_printed)
                    System.out.print(class_name+".");
                    var_value.print_var_info();
                    num++;
                }
                

            }
            
        }
        
        num = 0;
        while(num < meth_count)
        {
            for(String key: Methods_Table.keySet())
            {
               
                meth_value = Methods_Table.get(key);
                if(meth_value.num_id == num)
                {
                    // value.print_all();
                    System.out.print(class_name+".");
                    meth_value.print_meth_info();
                    num++;
                }
                
                

            }
        }
        
        
    }




}