package Lab4;

import java.util.Date;
import java.util.Timer;
class MainPart1 {
    public static void main (String args []) throws InterruptedException {
        run_method_A(250);
        run_method_A(500);
        run_method_A(1000);
        run_method_A(2000);
        System.out.println();
        run_method_B(250);
        run_method_B(500);
        run_method_B(1000);
        run_method_B(2000);
        System.out.println();
        run_method_C(250);
        run_method_C(500);
        run_method_C(1000);
        run_method_C(2000);
        System.out.println();
    }

    public static void run_method_A(int n) {
        int i = 0;
        double start, end;
        start = System.currentTimeMillis();
        methodA(n);
        end = System.currentTimeMillis() - start;
        System.out.println("methodA(n = " + n + ") time = " + end + "ms");
    }
    public static void run_method_B(int n) {
        int i = 0, loop = 1000;
        double start, end;
        start = System.currentTimeMillis();
        for (i = 0; i < loop; i++) {
            methodB(n);
        }
        end = System.currentTimeMillis() - start;
        System.out.println("methodE(n = " + n + ") time = " + end/loop + "ms");
    }
    public static void run_method_C(int n) {
        int i = 0;
        double start, end;
        start = System.currentTimeMillis();
        methodC(n);
        end = System.currentTimeMillis() - start;
        System.out.println("methodC(n = " + n + ") time = " + end + "ms");
    }
    public static void methodA (int n){
        int i = 0;
        int j = 0;
        int k = 0;
        int total = 0;
        while (i<n){
            while (j<n){
                while (k<n) {
                    total++;
                    k++;
                }
                k=0;
                j++;
            }
            j=0;
            i++;
        }
    }

    public static void methodB (int n){
        int i = 0;
        int j = 0;
        int total = 0;
        while (i<n){
            while (j<n){
                total++;
                j++;
            }
            i++;
        }
    }
    public static void methodC (int n){
        int i = 0;
        int j = 0;
        int total = 0;
        j = n;
        while ((j = j/2) > 0) {
            for (i = 0; i < 100*n; i++)
                total++;
        }
    }
}
