package modelo;

public enum Categoria {
	
	CELULAR(1), INFORMATICA(2), ELETRONICO(3), ELETRODOMESTICO(4), MOVEL(5), ESPORTE(6);
	
	private final int valor; 
	
	Categoria(int valorOpcao){ 
		valor = valorOpcao; 
	}
	
	public int getValor(){ 
		return valor; 
	}


}
