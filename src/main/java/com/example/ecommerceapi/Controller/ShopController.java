package com.example.ecommerceapi.Controller;

import com.example.ecommerceapi.Service.ShopService;
import com.example.ecommerceapi.entities.Product;
import com.example.ecommerceapi.entities.Shop;
import com.example.ecommerceapi.errorHandling.AlreadyExistException;
import com.example.ecommerceapi.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Shop shop) {
        shopService.create(shop);
        return new ResponseEntity<String>("Satici olusturuldu! Satici Ismi : " + shop.getShopName(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Shop>> getAll() {
        return new ResponseEntity<>(shopService.findAll(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Shop> getById(@PathVariable Long id) {
        return new ResponseEntity<>(shopService.findById(id),HttpStatus.OK);
    }
    @PostMapping("{id}")
    public ResponseEntity<Shop> update(@RequestBody Shop shop, @PathVariable Long id) {
        return new ResponseEntity<>(shopService.update(shop,id),HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        shopService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<List<Product>> getProducts(@PathVariable Long id) {
        return new ResponseEntity<>(shopService.getProducts(id),HttpStatus.OK);
    }
}
