package venter.mircea.onlinestore.repository;

import venter.mircea.onlinestore.model.entity.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository <OrderEntity,String> {

    List<OrderEntity> getOrderEntitiesByUserId(String userId);
}
