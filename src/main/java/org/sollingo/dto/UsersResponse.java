package org.sollingo.dto;

public class UsersResponse {

    private long id;
    private String email;
    public UsersResponse(Long id,String email){
        this.id=id;
        this.email=email;
    }
    public Long getId() { return id; }
    public String getEmail() { return email; }
}
