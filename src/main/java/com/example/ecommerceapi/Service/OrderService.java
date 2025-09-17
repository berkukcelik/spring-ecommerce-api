package com.example.ecommerceapi.Service;
import com.example.ecommerceapi.entities.Adress;
import com.example.ecommerceapi.entities.Order;
import com.example.ecommerceapi.entities.OrderStatus;
import com.example.ecommerceapi.entities.User;
import com.example.ecommerceapi.repository.AdressRepository;
import com.example.ecommerceapi.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AdressRepository adressRepository;
    @Autowired
    private CartService cartService;
    // cart -> Order
    @Transactional
    public Order createOrder(User user , Long adressId){
       Order order = new Order();
       // adres seçimi için :
       Adress adress = adressRepository.findById(adressId).get();
       order.setAdressId(adress);
       order.setOrderTotal(user.getCartId().getCartTotal());
       order.setCartId(user.getCartId());

       order.setStatus(OrderStatus.AWAITING_DELIVERY);
       user.getOrders().add(order);
       cartService.clearCart(user);
       return orderRepository.save(order);
    }
    public Order findById(Long orderId){
        return orderRepository.findById(orderId).get();
    }

    public Order changeOrderStatus(Long orderId, OrderStatus orderStatus){
        Order order = findById(orderId);
        order.setStatus(orderStatus);
        return  orderRepository.save(order);
    }
    public List<Order> getUserOrders(User user){
        return user.getOrders();
    }

}