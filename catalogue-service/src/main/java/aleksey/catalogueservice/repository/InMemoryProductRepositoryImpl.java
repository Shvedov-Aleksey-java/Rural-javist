package aleksey.catalogueservice.repository;


import aleksey.catalogueservice.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.IntStream;

@Repository
public class InMemoryProductRepositoryImpl implements ProductRepository {

    private final List<Product> products = Collections.synchronizedList(new LinkedList<>());

    public InMemoryProductRepositoryImpl() {
        IntStream.range(1, 4)
                .forEach(i -> this.products.add(new Product(i, "Товар № %d".formatted(i),
                        "Описанние Товара № %d".formatted(i))));
    }


    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product create(String details, String title) {
        Product product = new Product(products.stream()
                .max(Comparator.comparingInt(Product::getId))
                .map(Product::getId).orElse(0) + 1, details, title);
        products.add(product);
        return product;
    }

    @Override
    public void productUpdate(Integer id, String title, String details) {
        findById(id).ifPresent(o1 -> {
            o1.setTitle(title);
            o1.setDetails(details);
        });
    }

    @Override
    public Optional<Product> findById(Integer productId) {
        return products.stream()
                .filter(o1 -> o1.getId().equals(productId))
                .findFirst();
    }

    @Override
    public void remove(Integer productId) {
        products.stream()
                .filter(o1 -> o1.getId().equals(productId))
                .findFirst().ifPresent(products::remove);
    }
}
