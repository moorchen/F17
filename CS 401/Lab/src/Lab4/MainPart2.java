/*

//  Below is code I write to find out how to accomplish this work, please skip these code.

package Lab4;

import java.util.*;
import java.io.*;

class Employee {
    public int ID;
    public String Name;
    public String getName(){  //  getter
        return this.Name;
    }
    public int getID(){
        return this.ID;
    }
    public void setID(int newid){  //  setter
        this.ID = newid;
    }
    public void setName(String newname){
        this.Name = newname;
    }
    public Employee(String checkname, int checkid){  //  constructor
        this.setName(checkname);
        this.setID(checkid);
    }
    public boolean Check(Employee another){
        boolean bName;
        boolean both = false;
        int check1 = this.Name.compareTo(another.Name);  //  use compareTo()
        if (check1 == 0) {  //  use equals()
            bName = true;
            if (compare(this.ID, another.ID) && bName) {
                both = true;
            }
        }
        return both;
    }
    private boolean compare(int a, int b) {  //  use compare()
        if (a == b) {
            return true;
        }
        return false;
    }
    public String toString(Employee another){  //  toString()
        if(this.Check(another)){
            return "Same person.";
        }
        else {
            return "These are different employee.";
        }
    }
}

class MainPart2 {

    public static void main(String[] args) {
        // Read file
        ArrayList<String> Name = new ArrayList<>();
        ArrayList<Integer> ID = new ArrayList<>();
        try{
            //File emp = new File("D:\\文件\\学校文件\\CS 401\\Lab\\src\\Lab4\\emp.txt");
            File emp = new File("emp.txt");
            Scanner scannerEmp = new Scanner(emp);
            while (scannerEmp.hasNext()) {
                Name.add(scannerEmp.next());
                ID.add(Integer.valueOf(scannerEmp.next()));
            }
            scannerEmp.close();
            System.out.println(Name);
            System.out.println(ID);
        }
        catch (FileNotFoundException e) {
            System.out.println("ERROR!");
        }
        for (int i = 0; i < Name.size(); i++) {
            int temp;
            String tempName;
            int mmNum = i;
            int mm = ID.get(i);
            String mmName = Name.get(i);
            for (int j = i + 1; j < Name.size(); j++) {
                if (ID.get(j) < mm) {
                    mm = ID.get(j);
                    mmName = Name.get(j);
                    mmNum = j;
                }
            }
            temp = ID.get(i);
            tempName = Name.get(i);
            ID.set(i, mm);
            Name.set(i, mmName);
            ID.set(mmNum, temp);
            Name.set(mmNum, tempName);
        }
        System.out.println(Name);
        System.out.println(ID);

        // Read file
        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        try{
            //File emp = new File("D:\\文件\\学校文件\\CS 401\\Lab\\src\\Lab4\\emp.txt");
            File emp = new File("emp.txt");
            Scanner scannerEmp = new Scanner(emp);
            while (scannerEmp.hasNext()) {
                employeeList.add(new Employee(scannerEmp.next(), Integer.valueOf(scannerEmp.next())));
            }
            scannerEmp.close();
            //System.out.println(employeeList);
        }
        catch (FileNotFoundException e) {
            System.out.println("ERROR!");
        }
        for (int i = 0; i < employeeList.size(); i++) {
            int mmNum = i;
            int mm = employeeList.get(i).getID();
            String mmName = employeeList.get(i).getName();
            for (int j = i + 1; j < employeeList.size(); j++) {
                if (employeeList.get(j).getID() < mm) {
                    mm = employeeList.get(j).getID();
                    mmName = employeeList.get(j).getName();
                    mmNum = j;
                }
            }
            Employee temp = employeeList.get(i);
            employeeList.set(i, employeeList.get(mmNum));
            employeeList.set(mmNum, temp);
        }
        try {
            Formatter formatter = new Formatter("sorted emp.txt");
            for (int i = 0; i < employeeList.size(); i++) {
                formatter.format("%s %s %s", employeeList.get(i).getName(), employeeList.get(i).getID(), "\r\n");
            }
            formatter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
*/

package Lab4;

import java.util.*;
import java.io.*;

class MainPart2 {

    public static void main(String[] args) {
        //  Read file
        ArrayList<Employee> employeeList = new ArrayList<>();
        try{
            File emp = new File("emp.txt");
            Scanner scannerEmp = new Scanner(emp);
            while (scannerEmp.hasNext()) {
                employeeList.add(new Employee(scannerEmp.next(), Integer.valueOf(scannerEmp.next())));
            }
            scannerEmp.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("ERROR!");
        }
        //  Read file over
        double start, end;
        start = System.currentTimeMillis();
        //  Bubble sort
        for (int i = 0; i < employeeList.size(); i++) {
            for (int j = 1; j < employeeList.size() - i; j++) {
                if (employeeList.get(j).getID() < employeeList.get(j-1).getID()) {
                    Employee temp = employeeList.get(j-1);
                    employeeList.set(j-1, employeeList.get(j));
                    employeeList.set(j, temp);
                }
            }
        }
        //  Bubble sort over
        end = System.currentTimeMillis() - start;
        System.out.println("My algorithm of bubble sort spends " + end + " ms");
        //  Write file
        try {
            Formatter formatter = new Formatter("sorted emp.txt");
            for (Employee anEmployeeList : employeeList) {
                formatter.format("%s %s %s", anEmployeeList.getName(), anEmployeeList.getID(), "\r\n");
            }
            formatter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //  Write file over
    }

}


