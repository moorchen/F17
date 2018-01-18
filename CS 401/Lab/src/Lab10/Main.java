package Lab10;

public class Main {
    public static void main(String args[]) {
        DoublyLinkedList test = new DoublyLinkedList();
        test.add(10);
        test.add(210);
        test.add(40);
        test.add(170);
        test.add(100);
        test.add(880);
        test.add(1230);
        test.add(16770);
        //test.check();
        test.add(Where.FRONT, 888);
        test.add(Where.REAR, 666);
        //test.check();
        System.out.println(test.contains(10));
        test.print_from_beginning();
        test.print_from_end();


    }
}
