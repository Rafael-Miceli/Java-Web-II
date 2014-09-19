package viewmodels;

import lombok.Getter;
import lombok.Setter;
import modelo.Produto;
import modelo.Usuario;

@Getter
@Setter
public class ProdutosParaFornecedorModel {
	
	private boolean selecionado;
	
	private Produto produto;
	
	private int quantidade;
}
