package br.com.gaia.api;

import br.com.gaia.model.AccountInfo;
import br.com.gaia.model.UserInfo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

@Component
public class AdapterUserImpl implements AdapterUser {

    private RestTemplate restTemplate = new RestTemplate();
    public static final String UrlBase = "http://localhost:8083/marionete/useraccount";

   public UserInfo getUserAccountApi(String token){
       HttpHeaders header = new HttpHeaders();
       header.set("Authorization",token);
       HttpEntity<HttpHeaders> entity = new HttpEntity<>(header);
       var response =  restTemplate.exchange(UrlBase+"/user", HttpMethod.GET, entity,UserInfo.class);
       return response.getBody();
    }


    public AccountInfo getAccountInfoApi(String token){
        HttpHeaders header = new HttpHeaders();
        header.set("Authorization",token);
        HttpEntity<HttpHeaders> entity = new HttpEntity<>(header);
        var res =  restTemplate.exchange(UrlBase+"/account", HttpMethod.GET, entity,AccountInfo.class);
        return res.getBody();
    }

}
