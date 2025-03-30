package com.example.productapp.service;

import com.example.productapp.model.Product;
import java.util.List;

public interface ProductService {
    Product createProduct(Product p);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product updateProduct(Long id, Product p);
    void deleteProduct(Long id);

    List<Product> searchByName(String name);
    List<Product> filterByPriceRange(Double min, Double max);
    List<Product> lowStock(int threshold);
    List<Product> getFeaturedProducts();
    List<Product> getProductsByCategory(Long categoryId);
    List<Product> getRecentlyAdded();
    List<Product> topSellingProducts();
    Product markAsFeatured(Long id);
    Product deactivateProduct(Long id);
    Product reactivateProduct(Long id);
    void bulkDelete(List<Long> ids);
    void bulkUpdateQuantities(List<Product> updatedProducts);
    Product addProductImage(Long id, String imageUrl);
}
