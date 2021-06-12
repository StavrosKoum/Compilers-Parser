class Factorial{
    public static void main(String[] a){
        System.out.println(new Fac().ComputeFac(10));
    }
}

class Fac {
// public int ComputeFac1(int num){return 1;}
//public int ComputeFac2(int num){return 1;}
        int num_aux1 ;
        // int num_aux2 ;
        // int num_aux3 ;
        // boolean an;
    public int ComputeFac(int num){
        int num_aux ;
        num_aux1 = 1;
        // 
        // if(!true)
        // System.out.println(666);
        // else
        // System.out.println(666);
        // 
        // while(num_aux<2)
        // {
        //     System.out.println(777);
        //     num_aux = 3;
        //     
        // }
        num_aux1 = 787 ; 
        if ((num < 1) && (1<4))
            num_aux = 1 ;
        else
           num_aux = num * (this.ComputeFac(num-1)) ;
            //num_aux = num * 40 ;
        return num_aux ;
    }
    public int testfun()
    {
        return 1;
    }
    
}

class B extends Fac {
    //int b_var_0;
    public boolean foobar(){return true; }
    public int ComputeFac(int num){
        
        return 1 ;
    }
    
    
}
