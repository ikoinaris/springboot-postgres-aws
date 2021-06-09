package ik.springboot.springbootaws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ik.springboot.springbootaws.entity.User;
import ik.springboot.springbootaws.exception.ResourceNotFoundException;
import ik.springboot.springbootaws.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") long id){
        return this.userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User with id: " + id +  "not found."));
    }

    @PostMapping
    public User creatUser(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable(value = "id") long id) {
        User userToUpdate = this.userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User with id: " + id +  "not found."));

        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());

        return this.userRepository.save(userToUpdate);
    }

    @DeleteMapping("/id")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") long id) {
        User userToDelete = this.userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User with id: " + id +  "not found."));

        this.userRepository.delete(userToDelete);
        return ResponseEntity.ok().build();
    }
    
}
