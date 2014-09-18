package modelo;

import lombok.Getter;


@Getter

public enum Categoria {
	
	CELULAR("celular"), INFORMATICA("informatica"), ELETRONICO("eletronico"), ELETRODOMESTICO("eletrodomestico"), MOVEL("movel"), ESPORTE("esporte");
	
	public String valores;

	Categoria(String descricao){
		this.valores = descricao;
	}

	
	


}
