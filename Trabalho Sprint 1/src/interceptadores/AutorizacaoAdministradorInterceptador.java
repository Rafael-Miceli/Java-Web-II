package interceptadores;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import modelo.Usuario;

//Somente usuário com perfil admin passa por ele
public class AutorizacaoAdministradorInterceptador implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	Usuario usuario;

	@AroundInvoke
	public Object interceptar(InvocationContext contexto) throws Exception {
		
		FacesContext context = FacesContext.getCurrentInstance();
		usuario = (Usuario)context.getExternalContext().getSessionMap().get("user");
			
		if(usuario != null)	
			return contexto.proceed();
		
		throw new RuntimeException("Permissão negada");
	}
}
