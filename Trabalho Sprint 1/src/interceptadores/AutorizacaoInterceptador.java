package interceptadores;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import modelo.Usuario;

//Somente usuário com perfil admin ou comprador passa por ele.
//Usuários anônimos não podem acessar as páginas interceptadas.
public class AutorizacaoInterceptador implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	Usuario usuario;

	@AroundInvoke
	public Object interceptar(InvocationContext contexto) throws Exception {
		
		FacesContext context = FacesContext.getCurrentInstance();
		usuario = (Usuario)context.getExternalContext().getSessionMap().get("user");
		
		if (usuario != null && usuario.getPapel().equals("comprador") || usuario.getPapel().equals("admin"))
			return contexto.proceed();
		
		throw new RuntimeException("Permissão negada");
	}
}
