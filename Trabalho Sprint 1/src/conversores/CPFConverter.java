package conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("cpfConverter")
public class CPFConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String valor) {
		
		StringBuilder builder = new StringBuilder();
		builder.append(valor.substring(0, 3));
		builder.append(valor.substring(4, 7));
		builder.append(valor.substring(8, 11));
		builder.append(valor.substring(12, 14));
		
		return builder.toString();
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object valor) {

		String valorComMascara = (String) valor;
		
		StringBuilder builder = new StringBuilder();
		builder.append(valorComMascara.substring(0, 3));
		builder.append(".");
		builder.append(valorComMascara.substring(3, 6));
		builder.append(".");
		builder.append(valorComMascara.substring(6, 9));
		builder.append("-");
		builder.append(valorComMascara.substring(9, 11));
		
		return builder.toString();
	}
}