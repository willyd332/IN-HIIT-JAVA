package HIIT.IN;

import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String createWorkout(){ return "Creating Workout"; }


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
    public String putWorkout(@PathVariable Long id){ return "Editing Workout " + id; }


    @GetMapping("{id}")
    public String showWorkout(@PathVariable Long id){ return "Showing Workout " + id; }



}