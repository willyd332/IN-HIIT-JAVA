package HIIT.IN;

import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/workouts")
public class WorkoutController {


    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private WorkoutRepository WorkoutRepository;


    @GetMapping
    public Iterable<Workout> getWorkouts(){

        return WorkoutRepository.findAll();
    }


    @PostMapping
    public Workout createWorkout(@RequestBody Workout newWorkout, HttpSession session) throws IOException {

        User user = UserRepository.findByUsername(session.getAttribute("username").toString());

        if (user == null){
            throw new IOException("You Must Be Logged In");
        }

        newWorkout.setUser(user);
        Workout createdWorkout = WorkoutRepository.save(newWorkout);

        if (createdWorkout != null){
            return createdWorkout;
        } else {
            throw new IOException("Creation Failed");
        }

        }


    @DeleteMapping("{id}")
    public Workout deleteWorkout(@PathVariable Long id) throws IOException {

        Optional<Workout> queryResponse = WorkoutRepository.findById(id);

        System.out.println(queryResponse.get());
        System.out.println(id);

        if (queryResponse.isPresent()){

            Workout deletedWorkout = queryResponse.get();
            WorkoutRepository.deleteById(id);

            return deletedWorkout;

        } else {

            throw new IOException("This Workout Doesn't Exists");

        }

    }


    @PutMapping("{id}")
    public Workout editWorkout(@RequestBody Workout newWorkout, @PathVariable("id") Long id) throws IOException {

        Optional<Workout> queryResponse = WorkoutRepository.findById(id);

        if (queryResponse.isPresent()){

          Workout workoutToEdit = queryResponse.get();
          workoutToEdit.setWorkout(newWorkout);
          return WorkoutRepository.save(workoutToEdit);

        } else {

            throw new IOException("This Workout Doesn't Exists");

        }

    }


}