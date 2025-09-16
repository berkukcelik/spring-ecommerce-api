package com.example.ecommerceapi.Service;

import com.example.ecommerceapi.entities.Product;
import com.example.ecommerceapi.entities.Shop;
import com.example.ecommerceapi.errorHandling.AlreadyExistException;
import com.example.ecommerceapi.errorHandling.EntityNotFoundException;
import com.example.ecommerceapi.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ShopService {
    @Autowired
    private ShopRepository shopRepository;

    public Shop create(Shop shop) {
        if(shopRepository.getShopByshopName(shop.getShopName()) != null){
            throw new AlreadyExistException("Satici zaten mevcut");

        }
        Shop newShop = new Shop(shop.getShopName());
        return shopRepository.save(newShop);
    }

    public Shop findById(Long id) {
      Optional<Shop> shop = shopRepository.findById(id);
      if(shop.isEmpty()){
          throw new EntityNotFoundException("Bu ID'de satici bulunamadi!!");
      }
      return shop.get();
    }

    public List<Shop> findAll() {
        return shopRepository.findAll();
    }

    public Shop update(Shop shop , Long id) {
        var updatedShop = shopRepository.findById(id).get();
        updatedShop.setShopName(shop.getShopName());
        updatedShop.setUpdatedAt(LocalDateTime.now());
        return shopRepository.save(updatedShop);
    }

    public void deleteById(Long id) {
        shopRepository.deleteById(id);
    }

    public Shop findByshopName(String shopName) {
        return shopRepository.getShopByshopName(shopName);
    }
    public List<Product> getProducts(Long shopId) {
        var shop = shopRepository.findById(shopId).get();
        return shop.getProducts();
    }
}