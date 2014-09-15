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
					
		            /*FacesContext context = FacesContext.getCurrentInstance();  
			          
			        context.addMessage(null, new FacesMessage("Successful", "Hello "));  
			        context.addMessage(null, new FacesMessage("Second Message", "Additional Info Here..."));  
			        throw new RuntimeException(messages.getString("login.de.outro.usuario"));*/
				}				
			}			
		}
		
		if(usuarioSelecionado.getPapel().equals("fornecedor")){
			usuarioSelecionado.setDataCadastro(new Date());
		}
		
		usuarioDao.adicionarUsuario(usuarioSelecionado);
		listarUsuarios();
		
		return "listaUsuarios";
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
		
		return "listaUsuarios";
	}
	
	// Exclui Usuario
	public String excluirUsuario() throws Exception {
		usuarioDao.excluirUsuario(usuarioSelecionado);
		listarUsuarios();
		return "listaUsuarios";
	}
	
	
	
	
	/***** Comprador *****/
	/*****           *****/
	
	//Adiciona Comprador
	/*public String adicionarComprador() throws Exception {		
		usuarioSelecionado = new Usuario();	
		
		return "adicionaComprador";
	}
	
	//Salva Novo comprador
	public String salvarNovoComprador() throws Exception {
				
		for (Usuario comprador : listaCompradores) {
			if(usuarioSelecionado.getLogin().equals(comprador.getLogin())){
								
				FacesMessage message = new FacesMessage("Login já existe");
	            FacesContext context = FacesContext.getCurrentInstance();
	            context.addMessage(this.campoLogin.getClientId(context), message);
				
	            //FacesContext context = FacesContext.getCurrentInstance();  
		          
		        //context.addMessage(null, new FacesMessage("Successful", "Hello "));  
		        //context.addMessage(null, new FacesMessage("Second Message", "Additional Info Here..."));  
		        //throw new RuntimeException(messages.getString("login.de.outro.usuario"));
			}
		}
		
		usuarioSelecionado.setPapel("comprador");
		usuarioDao.adicionarUsuario(usuarioSelecionado);
		listarCompradores();
		
		return "listaCompradores";
	}
		
		
	// Editar Comprador
	public String editarComprador() {		
		return "editaComprador";
	}
	
	//Salva alterações do comprador
	public String salvarAlteracoesDoComprador() throws Exception {
				
		usuarioDao.editarComprador(usuarioSelecionado);
		listarCompradores();
		
		return "listaCompradores";
	}
	
	// Exclui Comprador
	public String excluirComprador() throws Exception {
		usuarioDao.excluirComprador(usuarioSelecionado);
		listarCompradores();
		return "listaCompradores";
	}*/
	
	
	/*****           *****/
	/***** Fornecedor *****/
	/*****           *****/

	//Adiciona Fornecedor
	/*public String adicionarFornecedor() throws Exception {	
		
		usuarioSelecionado = new Usuario();	
		
		Date dataAtual = new Date();
		
		usuarioSelecionado.setDataCadastro(dataAtual);
		
		return "adicionaFornecedor";
	}
	
	//Salva Novo Fornecedor
	public String salvarNovoFornecedor() throws Exception {
		
		for (Usuario fornecedor : listaFornecedores) {
								
			if(usuarioSelecionado.getLogin().equals(fornecedor.getLogin())){
				
				FacesMessage message = new FacesMessage("Login já existe");
	            FacesContext context = FacesContext.getCurrentInstance();
	            context.addMessage(this.campoLogin.getClientId(context), message);
				
	            //FacesContext context = FacesContext.getCurrentInstance();  
		          
		        //context.addMessage(null, new FacesMessage("Successful", "Hello "));  
		        //context.addMessage(null, new FacesMessage("Second Message", "Additional Info Here..."));  
		        //throw new RuntimeException(messages.getString("login.de.outro.usuario"));
			}		
				
		}
		
		usuarioSelecionado.setPapel("fornecedor");
		usuarioDao.adicionarUsuario(usuarioSelecionado);
		listarFornecedores();
		
		return "listaFornecedores";
	}
		
	
	// Editar Comprador
	public String editarFornecedor() {	
		
		Date dataAtual = new Date();
		
		usuarioSelecionado.setDataCadastro(dataAtual);
		
		return "editarFornecedor";
	}
	
	//Salva alterações do fornecedor
	public String salvarAlteracoesDoFornecedor() throws Exception {
		
		usuarioDao.editarFornecedor(usuarioSelecionado);
		listarFornecedores();
		
		return "listaFornecedores";
	}

	
	// Exclui Usuário
	public String excluirFornecedor() throws Exception {
		usuarioDao.excluirFornecedor(usuarioSelecionado);
		listarFornecedores();
		return "listaFornecedores";
	}*/
		
	

	/******************    ******************/
	/******************    ******************/
			
		
	
	// Lista os compradores
	/*public List<Usuario> listarCompradores() throws Exception {
		
		listaCompradores = usuarioDao.listarCompradores();		
		return listaCompradores;
	}
	
	
	public void setListarCompradores(List<Usuario> listarCompradores){
		this.listaCompradores = listarCompradores; 
	}
	
	// Lista os fornecedores
	public List<Usuario> listarFornecedores() throws Exception {
		
		listaFornecedores = usuarioDao.listarFornecedores();		
		return listaFornecedores;
	}
	
	
	public void setListarFornecedores(List<Usuario> listarFornecedores){
		this.listaFornecedores = listarFornecedores; 
	}*/
	
	
	
	
	//FacesContext.getCurrentInstance().addMessage("campoLogin", 
	  //new FacesMessage(FacesMessage.SEVERITY_INFO,"Login já existe", "Login já existe"));  
    //throw new RuntimeException(messages.getString("login.de.outro.usuario"));

    //campoLogin.getClientId(FacesContext.getCurrentInstance());
    //FacesContext.getCurrentInstance().addMessage(campoLogin, "sua mensagem aqui");

    //this.campoLogin.getClientId(FacesContext.getCurrentInstance(), "sua mensagem aqui");
		
	  //FacesContext.getCurrentInstance().addMessage("adicionaComprador:campoLogin", new FacesMessage("Erro", "Erro"));
		 
	  //throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "msg", "msg"));
		
}
