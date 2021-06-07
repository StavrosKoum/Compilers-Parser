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
        emit("define i32 @main(){");
        
        //System.out.println("Class: " + classname);
         
        super.visit(n, argu);
        emit("\n\tret i32 0\n}\n");

        
        //System.out.println();

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
        type = give_types(type);
        emit("\t%"+name+" = alloca "+ type+"\n");
        emit("\tstore "+ type+ " %." + name + ", "+ type+"* %"+name+"\n");



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
    * f0 -> <IDENTIFIER> okkkkkk
    */
    public String visit(Identifier n, Void argu) throws Exception 
    {
        //System.out.println("3");
        return n.f0.tokenImage;
    }



































}
