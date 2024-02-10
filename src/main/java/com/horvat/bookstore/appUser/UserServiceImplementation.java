package com.horvat.bookstore.appUser;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horvat.bookstore.appUser.dtos.requests.Create;
import com.horvat.bookstore.appUser.dtos.requests.ReqUserDto;
import com.horvat.bookstore.appUser.dtos.responses.Created;
import com.horvat.bookstore.appUser.dtos.responses.ResUserDto;
@Service
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;

    @Autowired
    UserServiceImplementation(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public ResUserDto getUser(Integer id) {
        Optional<UserModel> userOptional = this.userRepository.findById(id);

        if(!userOptional.isPresent()){
            // TODO: Throw an exception ? 
            return null;
        }

        return ResUserDto.fromEntity(userOptional.get());
    }

    @Override
    public Created createUser(Create newUserDto) {
        //TODO: check if password match repeated password or throw an error; hash the password
        UserModel newUser = Create.getEntity(newUserDto);
        newUser.setActive(false);
        newUser.setRole(RoleModel.REGULAR);
        newUser = this.userRepository.save(newUser);

        return Created.fromEntity(newUser);
    }

    @Override
    public ResUserDto updateUser(Integer id, ReqUserDto updateDto) {
        Optional<UserModel> userOptional = this.userRepository.findById(id);

        if(!userOptional.isPresent()){
            // TODO: Throw an exception ? 
            return null;
        }

        UserModel user = userOptional.get();
        BeanUtils.copyProperties(updateDto, user);

        return ResUserDto.fromEntity(this.userRepository.save(user));

    }

    public ResUserDto activateUser(String activationCode){
        //TODO:

        //Check the db for the activation code
        //Retrieve the user under that activation code
        //Update user as active
        //Return the user info

        return new ResUserDto();
    }
    
}
