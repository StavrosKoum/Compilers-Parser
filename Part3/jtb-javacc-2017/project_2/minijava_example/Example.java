class Example {
    public static void main(String[] args) {
    }
}

class A {
    int[] i;
    A a;
    int b;
    
    

    public int foo(int i, int j) { return i+j; }
    public int bar(){ return 1; }
}

class B extends A {
    int b_var_0;
    
    
    
    

    public int foo(int i, int j) { i = b; return i+j; }
    public boolean foobar(boolean k){ k = true; return true; }
}
