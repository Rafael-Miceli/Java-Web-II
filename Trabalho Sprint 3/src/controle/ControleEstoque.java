package controle;

import java.io.Serializable;
import java.util.List;


import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import dao.EstoqueDao;

import lombok.Getter;
import lombok.Setter;
import modelo.Estoque;

import modelo.Usuario;


@Getter
@Setter
@Named
@RequestScoped
public class ControleEstoque implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Estoque estoqueSelecionado = new Estoque();	
	private List<Estoque> Listestoque=null;
	private EstoqueDao estoquedao;
	private Usuario fornecedor;
	
	//adicionar item ao estoque	
	public String AdicionaItemEstoque() throws Exception{
		estoquedao.adicionarProdutoEmEstoque(estoqueSelecionado);
		
		return "listaProdutosParaFornecedor";
	}

}
