package com.sarbesh.AuthService.security;

import com.sarbesh.AuthService.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import static java.lang.System.out;

@Component
public class Authentication {
    private static HashMap<Long,String> map= new HashMap<>();

    @Autowired
    private RestTemplate restTemplate;

    private Authentication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generateToken(User usr){
        out.println("In Token");
        long id = usr.getId();
        String name = new Authentication(restTemplate).getFullName(id);
        String token = id + "." + name + "." + usr.getEmail();
        out.println(token);
        if (map.isEmpty()||map.get(id).equals(token)) {
            map.put(id, token);
        } else {
            throw new RuntimeException("User has an session active");
        }
        out.println("value of "+ id+ "Token in map: "+ map.get(id));
        return token;
    }

    private String getFullName(Long id){
        try {
            String url= "http://localhost:8091/profile/getName/" + id;
            out.println(url);
            ResponseEntity<String> resp = restTemplate.getForEntity( url, String.class);
            out.println(resp);
            if (resp.getStatusCode()== HttpStatus.OK){
                return resp.getBody();
            } else {
                return "Default Name";
            }
        } catch (NullPointerException e){
            out.println("Get FullName failed with "+e);
            return "Exception";
        }
    }

}
