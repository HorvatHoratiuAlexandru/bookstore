package com.horvat.bookstore.appUser;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.horvat.bookstore.appUser.dtos.requests.Create;
import com.horvat.bookstore.appUser.dtos.requests.ReqUserDto;
import com.horvat.bookstore.appUser.dtos.responses.Created;
import com.horvat.bookstore.appUser.dtos.responses.ResUserDto;
import com.horvat.bookstore.appUser.exceptions.UserNotFoundException;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public ResUserDto getUser(String id) {
        return ResUserDto.fromEntity(this.findUser(id));
    }

    @Override
    public Created createUser(Create newUserDto) {
        UserModel newUser = Create.getEntity(newUserDto);
        
        newUser.setActive(true);
        newUser.setRole(RoleModel.REGULAR);
        newUser.setPassword(this.bCryptPasswordEncoder.encode(newUser.getPassword()));
        newUser.autoGenerateUID();
        newUser = this.userRepository.save(newUser);

        return Created.fromEntity(newUser);
    }

    @Override
    public ResUserDto updateUser(String id, ReqUserDto updateDto) {
        UserModel user = this.findUser(id);
        
        if (user == null) return null;
        if(updateDto == null) return ResUserDto.fromEntity(user);
        
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

    private UserModel findUser(String id){
        List<UserModel> userQuerySet = this.userRepository.findByUid(id);

        if(userQuerySet!=null && !userQuerySet.isEmpty()){
           return userQuerySet.get(0); 
        }

        StringBuilder sb = new StringBuilder();
        sb.append("User with Id: ").append(id.toString()).append(" NotFound");
        throw new UserNotFoundException(sb.toString());

    }
    
}
