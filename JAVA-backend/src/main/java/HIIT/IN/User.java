package HIIT.IN;

import javax.persistence.*;


@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String userpassword;

    private int zipcode;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipCode) {
        this.zipcode = zipCode;
    }
}
