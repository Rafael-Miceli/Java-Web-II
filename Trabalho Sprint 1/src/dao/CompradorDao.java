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
public class CompradorDao implements Serializable {

	private static final long serialVersionUID = 1L;

	List<Usuario>compradores;
	
	public CompradorDao() {
		compradores = new ArrayList();
	}
	
	
	//Adiciona Comprador
	@Interceptors(AutorizacaoAdministradorInterceptador.class)
	public void adicionarComprador(Usuario compradorNovo) throws Exception{
		
		for (Usuario comprador : compradores) {
			if(comprador.getNome().equals(compradorNovo.getNome()))
				throw new Exception();
		}
		
		this.compradores.add(compradorNovo);
	}
	
	//Exclui comprador
	@Interceptors(AutorizacaoAdministradorInterceptador.class)
	public void excluirComprador(Usuario comprador){
		this.compradores.remove(comprador);
	}

	//Lista os compradores
	@Interceptors(AutorizacaoAdministradorInterceptador.class)
	public List<Usuario> listarCompradores(){
		return this.compradores;
	}
	
	//Editar Comprador
	@Interceptors(AutorizacaoAdministradorInterceptador.class)
	public void editarComprador(Usuario comprador){
		
	}

	
}
