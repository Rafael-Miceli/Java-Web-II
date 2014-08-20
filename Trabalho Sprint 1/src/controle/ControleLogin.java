package controle;

import i18n.MessageBundleImpl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dao.CompradorDao;
import dao.InicializaAdministradores;
import regrasNegocio.RealizaAutentificacao;
import lombok.Getter;
import lombok.Setter;
import modelo.Usuario;

@Getter
@Setter
@Named
@RequestScoped
public class ControleLogin implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private InicializaAdministradores inicializaAdministradores;
	
	@Inject
	private CompradorDao compradorDao;
	
	@Inject
	private Usuario usuario;

	@Inject
	@MessageBundleImpl
	private ResourceBundle messages;

	
	//Método que realiza o login
	public String realizarLogin()
	{	
		/*if(RealizaAutentificacao.autentificarUsuario(this.usuario, this.compradorDao)){
			return "paginaConstrucao";
		}
		return "falha";*/
		
		String login = usuario.getLogin();
		String senha = usuario.getSenha();
		
		//List<Usuario> compradores = compradorDao.listarCompradores();
		
		//Verifica se usuário é administrador
		if(login.equals("admin") && senha.equals("admin"))
		{	
			usuario.setPapel("admin");		
			return "paginaConstrucao";
		}
		/*else if(login.equals("admin2") && senha.equals("admin2"))
		{
			usuario.setPapel("admin");		
			return "paginaConstrucao";
		}*/
		//verifica se usuário é comprador
		/*else if(verificaLoginSenhaDeCompradores(login, senha))
		{
			usuario.setPapel("comprador");		
			return "paginaConstrucao";
		}*/
		else
		{
			return "falha";
		}
	}
	
	private boolean verificaLoginSenhaDeCompradores(String login, String senha, List<Usuario> compradores)
	{
		
		for (Usuario comprador : compradores) {
			if(login.equals(comprador.getLogin())
					&& senha.equals(comprador.getSenha()))
			{
				return true;
			}
		}
		
		return false;		
	}
	
}