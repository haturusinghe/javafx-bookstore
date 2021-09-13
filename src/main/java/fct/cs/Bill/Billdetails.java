package fct.cs.Bill;

public class Billdetails {

        private int book_id;
        private int order_id;
        private int quantity;
        private int unit_price;
        private String book_name;
        private int totalForItem;

        public Billdetails(int order_id, int book_id, String book_name, int quantity, int unit_price , int totalForItem) {
            this.book_id = book_id;
            this.order_id = order_id;
            this.book_name = book_name ;
            this.quantity = quantity;
            this.unit_price = unit_price;
            this.totalForItem =  totalForItem;
        }



        public int getBook_id() {
            return book_id;
        }

        public void setBook_id(int book_id) {
            this.book_id = book_id;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }


          public String getBook_name() {
                return book_name;
            }

          public void setBook_name(String book_name) {
                this.book_name = book_name;
          }


    public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getTotalForItem() {
            return totalForItem;
        }

        public void setTotalForItem(int totalForItem) {
            this.totalForItem = totalForItem;
        }

        public int getUnit_price() {
            return unit_price;
        }

        public void setUnit_price(int unit_price) {
            this.unit_price = unit_price;
        }



        @Override
        public String toString() {
            return "OrderDetailEntry{" +
                    ", book_id=" + book_id +
                    ", order_id=" + order_id +
                    ", quantity=" + quantity +
                    ", unit_price=" + unit_price +
                    ", totalForItem=" + totalForItem +
                    '}';
        }
    }

