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
    private String temp_method;


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

    //for plus,minus,compare and times expression
    public void check_expressions(String exp1,String exp2) throws Exception
    {
        if(exp1.equals(exp2) && exp1.equals("int"))
        {
            //all ok
        }
        else
        {
            String myclass = temp_class;
            if(temp_extended_class!=null)
            {
                myclass = temp_extended_class;
            }
            class_class tmp;
            Method_class tmp_meth;
            tmp= Table.get(myclass);

            //System.out.println("->"+temp_method);

            tmp_meth = tmp.Methods_Table.get(temp_method);
            
            //System.out.println(myclass);
            if(!tmp_meth.args.contains("int "+exp1))
            {
                throw new Exception(" Incompatible type " + exp1 +" cannot be converted to int");
            }
            if(!tmp_meth.args.contains("int "+ exp2))
            {
                throw new Exception(" Incompatible type " + exp2 +" cannot be converted to int");
            }
        }
        //System.out.println("fuck yes");

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

        this.temp_method = myName;

        System.out.println(myType);
        check_types(myType);
       
        System.out.println("expression is->"+n.f10.accept(this,null));
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

    

    /**
    * f0 -> PrimaryExpression()
    * f1 -> "+"
    * f2 -> PrimaryExpression()
    */
    @Override
    public String visit(PlusExpression n, Void argu) throws Exception
    {
        String one = n.f0.accept(this,null);
        String two = n.f2.accept(this,null);
        System.out.println(one + "+"+ two);
        check_expressions(one,two);
        return "int";
    }


    /**
    * f0 -> PrimaryExpression()
    * f1 -> "-"
    * f2 -> PrimaryExpression()
    */
    @Override
    public String visit(MinusExpression n, Void argu) throws Exception
    {
        String one = n.f0.accept(this,null);
        String two = n.f2.accept(this,null);
        System.out.println(one + "-"+ two);
        check_expressions(one,two);
        return "int";
    }

     /**
    * f0 -> PrimaryExpression()
    * f1 -> "*"
    * f2 -> PrimaryExpression()
    */
    @Override
    public String visit(TimesExpression n, Void argu) throws Exception
    {
        String one = n.f0.accept(this,null);
        String two = n.f2.accept(this,null);
        System.out.println(one + "*"+ two);
        check_expressions(one,two);
        return "int";
    }


    /**
    * f0 -> PrimaryExpression()
    * f1 -> "<"
    * f2 -> PrimaryExpression()
    */
    public String visit(CompareExpression n, Void argu) throws Exception
    {
        String one = n.f0.accept(this,null);
        String two = n.f2.accept(this,null);
        System.out.println(one + "<"+ two);
        check_expressions(one,two);
        return "boolean";
    }



    /**
    * f0 -> <INTEGER_LITERAL>
    */
    @Override
    public String visit(IntegerLiteral n, Void argu) throws Exception
    {
        //System.out.println("0");
        return n.f0.tokenImage;
    }

   /**
    * f0 -> "true"
    */
    public String visit(TrueLiteral n, Void argu) throws Exception 
    {
        System.out.println("1");
        return n.f0.tokenImage;
    }

    /**
    * f0 -> "false"
    */
    public String visit(FalseLiteral n, Void argu) throws Exception 
    {
        System.out.println("2");
        return n.f0.tokenImage;
    }
    
    /**
    * f0 -> <IDENTIFIER>
    */
    public String visit(Identifier n, Void argu) throws Exception 
    {
        System.out.println("3");
        return n.f0.tokenImage;
    }
    /**
     * f0 -> "this"
    */
    public String visit(ThisExpression n, Void argu) throws Exception 
    {
        System.out.println("4");
        return n.f0.tokenImage;
    }

     /**
    * f0 -> "new"
    * f1 -> "int"
    * f2 -> "["
    * f3 -> Expression()
    * f4 -> "]"
    */
   public String visit(ArrayAllocationExpression n, Void argu) throws Exception 
    {
   
        System.out.println("5");
        n.f3.accept(this, argu);
    
        return "skata";
    }

     /**
    * f0 -> "new"
    * f1 -> Identifier()
    * f2 -> "("
    * f3 -> ")"
    */
    public String visit(AllocationExpression n, Void argu) throws Exception 
    {
        
        n.f1.accept(this, argu);
        System.out.println("6");
        
        return "new kati()";
    }

    /**
     * f0 -> "!"
    * f1 -> PrimaryExpression()
    */
    public String visit(NotExpression n,  Void argu) throws Exception 
    {
        System.out.println("7");
        n.f1.accept(this, argu);
        return "!expr";
    }

    /**
     * f0 -> "("
    * f1 -> Expression()
    * f2 -> ")"
    */
    public String visit(BracketExpression n,  Void argu) throws Exception 
    {
        System.out.println("8");
        n.f1.accept(this, argu);
        
        return "(   )";
 }
}
