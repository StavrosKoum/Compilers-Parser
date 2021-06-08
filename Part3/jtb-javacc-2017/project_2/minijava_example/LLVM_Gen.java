import syntaxtree.*;
import visitor.*;
import java.util.HashMap;
import java.util.*;
import javax.print.DocFlavor.STRING;
import java.io.*;


public class LLVM_Gen extends GJDepthFirst<String,Void>
{
    private HashMap <String,class_class> Table;
    private Writer writer;
    private int class_counter;
    //Lists to temporary store param type and name
    public List<String> name_list= new ArrayList<String>();
    public List<String> type_list= new ArrayList<String>();
    String tmp_class;
    private int if_num = 0;

    public LLVM_Gen(HashMap <String,class_class> MyTable,Writer wr,int class_count)
    {
        this.Table = MyTable;
        this.writer = wr;
        this.class_counter = class_count;
    }

    public void emit(String str) throws Exception
    {
        writer.write(str);
    }

    public void create_vt() throws Exception
    {
        int num = 0;
        class_class value= null;
        while(num < this.class_counter) ///class while
        {
            for(String key: Table.keySet())
            {
                value = Table.get(key);
                if(value.num_id == num)
                {
                    System.out.println("-----"+value.class_name+"-----");
                    //emit(value.class_name+"\n");

                    int ex_meths_count = 0;
                    if(value.ex_class != null)
                        ex_meths_count = Table.get(value.ex_class).meth_count;

                    int total_methods = ex_meths_count + value.meth_count;
                    //emit("class has this many methods-> "+total_methods+"\n");
                    if(total_methods != 0)
                        emit("\n@."+value.class_name+"_vtable = global ["+total_methods+" x i8*] ");
                    else
                        emit("\n@."+value.class_name+"_vtable = global ["+total_methods+" x i8*] ");

                    
                    emit("[");
                    if(value.ex_class != null)
                    {
                        
                        emit_extended_class_meth(value.ex_class,value.class_name);
                    }
                    
                    //methods here++++++++++++++++++++++++++++++++++++++++++++++
                    Method_class  meth_value = null;
                    int meth_num = 0;
                    String emit_str;
                    while(meth_num < value.meth_count)
                    {
                        for(String key1: value.Methods_Table.keySet())
                        {
                            
                            meth_value = value.Methods_Table.get(key1);
                            if(meth_value.num_id == meth_num)
                            {

                                ///////////////////////////////////
                                //////////////////////////////////
                                if(meth_num == 0 && value.ex_class == null )
                                {
                                    emit_str = "i8* bitcast (" + give_types(meth_value.type) + " (" + give_args_types(meth_value.args_list,meth_value.empty_args) + ")* @" + value.class_name+"."+meth_value.method_name 
                                    +" to i8*)";
                                }
                                else
                                {
                                    emit_str = ", i8* bitcast (" + give_types(meth_value.type) + " (" + give_args_types(meth_value.args_list,meth_value.empty_args) + ")* @" + value.class_name+"."+meth_value.method_name 
                                    +" to i8*)";
                                }
                                //////////////////////////////////
                                //////////////////////////////////
                                emit(emit_str);


                                // // value.print_all();
                                // System.out.println(meth_value.method_name);
                                // emit("name->"+meth_value.method_name+"\n");
                                // //take meth info here
                                // emit("type->"+meth_value.type+"\n");
                                // //meth args++++++++++++++++++++++++++++++++++++++=
                                // for(int i = 0; i <meth_value.args_list.size(); i ++)
                                // {
                                //     if(i == 0)
                                //     emit(meth_value.args_list.get(i));
                                //     else
                                //     emit(","+meth_value.args_list.get(i));
                                // }
                                
                               


                                meth_num++;
                            }
                            
                            

                        }
                    }

                    emit("]");









                    num++;
                }
                
                
    
            }
        }
        
    }
    
