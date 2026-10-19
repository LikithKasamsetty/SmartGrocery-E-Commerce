//package com.smartgrocery.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.stereotype.Service;
//
//import com.smartgrocery.Entity.Product;
//import com.smartgrocery.repository.ProductRepository;
//
//@Service
//public class ProductService {
//
//    private final ProductRepository productRepository;
//
//    public ProductService(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }
//
//    // CREATE
//    public Product createProduct(Product product) {
//        product.setId(null);                       // always insert a new row
//        product.setName(product.getName().trim()); // small business rule: clean the name
//        return productRepository.save(product);
//    }
//
//    // READ ALL
//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }
//
//    // READ ONE
//    public Optional<Product> getProductById(Long id) {
//        return productRepository.findById(id);
//    }
//
//    // UPDATE
//    public Optional<Product> updateProduct(Long id, Product updated) {
//        return productRepository.findById(id).map(existing -> {
//            existing.setName(updated.getName().trim());
//            existing.setPrice(updated.getPrice());
//            existing.setQuantity(updated.getQuantity());
//            return productRepository.save(existing);
//        });
//    }
//
//    // DELETE: returns true if deleted, false if the id doesn't exist
//    public boolean deleteProduct(Long id) {
//        if (!productRepository.existsById(id)) {
//            return false;
//        }
//        productRepository.deleteById(id);
//        return true;
//    }
//
//    // SEARCH
//    public List<Product> searchByName(String name) {
//        return productRepository.findByName(name);
//    }
//
//    public List<Product> searchContains(String keyword) {
//        return productRepository.findByNameContainingIgnoreCase(keyword);
//    }
//
//    public List<Product> cheaperThan(double price) {
//        return productRepository.findByPriceLessThan(price);
//    }
//
//    public List<Product> higherThan(double price) {
//        return productRepository.findByPriceGreaterThan(price);
//    }
//
//    public List<Product> lowStock(int quantity) {
//        return productRepository.findByQuantityLessThan(quantity);
//    }
//}
//


//package com.smartgrocery.Service;   // keep YOUR package prefix
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.smartgrocery.Entity.Product;
//import com.smartgrocery.dto.ProductRequest;
//import com.smartgrocery.dto.ProductResponse;
//import com.smartgrocery.repository.ProductRepository;
//
//@Service
//@Transactional(readOnly = true)          // default for the whole class: read only
//public class ProductService {
//
//    private final ProductRepository productRepository;
//
//    public ProductService(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }
//
//    // ---------- WRITE operations (override with a normal transaction) ----------
//
//   @Transactional
//    public ProductResponse createProduct(ProductRequest productRequest) {
//        validateName(productRequest);
//        Product saved = productRepository.save(toEntity(request));
//        return toResponse(saved);
//    }
//
//   @ @Transactional
//   public List<ProductResponse> createProducts(List<ProductRequest> requests) {
//       List<ProductResponse> result = new ArrayList<>();
//       for (ProductRequest r : requests) {
//           validateName(r);
//           result.add(toResponse(productRepository.save(toEntity(r))));
//       }
//       return result;
//   }
//
//   @Transactional
//   public Optional<ProductResponse> updateProduct(Long id, ProductRequest request) {
//       return productRepository.findById(id).map(existing -> {
//           validateName(request);
//           existing.setName(request.getName().trim());
//           existing.setPrice(request.getPrice());
//           existing.setQuantity(request.getQuantity());
//           return toResponse(productRepository.save(existing));
//       });
//   }
//
//   @Transactional
//   public boolean deleteProduct(Long id) {
//       if (!productRepository.existsById(id)) {
//           return false;
//       }
//       productRepository.deleteById(id);
//       return true;
//   }
//
//    
//
//    // ---------- READ operations (use the class-level readOnly) ----------
//
////    public List<Product> getAllProducts() {
////        return productRepository.findAll();
////    }
////
////    public Optional<Product> getProductById(Long id) {
////        return productRepository.findById(id);
////    }
//
//    public List<Product> searchByName(String name) {
//        return productRepository.findByName(name);
//    }
//
//    public List<Product> searchByPrice(double price) {
//        return productRepository.findByPrice(price);
//    }
//
//    public List<Product> searchByQuantity(int quantity) {
//        return productRepository.findByQuantity(quantity);
//    }
//
//    public List<Product> searchByNameAndPrice(String name, double price) {
//        return productRepository.findByNameAndPrice(name, price);
//    }
//
//    public List<Product> searchContains(String keyword) {
//        return productRepository.findByNameContainingIgnoreCase(keyword);
//    }
//
//    public List<Product> cheaperThan(double price) {
//        return productRepository.findByPriceLessThan(price);
//    }
//
//    public List<Product> higherThan(double price) {
//        return productRepository.findByPriceGreaterThan(price);
//    }
//
//    public List<Product> lowStock(int quantity) {
//        return productRepository.findByQuantityLessThan(quantity);
//    }
//
//    // ---------- helper ----------
//
//    private void validateName(ProductRequest request) {
//        if (request.getName() == null || request.getName().isBlank()) {
//            throw new IllegalArgumentException("Product name is required");
//        }
//    }
//    
//    //---------------- Mapping to dto's-------------------------------------------------
//    // Here we are Setting the data from DTO to Entity
//    private Product toEntity(ProductRequest productRequest) {
//    	    Product product = new Product();
//    	    product.setName(productRequest.getName().trim());
//    	    product.setPrice(productRequest.getPrice());
//    	    product.setQuantity(productRequest.getQuantity());
//    	return product;  
//    }
// // Here we are return to the client
//    private ProductResponse toResponse(Product product) {
//        return new ProductResponse(
//                product.getId(),
//                product.getName(),
//                product.getPrice(),
//                product.getQuantity()); // in ProductResponse had created a constructor so this data passes to constructor then the constructor to method and method to returns data  
//    }
//    
//    @Transactional
//    public ProductResponse createProduct(ProductRequest request) {
//        validateName(request);
//        Product saved = productRepository.save(toEntity(request));
//        return toResponse(saved);
//    }
//
//    @Transactional
//    public List<ProductResponse> createProducts(List<ProductRequest> requests) {
//        List<ProductResponse> result = new ArrayList<>();
//        for (ProductRequest r : requests) {
//            validateName(r);
//            result.add(toResponse(productRepository.save(toEntity(r))));
//        }
//        return result;
//    }
//
//    @Transactional
//    public Optional<ProductResponse> updateProduct(Long id, ProductRequest request) {
//        return productRepository.findById(id).map(existing -> {
//            validateName(request);
//            existing.setName(request.getName().trim());
//            existing.setPrice(request.getPrice());
//            existing.setQuantity(request.getQuantity());
//            return toResponse(productRepository.save(existing));
//        });
//    }
//
//    public List<ProductResponse> getAllProducts() {
//        return productRepository.findAll().stream().map(this::toResponse).toList();
//    }
//
//    public Optional<ProductResponse> getProductById(Long id) {
//        return productRepository.findById(id).map(this::toResponse);
//    }
//    
//}


