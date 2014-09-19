package controle;

import i18n.MessageBundleImpl;
import interceptadores.AdministradorFornecedorInterceptador;
import interceptadores.AdministradorInterceptador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import viewmodels.ProdutosParaFornecedorModel;
import lombok.Getter;
import lombok.Setter;
import modelo.Categoria;
import modelo.Estoque;
import modelo.Produto;
import modelo.Usuario;
import dao.EstoqueDao;
import dao.ProdutoDao;

@Getter
@Setter
@Named
@RequestScoped
public class ControleProduto implements Serializable {	

	private static final long serialVersionUID = 1L;
	

	private Produto produtoSelecionado = new Produto();	
	
	private List<Produto> listaProdutos = null;
		
	private ProdutoDao produtoDao;
	
	private EstoqueDao estoqueDao;

	private Usuario fornecedor;	
	
	private List<String> categorias;
	
	private Map<String, Boolean> produtosSelecionadosPorFornecedor = new HashMap<String, Boolean>();
	
		
	@Inject
	@MessageBundleImpl
	private ResourceBundle messages;
	
	
	public ControleProduto() throws Exception {
		
		FacesContext context = FacesContext.getCurrentInstance();
		fornecedor = (Usuario)context.getExternalContext().getSessionMap().get("user");
		
		categorias = new ArrayList<String>();
		
		for(Categoria categoria : Categoria.values()){
			categorias.add(categoria.getValores());
		}
		
		produtoDao = ProdutoDao.Create();
		estoqueDao = EstoqueDao.Create();
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
							       
			}
		}
		
		produtoDao.adicionarProduto(produtoSelecionado);
		listarProdutos();
		
		return "PainelAdmin";
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
	
	
	/******************    ******************/
	/******************    ******************/	
	
	
	//Salva alterações do Produto
	@Interceptors(AdministradorFornecedorInterceptador.class)
	public String salvarAlteracoesDeEntradaDeMercadoria() throws Exception {				
		
		List<Produto> checkedProdutos = new ArrayList<Produto>();

        for (Produto item : this.listaProdutos) {
            if (produtosSelecionadosPorFornecedor.get(item.getCodigo())) {
            	checkedProdutos.add(item);
            }
        }
		
		return "listaProdutosParaFornecedor";
	}
	
	@Interceptors(AdministradorFornecedorInterceptador.class)
	public List<ProdutosParaFornecedorModel> retornaProdutosEmEstoqueModel() throws Exception {				
		
		List<Estoque> produtosEmEstoque = estoqueDao.listarProdutosEmEstoquePorFornecedor(fornecedor);
		
		List<ProdutosParaFornecedorModel> produtosParaFornecedor = new ArrayList<ProdutosParaFornecedorModel>();
		
		for(Produto produto : listaProdutos){
			ProdutosParaFornecedorModel produtoParaFornecedor = new ProdutosParaFornecedorModel();
			Estoque produtoEmEstoque = produtoJaEmEstoque(produtosEmEstoque, produto);
			
			if(produtoEmEstoque != null){
				produtoParaFornecedor.setSelecionado(true);
				produtoParaFornecedor.setQuantidade(produtoEmEstoque.getQuantidade());
			}
			else{
				produtoParaFornecedor.setSelecionado(false);
				produtoParaFornecedor.setQuantidade(1);
			}			
			
			produtoParaFornecedor.setProduto(produto);
			
			produtosParaFornecedor.add(produtoParaFornecedor);
		}
		
		return produtosParaFornecedor;
	}


	private Estoque produtoJaEmEstoque(List<Estoque> produtosEmEstoque,	Produto produto) {
		
		for(Estoque estoque: produtosEmEstoque){
			if(estoque.getProduto().getCodigo().equals(produto.getCodigo())){
				return estoque;
			}
				
		}
		
		return null;
	}

}



