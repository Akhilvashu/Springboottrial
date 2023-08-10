package com.mysql.trial.controller;

import com.mysql.trial.service.UserService;
import com.mysql.trial.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;



@RestController
@RequestMapping("/users")
public class Controller {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> getallusers(){
		List<User> user = userService.getAllUsers();
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/{Username}")
	public ResponseEntity<User> getUserByusername(@PathVariable String Username){
		User user = userService.getByUsername(Username);
		if (user != null) {
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> enterUser(@RequestBody User NewUser){
		User SavedUser = userService.saveUser(NewUser);
        return new ResponseEntity<>(SavedUser, HttpStatus.CREATED);
		
	}

	@PutMapping("/{Username}")
	public ResponseEntity<User> NewUser(@PathVariable String Username, @RequestBody User UpdatedUser){
		User ExistingUser = userService.getByUsername(Username);
		if (ExistingUser != null) {
			ExistingUser.setUsername(UpdatedUser.getUsername());
			ExistingUser.setPassword(UpdatedUser.getPassword());
			
			User SavedUser = userService.saveUser(ExistingUser);
			return ResponseEntity.ok(SavedUser);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{Username}")
	public ResponseEntity<User> DeleteUser(@PathVariable String Username){
		User UserExistornot = userService.getByUsername(Username);
		if (UserExistornot != null) {
			User deletedUser = userService.deleteUser(Username);
			return new ResponseEntity<>(deletedUser,HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
}

