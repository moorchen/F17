package Lab8;

public class DoublyLinkedList<T> {

    node front, rear;

    private class node<T> {

        T info;
        node forwardLink;
        node backwardLink;

        node(T info) {
            this.info = info;
        }

        void setForwardLink(node forwardNode) {
            this.forwardLink = forwardNode;
        }

        void setBackwardLink(node backwordNode) {
            this.backwardLink = backwordNode;
        }

        node getForwardLink() {
            return forwardLink;
        }

        node getBackwardLink() {
            return backwardLink;
        }

    }

    DoublyLinkedList() {
        front = null;
        rear = null;
    }

    public void add(T info) {
        if (front == null) {
            front = new node(info);
            rear = front;
        }
        else {
            node temp = rear;
            rear.setForwardLink(new node(info));
            rear = rear.getForwardLink();
            rear.setBackwardLink(temp);
        }
    }

    private boolean recursiveCheck(node posFront, node posRear) {
        if(posFront == posRear) {
            return true;
        }
        else if(posFront.getForwardLink() == posRear) {
            if(posFront.info == posRear.info) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            if(posFront.info == posRear.info) {
                return recursiveCheck(posFront.getForwardLink(), posRear.getBackwardLink());
            }
            else {
                return false;
            }
        }
    }


    public void check() {
        node posFront = front;
        node posRear = rear;
        if(recursiveCheck(front, rear)) {
            System.out.println("This is a palindrome");
        }
        else {
            System.out.println("This is not a palindrome");
        }
    }

}
