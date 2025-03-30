package com.example.productapp.controller;

import com.example.productapp.model.Product;
import com.example.productapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product create(@RequestBody Product p) {
        return productService.createProduct(p);
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product p) {
        return productService.updateProduct(id, p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
    
    @GetMapping("/search")
    public List<Product> searchByName(@RequestParam String name) {
        return productService.searchByName(name);
    }

    @GetMapping("/filter")
    public List<Product> filterByPrice(
        @RequestParam Double minPrice,
        @RequestParam Double maxPrice) {
        return productService.filterByPriceRange(minPrice, maxPrice);
    }
    
    @GetMapping("/low-stock")
    public List<Product> lowStock() {
        return productService.lowStock(10);
    }
    
    @PutMapping("/update-quantities")
    public void bulkUpdateQuantities(@RequestBody List<Product> products) {
        productService.bulkUpdateQuantities(products);
    }

    @GetMapping("/top-selling")
    public List<Product> topSelling() {
        return productService.topSellingProducts();
    }

    @PutMapping("/{id}/mark-featured")
    public Product markAsFeatured(@PathVariable Long id) {
        return productService.markAsFeatured(id);
    }
    
    @GetMapping("/featured")
    public List<Product> getFeatured() {
        return productService.getFeaturedProducts();
    }

    @PutMapping("/{id}/deactivate")
    public Product deactivate(@PathVariable Long id) {
        return productService.deactivateProduct(id);
    }
    
    @PutMapping("/{id}/reactive")
    public Product reactivate(@PathVariable Long id) {
        return productService.reactivateProduct(id);
    }
    
    @GetMapping("/category/{categoryId}")
    public List<Product> getByCategory(@PathVariable Long categoryId) {
        return productService.getProductsByCategory(categoryId);
    }

    @GetMapping("/recent")
    public List<Product> getRecent() {
        return productService.getRecentlyAdded();
    }

    @DeleteMapping("/bulk-delete")
    public void bulkDelete(@RequestBody List<Long> ids) {
        productService.bulkDelete(ids);
    }

    @PostMapping("/{id}/images")
    public Product addImage(@PathVariable Long id, @RequestParam String imageUrl) {
        return productService.addProductImage(id, imageUrl);
    }
}
