package HIIT.IN;

import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String deleteWorkout(@PathVariable Long id){ return "Deleting Workout " + id; }


    @PutMapping("{id}")
    public String putWorkout(@PathVariable Long id){ return "Editing Workout " + id; }


    @GetMapping("{id}")
    public String showWorkout(@PathVariable Long id){ return "Showing Workout " + id; }



}