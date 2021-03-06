package venter.mircea.onlinestore.repository;

import venter.mircea.onlinestore.model.entity.CartEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CartRepository extends MongoRepository<CartEntity, String> {
    Optional<CartEntity> findCartEntityByUserId(String id);
}
