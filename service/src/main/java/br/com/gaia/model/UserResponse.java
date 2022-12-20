package br.com.gaia.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private AccountInfo accountInfo;
    private UserInfo userInfo;
}
