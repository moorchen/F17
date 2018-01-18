package Project;

public class SearchedCoupons {

    SearchedACoupon ref;
    SearchedACoupon now;
    int n;

    class SearchedACoupon extends ACoupon {

        int bst;
        int linear;
        SearchedACoupon link;

        SearchedACoupon(String site, String name, double price, int rate, int period, statusType status) {
            super(site, name, price, rate, period, status);
            this.bst = 0;
            this.linear = 0;
            this.link = null;
        }

        SearchedACoupon(ACoupon ac) {
            super(ac.site, ac.name, ac.price, ac.rate, ac.period, ac.status);
            this.bst = 0;
            this.linear = 0;
            this.link = null;
        }

        @Override
        public String toString() {
            return super.toString() + " linear " + linear + " bst " + bst;
        }

        public boolean compare(ACoupon target) {
            if (this.site == target.site && this.name == target.name && this.rate == target.rate && this.price == target.price && this.period == target.period && this.status == target.status) {
                return true;
            } else return false;
        }

    }

    SearchedCoupons() {
        this.ref = null;
        this.now = null;
        this.n = 0;
    }

    public void add(ACoupon newCoupon) {
        SearchedACoupon newc = new SearchedACoupon(newCoupon);
        if (now == null) {
            ref = newc;
            now = ref;
        } else {
            now.link = newc;
            now = now.link;
        }
        n++;
    }

    @Override
    public String toString() {
        String output = "";
        SearchedACoupon pos = ref;
        while (pos != null) {
            output += pos.toString() + "\n\r";
            pos = pos.link;
        }
        return output;
    }

}
