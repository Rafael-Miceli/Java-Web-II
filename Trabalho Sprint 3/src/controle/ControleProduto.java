package controle;

import i18n.MessageBundleImpl;
import interceptadores.AdministradorFornecedorInterceptador;
import interceptadores.AdministradorInterceptador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import lombok.Getter;
import lombok.Setter;
import modelo.Categoria;
import modelo.Produto;
import modelo.Usuario;
import dao.ProdutoDao;

@Getter
@Setter
@Named
@RequestScoped
public class ControleProduto implements Serializable {	

	private static final long serialVersionUID = 1L;
	

	private Produto produtoSelecionado = new Produto();	
	
	private List<Produto> listaProdutos = null;
	
	private List<Produto> listaProdutosComQuantidadeAcimaDeZero = null;
	
	private List<Produto> listaProdutosComCategoriaCelular = null;
		
	private List<Produto> listaProdutosComCategoriaInformatica = null;
	
	private List<Produto> listaProdutosComCategoriaEletronico = null;
	
	private List<Produto> listaProdutosComCategoriaEletrodomestico = null;
	
	private List<Produto> listaProdutosComCategoriaMovel = null;
	
	private List<Produto> listaProdutosComCategoriaEsporte = null;
	
	private ProdutoDao produtoDao;

	private Usuario fornecedor;
	
	
	private List<String> categorias;
	
		
	@Inject
	@MessageBundleImpl
	private ResourceBundle messages;
	
	
	public ControleProduto() throws Exception {
		
		listarProdutosComQtdAcimaDeZero();
		listarProdutosComCategoriaCelular();
		listarProdutosComCategoriaInformatica();
		listarProdutosComCategoriaEletronico();
		listarProdutosComCategoriaEletrodomestico();
		listarProdutosComCategoriaMovel();
		listarProdutosComCategoriaEsporte();
		
		FacesContext context = FacesContext.getCurrentInstance();
		this.fornecedor = (Usuario)context.getExternalContext().getSessionMap().get("user");
		
		categorias = new ArrayList<String>();
		
		for(Categoria categoria : Categoria.values()){
			categorias.add(categoria.getValores());
		}
		
		produtoDao = ProdutoDao.Create();
		listarProdutos();		
		
	}
		
	
	/***** Produto   *****/
	/*****           *****/	
	
	//listar categorias
		
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
				
				//FacesMessage message = new FacesMessage("Login já existe");
	            //FacesContext context = FacesContext.getCurrentInstance();
	            //context.addMessage(this.campoLogin.getClientId(context), message);
				
