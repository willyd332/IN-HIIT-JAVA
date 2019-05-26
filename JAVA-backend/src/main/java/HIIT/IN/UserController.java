package HIIT.IN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private WorkoutRepository WorkoutRepository;


    @GetMapping
    public String getUsers(){
        return "Getting Users";
    }


    @PostMapping("/register")
    public String register() { return "Registering User"; }


    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable Long id) { return "Deleting User " + id; }


    @PutMapping("{id}")
    public String editUser(@PathVariable Long id) { return "Edititng User " + id; }


    @GetMapping("{id}")
    public String showUser(@PathVariable Long id) { return "Showing User " + id; }


    @GetMapping("/logout")
    public String logOut() { return "Logging Out"; }


    @PostMapping("/login")
    public String logIn() { return "Logging In"; }




}
