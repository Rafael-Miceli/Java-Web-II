package dao;

import java.util.ArrayList;
import java.util.List;

import modelo.Pessoa;


public class PessoaDao {

	static List<Pessoa> pessoas;	
	
	private static PessoaDao pessoaDaoInstance;
	
	private PessoaDao()
	{
		pessoas = new ArrayList<Pessoa>();
	}
	
	
	public static PessoaDao Create()
	{
		if(pessoaDaoInstance == null)
		{			
			pessoaDaoInstance = new PessoaDao();
		}
		
		return pessoaDaoInstance;
	}	
	
	
	public Pessoa buscarPessoaPorCpf(String cpf) {
		for (Pessoa pessoa : pessoas) {
			if(pessoa.getCpf().equals(cpf))
				return pessoa;
		}
		
		return null;
	}	
	
	public void adicionarPessoa(Pessoa pessoaNova) throws Exception{
		
		for (Pessoa comprador : pessoas) {
			if(comprador.getCpf().equals(pessoaNova.getCpf()))
				throw new RuntimeException();
		}
		
		pessoas.add(pessoaNova);		
	}
	
	public void excluirPessoa(Pessoa pessoa) throws Exception{
		
		pessoas.remove(pessoa);		
		
	}

	public List<Pessoa> listarPessoas() throws Exception{				
		
		return pessoas;
	}
	
	public void editarPessoa(Pessoa pessoa){
		
	}
	
}