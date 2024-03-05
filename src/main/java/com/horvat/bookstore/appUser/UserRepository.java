package com.horvat.bookstore.appUser;

import java.util.Optional;
import org.springframework.data.repository.Repository;
import java.util.List;

public interface UserRepository extends Repository<UserModel, Integer> {
    
    Optional<UserModel> findById(Integer id);
    Optional<UserModel> findByEmail(String email);
    UserModel save(UserModel user);
    List<UserModel> findByUid(String uid);
    List<UserModel> findByGoogleUid(String googleUid);

}
