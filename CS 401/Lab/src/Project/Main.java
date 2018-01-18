package Project;

import static java.lang.Math.ceil;
import static java.lang.Math.log;

public class Main {
    public static void main(String args[]) {
        Coupons myCoupons = new Coupons();
        myCoupons.add("Amazon", "Mouse", 99.99, 30, 5, statusType.UNUSED);
        myCoupons.add("Amazon", "Keyboard", 199.99, 50, 15, statusType.UNUSED);
        myCoupons.add("Ebay", "Table", 39.99, 10, 10, statusType.UNUSED);
        myCoupons.add("Ebay", "Shelf", 45.99, 20, 5, statusType.UNUSED);
        myCoupons.add("Walmart", "Milk", 6.99, 5, 50, statusType.UNUSED);
        myCoupons.add("Walmart", "Snacks", 2.99, 50, 1, statusType.UNUSED);
        myCoupons.add("Costco", "Xbox", 199.99, 10, 20, statusType.UNUSED);
        myCoupons.add("Costco", "Steaks", 19.99, 10, 55, statusType.UNUSED);
        myCoupons.add("Target", "Beer", 9.99, 30, 35, statusType.UNUSED);
        myCoupons.add("Target", "Ketchup", 3.99, 10, 300, statusType.UNUSED);
        //System.out.println(myCoupons.toString());
        /*
        myCoupons.doSort(fieldType.PRICE);
        System.out.println(myCoupons.toString());
        myCoupons.doSort(fieldType.NAME);
        System.out.println(myCoupons.toString());
        myCoupons.doSort(fieldType.SITE);
        System.out.println(myCoupons.toString());
        myCoupons.doSort(fieldType.PERIOD);
        System.out.println(myCoupons.toString());
        myCoupons.preOrder(myCoupons.sortedListToBST());
        */
        //System.out.println(myCoupons.leftLocate(myCoupons.rightLocate(myCoupons.ref, 3), 3).toString());
        //myCoupons.preOrder(myCoupons.sortedListToBST());
        //System.out.println(myCoupons.searchByField("Tar", fieldType.SITE).toString());
        int n = 10;
        System.out.println(log(10));
        System.out.println(ceil(log(10)));
    }
}
