package dao;

import java.util.ArrayList;
import java.util.List;

import modelo.Produto;
import modelo.Usuario;


public class ProdutoDao {

	static List<Produto> produtos;	
	
	private static ProdutoDao produtoDaoInstance;
	
	private ProdutoDao() {
		produtos = new ArrayList<>();
	}
	
	public static ProdutoDao Create()
	{
		if(produtoDaoInstance == null)
		{			
			produtoDaoInstance = new ProdutoDao();
		}
		
		return produtoDaoInstance;
	 }
	
	
	//Adiciona Produto
	public void adicionarProduto(Produto produtoNovo) throws Exception{
				
		this.produtos.add(produtoNovo);		

	}
	
	
	//Exclui Produto
	public void excluirProduto(Produto produtoNovo) throws Exception{
		
		this.produtos.remove(produtoNovo);		
		
	}
	
	//Edita Comprador
	public void editarProduto(Produto produto){
		
		//Usuario compradorEditado = this.buscarUsuarioPorLogin(comprador.getLogin());
		
		for(Produto produtoEditado : this.produtos){
			
			if(produtoEditado.getCodigo().equals(produto.getCodigo())){
				
				produtoEditado.setNome(produto.getNome());
				//produtoEditado.setCategoria(produto.getCategoria());	
				
			}			
		}				
	}
	
	//Lista os compradores
	public List<Produto> listarProdutos() throws Exception{
		
		return this.produtos;
		
	}
		
}
