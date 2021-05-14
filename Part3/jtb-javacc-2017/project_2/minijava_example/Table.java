import visitor.GJDepthFirst;

import java.util.HashMap;


import syntaxtree.*;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class Table extends GJDepthFirst<String,Void>
{
    private HashMap <String,class_class> Table = new HashMap <String,class_class>();
    private String temp_class;
    private String temp_extended_class = null;
    private String temp_meth;
    private int counter = 0;



    public  void Insert_Class_Table(String class_name) throws Exception
    {
        
        if(Table.get(class_name) != null )
        {
            throw new Exception("Class" + class_name + " has already been declared");
            
        }
        else if(class_name.equals("test"))
        {
            System.out.println("irthe null class");
        }
        else
        {

            System.out.println("Insert " + class_name+counter + " and create class_class");
            //create class_class here to instert other declarations later
            class_class test = new class_class(class_name,counter);
            Table.put(test.class_name,test);
            this.counter++;
        }
       
    }

    //more to insert here
    public void Insert_Method_to_class_class(String method_name,String method_type,String argumentList,String classname) throws Exception
    {
        
        class_class temp = Table.get(classname);
        if(method_name != null)
        temp.Insert_Method_MethTable(method_name,method_type,argumentList);

    }
    public void Override_Insert_Method_to_class_class(String method_name,String classname,String Ex_classname,String method_type, String argumentList) throws Exception
    {

        //search extended class for this method
        class_class temp = Table.get(Ex_classname);
        //if already exists-update
        
       

        if(temp.Search_for_override_meth(method_name,method_type,argumentList))//no need to update offset
        {
            //do nothing method updated
        }
        else  //if not create new at classname
        {

            //update parend class offset here and give number to method
            int off = temp.give_and_update_meth_offset();


            temp = Table.get(classname);
            if(method_name != null) //useless to be sure
            temp.Ex_Insert_Method_MethTable(method_name,method_type,argumentList,off);
        }

        
       

        

    }

    public void Insert_Variable_to_class_class(String id,String var_type,String classname) throws Exception
    {
        
        if(temp_extended_class==null)
        {
            class_class temp = Table.get(classname);
            // if(!var_type.equals("int") && !var_type.equals("boolean") && !var_type.equals("int[]"))
            // {
            //     //check if variable is a class
            //     if(Table.get(var_type) == null)
            //     {
            //         throw new Exception(" I dont know who this type is");
            //     }
                

            // }
        
            temp.Insert_Variable_VarTable(id,var_type,temp_meth);
        }
        else
        {
            
            //take and update offset from mother class
            class_class temp = Table.get(temp_extended_class);
            int off = temp.Mother_offset(var_type);

            //System.out.print("alalalalalalalalall" + off);
            temp = Table.get(classname);
            temp.Ex_Insert_Variable_VarTable(id,var_type,off,temp_meth);

        }
    }

    

    public  void Print_Keys()
    {
        System.out.print("\n\n\n\n------------------ PRINTING---------------------\n\n\n");
        int num = 0;
        //System.out.println(Table.keySet());
        class_class value= null;
        while(num < this.counter)
        {
            for(String key: Table.keySet())
            {
                value = Table.get(key);
                if(value.num_id == num)
                {
                    value.print_all();
                    num++;
                }
                
                //System.out.println("777777777"+key);
    
            }
        }
        System.out.print("\n\n\n\n --------------end PRINTING----------------\n\n\n");
    }

        public HashMap <String,class_class> give_table()
        {
            return this.Table;
        }






















    //Visitor Paterns !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Override
    public String visit(MainClass n, Void argu) throws Exception {
        
        String classname = n.f1.accept(this, null);
        temp_extended_class = null;
        temp_meth = "class";
        
        if(classname !=null)
        Insert_Class_Table(classname);
        temp_class = classname;
        
        
        super.visit(n, argu);
        System.out.println();

        return null;
    }

    @Override
    public String visit(ClassDeclaration n, Void argu) throws Exception {
        String classname = n.f1.accept(this, null);
        temp_extended_class = null;
        temp_meth = "class";

        if(classname !=null)
        Insert_Class_Table(classname);
        temp_class = classname;
        
        
        
        super.visit(n, argu);
        System.out.println();

        return null;
    }
    @Override
    public String visit(ClassExtendsDeclaration n, Void argu) throws Exception {
        String classname = n.f1.accept(this, null);
        temp_extended_class = n.f3.accept(this, null);
        temp_meth = "class";
        
        if(Table.get(temp_extended_class) == null)
        {
            throw new Exception("There is not any  class " + temp_extended_class);
        }
        
        
        if(classname !=null)
        Insert_Class_Table(classname);
        temp_class = classname;
        
        super.visit(n, argu);

        System.out.println();

        return null;
    }

    @Override
    public String visit(VarDeclaration n, Void argu) throws Exception {
        String type = n.f0.accept(this,argu);
        String id = n.f1.accept(this,argu);

        //System.out.println("-----------> " + type + id);
        Insert_Variable_to_class_class(id,type,temp_class);

        super.visit(n, argu);
        return null;


    }

    @Override
    public String visit(MethodDeclaration n, Void argu) throws Exception {
        String argumentList = n.f4.present() ? n.f4.accept(this, null) : "";

        String myType = n.f1.accept(this, null);
        String myName = n.f2.accept(this, null);
        
        temp_meth = myName;
        if(temp_extended_class == null)
        {
            
            //System.out.println( argumentList);
            Insert_Method_to_class_class(myName,myType,argumentList,temp_class);
        }
        else
        {
            //check for method Override
            Override_Insert_Method_to_class_class(myName,temp_class,temp_extended_class,myType,argumentList);


        }
       
        //System.out.println(myType + " " + myName + " -- " + argumentList);
        super.visit(n, argu);
        return null;
    }

    /**
     * f0 -> FormalParameter()
     * f1 -> FormalParameterTail()
     */
    @Override
    public String visit(FormalParameterList n, Void argu) throws Exception {
        String ret = n.f0.accept(this, null);
        
        if (n.f1 != null) {
            ret += n.f1.accept(this, null);
        }

        return ret;
    }

    /**
     * f0 -> FormalParameter()
     * f1 -> FormalParameterTail()
     */
    public String visit(FormalParameterTerm n, Void argu) throws Exception {
        
        return n.f1.accept(this, argu);
    }

    /**
     * f0 -> ","
     * f1 -> FormalParameter()
     */
    @Override
    public String visit(FormalParameterTail n, Void argu) throws Exception {
        String ret = "";
        for ( Node node: n.f0.nodes) {
            ret += ", " + node.accept(this, null);
        }

        return ret;
    }

    /**
     * f0 -> Type()
     * f1 -> Identifier()
     */
    @Override
    public String visit(FormalParameter n, Void argu) throws Exception{
        String type = n.f0.accept(this, null);
        String name = n.f1.accept(this, null);
        //System.out.println("->>>>>>>>" + type + name );
        if(!temp_meth.equals("class") && temp_extended_class==null)
        Table.get(temp_class).Methods_Table.get(temp_meth).Args_Table.put(name,type);
        return type + " " + name;
    }

    @Override
    public String visit(ArrayType n, Void argu) {
        return "int[]";
    }

    public String visit(BooleanType n, Void argu) {
        return "boolean";
    }

    public String visit(IntegerType n, Void argu) {
        return "int";
    }

    @Override
    public String visit(Identifier n, Void argu) {
        return n.f0.toString();
    }
}
