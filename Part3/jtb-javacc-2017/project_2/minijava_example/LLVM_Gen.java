import syntaxtree.*;
import visitor.*;
import java.util.HashMap;
import java.util.*;
import javax.print.DocFlavor.STRING;
import java.io.*;


public class LLVM_Gen 
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
                        emit("\n@."+value.class_name+"_vtable = global ["+total_methods+" x i8*]");
                    else
                        emit("\n@."+value.class_name+"_vtable = global ["+total_methods+" x i8*] []");

                    
                    emit("[");
                    if(value.ex_class != null)
                    {
                        
                        emit_extended_class_meth(value.ex_class);
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
                                if(meth_num == 0)
                                {
                                    emit_str = "i8* bitcast (" + give_types(meth_value.type) + " (" + give_args_types(meth_value.args_list) + ")* @" + value.class_name+"."+meth_value.method_name 
                                    +" to i8*";
                                }
                                else
                                {
                                    emit_str = ",i8* bitcast (" + give_types(meth_value.type) + " (" + give_args_types(meth_value.args_list) + ")* @" + value.class_name+"."+meth_value.method_name 
                                    +" to i8*";
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
                                emit(emit_str);
                               


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
    



public int emit_extended_class_meth(String ex_class) throws Exception
{
    class_class value= null;
    value = Table.get(ex_class);

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
                if(meth_num == 0)
                {
                    emit_str = "i8* bitcast (" + give_types(meth_value.type) + " (" + give_args_types(meth_value.args_list) + ")* @" + value.class_name+"."+meth_value.method_name 
                    +" to i8*";
                }
                else
                {
                    emit_str = ",i8* bitcast (" + give_types(meth_value.type) + " (" + give_args_types(meth_value.args_list) + ")* @" + value.class_name+"."+meth_value.method_name 
                    +" to i8*";
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
                emit("\n");


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

public String give_args_types(List<String> arguments)
{
    String a = "i8*";
    for (int i = 0; i < arguments.size(); i++ )
    {
        a = a + "," + give_types(arguments.get(i));
    }
    return a;
}










































}
