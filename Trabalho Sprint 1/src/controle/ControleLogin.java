package controle;

import i18n.MessageBundleImpl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import dao.UsuarioDao;
import lombok.Getter;
import lombok.Setter;
import modelo.Usuario;

@Getter
@Setter
@Named
@RequestScoped
public class ControleLogin implements Serializable {
	
	private static final long serialVersionUID = 1L;		
	
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
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().put("user", usuario);
			
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
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login";
	}
}
