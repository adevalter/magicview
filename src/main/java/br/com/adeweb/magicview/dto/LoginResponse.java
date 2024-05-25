package br.com.adeweb.magicview.dto;

public record LoginResponse(UserDto user, String token) {
}
