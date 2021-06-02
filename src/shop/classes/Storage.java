package shop.classes;

import shop.exceptions.NotEnoughQuantityException;
import shop.interfaces.StorageInterface;

import java.util.ArrayList;
import java.util.List;

public class Storage implements StorageInterface {
    private List<Product> productList;
    public Storage(){
        this.productList = new ArrayList<>();
    }
    @Override
    public void addProduct(Product product) {
        this.productList.add(product);
    }

    public void removeProduct(Product product) throws NotEnoughQuantityException {
        for (Product currentProduct:this.productList) {
            if(currentProduct.getProductId() == product.getProductId()){
                int productCount = product.getCount();
                if(productCount > currentProduct.getCount()){
                    throw new NotEnoughQuantityException(currentProduct,productCount- currentProduct.getCount());
                }else
                    currentProduct.setCount(currentProduct.getCount() - productCount);

                if(currentProduct.getCount() == 0)
                    this.productList.remove(currentProduct);
                break;
            }

        }
    }

    public List<Product> getProductList() {
        return this.productList;
    }
    public void showStorage(){
        for (Product currentProduct:this.productList) {
            System.out.println(currentProduct);
        }
    }
}
