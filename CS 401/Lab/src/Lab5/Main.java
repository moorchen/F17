package Lab5;
import java.util.*;
import java.io.*;
import Lab4.*;

class myArray {
    ArrayList<Employee> myAr = new ArrayList<>();
    public void push(String name, int ID) {
        myAr.add(new Employee(name, ID));
    }
    public void pop() {
        if (myAr.isEmpty())
            System.out.println("This arrayList is already empty!");
        else
        myAr.remove((myAr.size() - 1));
    }
    public void peek() {
        if (myAr.isEmpty())
            System.out.println("This arrayList is already empty!");
        else
            System.out.println("Name: " + myAr.get(myAr.size()-1).getName() + "  ID: " + myAr.get(myAr.size()-1).getID());
    }
}

class Main {

    public static void main(String[] args) {
        //  Read file
        myArray employeeList = new myArray();
        try {
            File emp = new File("emp.txt");
            Scanner scannerEmp = new Scanner(emp);
            while (scannerEmp.hasNext()) {
                employeeList.push(scannerEmp.next(), Integer.valueOf(scannerEmp.next()));
            }
            scannerEmp.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR!");
        }
        //  Read file over
        employeeList.peek();
        employeeList.pop();
        employeeList.pop();
        employeeList.peek();
    }
}

