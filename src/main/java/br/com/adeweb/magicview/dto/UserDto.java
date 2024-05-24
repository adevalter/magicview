package br.com.adeweb.magicview.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class UserDto {
    private UUID id;
    private String nick;
    private String email;

    public UserDto(UUID id, String nick, String email) {
        this.id = id;
        this.nick = nick;
        this.email = email;
    }

}
