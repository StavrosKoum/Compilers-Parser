import visitor.GJDepthFirst;
import visitor.*;

import syntaxtree.*;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;



public class Check_visitor extends GJDepthFirst<String,Void>
{
    private HashMap <String,class_class> Table;
    private String temp_class;
    private String temp_extended_class = null;
    private String temp_method;

    private List<String> MyArgs_list = new ArrayList<String>();


    public Check_visitor(HashMap <String,class_class> get_table)
    {
        this.Table = get_table;
        // System.out.println();
        // System.out.println();
        // System.out.println();
        // System.out.println();
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
    public static boolean isArithmetic(String num) 
    {
        try 
        {
            Integer.parseInt(num);
            return true;
        } catch(NumberFormatException e)
        {  
            return false; 
        } catch (NullPointerException e) 
        {
            return false; 
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
            Variable_class tmp_var;

            //System.out.println("->"+temp_method);

            


            //CAUTION!! ONLY FOR VARIABLE 
            //bacause extended class vars are stored at their own table with mother offset
            class_class var_class_tmp = Table.get(temp_class);



            tmp_meth = tmp.Methods_Table.get(temp_method);
            

            //check if arithmetic
            if(!isArithmetic(exp1) && !exp1.equals("int"))
            {
                //check if declared arg
                tmp_var = var_class_tmp.Variables_Table.get(exp1+temp_method);
                //System.out.println("===========->"+tmp_var.var_name);
                if(tmp_var == null || !tmp_var.type.equals("int") || !tmp_var.method.equals(temp_method))
                {
                    //if its not the "class " method
                    if(temp_method.equals("class"))
                    {
                        throw new Exception(" Incompatible types " + exp1 +" cannot be converted to int");
                    }
                    else
                    {
                        //ckeck if its int argument
                        if(!tmp_meth.args.contains("int "+exp1))
                        {
                            //System.out.println("===========->"+tmp_meth.method_name);
                            throw new Exception(" Incompatible type " + exp1 +" cannot be converted to int");
                        }
                    }
                    
                }
                
            }

            
            //check if arithmetic
            if(!isArithmetic(exp2)&& !exp2.equals("int"))
            {
                //check if declared arg
                tmp_var = var_class_tmp.Variables_Table.get(exp2+temp_method);
                if(tmp_var == null || !tmp_var.type.equals("int") || !tmp_var.method.equals(temp_method))
                {
                    //if its not the "class " method
                    if(temp_method.equals("class"))
                    {
                        throw new Exception(" Incompatible type " + exp2 +" cannot be converted to int");
                    }
                    else
                    {
                        //ckeck if its int argument
                        if(!tmp_meth.args.contains("int "+exp2))
                        {
                            throw new Exception(" Incompatible type " + exp2 +" cannot be converted to int");
                        }
                    }
                    
                }
                
            }
        }
        //System.out.println("fuck yes");









        

    }

    public void check_return_type(String ret_type) throws Exception
    {
        // System.out.println(temp_class);
        //System.out.println("_______________________________"+ret_type);

        class_class tmp = Table.get(temp_class);
        Method_class meth = tmp.Methods_Table.get(temp_method);

        if(meth == null)
        {
            //check at mother class for the method
            tmp = Table.get(temp_extended_class);
            meth = tmp.Methods_Table.get(temp_method);
            if(meth.type.equals(ret_type))
            {
                //all good
            }
            else
            {
                throw new Exception("return type "+ ret_type+" is not of function type " + meth.type);
            }
        }
        else
        {
            if(meth.type.equals(ret_type))
            {
                //all good
            }
            else
            {
                
                throw new Exception("return type "+ ret_type+" is not of function type " + meth.type);
                
            }
        }
        
    }



    public String find_id_type(String id)
    {
        class_class tmp_class;
        Variable_class tmp;
        Method_class meth_tmp;

        String ex_class = temp_extended_class;
        //change to while and update ex_class at the end with new
        if(ex_class!=null)
        {
            //System.out.println(this.temp_class);
            tmp_class = Table.get(ex_class);

            
            if(!temp_method.equals("class"))
            {
                meth_tmp = tmp_class.Methods_Table.get(temp_method);
                
                if(meth_tmp!=null)
                {
                    //System.out.println("aaaaaaaaaaaaaaaaaaaaaaa "+temp_method);
                    String type = meth_tmp.Args_Table.get(id);
                    if(type!=null)
                    {
                        return type;
                    }
                }
            }
            ex_class = tmp_class.ex_class;
        }
        
        
        
        //System.out.println(this.temp_class);
        tmp_class = Table.get(temp_class);

        //check if its an argument
        
        if(!temp_method.equals("class"))
        {
            meth_tmp = tmp_class.Methods_Table.get(temp_method);

            if(meth_tmp!=null)
            {
                String type = meth_tmp.Args_Table.get(id);
                if(type!=null)
                {
                    return type;
                }
            }
        }

        //System.out.println(tmp_class.class_name);
        //search for variables now .not method types
        tmp = tmp_class.Variables_Table.get(id+temp_method);
        if(tmp != null)
        {
            return tmp.type;
        }
        tmp = tmp_class.Variables_Table.get(id+"class");
        if(tmp != null)
        {
            return tmp.type;
        }



        if(temp_extended_class != null)
        {
            
            tmp_class = Table.get(temp_extended_class);
            tmp = tmp_class.Variables_Table.get(id+"class");
            if(tmp != null)
            {
                return tmp.type;
            }

        }
        
        
        return id;
        
    }


    // public void coompare_assign_types(String id_type,String ass_type)
    // {
    //     if(id_type.equals("int"))
    //     {
    //         if(ass_type)
    //     }
    // }

    public String Check_MessageSend(String type_class,String this_meth,String this_args) throws Exception
    {
        if(type_class.equals("this"))
        type_class = temp_class;

        class_class myClass = Table.get(type_class);
        if(myClass == null )
        {
            throw new Exception("How the hell did this happen.It should have been an error at varDecl");
        }

        Method_class myMeth = myClass.Methods_Table.get(this_meth);
        boolean flag = true;
        if(myMeth == null)
        {
            while(myClass.ex_class!= null)
            {
               
                myClass = Table.get(myClass.ex_class);
                
                myMeth = myClass.Methods_Table.get(this_meth);
                if(myMeth != null)
                {
                    flag = false;
                    break;
                }
               
               

            }
            
            myClass = Table.get(type_class);
            if(flag)
            throw new Exception("Class "+ myClass.class_name + " has no method "+ this_meth);
            
            
        }

        if(MyArgs_list.size() == 0 && myMeth.empty_args == true)
        {
         //do nothing.Its ok   
         
        }
        else//check arguments
        {
            class_class checker;
            //mymethod list compare with myarglist
            if(myMeth.args_list.size() != MyArgs_list.size())
            {
                //System.out.println(myMeth.args_list.size());
                throw new Exception("Method args too few or too many");
            }
            for(int i = 0; i < myMeth.args_list.size(); i++)
            {
                String one = myMeth.args_list.get(i);
                String two = MyArgs_list.get(i);
                //System.out.println(one+"---" + two);
                if(!(one.equals(two)))
                {
                    if(!two.equals("null"))
                    {
                        //check for parent class
                        checker = Table.get(two);
                        if(checker != null)
                        {
                            if(!(one.equals(checker.ex_class)))
                            throw new Exception("Wrong meth args");
                        }
                       
                    }
                    
                }
            }
            //System.out.println(this_args);
        }
        return myMeth.type;

    }

    public void give_to_arg_list(String arg)
    {
        //must check if initialised here

        //take type
        String tmp = find_id_type(arg);
        MyArgs_list.add(tmp);


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
        this.temp_class = classname;
        temp_method = "class";
        //System.out.println("Class: " + classname);
       
       

        super.visit(n, argu);
        

        
        //System.out.println();

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
        temp_method = "class";
        temp_extended_class = null;
        temp_class = classname;


        
        
        
        
        super.visit(n, argu);
        //System.out.println();

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
        temp_class = classname;
        temp_method = "class";
        //System.out.println("Class: " + classname);
        
        
        temp_extended_class = n.f3.accept(this, null);
        
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
        n.f7.accept(this, argu);
        n.f8.accept(this, argu);

        
        //System.out.println("_______+++++++++++++++++++++++++++++++++++++++++++"+temp_method);


        String ret_type = n.f10.accept(this,null);


        //System.out.println(myType);
        
        check_types(myType);
        ret_type = find_id_type(ret_type);
        check_return_type(ret_type);
       
        //System.out.println("expression is->"+n.f10.accept(this,null));
        return null;
    }




    /**
    * f0 -> Identifier()
    * f1 -> "="
    * f2 -> Expression()
    * f3 -> ";"
    */
    @Override
    public String visit(AssignmentStatement n, Void argu) throws Exception
    {
    
        String name = n.f0.accept(this, argu);
        
        String id = find_id_type(name);

        
        
        String expr = find_id_type(n.f2.accept(this, argu));


        //System.out.println(id+"------------Ass----\"=\"----between------------>");
        if(!id.equals(expr))
        {
            
            if(expr != null && !expr.equals("this"))
            System.out.println("Problem at project part2.It is commented so part3 can run");
            //throw new Exception("assignment Error "+id+" ="+ expr);
        }


        //above its to update initialised boolean
        //if var is argument we dont have to initialise
        // if(Table.get(temp_class).Methods_Table.get(temp_method)!=null)
        // {
        //     if(Table.get(temp_class).Methods_Table.get(temp_method).Args_Table.get(name)!=null)
        //     {
        //         //System.out.println("Argument reinitialised");
        //         return null;
        //     }
        // }
        // else if(Table.get(temp_extended_class).Methods_Table.get(temp_method)!=null)
        // {
        //     if(Table.get(temp_extended_class).Methods_Table.get(temp_method).Args_Table.get(name)!=null)
        //     {
        //         //System.out.println("Argument reinitialised");
        //         return null;
        //     }
        // }
        
        // //update init
        // if( Table.get(temp_class).Variables_Table.get(name+temp_method)!=null)
        // {
        //     //System.out.println("-----------11------------");
        //     Table.get(temp_class).Variables_Table.get(name+temp_method).init = true;
        //     return null;
        // }
        // else if(Table.get(temp_class).Variables_Table.get(name+"class")!=null)
        // {
        //     //System.out.println("-----------12------------");
        //     Table.get(temp_class).Variables_Table.get(name+"class").init = true;
        //     return null;
        // }
        // //search extended class
        // if(temp_extended_class!=null)
        // {   
        //     if(Table.get(temp_extended_class).Variables_Table.get(name+temp_method)!=null)
        //     {   
        //         //System.out.println("-----------13------------");
        //         Table.get(temp_extended_class).Variables_Table.get(name+temp_method).init = true;
        //         return null;
        //     }
        //     else if(Table.get(temp_extended_class).Variables_Table.get(name+"class")!=null)
        //     {
        //         //System.out.println("-----------14------------");
        //         Table.get(temp_extended_class).Variables_Table.get(name+"class").init = true;
        //         return null;
        //     }
        // }

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
        //System.out.println(one + "+"+ two);
        one = find_id_type(one);
        two = find_id_type(two);
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
        //System.out.println(one + "-"+ two);
        one = find_id_type(one);
        two = find_id_type(two);
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
        //System.out.println(one + "*"+ two);
        one = find_id_type(one);
        two = find_id_type(two);
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
        //System.out.println(one + "<"+ two);
        one = find_id_type(one);
        two = find_id_type(two);
        check_expressions(one,two);
        return "boolean";
    }



    /**
    * f0 -> <INTEGER_LITERAL> okkkkk
    */
    @Override
    public String visit(IntegerLiteral n, Void argu) throws Exception
    {
        //System.out.println("0");
        return "int";
    }

   /**
    * f0 -> "true" okkkkk
    */
    public String visit(TrueLiteral n, Void argu) throws Exception 
    {
        //System.out.println("1");
        return "boolean";
    }

    /**
    * f0 -> "false" okkkk
    */
    public String visit(FalseLiteral n, Void argu) throws Exception 
    {
        //System.out.println("2");
        return "boolean";
    }
    
    /**
    * f0 -> <IDENTIFIER> okkkkkk
    */
    public String visit(Identifier n, Void argu) throws Exception 
    {
        //System.out.println("3");
        return n.f0.tokenImage;
    }
    /**
     * f0 -> "this" ookkkkkkk
    */
    public String visit(ThisExpression n, Void argu) throws Exception 
    {
        //System.out.println("4");
        return temp_class;
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
   
        //System.out.println("5");
        n.f3.accept(this, argu);
    
        return "int[]";
    }

     /**
    * f0 -> "new"
    * f1 -> Identifier() okkkkkk i guess
    * f2 -> "("
    * f3 -> ")"
    */
    public String visit(AllocationExpression n, Void argu) throws Exception 
    {
        
        
        //System.out.println("6");

        String tmp = n.f1.accept(this, argu);
        class_class sh = Table.get(tmp);
        if(sh == null)
        {
            throw new Exception("Undefined reference to this boi class " + tmp);
        }
        
        return tmp;
    }

    /**
     * f0 -> "!"
    * f1 -> PrimaryExpression() okkkkkkk
    */
    public String visit(NotExpression n,  Void argu) throws Exception 
    {
        //System.out.println("7");
        String tmp = n.f1.accept(this, argu);
        return tmp;
    }

    /**
     * f0 -> "("
    * f1 -> Expression() ookkkkkkk
    * f2 -> ")"
    */
    public String visit(BracketExpression n,  Void argu) throws Exception 
    {
        //System.out.println("8");
        String tmp = n.f1.accept(this, argu);
        
        return tmp;
    }


    /**
    * f0 -> PrimaryExpression()
    * f1 -> "."
    * f2 -> Identifier()
    * f3 -> "("
    * f4 -> ( ExpressionList() )?
    * f5 -> ")"
    */
    public String visit(MessageSend n,  Void argu) throws Exception {
        String _ret=null;
        String this_class_var = n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        String this_meth =  n.f2.accept(this, argu);
        n.f3.accept(this, argu);

        String this_args = null;

        //empty list
        MyArgs_list.clear();
        //call so the list will fill
        n.f4.accept(this, argu);
        //chech args from list in check_messageSend
        //System.out.println("liiiiiist"+MyArgs_list);
        
        


        //System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$"+this_class_var+"."+this_meth+"(dont mind this)");
        String type_class = find_id_type(this_class_var);
        //Check_MessageSend(type_class,this_meth,this_args);

        
        //returns type
        return Check_MessageSend(type_class,this_meth,this_args);
    }




    /**
    * f0 -> Expression()
    * f1 -> ExpressionTail()
    */
   public String visit(ExpressionList n, Void argu ) throws Exception 
   {
    String _ret=null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    return _ret;
 }




    /**
    * f0 -> AndExpression()
    *       | CompareExpression()
    *       | PlusExpression()
    *       | MinusExpression()
    *       | TimesExpression()
    *       | ArrayLookup()
    *       | ArrayLength()
    *       | MessageSend()
    *       | PrimaryExpression()
    */
    public String visit(Expression n, Void argu) throws Exception 
    {
        String tmp  = n.f0.accept(this, argu);
        //System.out.println("yyyyyy"+tmp);
        give_to_arg_list(tmp);
        return tmp;
    }







    /**
    * f0 -> "if"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> Statement()
    * f5 -> "else"
    * f6 -> Statement()
    */
   public String visit(IfStatement n,Void argu) throws Exception {
    String _ret=null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    String a = n.f2.accept(this, argu);
    a = find_id_type(a);
    //System.out.println(a);
    if(a!=null)
    {
        if(!a.equals("boolean") )
        {
            
            throw new Exception("If statement must be type boolean");
        }
    }
    
    n.f3.accept(this, argu);
    n.f4.accept(this, argu);
    n.f5.accept(this, argu);
    n.f6.accept(this, argu);
    return _ret;
 }

    /**
    * f0 -> "while"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> Statement()
    */
    public String visit(WhileStatement n, Void argu) throws Exception {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        String a = n.f2.accept(this, argu);
        a = find_id_type(a);
        //System.out.println(a);
        if(a!=null)
        {
            if(!a.equals("boolean") )
            {
                
                throw new Exception("While statement must be type boolean");
            }
        }
        n.f3.accept(this, argu);
        n.f4.accept(this, argu);
        return _ret;
     }

     /**
    * f0 -> "System.out.println"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> ";"
    */
    public String visit(PrintStatement n, Void argu) throws Exception {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        String a = n.f2.accept(this, argu);
        a = find_id_type(a);
        if(a!=null)
        {
            if(!a.equals("int") )
            {
                
                throw new Exception("Print statement must have type int");
            }
        }
        n.f3.accept(this, argu);
        n.f4.accept(this, argu);
        return _ret;
    }
}
