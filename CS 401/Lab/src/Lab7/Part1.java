package Lab7;

public class Part1 {

    /*
    This is the recursive method
     */
    static long Jacobsthal_recursive (int n) {
        if (n ==  0) {
            return 0;
        }
        else if (n == 1) {
            return 1;
        }
        else {
            return Jacobsthal_recursive(n - 1) + 2 * Jacobsthal_recursive(n - 2);
        }
    }

    /*
    This is the iterative method
    */
    static long Jacobsthal_iterative (int n) {
        if (n ==  0) {
            return 0;
        }
        else if (n == 1) {
            return 1;
        }
        else {
            long result = 0;
            long last1 = 1;
            long last2 = 0;
            for(int i = 2; i <= n; i++) {
                result = last1 + 2 * last2;
                last2 = last1;
                last1 = result;
            }
            return result;
        }
    }

    /*
    This is a method to call both methods
     */
    static void Jacobsthal (int n) {
        n = n - 1;
        if (n < 0) {
            try {
                throw new negativeException();
            } catch (negativeException e) {
                System.out.println("Error! Please insert a positive number or zero.");
            }
        }
        else {
            String seq = "0";
            if (n > 0) {
                for (int i = 1; i <= n; i++){
                    seq = seq + ", " + Jacobsthal_iterative(i);
                }
            }
            double start, end;
            System.out.println("Recursive version: " + seq);
            start = System.currentTimeMillis();
            long r = Jacobsthal_recursive(n);
            end = System.currentTimeMillis() - start;
            System.out.println("Time taken to execute recursive version: " + end +" msec");
            System.out.println("Iterative version: " + seq);
            start = System.currentTimeMillis();
            long i = Jacobsthal_iterative(n);
            end = System.currentTimeMillis() - start;
            System.out.println("Time taken to execute iterative version: " + end +" msec");
        }
    }

    public static void main(String[] args){
        Jacobsthal(30);
        for (int i = 0; i <= Integer.MAX_VALUE; i++) {
            if (Jacobsthal_iterative(i) < 0) {
                System.out.println("OVERFLOWING!");
                System.out.println("x = " + (i - 1));
                System.out.println("Max Jacobsthal number = " + Jacobsthal_iterative(i - 1));
                break;
            }
        }
    }

    private static class negativeException extends Throwable {
    }
}
