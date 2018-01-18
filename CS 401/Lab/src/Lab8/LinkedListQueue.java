package Lab8;

public class LinkedListQueue<T>{

    public node ref = null;

    private class node<T> {

        private T info;
        private node link;

        private node(T info) {
            this.info = info;
        }

        private node getLink() {
            return this.link;
        }

        private void setLink(node link) {
            this.link = link;
        }

    }

    public void enqueue(T info) {
        if (ref == null) {
            ref = new node(info);
        }
        else {
            node pos = ref;
            while(pos.getLink() != null) {
                pos = pos.getLink();
            }
            pos.setLink(new node(info));
        }
    }

    public void dequeue() throws IndexOutOfBoundsException {
        if(ref == null) {
            throw new IndexOutOfBoundsException("There is no node to dequeue");
        }
        else {
            ref = ref.getLink();
        }
    }

    public void check() {
        if(ref == null) {
            System.out.println("Empty queue.");
        }
        else {
            node pos = ref;
            String output = pos.info.toString() + "\n";
            while(pos.getLink() != null) {
                pos = pos.getLink();
                output = output + pos.info.toString() + "\n";
            }
            System.out.println(output);
        }
    }

}
