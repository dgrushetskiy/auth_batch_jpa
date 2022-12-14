package ru.gothmog.ws.auth.rest.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
    private Long id;
    private String token;
    private String type = "Bearer";
    private String username;
    private String email;
    private List<String> roles;
    private List<String> offices;

    public JwtResponse(Long id, String token, String username, String email, List<String> roles, List<String> offices) {
        this.id = id;
        this.token = token;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.offices = offices;
    }
}
