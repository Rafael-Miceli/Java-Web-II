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
public class UsuarioDao implements Serializable {

	private static final long serialVersionUID = 1L;

	static List<Usuario> usuarios;
	
	private Usuario compradorNovo = new Usuario();
	
	private static UsuarioDao usuarioDaoInstance;
	
	public UsuarioDao()
	{
		CriarAdmin();
	}
	
	public static UsuarioDao Create()
	{
		if(usuarioDaoInstance == null)
		{			
			usuarioDaoInstance = new UsuarioDao();
		}
		
		return usuarioDaoInstance;
	}
	
	private static void CriarAdmin() {
		usuarios = new ArrayList();
		
		Usuario admin = new Usuario();
		admin.setNome("Ricardo");
		admin.setLogin("admin");
		admin.setSenha("admin");
		admin.setPapel("admin");
		
		usuarios.add(admin);
	}
	
	public Usuario buscarUsuarioPorLogin(String login) {
		for (Usuario usuario : usuarios) {
			if(usuario.getLogin().equals(login))
				return usuario;
		}
		
		return null;
	}	
	
	//Adiciona Comprador
	//@Interceptors(AutorizacaoAdministradorInterceptador.class)
	public void adicionarComprador(Usuario compradorNovo) throws Exception{
		
		for (Usuario comprador : usuarios) {
			if(comprador.getLogin().equals(compradorNovo.getLogin()))
				throw new Exception();
		}
		
		this.compradorNovo.setPapel("comprador");
		this.usuarios.add(this.compradorNovo);
		this.compradorNovo = new Usuario();
	}
	
	//Exclui comprador
	//@Interceptors(AutorizacaoAdministradorInterceptador.class)
	public void excluirComprador(Usuario comprador){
		this.usuarios.remove(comprador);
	}

	//Lista os compradores
	//@Interceptors(AutorizacaoAdministradorInterceptador.class)
	public List<Usuario> listarCompradores(){
		return this.usuarios;
	}
	
	//Editar Comprador
	//@Interceptors(AutorizacaoAdministradorInterceptador.class)
	public void editarComprador(Usuario comprador){
		
	}

	
}
