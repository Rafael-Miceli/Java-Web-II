package regrasNegocio;

import java.util.List;

import dao.UsuarioDao;
import modelo.Usuario;

public class RealizaAutentificacao {
	
	public static boolean autentificarUsuario(Usuario usuario, UsuarioDao compradorDao)
	{
		return true;
		/*String login = usuario.getLogin();
		String senha = usuario.getSenha();
		
		//List<Usuario> compradores = compradorDao.listarCompradores();
		
		//Verifica se usuário é administrador
		if(login.equals("admin1") && senha.equals("admin1"))
		{	
			usuario.setPapel("admin");		
			return true;
		}
		else if(login.equals("admin2") && senha.equals("admin2"))
		{
			usuario.setPapel("admin");		
			return true;
		}
		//verifica se usuário é comprador
		else if(verificaLoginSenhaDeCompradores(login, senha, compradores))
		{
			usuario.setPapel("comprador");		
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private static boolean verificaLoginSenhaDeCompradores(String login, String senha, List<Usuario> compradores)
	{
		
		for (Usuario comprador : compradores) {
			if(login.equals(comprador.getLogin())
					&& senha.equals(comprador.getSenha()))
			{
				return true;
			}
		}
		
		return false;*/	
	}
}
