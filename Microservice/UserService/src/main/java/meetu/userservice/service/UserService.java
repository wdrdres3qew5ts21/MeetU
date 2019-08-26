/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.service;

import java.util.List;
import meetu.userservice.filters.TokenAuthenticationService;
import meetu.userservice.repository.UserRepository;
import meetu.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author wdrdr
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    public User createUser(User user) {
    	
    	BCryptPasswordEncoder passwordEncrypt = new BCryptPasswordEncoder();
        user.setPassword(passwordEncrypt.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        return userRepository.findById(id).get();
    }

    public List<User> findByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    public List<User> findByEmailLike(String email) {
        return userRepository.findByEmailLike(email);
    }

    public boolean isUserMoreThanOne(List<User> users) {
        return users.size() > 1 ? true : false;
    }
    
    public String verifyJwtToken(){
        return null;
    }

    public String login(User userFromInput) {
        User userFromDatabase = userRepository.findByUsername(userFromInput.getUsername());
        String usernameFromInput = userFromInput.getUsername();
        String passwordFromInput = userFromInput.getPassword();
        String usernameFromDatabase = userFromDatabase.getUsername();
        BCryptPasswordEncoder passwordDecrypt = new BCryptPasswordEncoder();
        String passwordFromDatabase = userFromDatabase.getPassword();
        boolean isPasswordCorrectFromEncryptDatabase = passwordDecrypt.matches(passwordFromInput, passwordFromDatabase);
      
        if (usernameFromInput.equals((usernameFromDatabase)) && isPasswordCorrectFromEncryptDatabase ) {
        	return tokenAuthenticationService.createTokenUser(userFromDatabase);
        	
        }else{
           return "Sorry Login fail!!!!!!!";
        }
        
    }

}
