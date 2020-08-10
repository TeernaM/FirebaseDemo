package restDemo.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import restDemo.demo.object.Person;
import restDemo.demo.service.FirebaseService;

import java.util.concurrent.ExecutionException;

@RestController
public class UserController {

    @Autowired
    FirebaseService firebaseService;

    @GetMapping("/getUserDetails")
    public Person getUserDetails(@RequestHeader String name) throws ExecutionException, InterruptedException {
        return firebaseService.getUserDetails(name);
    }
    @PostMapping("/createNewUser")
    public String createNewUser(@RequestBody Person person) throws ExecutionException, InterruptedException {
        return firebaseService.saveUserDetails(person);
    }
    @PutMapping("/updateUser")
    public String updateUser(@RequestBody Person person) throws ExecutionException, InterruptedException {
        return firebaseService.updateUserDetails(person);
    }
    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestHeader String name){
        return firebaseService.deleteUserDetails(name);
    }
}
