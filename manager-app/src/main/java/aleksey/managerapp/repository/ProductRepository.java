package aleksey.managerapp.repository;

import aleksey.managerapp.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();

    Product create(String details, String title);

    Optional<Product> findById(Integer productId);

    void productUpdate(Integer id, String title, String details);

    void remove(Integer id);
}
