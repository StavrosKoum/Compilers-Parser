import syntaxtree.*;
import visitor.*;
import java.util.HashMap;

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
        int num = 1;
        //System.out.println(Table.keySet());
        class_class value= null;
        while(num < this.class_counter)
        {
            for(String key: Table.keySet())
            {
                value = Table.get(key);
                if(value.num_id == num)
                {
                    System.out.println("-----"+value.class_name+"-----");
                    emit(value.class_name+"\n");
                    
                    num++;
                }
                
                
    
            }
        }
        
    }
    














































}
