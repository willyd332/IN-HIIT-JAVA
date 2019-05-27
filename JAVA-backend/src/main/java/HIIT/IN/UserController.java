package HIIT.IN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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



    @PostMapping("/register")
    public User register(@RequestBody User newUser, HttpSession session) throws IOException {

        User createdUser = UserService.saveUser(newUser);

        if (createdUser == null){
            throw new IOException("This User Already Exists");
        } else {
            session.setAttribute("username", createdUser.getUsername());
        }

        return createdUser;
    }


    @DeleteMapping("{id}")
    public User deleteUser(@PathVariable Long id) throws IOException {

        Optional<User> queryResponse = UserRepository.findById(id);

        if (queryResponse.isPresent()){

            User deletedUser = queryResponse.get();
            UserRepository.deleteById(id);

            return deletedUser;

        } else {

            throw new IOException("This User Doesn't Exists");

        }

    }


    @GetMapping("/logout")
    public HashMap<String,String> logOut(HttpSession session) {

        session.invalidate();

        HashMap<String,String> response = new HashMap<>();
        response.put("data", "logged out");
        response.put("status", "200");

        return response;
    }


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
