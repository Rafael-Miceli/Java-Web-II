package dao;

import java.io.Serializable;

import interceptadores.AutorizacaoInterceptador;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named
@RequestScoped
@Interceptors(AutorizacaoInterceptador.class)
public class Construcao implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//@Inject	
	private String validador;

}
