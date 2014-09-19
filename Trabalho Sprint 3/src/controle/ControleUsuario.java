package controle;

import i18n.MessageBundleImpl;
import interceptadores.AdministradorInterceptador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent; 
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import lombok.Getter;
import lombok.Setter;
import modelo.Usuario;
import dao.UsuarioDao;

@Getter
@Setter
@Named
@RequestScoped
@Interceptors(AdministradorInterceptador.class)
public class ControleUsuario implements Serializable {	


	private static final long serialVersionUID = 1L;
	

	private Usuario usuarioSelecionado = new Usuario();	
	
	private List<Usuario> listaUsuarios = null;
	
	//private List<Usuario> listaCompradores = null;
	
	//private List<Usuario> listaFornecedores = null;
	
	private UsuarioDao usuarioDao;
	
	private UIComponent campoLogin;
	
	@Inject
	@MessageBundleImpl
	private ResourceBundle messages;
	
	public ControleUsuario() throws Exception {
		usuarioDao = UsuarioDao.Create();
		listarUsuarios();
	}
	
	//Usuário selecionado
	public Usuario getUsuarioSelecionado(){
		return usuarioSelecionado;
	}
	
	public void setUsuarioSelecionado(Usuario usuarioSelecionado){
		this.usuarioSelecionado = usuarioSelecionado;
	}
	
	/*****           *****/
	/***** Usuários *****/
	/*****           *****/
	
	//Lista de Usuários
	public List<Usuario> listarUsuarios() throws Exception {
		
		listaUsuarios = usuarioDao.listarUsuariosSemPapelAdmin();		
		return listaUsuarios;
		
	}
	
	
	public void setListarUsuarios(List<Usuario> listarUsuarios){
		this.listaUsuarios = listarUsuarios; 
	}
	
	//Adiciona Usuario
	public String adicionarUsuario() throws Exception {		
		usuarioSelecionado = new Usuario();	
		
		return "adicionaUsuario";
	}
	
	//Salva Novo Usuario
	public String salvarNovoUsuario() throws Exception {
				
		for (Usuario usuario : listaUsuarios) {
			
			if(usuarioSelecionado.getPapel().equals(usuario.getPapel())){
				
				if(usuarioSelecionado.getLogin().equals(usuario.getLogin())){
					
					FacesMessage message = new FacesMessage("Login já existe");
		            FacesContext context = FacesContext.getCurrentInstance();
		            context.addMessage(this.campoLogin.getClientId(context), message);				
		          
				}				
			}			
		}
		
		if(usuarioSelecionado.getPapel().equals("fornecedor")){
			usuarioSelecionado.setDataCadastro(new Date());
		}
		
		usuarioDao.adicionarUsuario(usuarioSelecionado);
		listarUsuarios();
		
		return "PainelAdmin";
	}
	
	// Editar Usuário
	public String editarUsuario() {		
		return "editaUsuario";
	}
	
	//Salva alterações do usuario
	public String salvarAlteracoesDoUsuario() throws Exception {
				
		if(usuarioSelecionado.getPapel().equals("fornecedor")){
			usuarioSelecionado.setDataCadastro(new Date());
		}
		
		usuarioDao.editarUsuario(usuarioSelecionado);
		listarUsuarios();
		
		return "PainelAdmin";
	}
	
	// Exclui Usuario
	public String excluirUsuario() throws Exception {
		usuarioDao.excluirUsuario(usuarioSelecionado);
		listarUsuarios();
		return "PainelAdmin";
	}
	
	
	
	
}
