package aleksey.managerapp.service;

import aleksey.managerapp.entity.Product;
import aleksey.managerapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultProductServiceImpl implements ProductService {

    private final ProductRepository repository;


    @Override
    public List<Product> findAllProducts() {
        return repository.findAll();
    }

    @Override
    public Product create(String details, String title) {
        return repository.create(details, title);
    }

    @Override
    public Optional<Product> findById(Integer productId) {
        return repository.findById(productId);
    }

    @Override
    public void productUdate(Integer id, String title, String details) {
        repository.productUpdate(id, title, details);
    }

    @Override
    public void remove(Integer id) {
        repository.remove(id);
    }
}
