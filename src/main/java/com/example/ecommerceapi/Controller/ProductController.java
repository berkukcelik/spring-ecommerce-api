package com.example.ecommerceapi.Controller;

import com.example.ecommerceapi.Service.ProductService;
import com.example.ecommerceapi.entities.Product;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<List<Product>>(productService.findAll(), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.create(product), HttpStatus.CREATED);
    }
    @PostMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return new ResponseEntity<>(productService.update(product, id), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}