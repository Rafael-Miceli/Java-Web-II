package dao;

import interceptadores.AutorizacaoAdministradorInterceptador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import modelo.Usuario;


@Getter
@Setter
@Named
@SessionScoped
//@Interceptors(AutorizacaoAdministradorInterceptador.class)
public class InicializaAdministradores implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	private List<Usuario> administradores;

	public InicializaAdministradores() {
		
		//Instancia lista de administradores
		administradores = new ArrayList<>();
		
		//Instancia primeiro administrador
		Usuario admin1 = new Usuario();
		admin1.setNome("Ricardo");
		admin1.setLogin("admin1");
		admin1.setSenha("admin1");
		admin1.setPapel("admin");
		
		//Instancia segundo administrador
		Usuario admin2 = new Usuario();
		admin2.setNome("Mendes");
		admin2.setLogin("admin2");
		admin2.setSenha("admin2");
		admin2.setPapel("admin");
		
		//Adiciona os administradores na lista
		administradores.add(admin1);
		administradores.add(admin2);
		
	}
	
}
