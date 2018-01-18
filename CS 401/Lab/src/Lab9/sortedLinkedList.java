package Lab9;

public class sortedLinkedList<T> extends LinkedList {

    public sortedLinkedList(int max) throws NullPointerException {
        super(max);
    }

    @Override
    public void add(Comparable newInfo) throws StackOverflowError {
        if (is_full()) {
            new StackOverflowError("List is full.");
        }
        if (ref == null) {
            ref = new node(newInfo);
            n++;
        } else if (ref.getLink() == null) {
            if (newInfo.compareTo(ref.getInfo()) > 0) {
                ref.setLink(new node(newInfo));
            } else {
                node newNode = new node(newInfo);
                newNode.setLink(ref);
                ref = newNode;
            }
            n++;
        } else {
            node pos = ref;
            if (newInfo.compareTo(pos.getInfo()) < 0) {
                node newNode = new node(newInfo);
                newNode.setLink(ref);
                ref = newNode;
            } else {
                while (pos.getLink() != null) {
                    if (newInfo.compareTo(pos.getLink().getInfo()) < 0) {
                        break;
                    }
                    pos = pos.getLink();
                }
                node newNode = new node(newInfo);
                if (pos.getLink() != null) {
                    newNode.setLink(pos.getLink());
                    pos.setLink(newNode);
                } else {
                    pos.setLink(new node(newInfo));
                }
            }
            n++;
        }
    }

    public void check() {
        node pos = ref;
        if (pos != null) {
            System.out.println(pos.info.toString());
            while (pos.getLink() != null) {
                pos = pos.getLink();
                System.out.println(pos.info.toString());
            }
        }
    }

}
