package com.example.serratec.exception;

public class ResourceBadRequestException extends RuntimeException{
	public ResourceBadRequestException(String mensagem) {
		super(mensagem);
	}
}
