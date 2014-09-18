package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		admin.setDataNascimento(null);
		admin.setEmail("ricardo@email.com");
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
	
	public Usuario buscarUsuarioPorLoginNumPapel(String login, String papel) {
		for (Usuario usuario : usuarios) {
			if(usuario.getPapel().equals(papel)){
				if(usuario.getLogin().equals(login)){
					return usuario;
				}				
			}			
		}
		
		return null;
	}	
	
	//Adiciona Usuario
	public void adicionarUsuario(Usuario compradorNovo) throws Exception{
		
		this.usuarios.add(compradorNovo);		
		
	}
	
	//Lista os Usuarios
	public List<Usuario> listarUsuarios() throws Exception{
		
		return usuarios;
		
	}
	
	//Lista os Usuarios
	public List<Usuario> listarUsuariosSemPapelAdmin() throws Exception{
		
		List<Usuario> usuariosSemPapelAdmin = new ArrayList<Usuario>();
		
		for (Usuario usuario : usuarios){
			if(!(usuario.getPapel().equals("admin"))){
				usuariosSemPapelAdmin.add(usuario);
			}				
		}
					
		return usuariosSemPapelAdmin;
		
	}
	
	//Exclui Usuario
	public void excluirUsuario(Usuario usuario) throws Exception{
		
		this.usuarios.remove(usuario);		
		
	}
	
	//Edita Usuario
	public void editarUsuario(Usuario usuario){
		
		//Usuario compradorEditado = this.buscarUsuarioPorLogin(comprador.getLogin());
		
		for(Usuario usuarioEditado : this.usuarios){
			
			if(usuarioEditado.getPapel().equals(usuario.getPapel())){
				
				if(usuarioEditado.getLogin().equals(usuario.getLogin())){
					
					usuarioEditado.setNome(usuario.getNome());
					usuarioEditado.setDataNascimento(usuario.getDataNascimento());
					usuarioEditado.setEmail(usuario.getEmail());
					usuarioEditado.setSenha(usuario.getSenha());	
					
				}
			}			
		}				
	}
		
		
	
	/***** Comprador *****/
	/*****           *****/
	
	//Exclui Comprador
	public void excluirComprador(Usuario comprador) throws Exception{
		
		this.usuarios.remove(comprador);		
		
	}
	
	//Edita Comprador
	public void editarComprador(Usuario comprador){
		
		//Usuario compradorEditado = this.buscarUsuarioPorLogin(comprador.getLogin());
		
		for(Usuario compradorEditado : this.usuarios){
			
			if(compradorEditado.getLogin().equals(comprador.getLogin())){
				
				compradorEditado.setNome(comprador.getNome());
				compradorEditado.setDataNascimento(comprador.getDataNascimento());
				compradorEditado.setEmail(comprador.getEmail());
				compradorEditado.setSenha(comprador.getSenha());		
				
			}
			
		}				
	}
	
	//Lista os compradores
	public List<Usuario> listarCompradores() throws Exception{
		
		List<Usuario> compradores = new ArrayList<Usuario>();
		
		for (Usuario comprador : usuarios) 
			if(comprador.getPapel().equals("comprador"))
				compradores.add(comprador);
		
		return compradores;
	}
	
	
	/*****           *****/
	/***** Fornecedor *****/
	/*****           *****/

			
	//Exclui Fornecedor
	public void excluirFornecedor(Usuario fornecedor) throws Exception{
		
		this.usuarios.remove(fornecedor);		
		
	}
		
	
	//Edita Fornecedor
	public void editarFornecedor(Usuario fornecedor){
		
		//Usuario compradorEditado = this.buscarUsuarioPorLogin(comprador.getLogin());
		
		for(Usuario compradorEditado : this.usuarios){
			
			if(compradorEditado.getLogin().equals(fornecedor.getLogin())){
				
				compradorEditado.setNome(fornecedor.getNome());
				compradorEditado.setDataNascimento(fornecedor.getDataNascimento());
				compradorEditado.setDataNascimento(fornecedor.getDataCadastro());
				compradorEditado.setEmail(fornecedor.getEmail());
				compradorEditado.setSenha(fornecedor.getSenha());
				
			}			
		}				
	}
		
	//Lista os fornecedores
	public List<Usuario> listarFornecedores() throws Exception{
		
		List<Usuario> fornecedores = new ArrayList<Usuario>();
		
		for (Usuario fornecedor : usuarios) 
			if(fornecedor.getPapel().equals("fornecedor"))
				fornecedores.add(fornecedor);
		
		return fornecedores;
	}
	
	
	
}
