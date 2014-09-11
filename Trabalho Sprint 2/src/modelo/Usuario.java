package modelo;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Named
@SessionScoped
public class Usuario implements Serializable {
	
	/**
	 * UID de serialização estática.
	 */
	private static final long serialVersionUID = 6185173587860633317L;


	@NonNull
	@Size(min = 3, max = 50,  message = "{nome.tamanho}")
	private String nome;
		
	private Date dataNascimento;	
	
	private Date dataCadastro;
	
	@NonNull
	private String email;
	
	
	@NonNull
	@Size(max = 10, message = "{login.tamanho.minimo}")
	private String login;
	
	@NonNull
	@Size(max = 20, message = "{senha.tamanho.minimo}")
	private String senha;
	
	@NonNull
	private String papel;
}
