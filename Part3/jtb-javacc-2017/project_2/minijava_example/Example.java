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
    int i;
    

    public int foo(int i, int j) { int h; return h*j; }
    public boolean foobar(boolean k){ return true; }
}
