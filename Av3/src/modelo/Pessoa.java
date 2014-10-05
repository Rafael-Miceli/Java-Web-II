package modelo;

import java.io.Serializable;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Pessoa implements Serializable {


	private static final long serialVersionUID = 1L;

	
	@NonNull
	@Size(min = 4, max = 20, message = "{Nome.tamanho.minimo}")
	private String nome;
	
	@Size(min = 11, max = 11, message = "{Cpf.tamanho.minimo}")
	private String cpf;
}
