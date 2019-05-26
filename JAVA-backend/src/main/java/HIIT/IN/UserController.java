package HIIT.IN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.rmi.server.ExportException;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private WorkoutRepository WorkoutRepository;

    @Autowired
    private UserService UserService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping
    public String getUsers(){
        return "Getting Users";
    }


    @PostMapping("/register")
    public User register(@RequestBody User newUser, HttpSession session) throws IOException {

        System.out.println("**********************************************************************");
        System.out.println(newUser.getUsername());
        System.out.println(newUser.getUserpassword());
        System.out.println("**********************************************************************");

        User createdUser = UserService.saveUser(newUser);

        if (createdUser == null){
            throw new IOException("This User Already Exists");
        } else {
            session.setAttribute("username", createdUser.getUsername());
        }

        return createdUser;
    }


    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable Long id) { return "Deleting User " + id; }


    @PutMapping("{id}")
    public String editUser(@PathVariable Long id) { return "Edititng User " + id; }


    @GetMapping("{id}")
    public String showUser(@PathVariable Long id) { return "Showing User " + id; }


    @GetMapping("/logout")
    public String logOut() { return "Logging Out"; }


    @PostMapping("/login")
    public User logIn(@RequestBody User user, HttpSession session) throws IOException {

        System.out.println("Logging in as " + user.getUsername());

        User loggedUser = UserRepository.findByUsername(user.getUsername());

        if (loggedUser == null){
            throw new IOException("Invalid Username or Password");
        }

        boolean valid = bCryptPasswordEncoder.matches(user.getUserpassword(), loggedUser.getUserpassword());

        if (valid){
            session.setAttribute("username", loggedUser.getUsername());
            return loggedUser;
        } else {
            throw new IOException("Invalid Username or Password");
        }
    }




}
