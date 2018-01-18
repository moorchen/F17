package Project;

public class Coupons {

    /**
     * This is a class that represent the unsorted list of coupons.
     */

    ACoupon ref;

    public Coupons() {
        /**
         * Constructor of Coupons.
         */
        ref = null;
    }

    public void add(String site, String name, double price, int rate, int period, statusType status) {
        /**
         * A method that can add a new coupon into the system.
         */
        ACoupon newNode = new ACoupon(site, name, price, rate, period, status);
        if (ref == null) {
            ref = newNode;
        } else {
            ACoupon pos = ref;
            while (pos.link != null) {
                pos = pos.link;
            }
            pos.link = newNode;
            newNode.revlink = pos;
        }
    }

    @Override
    public String toString() {
        /**
         * A method that can print a sorted list of coupons.
         */
        String output = null;
        if (ref == null) {
            output = "No coupon in the system.";
        } else {
            ACoupon pos = ref;
            output = "Printing unsorted coupons:" + "\n\r" + pos.toString() + "\n\r";
            while (pos.link != null) {
                pos = pos.link;
                output = output + pos.toString() + "\n\r";
            }
        }
        return output;
    }

    protected void swap(ACoupon a, ACoupon b) {
        String tempsite = a.site;
        String tempname = a.name;
        double tempprice = a.price;
        int temprate = a.rate;
        int tempperiod = a.period;
        statusType tempstatus = a.status;
        double tempfinalp = a.finalP;
        a.site = b.site;
        a.name = b.name;
        a.price = b.price;
        a.rate = b.rate;
        a.period = b.period;
        a.status = b.status;
        a.finalP = b.finalP;
        b.site = tempsite;
        b.name = tempname;
        b.price = tempprice;
        b.rate = temprate;
        b.period = tempperiod;
        b.status = tempstatus;
        b.finalP = tempfinalp;
    }

    protected void sort(ACoupon start, fieldType field) {
        /**
         * A internal method that convert UL to SL.
         * This method is called with the field type to decide by which field it was sorted.
         */
        ACoupon pos = start;
        ACoupon flag = pos;
        if (start.link != null) {
            while (pos.link != null) {
                pos = pos.link;
                switch (field) {
                    case SITE: {
                        if (flag.site.compareTo(pos.site) > 0) {
                            flag = pos;
                        }
                        break;
                    }
                    case NAME: {
                        if (flag.name.compareTo(pos.name) > 0) {
                            flag = pos;
                        }
                        break;
                    }
                    case PRICE: {
                        if (flag.price > pos.price) {
                            flag = pos;
                        }
                        break;
                    }
                    case RATE: {
                        if (flag.rate > pos.rate) {
                            flag = pos;
                        }
                        break;
                    }
                    case PERIOD: {
                        if (flag.period > pos.period) {
                            flag = pos;
                        }
                        break;
                    }
                    case STATUS: {
                        if (flag.status.compareTo(pos.status) > 0) {
                            flag = pos;
                        }
                        break;
                    }
                    case FINAL: {
                        if (flag.finalP > pos.finalP) {
                            flag = pos;
                        }
                        break;
                    }
                }
            }
            swap(start, flag);
            sort(start.link, field);
        } else return;
    }

    public void doSort(fieldType field) {
        sort(ref, field);
    }

    protected ACoupon sortedListToBST() {
        /*Count the number of nodes in Linked List */
        int n = countNodes(ref);
        /* Construct BST */
        return sortedListToBSTRecur(rightLocate(ref, n / 2), n);
    }

    protected ACoupon sortedListToBSTRecur(ACoupon parent, int n) {
        if (n == 0) {
            return null;
        } else if (n == 1) {
            ACoupon root = new ACoupon(parent.site, parent.name, parent.price, parent.rate, parent.period, parent.status);
            return root;
        } else {
            ACoupon root = new ACoupon(parent.site, parent.name, parent.price, parent.rate, parent.period, parent.status);
            int left = (n - n % 2) / 2;
            int right = (n - 2 + n % 2) / 2;
            root.link = sortedListToBSTRecur(rightLocate(parent, right / 2 + 1), right);
            root.revlink = sortedListToBSTRecur(leftLocate(parent, left / 2 + left % 2), left);
            return root;
        }
    }

