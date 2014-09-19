package dao;

import java.util.ArrayList;
import java.util.List;

import modelo.Estoque;
import modelo.Produto;

public class EstoqueDao {

	static List<Estoque> estoque;
	private static EstoqueDao estoqueDaoInstance;
	
	private EstoqueDao() {
		estoque = new ArrayList<Estoque>();
	}
	
	public void adicionarEstoque(Estoque adicionarQtde) throws Exception{
		
		this.estoque.add(adicionarQtde);		

	}
}