package com.smartgrocery.Service;   // keep YOUR package prefix

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartgrocery.Entity.Product;
import com.smartgrocery.Exception.ProductNotFoundException;
import com.smartgrocery.dto.ProductRequest;
import com.smartgrocery.dto.ProductResponse;
import com.smartgrocery.repository.ProductRepository;

@Service
@Transactional(readOnly = true)          // default for the whole class: read only
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // ---------- WRITE operations (override with a normal transaction) ----------

    @Transactional
    public ProductResponse createProduct(ProductRequest request) {
        validateName(request);
        Product saved = productRepository.save(toEntity(request));
        return toResponse(saved);
    }

    @Transactional
    public List<ProductResponse> createProducts(List<ProductRequest> requests) {
        List<ProductResponse> result = new ArrayList<>();
        for (ProductRequest r : requests) {
            validateName(r);
            result.add(toResponse(productRepository.save(toEntity(r))));
        }
        return result;
    }
    
    @Transactional
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        validateName(request);
        existing.setName(request.getName().trim());
        existing.setPrice(request.getPrice());
        existing.setQuantity(request.getQuantity());
        return toResponse(productRepository.save(existing));
    }
    
    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }




    // ---------- READ operations (use the class-level readOnly) ----------

//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }
//
//    public Optional<Product> getProductById(Long id) {
//        return productRepository.findById(id);
//    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(this::toResponse).toList();
    }

    public ProductResponse getProductById(Long id) {
        return productRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public List<ProductResponse> searchByName(String name) {
        return productRepository.findByName(name).stream().map(this::toResponse).toList();
    }

    public List<ProductResponse> searchByPrice(double price) {
        return productRepository.findByPrice(price).stream().map(this::toResponse).toList();
    }

    public List<ProductResponse> searchByQuantity(int quantity) {
        return productRepository.findByQuantity(quantity).stream().map(this::toResponse).toList();
    }

    public List<ProductResponse> searchByNameAndPrice(String name, double price) {
        return productRepository.findByNameAndPrice(name, price).stream().map(this::toResponse).toList();
    }

    public List<ProductResponse> searchContains(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword).stream().map(this::toResponse).toList();
    }

    public List<ProductResponse> cheaperThan(double price) {
        return productRepository.findByPriceLessThan(price).stream().map(this::toResponse).toList();
    }

    public List<ProductResponse> higherThan(double price) {
        return productRepository.findByPriceGreaterThan(price).stream().map(this::toResponse).toList();
    }

    public List<ProductResponse> lowStock(int quantity) {
        return productRepository.findByQuantityLessThan(quantity).stream().map(this::toResponse).toList();
    }

    // ---------- helper ----------

    private void validateName(ProductRequest request) {
        if (request.getName() == null || request.getName().isBlank()) {
            throw new IllegalArgumentException("Product name is required");
        }
    }

    //---------------- Mapping to dto's-------------------------------------------------
    // Here we are Setting the data from DTO to Entity
    private Product toEntity(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName().trim());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        return product;
    }

 // Here we are return to the client
    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity()); // in ProductResponse had created a constructor so this data passes to constructor then the constructor to method and method to returns data  
    }

}