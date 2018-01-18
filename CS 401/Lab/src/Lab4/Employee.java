package Lab4;

public class Employee implements Comparable {
    public int ID;
    public String Name;

    public String getName() {  //  getter
        return this.Name;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int newid) {  //  setter
        this.ID = newid;
    }

    public void setName(String newname) {
        this.Name = newname;
    }

    public Employee(String checkname, int checkid) {  //  constructor
        this.setName(checkname);
        this.setID(checkid);
    }

    public boolean Check(Employee another) {
        boolean bName;
        boolean both = false;
        int check1 = this.Name.compareTo(another.Name);  //  use compareTo()
        if (check1 == 0) {  //  use equals()
            bName = true;
            if (compare(this.ID, another.ID)) {
                both = true;
            }
        }
        return both;
    }

    private boolean compare(int a, int b) {  //  use compare()
        return a == b;
    }

    public String toString(Employee another) {  //  toString()
        if (this.Check(another)) {
            return "Same person.";
        } else {
            return "These are different employee.";
        }
    }

    public String toString() {
        return this.ID + " " + this.Name;
    }

    @Override
    public int compareTo(Object o) {
        Employee e = (Employee) o;
        return this.ID - e.ID;
    }

}
