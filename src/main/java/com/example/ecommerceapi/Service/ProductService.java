package com.example.ecommerceapi.Service;


import com.example.ecommerceapi.entities.Product;
import com.example.ecommerceapi.entities.Shop;
import com.example.ecommerceapi.errorHandling.AlreadyExistException;
import com.example.ecommerceapi.errorHandling.EntityNotFoundException;
import com.example.ecommerceapi.repository.ProductRepository;
import com.example.ecommerceapi.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShopRepository shopRepository;

    public Product create(Product product) {
        if(productRepository.getProductByproductName(product.getProductName()) != null){
            throw new AlreadyExistException("Bu isimde ürün mevcut");
        }
        if(!shopRepository.findById(product.getShopId().getId()).isPresent()){
            throw new EntityNotFoundException("Shop cannot be  null");
        }
        var newProduct = new Product(product.getPrice()
        ,       product.getProductName(),
                product.getProductDescription(),
                product.getShopId(),
                product.getCategories(),
                product.getQuantity()
        );
        return productRepository.save(newProduct);
    }
    public Product getById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new EntityNotFoundException("Bu ID'de ürün bulunamadı");
        }
        return product.get();
    }
    public Product getByName(String productName) {
        var product = productRepository.getProductByproductName(productName);
        if(product == null){
            throw new EntityNotFoundException("Ürün bulunamadı. Farklı bir ürün aratın");
        }
        return product;
    }
    public List<Product> findAll(){
        return productRepository.findAll();
    }
    public Product update(Product product,Long id) {
        var updatedProduct = getById(id);
        updatedProduct.setProductName(product.getProductName());
        updatedProduct.setProductDescription(product.getProductDescription());
        updatedProduct.setShopId(product.getShopId());
        updatedProduct.setCategories(product.getCategories());
        updatedProduct.setQuantity(product.getQuantity());
        return productRepository.save(updatedProduct);

    }
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}