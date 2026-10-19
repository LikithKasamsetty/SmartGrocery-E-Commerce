package com.smartgrocery.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartgrocery.Entity.Product;
import com.smartgrocery.Service.ProductService;
import com.smartgrocery.dto.ProductRequest;
import com.smartgrocery.dto.ProductResponse;

import jakarta.validation.Valid;

//import org.springframework.web.bind.annotation.*;
//
//import com.smartgrocery.Entity.Product;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping("/products")
//public class ProductController {
//
//    private List<Product> products = new ArrayList<>();
//
//    // CREATE
//    @PostMapping
//    public String addProduct(@RequestBody Product product) {
//
//        products.add(product);
//        return "Product added sussfully ";
//    }
//
//    // READ ALL
//    @GetMapping
//    public List<Product> getAllProducts() {
//
//        return products;
//    }
//
//    // READ ONE
//    @GetMapping("/{id}")
//    public Product getProductById(@PathVariable Long id) {
//
//        for (Product product : products) {
//
//            if (product.getId() != null &&
//                product.getId().equals(id)) {
//
//                return product;
//            }
//        }
//
//        return null;
//    }
//
//    // UPDATE
//    @PutMapping("/{id}")
//    public Product updateProduct(
//            @PathVariable Long id,
//            @RequestBody Product updatedProduct) {
//
//        for (Product product : products) {
//
//            if (product.getId() != null &&
//                product.getId().equals(id)) {
//
//                product.setName(updatedProduct.getName());
//                product.setPrice(updatedProduct.getPrice());
//                product.setQuantity(updatedProduct.getQuantity());
//
//                return product;
//            }
//        }
//
//        return null;
//    }
//
//    // DELETE
//    @DeleteMapping("/{id}")
//    public String deleteProduct(@PathVariable Long id) {
//
//        for (Product product : products) {
//
//            if (product.getId() != null &&
//                product.getId().equals(id)) {
//
//                products.remove(product);
//
//                return "Product deleted successfully";
//            }
//        }
//
//        return "Product not found";
//    }
//}





//import java.util.List;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.smartgrocery.Entity.Product;
//import com.smartgrocery.repository.ProductRepository;
//
//@RestController
//@RequestMapping("/products")
//public class ProductController {
//
//    private final ProductRepository productRepository;
//
//    public ProductController(ProductRepository productRepository) {
//        this.productRepository = productRepository; 
//    } //The controller depends on the repository, and Spring injects it through the constructor instead of the controller creating it.
//
//    // CREATE
//    @PostMapping
//    public Product addProduct(@RequestBody Product product) {
//        product.setId(null);                        // always insert a new row
//        return productRepository.save(product);
//    }
//
//    // READ ALL
//    @GetMapping
//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }
//
//    // READ ONE
//    @GetMapping("/{id}")
//    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
//        return productRepository.findById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    // UPDATE
//    @PutMapping("/{id}")
//    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
//                                                 @RequestBody Product updated) {
//        return productRepository.findById(id)
//                .map(existing -> {
//                    existing.setName(updated.getName());
//                    existing.setPrice(updated.getPrice());
//                    existing.setQuantity(updated.getQuantity());
//                    return ResponseEntity.ok(productRepository.save(existing));
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    // DELETE
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
//        if (!productRepository.existsById(id)) {
//            return ResponseEntity.notFound().build();
//        }
//        productRepository.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
//    
// // CREATE MANY
//    @PostMapping("/manyitemsadding")
//    public List<Product> addProducts(@RequestBody List<Product> products) {
//        products.forEach(p -> p.setId(null));      // always insert new rows
//        return productRepository.saveAll(products);
//    }
//    
// // findByName
//    @GetMapping("/search/name")
//    public List<Product> searchByName(@RequestParam String name) {
//        return productRepository.findByName(name);
//    }
//
//    // findByPrice
//    @GetMapping("/search/price")
//    public List<Product> searchByPrice(@RequestParam double price) {
//        return productRepository.findByPrice(price);
//    }
//
//    // findByQuantity
//    @GetMapping("/search/quantity")
//    public List<Product> searchByQuantity(@RequestParam int quantity) {
//        return productRepository.findByQuantity(quantity);
//    }
//
//    // findByNameAndPrice
//    @GetMapping("/search/name-price")
//    public List<Product> searchByNameAndPrice(@RequestParam String name,
//                                              @RequestParam double price) {
//        return productRepository.findByNameAndPrice(name, price);
//    }
//
//    // findByNameContainingIgnoreCase
//    @GetMapping("/search/contains")
//    public List<Product> searchContains(@RequestParam String keyword) {
//        return productRepository.findByNameContainingIgnoreCase(keyword);
//    }
//
//    // findByPriceLessThan
//    @GetMapping("/search/cheaper")
//    public List<Product> cheaperThan(@RequestParam double price) {
//        return productRepository.findByPriceLessThan(price);
//    }
//
//    // findByQuantityLessThan
//    @GetMapping("/search/low-stock")
//    public List<Product> lowStock(@RequestParam int quantity) {
//        return productRepository.findByQuantityLessThan(quantity);
//    }
//    
//    @GetMapping("/search/high-price")
//    public List<Product> highPrice(@RequestParam double price){
//    	return productRepository.findByPriceGreaterThan(price);
//    }
//}

//In this we used builder methods not constructors which are extraced from Response Entity
//It reads like English: status(CREATED).body(x).
//It chains headers easily: .status(...).header(...).body(...).
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @PostMapping("/single")
    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(request));
    }//here we used "ResponseEntity" in builder Style by using static methods 
    //.body(data) sets the content of the HTTP response, meaning the data the client receives.
    //It also finishes the builder and returns the completed ResponseEntity.
    //.status(...) sets the status code.
    

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
    
    
    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable Long id,
                                         @Valid @RequestBody ProductRequest request) {
        return productService.updateProduct(id, request);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
        //here it is the shortcut method and indicates that there is no content it will end it by using .build().okk
    }

    // search endpoints: same URLs as before, now calling the service
    @GetMapping("/search/name")
    public List<ProductResponse> searchByName(@RequestParam String name) {
        return productService.searchByName(name);
    }

    @GetMapping("/search/contains")
    public List<ProductResponse> searchContains(@RequestParam String keyword) {
        return productService.searchContains(keyword);
    }

    @GetMapping("/search/cheaper")
    public List<ProductResponse> cheaperThan(@RequestParam double price) {
        return productService.cheaperThan(price);
    }

    @GetMapping("/search/high-price")
    public List<ProductResponse> highPrice(@RequestParam double price) {
        return productService.higherThan(price);
    }

    @GetMapping("/search/low-stock")
    public List<ProductResponse> lowStock(@RequestParam int quantity) {
        return productService.lowStock(quantity);
    }
    
    @PostMapping("/bulk")
    public ResponseEntity<List<ProductResponse>> addProducts(@RequestBody List<@Valid ProductRequest> requests) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProducts(requests));
    } //Shortcut methods are used here we used "ResponseEntity" in builder Style by using static methods
    
 
    @GetMapping("/search/price")
    public List<ProductResponse> searchByPrice(@RequestParam double price) {
        return productService.searchByPrice(price);
    }

    @GetMapping("/search/quantity")
    public List<ProductResponse> searchByQuantity(@RequestParam int quantity) {
        return productService.searchByQuantity(quantity);
    }

    @GetMapping("/search/name-price")
    public List<ProductResponse> searchByNameAndPrice(@RequestParam String name,
                                              @RequestParam double price) {
        return productService.searchByNameAndPrice(name, price);
    }
}  
