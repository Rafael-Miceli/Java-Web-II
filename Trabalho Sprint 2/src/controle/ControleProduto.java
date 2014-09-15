package controle;

import i18n.MessageBundleImpl;
import interceptadores.AdministradorFornecedorInterceptador;
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
import modelo.Produto;
import modelo.Usuario;
import dao.ProdutoDao;
import dao.UsuarioDao;

@Getter
@Setter
@Named
@RequestScoped
public class ControleProduto implements Serializable {	

	private static final long serialVersionUID = 1L;
	

	private Produto produtoSelecionado = new Produto();	
	
	private List<Produto> listaProdutos = null;
		
	private ProdutoDao produtoDao;
	
	
	@Inject
	@MessageBundleImpl
	private ResourceBundle messages;
	
	public ControleProduto() throws Exception {
		produtoDao = ProdutoDao.Create();
		listarProdutos();
	}
	
	
	
	/***** Produto   *****/
	/*****           *****/
	
	//Adiciona Comprador
	@Interceptors(AdministradorInterceptador.class)
	public String adicionarProduto() throws Exception {		
		produtoSelecionado = new Produto();	
		
		return "adicionaProduto";
	}
	
	//Salva Novo Produto
	@Interceptors(AdministradorInterceptador.class)
	public String salvarNovoProduto() throws Exception {
				
		for (Produto produto : listaProdutos) {
			if(produtoSelecionado.getCodigo().equals(produto.getCodigo())){
							
				 throw new RuntimeException(messages.getString("login.de.outro.usuario"));
				
				//FacesMessage message = new FacesMessage("Login j� existe");
	            //FacesContext context = FacesContext.getCurrentInstance();
	            //context.addMessage(this.campoLogin.getClientId(context), message);
				
	            /*FacesContext context = FacesContext.getCurrentInstance();  
		          
		        context.addMessage(null, new FacesMessage("Successful", "Hello "));  
		        context.addMessage(null, new FacesMessage("Second Message", "Additional Info Here..."));*/ 					       
			}
		}
		
		produtoDao.adicionarProduto(produtoSelecionado);
		listarProdutos();
		
		return "listaProdutos";
	}
		
		
	// Edita Produto
	@Interceptors(AdministradorInterceptador.class)
	public String editarProduto() {		
		return "editaProduto";
	}
	
	//Salva altera��es do Produto
	@Interceptors(AdministradorInterceptador.class)
	public String salvarAlteracoesDoProduto() throws Exception {
				
		produtoDao.editarProduto(produtoSelecionado);
		listarProdutos();
		
		return "listaProdutos";
	}

	
	// Exclui Produto
	@Interceptors(AdministradorInterceptador.class)
	public String excluirProduto() throws Exception {
		produtoDao.excluirProduto(produtoSelecionado);
		listarProdutos();
		return "listaProdutos";
	}
		
	

	/******************    ******************/
	/******************    ******************/
			
		
	
	// Lista os Produtos
	@Interceptors(AdministradorFornecedorInterceptador.class)
	public List<Produto> listarProdutos() throws Exception {
		
		listaProdutos = produtoDao.listarProdutos();		
		return listaProdutos;
	}
	
	@Interceptors(AdministradorFornecedorInterceptador.class)
	public void setListarProdutos(List<Produto> listarProdutos){
		this.listaProdutos = listarProdutos; 
	}
	
	
	// Produto selecionado
	@Interceptors(AdministradorFornecedorInterceptador.class)
	public Produto getUsuarioSelecionado(){
		return produtoSelecionado;
	}
	
	@Interceptors(AdministradorFornecedorInterceptador.class)
	public void setUsuarioSelecionado(Produto produtoSelecionado){
		this.produtoSelecionado = produtoSelecionado;
	}
	

}
