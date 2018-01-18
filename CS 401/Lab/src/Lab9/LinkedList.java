package Lab9;

public abstract class LinkedList<Comparable> {

    public abstract void add(java.lang.Comparable newInfo) throws StackOverflowError;

    /**
     * LinkedList interface for Lab 9.
     *
     * @param <Comparable>
     */

    protected class node<Comparable> {
        /**
         * Node for linked list.
         */
        Comparable info;
        node link;

        protected node(Comparable info) {
            this.info = info;
        }

        public node getLink() {
            return link;
        }

        public Comparable getInfo() {
            return info;
        }

        protected void setLink(node link) {
            this.link = link;
        }

        protected void setInfo(Comparable info) {
            this.info = info;
        }

    }

    protected node ref;
    protected int max;
    protected int n;

    public LinkedList(int max) throws NullPointerException {
        /**
         * Constructor
         */
        if (max <= 0) {
            throw new NullPointerException("You cannot build a empty list.");
        } else {
            this.ref = null;
            this.max = max;
            this.n = 0;
        }
    }

    public boolean is_full() {
        if (n < max) {
            return false;
        } else {
            return true;
        }
    }

    protected void add(Comparable newInfo) throws StackOverflowError {
        if (is_full()) {
            throw new StackOverflowError("List is full.");
        } else if (ref == null) {
            ref = new node(newInfo);
            n++;
        } else {
            node pos = ref;
            while (pos.getLink() != null) {
                pos = pos.getLink();
            }
            pos.setLink(new node(newInfo));
            n++;
        }
    }

    public void remove(int target) throws IndexOutOfBoundsException {
        if (target < 0 || target > n) {
            throw new IndexOutOfBoundsException("No such node.");
        } else if (target == 1) {
            if (ref.getLink() != null) {
                ref = ref.getLink();
            } else {
                ref = null;
            }
        } else {
            node pos = ref;
            int posn = 1;
            while (posn != target - 1) {
                pos = pos.getLink();
                posn++;
            }
            pos.setLink(pos.getLink().getLink());
        }
    }

}
