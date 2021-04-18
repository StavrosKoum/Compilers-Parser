import java.io.InputStream;
import java.io.IOException;
import java.lang.Math;


class calculator {
    private final InputStream in;

    private int lookahead;

    public calculator(InputStream in) throws IOException {
        this.in = in;
        lookahead = in.read();
    }

    private void consume(int symbol) throws IOException, ParseError {

        if (lookahead == symbol)
        {
            lookahead = in.read();
            while(lookahead == ' ')
            {
                lookahead = in.read();
            }
        }    
        else
            throw new ParseError();
    }

    private boolean isDigit(int c) {
        return '0' <= c && c <= '9';
    }

    private int evalDigit(int c) {
        return c - '0';
    }

    
    
    public int expr() throws IOException, ParseError 
    {
        int value = 0;
        // int test = isDigit(lookahead);
        // System.out.println("ok"+test+"\n");

        if (isDigit(lookahead) || lookahead == '(')
        {
            //->term exprtail
            value = term();
            //System.out.println("ok\n");
            value = value + exprtail();

        }
        else
        {
            throw new ParseError();
        }
        return value;
    }

    public int term() throws IOException, ParseError 
    {
        
        //->factor termtail
        int value = factor();
        //call termtail with value
        int pow = termtail();
        value = (int) Math.pow(value,pow);


        return value;
    }

    public int termtail() throws IOException, ParseError 
    {

        int ret = 1;
        switch (lookahead) 
        {
            case '+':
                return 1;
            case '-':
                return 1;
            case '*':
                consume(lookahead);
                if(lookahead!='*')
                {
                    throw new ParseError();
                }
                //consume second *
                consume(lookahead);
                //call factor termtail
                int val = factor();
                int val2 = termtail();
                ret = (int) Math.pow(val,val2);
                return ret;




            //i think its not posible to read it because factor() consumes it
            case ')':
                return 1;
            case '\n':
                return 1; //not sure

               
        }
        System.out.println("---termtail ParseError---");
        throw new ParseError();


    }


    public int exprtail() throws IOException, ParseError 
    {
        int ret = 0;
        switch (lookahead) 
        {
            case '+':
                //+ term  +exprtail
                consume(lookahead);
                ret = term() + exprtail();
                return ret;

            case '-':
                //-term +exprtail times(-1)
                consume(lookahead);
                ret = term() + exprtail(); 
                ret = ret * (-1);
                return ret;
            case '\n':
                return 0;
            case ')':
                return 0;


        }
        System.out.println("---exprtail ParseError---");
        throw new ParseError();


    }

    public int factor() throws IOException, ParseError 
    {
        int value = 0;
        //-> num | (expr)
        if(isDigit(lookahead))
        {
            
            value = num();
            
        }
        else if(lookahead == '(')
        {
            
            //consume '('
            consume(lookahead);


            //call expr
            value = expr();

            
            if(lookahead != ')')
            {
                
                throw new ParseError();
            }
            //consume ')'
            consume(lookahead);
            
        }
        else
        {
            throw new ParseError();
        }
        
        return value;
    }

    public int num() throws IOException, ParseError 
    {
        //-> digit num | e
        StringBuilder dec_str = new StringBuilder();
        int decimal_num;
        int value;
        
        if(isDigit(lookahead))
        {
            
            while(isDigit(lookahead))
            {
                
                value = digit();
                dec_str.append(value);
                consume(lookahead);

            }
            decimal_num = Integer.valueOf(dec_str.toString());
            return decimal_num;

        }
        
        throw new ParseError();
    }

    public int digit() throws IOException, ParseError 
    {
        return lookahead - '0';
    }
    
    // int a = lookahead - '0';
    // int b = 5;
    // int c = 1;
    // StringBuilder con = new StringBuilder();
    // con.append(a);
    // con.append(b);
    // con.append(c);
    // int k = Integer.valueOf(con.toString());

    // System.out.println(k+"ok"+lookahead+"\n");
    
    
}

