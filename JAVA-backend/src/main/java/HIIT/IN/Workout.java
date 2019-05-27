package HIIT.IN;

import javax.persistence.*;

@Entity
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int intervalone;

    private int intervaltwo;

    private int cycles;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


    public String getName() {
        return name;
    }

    public void setWorkout(Workout newWorkout){
        this.setName(newWorkout.getName());
        this.setIntervalone(newWorkout.getIntervalone());
        this.setIntervaltwo(newWorkout.getIntervaltwo());
        this.setCycles(newWorkout.getCycles());
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIntervalone() {
        return intervalone;
    }

    public void setIntervalone(int intervalone) {
        this.intervalone = intervalone;
    }

    public int getIntervaltwo() {
        return intervaltwo;
    }

    public void setIntervaltwo(int intervaltwo) {
        this.intervaltwo = intervaltwo;
    }

    public int getCycles() {
        return cycles;
    }

    public void setCycles(int cycles) {
        this.cycles = cycles;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
