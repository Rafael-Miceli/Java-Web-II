package interceptadores;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import modelo.Usuario;

//Somente usu�rio com perfil admin ou comprador passa por ele.
//Usu�rios an�nimos n�o podem acessar as p�ginas interceptadas.
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
		
		throw new RuntimeException("Permiss�o negada");
	}
}
