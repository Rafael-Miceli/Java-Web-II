package controle;

import i18n.MessageBundleImpl;

import java.io.Serializable;
import java.util.ArrayList;
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
	
	private List<String> papeis;
	
	private String login; 
	
	@Inject
	private Usuario usuario;

	@Inject
	@MessageBundleImpl
	private ResourceBundle messages;
	
	public ControleLogin() {
		
		papeis = new ArrayList<String>();
		papeis.add("comprador");
		papeis.add("fornecedor");
		papeis.add("admin");
		
	}

	
	//Metodo que realiza o login
	public String realizarLogin()
	{			
		usuarioDao = UsuarioDao.Create();
		
		String login = usuario.getLogin();
		String senha = usuario.getSenha();
		String papel = usuario.getPapel();
		
		Usuario usuario = usuarioDao.buscarUsuarioPorLoginNumPapel(login, papel);				
		
		//Verifica se usuário existe, e se existir, verifica se sua senha está correta
		if (usuario != null && usuario.getSenha().equals(senha))
		{
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().put("user", usuario);
			
			login = context.getExternalContext().getSessionMap().put("user", usuario).toString();
			
			//usario administrador
			if (usuario.getPapel().equals("admin")){
				return "PainelAdmin";
			}	
			//usuario comprador
			else if (usuario.getPapel().equals("comprador")){
				return "paginaConstrucao";		
			}	
			//usario fornecedor
			else{
				return "listaProdutosParaFornecedor";	
			}
				
		}
		else{
			return "falha";		
		}
					
	}
	
	public String sairSistema(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login";
	}
	
	public void PegarUsuario(){
		FacesContext context = FacesContext.getCurrentInstance();
		login = context.getExternalContext().getSessionMap().put("user", usuario).toString();
	}
	
}
