class Example {
    public static void main(String[] args) {
       
    }
}

// class A {
//     int[] i;
//     A a;
//     int b;
//     int c;
//     
//     

//     public int foo(int i, int j) { return i+j; }
//     public int bar(){ return 1; }
// }

// class B extends A {
//     //int b_var_0;
//     
//     
//     int b_var_0;
//     

//     public int foo(int i, int j) 
//     { 
//       
//          return i+j; 
//     }
//     public boolean foobar(boolean k){return true; }
// }




// class Factorial{
//     public static void main(String[] a){
//         System.out.println(new Fac().ComputeFac(10));
//     }
// }

class Fac {
    public int ComputeFac(int num){
        int num_aux ;
        if (num < 1)
            num_aux = 1 ;
        else
            num_aux = num * (this.ComputeFac(num-1)) ;
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
