package fct.cs.Bill;

import java.sql.Date;

public class orderDetails {


    private int order_detailedId ;
        private int book_id;
        private int order_id;
        private int quantity;
        private int unit_price;
        private int discount;
        private int totalForItem;


        public orderDetails(int order_detailedId,int order_id, int book_id,  int quantity, int unit_price ) {

            this.order_detailedId = order_detailedId ;
            this.book_id = book_id;
            this.order_id = order_id;
            this.quantity = quantity;
            this.unit_price = unit_price;
            this.totalForItem = (unit_price*quantity) ;

        }
    public int getOrder_detailedID() {
        return order_detailedId;
    }

    public void setOrder_detailedID(int order_detailedID) {
        this.order_detailedId = order_detailedID;
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

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }




        @Override
        public String toString() {
            return "OrderDetailEntry{" +
                    " book_id=" + book_id +
                    ", order_detailedId =" + order_detailedId +
                    ", order_id=" + order_id +
                    ", quantity=" + quantity +
                    ", unit_price=" + unit_price +
                    ", discount=" + discount +
                    ", totalForItem=" + totalForItem +
                    '}';
        }


}
