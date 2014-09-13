package modelo;

import java.io.Serializable;

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
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NonNull
	@Size(min = 3, max = 50,  message = "Codigo dever ter 5 caracteres")
	private String codigo;
	
	@NonNull
	@Size(min = 3, max = 50,  message = "Nome dever ter entre 10 a 80 caracteres")
	private String nome;
	
	//private Categoria categoria;
	
}
