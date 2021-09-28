package hska.iwi.eShopMaster.model.businessLogic.manager.impl.microservices;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


//copy of class 'User' of the user service module
@Entity
public class MicroUser {

    @Id
    @GeneratedValue
    private long id;

    private long roleId;

    private String firstname;

    private String lastname;

    private String username;

    private String password;

    public MicroUser(long roleId, String firstname, String lastname, String username, String password) {
        this.roleId = roleId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    public MicroUser() {
    }

    public long getId() {
        return id;
    }

    public long getRoleId() {
        return roleId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
