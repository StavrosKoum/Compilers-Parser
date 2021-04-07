import java.io.IOException;

class Main {
    public static void main(String[] args) {
        try {
            System.out.println((new TernaryEvaluator(System.in)).expr());
        } catch (IOException | ParseError e) {
            System.err.println(e.getMessage());
        }
    }
}
