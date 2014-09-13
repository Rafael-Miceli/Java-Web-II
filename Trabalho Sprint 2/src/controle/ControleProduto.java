package controle;

import i18n.MessageBundleImpl;
import interceptadores.AutorizacaoAdministradorInterceptador;

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
@Interceptors(AutorizacaoAdministradorInterceptador.class)
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
	public String adicionarProduto() throws Exception {		
		produtoSelecionado = new Produto();	
		
		return "adicionaProduto";
	}
	
	//Salva Novo Produto
	public String salvarNovoProduto() throws Exception {
				
		//for (Produto produto : listaProdutos) {
			//if(produtoSelecionado.getCodigo().equals(produto.getCodigo())){
								
				//FacesMessage message = new FacesMessage("Login já existe");
	            //FacesContext context = FacesContext.getCurrentInstance();
	            //context.addMessage(this.campoLogin.getClientId(context), message);
				
	            /*FacesContext context = FacesContext.getCurrentInstance();  
		          
		        context.addMessage(null, new FacesMessage("Successful", "Hello "));  
		        context.addMessage(null, new FacesMessage("Second Message", "Additional Info Here..."));*/ 
				
		       // throw new RuntimeException(messages.getString("login.de.outro.usuario"));
			//}
		//}
		
		produtoDao.adicionarProduto(produtoSelecionado);
		listarProdutos();
		
		return "listaProdutos";
	}
		
		
	// Edita Produto
	public String editarProduto() {		
		return "editaProduto";
	}
	
	//Salva alterações do Produto
	public String salvarAlteracoesDoProduto() throws Exception {
				
		produtoDao.editarProduto(produtoSelecionado);
		listarProdutos();
		
		return "listaProdutos";
	}

	
	// Exclui Produto
	public String excluirProduto() throws Exception {
		produtoDao.excluirProduto(produtoSelecionado);
		listarProdutos();
		return "listaProdutos";
	}
		
	

	/******************    ******************/
	/******************    ******************/
			
		
	
	// Lista os Produtos
	public List<Produto> listarProdutos() throws Exception {
		
		listaProdutos = produtoDao.listarProdutos();		
		return listaProdutos;
	}
	
	
	public void setListarProdutos(List<Produto> listarProdutos){
		this.listaProdutos = listarProdutos; 
	}
	
	
	// Produto selecionado
	public Produto getUsuarioSelecionado(){
		return produtoSelecionado;
	}
	
	public void setUsuarioSelecionado(Produto produtoSelecionado){
		this.produtoSelecionado = produtoSelecionado;
	}
	

}
