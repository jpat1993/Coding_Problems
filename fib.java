import java.io.*;

class fib {
    public static void main (String[] args) {
        //code
        System.out.println(fib(4));
    }
    
    public static int fib(int n) {
        if(n == 1 || n == 0) {
            return n;
        }
        int n1 = 1;
        int n2 = 1;
        int current = 2;
        for (int i = 3; i <= n; i++) {
            current = n1 + n2;
            n2 = n1;
            n1 = current;
        }
        return current;
    }
}