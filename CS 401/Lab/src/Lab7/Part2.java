package Lab7;

public class Part2 {
    public static int minimum(int A[], int size) {
        if (size == 1) {
            return A[0];
        }
        else {
            int rest[] = new int [size - 1];
            for (int i = 1; i < size; i++) {
                rest[i - 1] = A[i];
            }
            if (A[0] <= minimum(rest, size - 1)) {
                return A[0];
            }
            else {
                return minimum(rest, size - 1);
            }
        }
    }
    public static void main(String args[]) {
        int A[] = {10, -20, 1, 2, 0, 5, 100};
        int s = minimum(A, A.length);
        System.out.println(s);
    }
}
