package Lab10;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> {

    protected node front, rear;
    protected int n;

    public DoublyLinkedList() {
        this.front = null;
        this.rear = null;
        this.n = 0;
    }

    protected class DLLIterator implements ListIterator<E> {

        public DLLIterator(node pos) {
            this.pos = pos;
            this.posn = indexOfNode(pos);
        }

        node pos;
        int posn;

        @Override
        public boolean hasNext() {
            return posn < n;
        }

        @Override
        public E next() {
            if (hasNext()) {
                E temp = pos.info;
                pos = pos.getNext();
                posn++;
                return temp;
            } else {
                throw new NoSuchElementException();
            }

        }

        @Override
        public boolean hasPrevious() {
            return posn >= 0;
        }

        @Override
        public E previous() {
            if (hasPrevious()) {
                E temp = pos.info;
                posn--;
                pos = pos.getPrevious();
                return temp;
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public int nextIndex() {
            return posn + 1;
        }

        @Override
        public int previousIndex() {
            return posn - 1;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(E e) {

        }

        @Override
        public void add(E e) {

        }
    }

    protected class node {

        E info;
        node previous;
        node next;

        public node(E info) {
            this.info = info;
        }

        public E getInfo() {
            return info;
        }

        public node getPrevious() {
            return previous;
        }

        public node getNext() {
            return next;
        }

        public void setPrevious(node previous) {
            this.previous = previous;
        }

        public void setNext(node next) {
            this.next = next;
        }

    }

    protected int indexOfNode(node target) {
        node pos = front;
        int posn = 0;
        while (pos != null) {
            if (pos == target) {
                return posn;
            }
            pos = pos.getNext();
            posn++;
        }
        throw new IndexOutOfBoundsException();
    }

    public E remove(int i) {
        if (i < 0 || i >= n) throw new IndexOutOfBoundsException();
        int posn = 0;
        node pos = front;
        while (pos != null) {
            if (posn == i) {
                E temp = pos.info;
                if (posn == 0) {
                    if (n == 1) {
                        front = rear = null;
                        n--;
                        return temp;
                    }
                    front = front.getNext();
                    n--;
                    return temp;
                }
                if (posn == n - 1) {
                    rear = rear.getPrevious();
                    rear.setNext(null);
                    n--;
                    return temp;
                }
                pos.getPrevious().setNext(pos.getNext());
                pos.getNext().setPrevious(pos.getPrevious());
                n--;
                return temp;
            }
            posn++;
            pos = pos.getNext();
        }
        return null;
    }

    public boolean contains(E e) {
        node pos = front;
        while (pos != null) {
            if (pos.info.equals(e)) {
                return true;
            }
            pos = pos.getNext();
        }
        return false;
    }

    public boolean add(E e) {
        add(Where.REAR, e);
        return true;
    }

    public boolean add(Where where, E e) {
        switch (where) {
            case FRONT: {
                if (front == null) {
                    front = rear = new node(e);
                } else {
                    node newNode = new node(e);
                    front.setPrevious(newNode);
                    newNode.setNext(front);
                    front = newNode;

                }
                n++;
                return true;
            }
            case REAR: {
                if (rear == null) {
                    rear = front = new node(e);
                } else {
                    node newNode = new node(e);
                    rear.setNext(newNode);
                    newNode.setPrevious(rear);
                    rear = newNode;
                }
                n++;
                return true;
            }
            case MIDDLE: {
                return false;
            }
        }
        return false;
    }

    public boolean add(Where where, int index, E e) throws IndexOutOfBoundsException {
        switch (where) {
            case FRONT: {
                add(Where.FRONT, e);
                return true;
            }
            case REAR: {
                add(Where.REAR, e);
                return true;
            }
            case MIDDLE: {
                if (index < 0 || index >= n) {
                    throw new IndexOutOfBoundsException();
                }
                node pos = front;
                int posn = 0;
                while (pos != null) {
                    if (posn == index) {
                        node newNode = new node(e);
                        newNode.setPrevious(pos.getPrevious());
                        newNode.setNext(pos);
                        pos.getPrevious().setNext(newNode);
                        pos.setPrevious(newNode);
                        if (posn == 0) front = newNode;
                        if (posn == n - 1) rear = newNode;
                        n++;
                        return true;
                    }
                    posn++;
                    pos = pos.getNext();
                }
                return false;
            }
        }
        return false;
    }

    public void check() {
        node pos = front;
        while (pos != null) {
            System.out.println(pos.info.toString());
            pos = pos.getNext();
        }
    }

    public void print_from_beginning() {
        ListIterator myIt = new DLLIterator(front);
        while (myIt.hasNext()) {
            System.out.println(myIt.next().toString());
        }
    }

    public void print_from_end() {
        ListIterator myIt = new DLLIterator(rear);
        while (myIt.hasPrevious()) {
            System.out.println(myIt.previous().toString());
        }
    }


}
