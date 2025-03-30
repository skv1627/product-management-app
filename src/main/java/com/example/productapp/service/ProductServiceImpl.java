package com.example.productapp.service;

import com.example.productapp.model.Product;
import com.example.productapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product p) {
        p.setCreatedDate(LocalDateTime.now());
        return productRepository.save(p);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product updateProduct(Long id, Product p) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isEmpty()) return null;

        Product existing = optional.get();
        existing.setName(p.getName());
        existing.setDescription(p.getDescription());
        existing.setPrice(p.getPrice());
        existing.setQuantity(p.getQuantity());
        existing.setIsFeatured(p.getIsFeatured());
        existing.setIsActive(p.getIsActive());
        existing.setCategoryId(p.getCategoryId());
        existing.setImageUrls(p.getImageUrls());

        return productRepository.save(existing);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Product> filterByPriceRange(Double min, Double max) {
        return productRepository.findByPriceBetween(min, max);
    }

    @Override
    public List<Product> lowStock(int threshold) {
        return productRepository.findByQuantityLessThan(threshold);
    }

    @Override
    public List<Product> getFeaturedProducts() {
        return productRepository.findByIsFeaturedTrue();
    }

    @Override
    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Product> getRecentlyAdded() {
        return productRepository.findTop5ByOrderByCreatedDateDesc();
    }

    @Override
    public List<Product> topSellingProducts() {
        return productRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Product::getQuantity).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public Product markAsFeatured(Long id) {
        Product p = productRepository.findById(id).orElse(null);
        if (p != null) {
            p.setIsFeatured(true);
            return productRepository.save(p);
        }
        return null;
    }

    @Override
    public Product deactivateProduct(Long id) {
        Product p = productRepository.findById(id).orElse(null);
        if (p != null) {
            p.setIsActive(false);
            return productRepository.save(p);
        }
        return null;
    }

    @Override
    public Product reactivateProduct(Long id) {
        Product p = productRepository.findById(id).orElse(null);
        if (p != null) {
            p.setIsActive(true);
            return productRepository.save(p);
        }
        return null;
    }

    @Override
    public void bulkDelete(List<Long> ids) {
        ids.forEach(productRepository::deleteById);
    }

    @Override
    public void bulkUpdateQuantities(List<Product> updatedProducts) {
        for (Product update : updatedProducts) {
            Product p = productRepository.findById(update.getId()).orElse(null);
            if (p != null) {
                p.setQuantity(update.getQuantity());
                productRepository.save(p);
            }
        }
    }

    @Override
    public Product addProductImage(Long id, String imageUrl) {
        Product p = productRepository.findById(id).orElse(null);
        if (p != null) {
            List<String> images = p.getImageUrls();
            images.add(imageUrl);
            p.setImageUrls(images);
            return productRepository.save(p);
        }
        return null;
    }
}
