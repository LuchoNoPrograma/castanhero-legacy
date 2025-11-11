package uap.usic.siga.dto;

import uap.usic.siga.anotacionesCliente.PasswordMatches;
import uap.usic.siga.anotacionesCliente.ValidEmail;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.Roles;

import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * Created by Keno&Kemo on 22.10.2017..
 */
@PasswordMatches
public class UserDto {

    private Long id;

    private String name;

    private String surname = "Ninguna";

    @NotBlank (message = "Username is required")
    private String username;

    private String email;

    @NotBlank (message = "Password is required")
    private String password;

    @NotBlank (message = "Matching password is required")
    private String matchingPassword;

    private boolean enabled;

    private Personas personas;

    public UserDto(Long id, String name, String surname, String username, String email, String password, String
            matchingPassword, Set<Roles> roles, boolean enabled) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.enabled = enabled;
    }

    public UserDto() {

    }

    
    public Personas getPersonas() {
		return personas;
	}

	public void setPersonas(Personas personas) {
		this.personas = personas;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
