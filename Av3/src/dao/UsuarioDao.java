package dao;

import java.util.ArrayList;
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
		admin.setLogin("admin");
		admin.setSenha("123");		
		
		usuarios.add(admin);
	}
	
	public Usuario buscarUsuarioPorLogin(String login) {
		for (Usuario usuario : usuarios) {
			if(usuario.getLogin().equals(login))
				return usuario;
		}
		
		return null;
	}	
	
}
