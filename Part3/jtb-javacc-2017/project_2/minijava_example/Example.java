class Example {
    public static void main(String[] args) {
    }
}

class A {
    int[] i;
    A a;
    
    

    public int foo(int i, int j) { return i+j; }
    public int bar(){ return 1; }
}

class B extends A {
    int b_var_0;
    
    
    

    public int foo(int i, int j) { int b_var_0; return b_var_0; }
    public boolean foobar(boolean k){ return true; }
}