    public void define_util() throws Exception
    {
        emit(
            "\n\ndeclare i8* @calloc(i32, i32)\n" +
            "declare i32 @printf(i8*, ...)\n" +
            "declare void @exit(i32)\n\n@_cint = constant [4 x i8] c\"%d\\0a\\00\"\n" +
            "@_cOOB = constant [15 x i8] c\"Out of bounds\\0a\\00\"\n" +
            "define void @print_int(i32 %i) {\n" + 
            "    %_str = bitcast [4 x i8]* @_cint to i8*\n" +
            "    call i32 (i8*, ...) @printf(i8* %_str, i32 %i)\n" +
            "    ret void\n" + 
            "}\n\n" +
            "define void @throw_oob() {\n" +
            "    %_str = bitcast [15 x i8]* @_cOOB to i8*\n" +
            "    call i32 (i8*, ...) @printf(i8* %_str)\n" +
            "    call void @exit(i32 1)\n" +
            "    ret void\n}\n"
            );
    }


public int emit_extended_class_meth(String ex_class,String child_name) throws Exception
{
    class_class value= null;
    value = Table.get(ex_class);

    //methods here++++++++++++++++++++++++++++++++++++++++++++++
    Method_class  meth_value = null;
    int meth_num = 0;
    String emit_str,name;
    while(meth_num < value.meth_count)
    {
        for(String key1: value.Methods_Table.keySet())
        {
        
            meth_value = value.Methods_Table.get(key1);
            if(meth_value.num_id == meth_num)
            {


                if(meth_value.redefined)
                {
                    name = child_name;
                }
                else{
                    name = value.class_name;
                }



                ///////////////////////////////////
                //////////////////////////////////
                if(meth_num == 0)
                {
                    emit_str = "i8* bitcast (" + give_types(meth_value.type) + " (" + give_args_types(meth_value.args_list,meth_value.empty_args) + ")* @" + name+"."+meth_value.method_name 
                    +" to i8*)";
                }
                else
                {
                    emit_str = ",i8* bitcast (" + give_types(meth_value.type) + " (" + give_args_types(meth_value.args_list,meth_value.empty_args) + ")* @" + value.class_name+"."+meth_value.method_name 
                    +" to i8*)";
                }
                //////////////////////////////////
                //////////////////////////////////
                emit(emit_str);
                // // value.print_all();
                // System.out.println(meth_value.method_name);
                // emit("name->"+meth_value.method_name+"\n");
                // //take meth info here
                // emit("type->"+meth_value.type+"\n");
                // //meth args++++++++++++++++++++++++++++++++++++++=
                // for(int i = 0; i <meth_value.args_list.size(); i ++)
                // {
                //     if(i == 0)
                //     emit(meth_value.args_list.get(i));
                //     else
                //     emit(","+meth_value.args_list.get(i));
                // }
                //emit("\n");


                meth_num++;
            }
            
            

        }
    }
    return value.meth_count;
}

public String give_types(String type)
{
    if(type.equals("int"))
        return "i32";

    if(type.equals("boolean"))
    return "i1";
    
    if(type.equals("int[]"))
    return "i32*";

    return "i8*";

    
}

public String give_args_types(List<String> arguments,boolean empty_args)
{
    
    String a = "i8*";
    if(empty_args)
    return a;
    for (int i = 0; i < arguments.size(); i++ )
    {
        a = a + "," + give_types(arguments.get(i));
    }
    return a;
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
        this.tmp_class = classname;
        emit("define i32 @main(){");
        
        //System.out.println("Class: " + classname);
         
        super.visit(n, argu);
        emit("\n\tret i32 0\n}\n");

        
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
        // temp_method = "class";
        // temp_extended_class = null;
        tmp_class = classname;
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
        tmp_class = classname;
        // temp_method = "class";
        // //System.out.println("Class: " + classname);
        // temp_extended_class = n.f3.accept(this, null);
        super.visit(n, argu);
        //System.out.println();
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
        String type;
        String myType = n.f1.accept(this, null);
        String myName = n.f2.accept(this, null);
        String parameters = "i8 %this";

        for(int i=0; i < name_list.size(); i++)
        {
            parameters += ", "+ give_types(type_list.get(i)) + " %." + name_list.get(i);
        }

        emit("define "+ give_types(myType)+" @"+this.tmp_class+"."+myName + "(" +parameters+")\n");

        for(int i=0; i < name_list.size(); i++)
        {
            type = give_types(type_list.get(i));
            emit("\t%"+name_list.get(i)+" = alloca "+ type+"\n");
            emit("\tstore "+ type+ " %." + name_list.get(i) + ", "+ type+"* %"+name_list.get(i)+"\n");
        }
        

        n.f7.accept(this, argu);
        n.f8.accept(this, argu);

      




        //clear the lists so other methods can use them
        this.name_list.clear();
        this.type_list.clear();
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

        // System.out.println(type);
        type = give_types(type);
        emit("\t%"+id+" = alloca "+ type+"\n");
        


        super.visit(n, argu);
        return null;


    }

     /**
     * f0 -> Type()
     * f1 -> Identifier()
     */
    @Override
    public String visit(FormalParameter n, Void argu) throws Exception{
        String type = n.f0.accept(this, null);
        String name = n.f1.accept(this, null);
        // type = give_types(type);
        // emit("\t%"+name+" = alloca "+ type+"\n");
        // emit("\tstore "+ type+ " %." + name + ", "+ type+"* %"+name+"\n");

        if(name!=null)
        {
            
            this.name_list.add(name);
            this.type_list.add(type);
        }
        


        return type + " " + name;
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
        String label1 = "if" + if_num;
        if_num+=1;
        String label2 = "if" + if_num;
        if_num+=1;

        String expr = n.f2.accept(this, argu);
        emit("\n    br i1 " + expr + ", label %" + label1 + ", label %" + label2);
        emit("\n\n" + label1 + ":");

        
        n.f3.accept(this, argu);
        n.f4.accept(this, argu);
        emit("\n    br label %if" + if_num);
        n.f5.accept(this, argu);
        emit("\n\n" + label2 + ":");
        n.f6.accept(this, argu);
        emit("\n    br label %if" + if_num);
        emit("\n\nif" + if_num + ":");
        if_num+=1;
        return _ret;
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
    * f0 -> <IDENTIFIER> okkkkkk
    */
    public String visit(Identifier n, Void argu) throws Exception 
    {
        //System.out.println("3");
        return n.f0.tokenImage;
    }



































}
