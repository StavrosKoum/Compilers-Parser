import java.util.HashMap;
import visitor.GJDepthFirst;
import visitor.*;

import syntaxtree.*;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;



public class Check_visitor extends GJDepthFirst<String,Void>
{
    private HashMap <String,class_class> Table;
    private String temp_class;
    private String temp_extended_class = null;

    public Check_visitor(HashMap <String,class_class> get_table)
    {
        this.Table = get_table;
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        // class_class value= null;
        // for(String key: Table.keySet())
        // {
        //     value = Table.get(key);
            
            
        //         value.print_all();
                
            
        
        //     //System.out.println("777777777"+key);

        // }
    }


    public void check_types(String var_type) throws Exception
    {
        //System.out.println("type ok");
        if(!var_type.equals("int") && !var_type.equals("boolean") && !var_type.equals("int[]"))
        {
            //check if variable is a class
            if(Table.get(var_type) == null)
            {
                throw new Exception(" I dont know who this type is");
            }
            //System.out.println("type ok");
            

        }
    }





    /**
     * f0 -> "class"
     * f1 -> Identifier()
     * f2 -> "{"
     * f3 -> "public"
     * f4 -> "static"
     * f5 -> "void"
     * f6 -> "main"
     * f7 -> "("
     * f8 -> "String"
     * f9 -> "["
     * f10 -> "]"
     * f11 -> Identifier()
     * f12 -> ")"
     * f13 -> "{"
     * f14 -> ( VarDeclaration() )*
     * f15 -> ( Statement() )*
     * f16 -> "}"
     * f17 -> "}"
     */
    @Override
    public String visit(MainClass n, Void argu) throws Exception {
        String classname = n.f1.accept(this, null);
        //System.out.println("Class: " + classname);
       

        super.visit(n, argu);
        

        
        System.out.println();

        return null;
    }

    /**
     * f0 -> "class"
     * f1 -> Identifier()
     * f2 -> "{"
     * f3 -> ( VarDeclaration() )*
     * f4 -> ( MethodDeclaration() )*
     * f5 -> "}"
     */
    @Override
    public String visit(ClassDeclaration n, Void argu) throws Exception {
        String classname = n.f1.accept(this, null);
        //System.out.println("Classs: " + classname);

        temp_extended_class = null;
        temp_class = classname;


        
        
        
        
        super.visit(n, argu);
        System.out.println();

        return null;
    }

    /**
     * f0 -> "class"
     * f1 -> Identifier()
     * f2 -> "extends"
     * f3 -> Identifier()
     * f4 -> "{"
     * f5 -> ( VarDeclaration() )*
     * f6 -> ( MethodDeclaration() )*
     * f7 -> "}"
     */
    @Override
    public String visit(ClassExtendsDeclaration n, Void argu) throws Exception {
        String classname = n.f1.accept(this, null);
        //System.out.println("Class: " + classname);
        
        
        temp_extended_class = n.f3.accept(this, null);
        temp_class = classname;

        super.visit(n, argu);
        System.out.println();

        return null;
    }


    /**
     * 
     * f0 -> Type()
     * f1 -> Identifier()
     * f2 -> ";"
     * 
     */
    @Override
    public String visit(VarDeclaration n, Void argu) throws Exception {
        String type = n.f0.accept(this,argu);
        String id = n.f1.accept(this,argu);

        //check if type is ok
        check_types(type);


        super.visit(n, argu);
        return null;


    }

    /**
     * f0 -> "public"
     * f1 -> Type()
     * f2 -> Identifier()
     * f3 -> "("
     * f4 -> ( FormalParameterList() )?
     * f5 -> ")"
     * f6 -> "{"
     * f7 -> ( VarDeclaration() )*
     * f8 -> ( Statement() )*
     * f9 -> "return"
     * f10 -> Expression()
     * f11 -> ";"
     * f12 -> "}"
     */
    @Override
    public String visit(MethodDeclaration n, Void argu) throws Exception {
        String argumentList = n.f4.present() ? n.f4.accept(this, null) : "";

        String myType = n.f1.accept(this, null);
        String myName = n.f2.accept(this, null);
       
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
