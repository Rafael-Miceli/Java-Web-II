package controle;

import interceptadores.AutorizacaoAdministradorInterceptador;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import lombok.Getter;
import lombok.Setter;
import modelo.Pessoa;
import dao.PessoaDao;

@Getter
@Setter
@Named
@SessionScoped
@Interceptors(AutorizacaoAdministradorInterceptador.class)
public class ControlePessoa implements Serializable {	
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pessoa pessoaSelecionada;	
	
	private List<Pessoa> listaPessoas = null;
	
	private PessoaDao pessoaDao;
	
	public ControlePessoa() throws Exception {
		pessoaDao = PessoaDao.Create();
		listarCompradores();
	}
	
	
	public String salvar() throws Exception {	
		pessoaDao.adicionarPessoa(pessoaSelecionada);
		listarCompradores();
		return "listaCompradores";
	}
	
	public String editar() throws Exception {	
		pessoaDao.editarPessoa(pessoaSelecionada);
		listarCompradores();
		return "listaCompradores";
	}
	
	
	public String cancelar(){
		return "adicionaComprador";
	}
	
	
	public String adicionarComprador() throws Exception {
		pessoaSelecionada = new Pessoa();
		
		return "adicionaComprador";
	}
	
	public String excluirComprador() throws Exception {
		pessoaDao.excluirPessoa(pessoaSelecionada);
		listarCompradores();
		return "listaCompradores";
	}

	public List<Pessoa> listarCompradores() throws Exception {
		
		listaPessoas = pessoaDao.listarPessoas();		
		return listaPessoas;
	}	
		

	public String editarComprador() {
		return "atualizaComprador";
	}
	
}
