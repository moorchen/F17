package Lab8;

import java.util.ArrayList;

public class FixedArrayQueue<T> {

    private ArrayList<T> q;
    int front, rear, n;

    private int length() {
        return rear - front;
    }

    public FixedArrayQueue(int n) {
        q = new ArrayList<T>();
        front = 0;
        rear = 0;
        this.n = n;
    }

    public void enqueue(T info) throws IndexOutOfBoundsException {
        if (rear >= n) {
            throw new IndexOutOfBoundsException("FULL");
        }
        else {
            q.add(info);
            rear++;
        }
    }

    public void dequeue() throws IndexOutOfBoundsException {
        if (front == rear) {
            throw new IndexOutOfBoundsException("EMPTY");
        }
        else {
            q.set(front, null);
            front++;
        }
    }

    public void check() {
        int pos = front;
        while (pos < rear) {
            System.out.println(q.get(pos).toString());
            pos++;
        }
    }
}
