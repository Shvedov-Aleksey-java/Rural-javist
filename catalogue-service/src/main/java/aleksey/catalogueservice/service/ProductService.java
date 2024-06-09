package aleksey.catalogueservice.service;

import aleksey.catalogueservice.entity.Product;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    List<Product> findAllProducts();

    Product create(String details, String title);

    Optional<Product> findById(Integer productId);

    void productUpdate(Integer id, String title, String details);

    void remove(Integer id);
}
