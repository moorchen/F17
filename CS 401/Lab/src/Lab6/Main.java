package Lab6;
import Lab4.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Character.*;

class myList {
    node head;
    static class node {
        Employee emp;
        node next;
        node(Employee i) {
            emp = i;
            next = null;
        }
        void setLink(node j) {
            this.next = j;
        }
        node getLink() {
            return this.next;
        }
    }
    public void push(String name, int ID) {
        Employee newEmployee = new Employee(name, ID);
        node newNode = new node(newEmployee);
        newNode.setLink(head);
        head = newNode;
    }
    public void pop() {
        node newHead = head.getLink();
        head = newHead;
    }
    public void peek() {
        System.out.println(head.emp.Name + " " + head.emp.ID);
    }
}

class postfixCalculator {
    public class myStack<T> {
        ArrayList<T> myS = new ArrayList<>();
        void push(T n) {
            myS.add(n);
        }
        void pop() {
            if(myS.size() > 0)
                myS.remove(myS.size() - 1);
            else
                System.out.println("ERROR");
        }
        T peek() {
            return myS.get(myS.size() - 1);
        }
    }
    void doCalculate(String expression) {
        System.out.println("Input postfix expression: " + expression);
        myStack<Integer> buffer = new myStack();
        char[] exp = expression.toCharArray();
        for(char c: exp) {
            if(isDigit(c)) {
                buffer.push(getNumericValue(c));
            }
            else {
                int temp;
                switch (String.valueOf(c)) {
                    case "+":
                        temp = buffer.peek();
                        buffer.pop();
                        temp = temp + buffer.peek();
                        buffer.pop();
                        buffer.push(temp);
                        break;
                    case "-":
                        temp = buffer.peek();
                        buffer.pop();
                        temp = buffer.peek() - temp;
                        buffer.pop();
                        buffer.push(temp);
                        break;
                    case "*":
                        temp = buffer.peek();
                        buffer.pop();
                        temp = temp * buffer.peek();
                        buffer.pop();
                        buffer.push(temp);
                        break;
                    case "/":
                        temp = buffer.peek();
                        buffer.pop();
                        temp = buffer.peek() / temp;
                        buffer.pop();
                        buffer.push(temp);
                        break;
                }
            }
        }
        System.out.println("Result is: " + buffer.peek() +"\n");
    }
    void doCalculate(myStack<String> expression) {
        System.out.println("Input postfix expression: " + expression.myS);
        myStack<Integer> buffer = new myStack();
        for(int i = 0; i < expression.myS.size(); i++) {
            if(isNumeric(expression.myS.get(i))) {
                buffer.push(Integer.parseInt(expression.myS.get(i)));
            }
            else {
                int temp;
                switch (String.valueOf(expression.myS.get(i))) {
                    case "+":
                        temp = buffer.peek();
                        buffer.pop();
                        temp = temp + buffer.peek();
                        buffer.pop();
                        buffer.push(temp);
                        break;
                    case "-":
                        temp = buffer.peek();
                        buffer.pop();
                        temp = buffer.peek() - temp;
                        buffer.pop();
                        buffer.push(temp);
                        break;
                    case "*":
                        temp = buffer.peek();
                        buffer.pop();
                        temp = temp * buffer.peek();
                        buffer.pop();
                        buffer.push(temp);
                        break;
                    case "/":
                        temp = buffer.peek();
                        buffer.pop();
                        temp = buffer.peek() / temp;
                        buffer.pop();
                        buffer.push(temp);
                        break;
                }
            }
        }
        System.out.println("Result is: " + buffer.peek() +"\n");
    }
    String doConvert(String expression) {
        char[] exp = expression.toCharArray();
        System.out.println("Input infix expression: " + expression);
        int priorNumber = 0;
        int number = 0;
        for(char c : exp) {
            if (Objects.equals(String.valueOf(c), "*") || Objects.equals(String.valueOf(c), "/")) {
                priorNumber++;
            }
                number++;
        }
        myStack<String> compress = new myStack<>();
        compress.push(String.valueOf(exp[0]));
        for(int i = 1; i <  (number); i++) {
            if(Objects.equals(String.valueOf(exp[i]), "*") || Objects.equals(String.valueOf(exp[i]), "/")) {
                compress.pop();
                compress.push(String.valueOf(exp[i - 1]) + String.valueOf(exp[i + 1]) + String.valueOf(exp[i]));
                i = i + 1;
            }
            else {
                compress.push(String.valueOf(exp[i]));
            }
        }
        String result = compress.myS.get(0);
        for(int i = 1; i < (number - 2 * priorNumber + 1) / 2; i++) {
            result = result + compress.myS.get(2 * i) + compress.myS.get(2 * i - 1);
        }
        System.out.println("Converted to postfix expression: " + result);
        return result;
    }
    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i));
            if (!Character.isDigit(str.charAt(i))) {
                return false;
                }
        }
        return true;
    }
}


public class Main {
    public static void main(String[] args) {

        //  Part 1
        //  Read file
        System.out.println("This is Part 1 \n");
        myList employeeList = new myList();
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
        // Part 1 end

        //Part 2
        System.out.println("\nThis is Part 2 \n");
        postfixCalculator calculator = new postfixCalculator();
        calculator.doCalculate(calculator.doConvert("1+3*8"));
        calculator.doCalculate(calculator.doConvert("8-2+8/4+6-1-6/2"));
        calculator.doCalculate(calculator.doConvert("8-3-4*6+3"));
        //Part 2 end
    }
}
