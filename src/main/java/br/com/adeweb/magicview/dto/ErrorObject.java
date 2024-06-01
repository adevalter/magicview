package br.com.adeweb.magicview.dto;

public record ErrorObject(
        String field,
        String message,
        Object parameter) {

}
