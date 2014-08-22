package controle;

import interceptadores.AutorizacaoAdministradorInterceptador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.ManagedBean;
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
@Interceptors(AutorizacaoAdministradorInterceptador.class)
public class ControleUsuario implements Serializable {	
	
	private Usuario usuario;
	
	private Usuario usuarioSelecionado = new Usuario();	
	
	private List<Usuario> listaCompradores = null;
	
	private UsuarioDao usuarioDao;
	
	public ControleUsuario() throws Exception {
		usuarioDao = UsuarioDao.Create();
		listarCompradores();
	}
	
	public String salvar() throws Exception {
		usuarioSelecionado.setPapel("comprador");
		usuarioDao.adicionarComprador(usuarioSelecionado);
		listarCompradores();
		return "listaCompradores";
	}
	
	public String cancelar(){
		return "adicionaComprador";
	}
	
	//Adiciona Comprador
	public String adicionarComprador() throws Exception {
		usuarioSelecionado = new Usuario();
		
		return "adicionaComprador";
	}
	
	// Exclui comprador
	public String excluirComprador() throws Exception {
		usuarioDao.excluirComprador(usuarioSelecionado);
		listarCompradores();
		return "listaCompradores";
	}

	// Lista os compradores
	public List<Usuario> listarCompradores() throws Exception {
		
		listaCompradores = usuarioDao.listarCompradores();		
		return listaCompradores;
	}
	
	
	public void setListarCompradores(List<Usuario> listarCompradores){
		this.listaCompradores = listarCompradores; 
	}
	
	public Usuario getUsuarioSelecionado(){
		return usuarioSelecionado;
	}
	
	public void setUsuarioSelecionado(Usuario usuarioSelecionado){
		this.usuarioSelecionado = usuarioSelecionado;
	}

	// Editar Comprador
	public String editarComprador() {
		return "adicionaComprador";
	}
	
}
