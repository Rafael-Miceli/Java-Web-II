package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import lombok.Getter;
import lombok.Setter;
import modelo.Usuario;


public class UsuarioDao {

	static List<Usuario> usuarios;	
	
	private static UsuarioDao usuarioDaoInstance;
	
	private UsuarioDao()
	{
		CriarAdmin();
	}
	
	public static UsuarioDao Create()
	{
		if(usuarioDaoInstance == null)
		{			
			usuarioDaoInstance = new UsuarioDao();
		}
		
		return usuarioDaoInstance;
	}
	
	private static void CriarAdmin() {
		usuarios = new ArrayList<Usuario>();
		
		Usuario admin = new Usuario();
		admin.setNome("Ricardo");
		admin.setLogin("admin");
		admin.setSenha("admin");
		admin.setPapel("admin");
		
		
		usuarios.add(admin);
	}
	
	public Usuario buscarUsuarioPorLogin(String login) {
		for (Usuario usuario : usuarios) {
			if(usuario.getLogin().equals(login))
				return usuario;
		}
		
		return null;
	}	
	
	//Adiciona Comprador
	public void adicionarComprador(Usuario compradorNovo) throws Exception{
		
		for (Usuario comprador : usuarios) {
			if(comprador.getLogin().equals(compradorNovo.getLogin()))
				throw new Exception();
		}
		
		this.usuarios.add(compradorNovo);		
	}
	
	//Exclui comprador
	public void excluirComprador(String login) throws Exception{
		
		for (Usuario comprador : usuarios) {
			if(comprador.getLogin().equals(login))
				this.usuarios.remove(comprador);
			else
				throw new Exception();
		}
	}

	//Lista os compradores
	public List<Usuario> listarCompradores() throws Exception{
		
		List<Usuario> compradores = new ArrayList<>();
		
		for (Usuario comprador : usuarios) {
			if(comprador.getPapel().equals("comprador"))
				compradores.add(comprador);
			else
				throw new Exception();
		}
		
		return compradores;
	}
	
	//Editar Comprador
	public void editarComprador(Usuario comprador){
		
	}
	
}
