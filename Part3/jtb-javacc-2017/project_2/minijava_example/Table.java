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



    public  void Insert_Class_Table(String class_name) throws Exception
    {
        
        if(Table.get(class_name) != null )
        {
            throw new Exception("Class" + class_name + " has already been declared");
            
        }
        else if(class_name.equals("test"))
        {
            System.out.println("ekanes malakia irthe null class");
        }
        else
        {
            System.out.println("Insert " + class_name + " and create class_class");
            //create class_class here to instert other declarations later
            class_class test = new class_class(class_name);
            Table.put(test.class_name,test);
        }
       
    }

    //more to insert here
    public void Insert_Method_to_class_class(String method_name,String classname) throws Exception
    {
        class_class temp = Table.get(classname);
        if(method_name != null)
        temp.Insert_Method_MethTable(method_name);

    }

    public  void Print_Keys()
    {
        System.out.println(Table.keySet());
    }























    //Visitor Paterns !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Override
    public String visit(MainClass n, Void argu) throws Exception {
        
        String classname = n.f1.accept(this, null);
        System.out.println("called");
        if(classname !=null)
        Insert_Class_Table(classname);
       
        
        super.visit(n, argu);

        System.out.println();

        return null;
    }

    @Override
    public String visit(ClassDeclaration n, Void argu) throws Exception {
        String classname = n.f1.accept(this, null);
        //System.out.println("Classs: " + classname);
        if(classname !=null)
        Insert_Class_Table(classname);
        
        super.visit(n, argu);

        System.out.println();

        return null;
    }
    @Override
    public String visit(ClassExtendsDeclaration n, Void argu) throws Exception {
        String classname = n.f1.accept(this, null);
        //System.out.println("Class: " + classname);
        if(classname !=null)
        Insert_Class_Table(classname);
        
        super.visit(n, argu);

        System.out.println();

        return null;
    }

    @Override
    public String visit(MethodDeclaration n, Void argu) throws Exception {
        String argumentList = n.f4.present() ? n.f4.accept(this, null) : "";

        String myType = n.f1.accept(this, null);
        String myName = n.f2.accept(this, null);
        Insert_Method_to_class_class(myName,"A");
       
        //System.out.println(myType + " " + myName + " -- " + argumentList);
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
