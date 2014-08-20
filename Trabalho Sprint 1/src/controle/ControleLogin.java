package controle;

import i18n.MessageBundleImpl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dao.InicializaAdministradores;
import dao.UsuarioDao;
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
	
	private UsuarioDao usuarioDao;
	
	@Inject
	private Usuario usuario;

	@Inject
	@MessageBundleImpl
	private ResourceBundle messages;

	
	//Método que realiza o login
	public String realizarLogin()
	{			
		usuarioDao = UsuarioDao.Create();
		
		String login = usuario.getLogin();
		String senha = usuario.getSenha();
		
		Usuario usuario = usuarioDao.buscarUsuarioPorLogin(login);				
		
		//Verifica se usuário existe, e se existir, verifica se sua senha está correta
		if (usuario != null && usuario.getSenha().equals(senha))
		{
			//Verifica se usuário é administrador
			if (usuario.getPapel().equals("admin"))
				return "listaCompradores";
			else
				return "paginaContrucao";			
					
		}
		else
			return "falha";			
		
	}
	
	public String sairSistema(){
		usuario.setPapel("null");
		return "login";
	}
}
