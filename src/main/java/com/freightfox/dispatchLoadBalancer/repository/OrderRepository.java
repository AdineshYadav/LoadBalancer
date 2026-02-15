package com.freightfox.dispatchLoadBalancer.repository;

import com.freightfox.dispatchLoadBalancer.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,String> {

    boolean existsByOrderId(String orderId);
}

