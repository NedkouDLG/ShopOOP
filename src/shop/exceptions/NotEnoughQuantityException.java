package shop.exceptions;

import shop.classes.Product;

public class NotEnoughQuantityException extends Exception{
    public final Product product;
    public final int quantity;
    public NotEnoughQuantityException(Product sellProduct, int quantity){
        this.product = sellProduct;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "NotEnoughQuantityException{" +
                "product=" + product.getName() +
                ", quantity=" + quantity +
                '}';
    }
}
