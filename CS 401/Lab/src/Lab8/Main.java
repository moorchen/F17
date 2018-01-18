package Lab8;
import Lab4.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {

        Lab8.LinkedListQueue<Employee> employeeList = new Lab8.LinkedListQueue<>();
        Lab8.FixedArrayQueue<Employee> employeeList1 = new Lab8.FixedArrayQueue<>(100);
        Lab8.FloatingArrayQueue<Employee> employeeList2 = new Lab8.FloatingArrayQueue<>();

        try{
            File emp = new File("emp.txt");
            Scanner scannerEmp = new Scanner(emp);
            while (scannerEmp.hasNext()) {
                Employee e = new Employee(scannerEmp.next(), Integer.valueOf(scannerEmp.next()));
                employeeList.enqueue(e);
                employeeList1.enqueue(e);
                employeeList2.enqueue(e);
            }
            scannerEmp.close();
            employeeList.check();
            employeeList1.check();
            employeeList2.check();
        }
        catch (FileNotFoundException e) {
            System.out.println("ERROR!");
        }

        System.out.println("Please Insert A Possible Palindrome String");
        Scanner input = new Scanner(System.in);  //  make input variable
        String pa = input.nextLine();  //  read input
        input.close();
        DoublyLinkedList dll = new DoublyLinkedList();
        for(int i = 0; i < pa.length(); i++) {
            dll.add(pa.charAt(i));
        }
        dll.check();

    }
}
