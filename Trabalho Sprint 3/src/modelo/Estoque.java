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


@Getter
@Setter
@Named
@SessionScoped
public class Estoque implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Produto produto;
	
	@Size(min = 1, message = "Deve selecionar pelo menos 1 produto")
	private int quantidade;
	
	private Usuario fornecedor;

}
