package lv.training.inventory.database;

import lv.training.inventory.model.Product;
import lv.training.inventory.model.ProductInput;
import java.util.ArrayList;
import java.util.List;


public class InMemoryDb implements Database{

    private final List<Product> productDb;

    public InMemoryDb() {
        this.productDb = new ArrayList<>();
    }

    @Override
    public void create(Product product) {
        productDb.add(product);
    }

    @Override
    public Product read(Integer id) {
        for (Product product: productDb){
            if (product.getId().equals(id)) {return product;}
        }
        return null;
    }

    @Override
    public List<Product> readAll() {
        return productDb;
    }

    @Override
    public void update(ProductInput product, Integer id) {
        for (Product it: productDb){
            if(it.getId().equals(id))
                it.setName(product.getName());
                it.setPrice(product.getPrice());
                it.setCategory(product.getCategory());
            }
        }

    @Override
    public void delete(Integer id) {
        Product it = read(id);
        productDb.remove(it);
    }
}
