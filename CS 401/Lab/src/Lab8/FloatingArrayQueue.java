package Lab8;

public class FloatingArrayQueue<T> {

    private T[] q;
    int front, rear, n;

    public FloatingArrayQueue() {
        q = (T[]) new Object[2];
        front = 0;
        rear = 0;
        n = 0;
    }

    public void enqueue(T info) throws IndexOutOfBoundsException {
        if (n == q.length) {
            resize(2 * q.length);
        }
        q[rear] = info;
        rear = (rear + 1) % q.length;
        n++;
    }

    public void dequeue() throws IndexOutOfBoundsException {
        if (n == 0) {
            throw new IndexOutOfBoundsException("EMPTY");
        }
        else {
            q[front] = null;
            front = (front + 1) % q.length;
            n--;
            if (n < q.length / 4) {
                resize(q.length / 2);
            }
        }
    }

    private void resize(int s) {
        int pos = front;
        int newPos = 0;
        T[] newQ = (T[]) new Object[s];
        while (newPos < n) {
            newQ[newPos] = q[pos];
            newPos++;
            pos = (pos + 1) % q.length;
        }
        q  = newQ;
        front = 0;
        rear = n;
    }

    public void check() {
        int pos = front;
        System.out.println("\n");
        while (pos < rear) {
            System.out.println(q[pos].toString());
            pos++;
        }
    }

}
