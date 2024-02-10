package com.horvat.bookstore.appUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    
    Optional<UserModel> findById(Integer id);
    UserModel save(UserModel user);
}
