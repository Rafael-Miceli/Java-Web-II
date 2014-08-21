package controle;

import interceptadores.AutorizacaoAdministradorInterceptador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import lombok.Getter;
import lombok.Setter;
import modelo.Usuario;
import dao.UsuarioDao;

@Getter
@Setter
@Named
@SessionScoped
public class ControleUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuario usuario;
	
	UsuarioDao usuarioDao;
	
	public ControleUsuario() {
		usuarioDao = UsuarioDao.Create();
	}
	
	@Interceptors(AutorizacaoAdministradorInterceptador.class)
	public void adicionarComprador() throws Exception {
		usuario.setPapel("comprador");
		usuarioDao.adicionarComprador(usuario);
	}
	
	@Interceptors(AutorizacaoAdministradorInterceptador.class)
	public void adicionarAdministrador() throws Exception {
		usuario.setPapel("admin");
		usuarioDao.adicionarComprador(usuario);
	}

	// Exclui comprador
	@Interceptors(AutorizacaoAdministradorInterceptador.class)
	public void excluirComprador() throws Exception {
		usuarioDao.excluirComprador(usuario.getLogin());
	}

	// Lista os compradores
	@Interceptors(AutorizacaoAdministradorInterceptador.class)
	public List<Usuario> listarCompradores() throws Exception {
		return usuarioDao.listarCompradores();
	}

	// Editar Comprador
	@Interceptors(AutorizacaoAdministradorInterceptador.class)
	public void editarComprador(Usuario comprador) {
		usuarioDao.editarComprador(usuario);
	}

}
