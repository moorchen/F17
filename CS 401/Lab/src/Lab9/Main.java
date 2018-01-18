package Lab9;

import Lab4.Employee;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        sortedLinkedList employeeList = new sortedLinkedList(100);
        try{
            File emp = new File("emp.txt");
            Scanner scannerEmp = new Scanner(emp);
            while (scannerEmp.hasNext()) {
                Employee e = new Employee(scannerEmp.next(), Integer.valueOf(scannerEmp.next()));
                employeeList.add(e);
            }
            scannerEmp.close();
            employeeList.check();
        }
        catch (FileNotFoundException e) {
            System.out.println("ERROR!");
        }
        CS401ArrayImpl myArray = new CS401ArrayImpl();
        myArray.add(1);
        myArray.add(2);
        myArray.add(3);
        myArray.add(4);
        myArray.add(5);
        myArray.add(6);
        myArray.add(7);
        myArray.add(8);
        myArray.add(9);
        myArray.add(9);
        myArray.add(9);
        myArray.add(9);
        myArray.add(9);
        myArray.add(9);
        myArray.add(9);
        myArray.add(9);
        System.out.println(myArray.get(0));
        System.out.println(myArray.contains("a"));
        myArray.remove(1);
        System.out.println(myArray.get(0));
        System.out.println(myArray.get(1));
        System.out.println(myArray.get(7));
        System.out.println(myArray.get(8));
        //System.out.println(myArray);
        System.out.println(myArray.get(9));
        System.out.println(myArray.get(10));
        System.out.println(myArray.get(11));
        System.out.println(myArray.get(12));

    }
}