	            /*FacesContext context = FacesContext.getCurrentInstance();  
		          
		        context.addMessage(null, new FacesMessage("Successful", "Hello "));  
		        context.addMessage(null, new FacesMessage("Second Message", "Additional Info Here..."));*/ 					       
			}
		}
		
		produtoDao.adicionarProduto(produtoSelecionado);
		listarProdutos();
		listarProdutosComQtdAcimaDeZero();
		
		return "listaProdutos";
	}
		
		
	// Edita Produto
	@Interceptors(AdministradorInterceptador.class)
	public String editarProduto() {		
		return "editaProduto";
	}
	
	//Salva alterações do Produto
	@Interceptors(AdministradorInterceptador.class)
	public String salvarAlteracoesDoProduto() throws Exception {
				
		produtoDao.editarProduto(produtoSelecionado);
		listarProdutos();
		listarProdutosComQtdAcimaDeZero();
		
		return "listaProdutos";
	}

	
	// Exclui Produto
	@Interceptors(AdministradorInterceptador.class)
	public String excluirProduto() throws Exception {
		produtoDao.excluirProduto(produtoSelecionado);
		listarProdutos();
		listarProdutosComQtdAcimaDeZero();
		return "listaProdutos";
	}
		
	

	/******************    ******************/
	/******************    ******************/
			
		
	
	// Lista os Produtos
	@Interceptors(AdministradorInterceptador.class)
	public List<Produto> listarProdutos() throws Exception {
		
		listaProdutos = produtoDao.listarProdutos();	
		return listaProdutos;
	}
	
	@Interceptors(AdministradorInterceptador.class)
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
	
	
	/******************    ******************/
	/******************    ******************/
	
	//Produtos para o fornecedor escolher
		
	@Interceptors(AdministradorFornecedorInterceptador.class)
	public String escolherProduto(){
		
		int quantidade = this.produtoSelecionado.getQuantidade();
		
		if(quantidade > 0){
			
			quantidade--;
			this.produtoSelecionado.setQuantidade(quantidade);
			List<Produto> listaDeProdutosDoFornecedor = this.fornecedor.getListaProdutos();
			listaDeProdutosDoFornecedor.add(this.produtoSelecionado);
			
		}
		else{
			throw new RuntimeException("Erro, produto zerado no estoque");
		}
		
		return "listaProdutosParaFornecedor";	
	}
	 
	// Lista os Produtos com quantidade acima de zero
	@Interceptors(AdministradorFornecedorInterceptador.class)
	public List<Produto> listarProdutosComQtdAcimaDeZero() throws Exception {
		
		listaProdutos = produtoDao.listarProdutos();
			
		for(Produto produto : listaProdutos){
			if(produto.getQuantidade() > 0){
				listaProdutosComQuantidadeAcimaDeZero.add(produto);
			}
		}
		
		return listaProdutosComQuantidadeAcimaDeZero;
	}
	
	/******************    ******************/
	/******************    ******************/
	
	//Produtos com a categoria Celular
	@Interceptors(AdministradorFornecedorInterceptador.class)
	public List<Produto> listarProdutosComCategoriaCelular() throws Exception {
		
		listaProdutos = produtoDao.listarProdutos();
			
		for(Produto produto : listaProdutos){
			if(produto.getCategoria().equals(Categoria.CELULAR)){
				listaProdutosComCategoriaCelular.add(produto);
			}
		}
		
		return listaProdutosComCategoriaCelular;
	}
	
	//Produtos com a categoria Informática
	@Interceptors(AdministradorFornecedorInterceptador.class)
	public List<Produto> listarProdutosComCategoriaInformatica() throws Exception {
		
		listaProdutos = produtoDao.listarProdutos();
			
		for(Produto produto : listaProdutos){
			if(produto.getCategoria().equals(Categoria.INFORMATICA)){
				listaProdutosComCategoriaCelular.add(produto);
			}
		}
		
		return listaProdutosComCategoriaInformatica;
	}
	
	//Produtos com a categoria Eletronico
	@Interceptors(AdministradorFornecedorInterceptador.class)
	public List<Produto> listarProdutosComCategoriaEletronico() throws Exception {
		
		listaProdutos = produtoDao.listarProdutos();
			
		for(Produto produto : listaProdutos){
			if(produto.getCategoria().equals(Categoria.ELETRONICO)){
				listaProdutosComCategoriaEletronico.add(produto);
			}
		}
		
		return listaProdutosComCategoriaEletronico;
	}
	
	//Produtos com a categoria Eletrodomestico
	@Interceptors(AdministradorFornecedorInterceptador.class)
	public List<Produto> listarProdutosComCategoriaEletrodomestico() throws Exception {
		
		listaProdutos = produtoDao.listarProdutos();
			
		for(Produto produto : listaProdutos){
			if(produto.getCategoria().equals(Categoria.ELETRODOMESTICO)){
				listaProdutosComCategoriaEletrodomestico.add(produto);
			}
		}
		
		return listaProdutosComCategoriaEletrodomestico;
	}
	
	//Produtos com a categoria Móvel
	@Interceptors(AdministradorFornecedorInterceptador.class)
	public List<Produto> listarProdutosComCategoriaMovel() throws Exception {
		
		listaProdutos = produtoDao.listarProdutos();
			
		for(Produto produto : listaProdutos){
			if(produto.getCategoria().equals(Categoria.MOVEL)){
				listaProdutosComCategoriaMovel.add(produto);
			}
		}
		
		return listaProdutosComCategoriaMovel;
	}
	
	//Produtos com a categoria Esporte
	@Interceptors(AdministradorFornecedorInterceptador.class)
	public List<Produto> listarProdutosComCategoriaEsporte() throws Exception {
		
		listaProdutos = produtoDao.listarProdutos();
			
		for(Produto produto : listaProdutos){
			if(produto.getCategoria().equals(Categoria.ESPORTE)){
				listaProdutosComCategoriaEsporte.add(produto);
			}
		}
		
		return listaProdutosComCategoriaEsporte;
	}
	
}