    protected ACoupon leftLocate(ACoupon start, int n) {
        /**
         * Helper method that can locate the nth node in the left from start point.
         */
        int count = 0;
        ACoupon pos = start;
        while (count != n) {
            pos = pos.revlink;
            count++;
        }
        return pos;
    }

    protected ACoupon rightLocate(ACoupon start, int n) {
        /**
         * Helper method that can locate the nth node in the right from start point.
         */
        int count = 0;
        ACoupon pos = start;
        while (count != n) {
            pos = pos.link;
            count++;
        }
        return pos;
    }

    protected int countNodes(ACoupon start) {
        if (start != null) {
            int count = 1;
            ACoupon pos = start;
            while (pos.link != null) {
                pos = pos.link;
                count++;
            }
            return count;
        } else {
            return 0;
        }
    }

    void preOrder(ACoupon node) {
        if (node == null)
            return;
        System.out.print(node.toString() + "\n\r");
        preOrder(node.revlink);
        preOrder(node.link);
    }

    public SearchedCoupons searchByField(String key, fieldType field) {
        SearchedCoupons sc = linearSearch(key, field);
        //System.out.println(sc.toString());
        BSTSearch(sc, field);
        return sc;
    }

    protected SearchedCoupons linearSearch(String key, fieldType field) throws NullPointerException {
        ACoupon pos = ref;
        SearchedCoupons result = new SearchedCoupons();
        if (ref != null) {
            int count = 1;
            switch (field) {
                case SITE: {
                    while (pos != null) {
                        if (pos.site.contains(key)) {
                            result.add(pos);
                            result.now.linear = count;
                        }
                        pos = pos.link;
                        count++;
                    }
                    break;
                }
                case NAME: {
                    while (pos != null) {
                        if (pos.name.contains(key)) {
                            result.add(pos);
                            result.now.linear = count;
                        }
                        pos = pos.link;
                        count++;
                    }
                    break;
                }
                case PRICE: {
                    while (pos != null) {
                        if (pos.price == Double.parseDouble(key)) {
                            result.add(pos);
                            result.now.linear = count;
                        }
                        pos = pos.link;
                        count++;
                    }
                    break;
                }
                case RATE: {
                    while (pos != null) {
                        if (pos.rate == Double.valueOf(key)) {
                            result.add(pos);
                            result.now.linear = count;
                        }
                        pos = pos.link;
                        count++;
                    }
                    break;
                }
                case PERIOD: {
                    while (pos != null) {
                        if (pos.period == Integer.valueOf(key)) {
                            result.add(pos);
                            result.now.linear = count;
                        }
                        pos = pos.link;
                        count++;
                    }
                    break;
                }
                case STATUS: {
                    while (pos != null) {
                        if (pos.status.toString().equals(key)) {
                            result.add(pos);
                            result.now.linear = count;
                        }
                        pos = pos.link;
                        count++;
                    }
                    break;
                }
            }
            return result;
        } else {
            throw new NullPointerException("No items existed.");
        }
    }

    protected void BSTSearch(SearchedCoupons sc, fieldType field) throws NullPointerException {
        ACoupon root = null;
        switch (field) {
            case SITE: {
                doSort(fieldType.SITE);
                root = sortedListToBST();
                break;
            }
            case NAME: {
                doSort(fieldType.NAME);
                root = sortedListToBST();
                break;
            }
            case PRICE: {
                doSort(fieldType.PRICE);
                root = sortedListToBST();
                break;
            }
            case RATE: {
                doSort(fieldType.RATE);
                root = sortedListToBST();
                break;
            }
            case PERIOD: {
                doSort(fieldType.PERIOD);
                root = sortedListToBST();
                break;
            }
            case STATUS: {
                doSort(fieldType.STATUS);
                root = sortedListToBST();
                break;
            }
        }
        SearchedCoupons.SearchedACoupon pos = sc.ref;
        while(pos != null){
            BSTSearchRecursive(pos, root, 1);
            pos = pos.link;
        }
    }

    protected void BSTSearchRecursive(SearchedCoupons.SearchedACoupon key, ACoupon root, int count) {
        if(root == null) return;
        if(key.compare(root)){
            key.bst = count;
        }
        BSTSearchRecursive(key, root.revlink, count + 1);
        BSTSearchRecursive(key, root.link, count + 1);
    }

    public int count(ACoupon start) {
        int n = 1;
        ACoupon pos = start;
        while (pos.link != null) {
            pos = pos.link;
            n++;
        }
        return n;
    }



}
