package br.com.gaia.api;

import br.com.gaia.model.AccountInfo;
import br.com.gaia.model.UserInfo;


public interface AdapterUser {
    public UserInfo getUserAccountApi(String token);
    public AccountInfo getAccountInfoApi(String token);
}
