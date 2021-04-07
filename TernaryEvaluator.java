import java.io.InputStream;
import java.io.IOException;
import java.lang.Math;
/*
* -------------------------------------------------------------------------
* 	        |     '0' .. '9'     |  ':'    |       '?'          |  $    |
* -------------------------------------------------------------------------
* 	        |		             |	       |	                |       |
* Tern      | '0'..'9' TernTail  |  error  |       error        | error |
*           | 	   	             |	       |    	            |       |
* -------------------------------------------------------------------------
*           |		             |	       |		            |       |
* TernTail  |       error	     |    e    |  '?' Tern ':' Tern |   e   |
* 	        |	  	             |	       |    	     	    |       |
* -------------------------------------------------------------------------
*/


class TernaryEvaluator {
    private final InputStream in;

    private int lookahead;

    public TernaryEvaluator(InputStream in) throws IOException {
        this.in = in;
        lookahead = in.read();
    }

    private void consume(int symbol) throws IOException, ParseError {
        if (lookahead == symbol)
            lookahead = in.read();
        else
            throw new ParseError();
    }

    private boolean isDigit(int c) {
        return '0' <= c && c <= '9';
    }

    private int evalDigit(int c) {
        return c - '0';
    }

    public int eval() throws IOException, ParseError {
       
        num();
        
        int value = Tern();

        if (lookahead != -1 && lookahead != '\n')
            throw new ParseError();

        return value;
    }

    private int Tern() throws IOException, ParseError {
        if (isDigit(lookahead)) {
            int cond = evalDigit(lookahead);

            consume(lookahead);
            return TernTail(cond); 
        }

        throw new ParseError();
    }

    private int TernTail(int condition) throws IOException, ParseError {
        switch (lookahead) {
            case '?':
                consume('?');
                int left = Tern();
                consume(':');
                int right = Tern();

                return condition != 0 ? left : right;
            case -1:
            case '\n':
            case ':':
                return condition;
        }

        throw new ParseError();
    }

    

    /////////////////////////////////////////////
    public int expr() throws IOException, ParseError 
    {
        
        // int test = isDigit(lookahead);
        // System.out.println("ok"+test+"\n");

        if (isDigit(lookahead) || lookahead == '(')
        {
            //->term exprtail
            int value = term();
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
        value = Math(value,pow);


        return value;
    }

    public int termtail() throws IOException, ParseError 
    {

        int ret = 1;
        switch (lookahead) 
        {
            case '+':
            case '-':
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
                ret = Math.pow(val,val2);
                return ret;





            case ')':
                //return 1?
            case '\n':
                //return 1?

               
        }






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

