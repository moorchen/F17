package Lab3;

import java.util.Scanner;

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
public class Main {

    public static void main(String[] args){
        Scanner input = new Scanner(System.in); //  scan begin
        System.out.println("Please insert the name of first employee:");
        String name1 = input.nextLine();
        System.out.println("Please insert the name of second employee:");
        String name2 = input.nextLine();
        System.out.println("Please insert the ID of first employee:");
        int id1 = input.nextInt();
        System.out.println("Please insert the ID of second employee:");
        int id2 = input.nextInt();
        input.close();  //  scanover
        Employee e1 = new Employee(name1, id1);
        Employee e2 = new Employee(name2, id2);
        System.out.println(e1.toString(e2));
    }
}
