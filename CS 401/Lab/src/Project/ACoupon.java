package Project;

public class ACoupon {

    /**
     * This is a class that works as a object of one single coupon.
     */

    public String site;
    public String name;
    public double price;
    public int rate;
    public int period;
    public statusType status;
    public double finalP;
    public ACoupon link;
    public ACoupon revlink;

    ACoupon(String site, String name, double price, int rate, int period, statusType status) {
        /**
         * Constructor of ACoupon.
         * Create a new coupon.
         */
        this.site = site;
        this.name = name;
        this.price = price;
        this.rate = rate;
        this.period = period;
        this.status = status;
        this.link = this.revlink = null;
        this.finalP = price * (100 - rate) / 100;
    }

    public String toString() {
        return site + " " + name + " $" + price + " " + rate + "% Expire in " + period + " day(s) Status: " + status + " Final Price: " + finalP;
    }

}
