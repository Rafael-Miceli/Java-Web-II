package dao;

import java.util.ArrayList;
import java.util.List;

import modelo.Estoque;
import modelo.Usuario;

public class EstoqueDao {

	static List<Estoque> produtosEstocados;	
	
	private static EstoqueDao estoqueDaoInstance;
	
	private EstoqueDao() {
		produtosEstocados = new ArrayList<Estoque>();
	}
	
	public static EstoqueDao Create()
	{
		if(estoqueDaoInstance == null)
		{			
			estoqueDaoInstance = new EstoqueDao();
		}
		
		return estoqueDaoInstance;
	 }
	
	
	public void adicionarProdutoEmEstoque(Estoque produtoEmEstoque) throws Exception{
		
		produtosEstocados.add(produtoEmEstoque);		

	}
	
	public List<Estoque> listarProdutosEmEstoque() throws Exception{
		
		return produtosEstocados;
		
	}
	
	public List<Estoque> listarProdutosEmEstoquePorFornecedor(Usuario usuario) throws Exception{
		
		List<Estoque> estoqueDeFornecedor = new ArrayList<Estoque>();
		
		for (Estoque estoque : produtosEstocados){
			if(estoque.getFornecedor().getLogin().equals(usuario.getLogin())){
				estoqueDeFornecedor.add(estoque);
			}
		}
		
		return estoqueDeFornecedor;
		
	}
}
