package modelo;

public class Cliente {
	
	private String runCliente;
	private String nombreCliente;
	private String apellidoCliente;
	private String aniosCliente;
	private String categoria;
	private enum CategoriaEnum {Activo, Inactivo}
	
	public Cliente() {
		
	}
	
	public Cliente(String runCliente, String nombreCliente, String apellidoCliente, String aniosCliente, String categoria) {
		super();
		this.runCliente = runCliente;
		this.nombreCliente = nombreCliente;
		this.apellidoCliente = apellidoCliente;
		this.aniosCliente = aniosCliente;
		this.categoria = categoria;
	}

	public String getRunCliente() {
		return runCliente;
	}

	public void setRunCliente(String runCliente) {
		this.runCliente = runCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getApellidoCliente() {
		return apellidoCliente;
	}

	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}

	public String getAniosCliente() {
		return aniosCliente;
	}

	public void setAniosCliente(String aniosCliente) {
		this.aniosCliente = aniosCliente;
	}
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	//método personalizado para definir el enum del pojo Cliente en el servicio ClienteServicio y en el string categoría del pojo Cliente
	public String setEnumEspecifico(boolean enumboolean) {
		
		if(enumboolean == true) {
			CategoriaEnum instanciaenum = CategoriaEnum.Activo;
			this.categoria = instanciaenum.toString();
			return instanciaenum.toString();
		}else {
			CategoriaEnum instanciaenum = CategoriaEnum.Inactivo;
			this.categoria = instanciaenum.toString();
			return instanciaenum.toString();
		}
	}

	@Override
    public String toString() {
        return "Cliente: " + this.getNombreCliente() + " " + this.getApellidoCliente() + ", " 
        		+ this.getAniosCliente() + " años, " + "run " + this.getRunCliente() + ", categoría " 
        		+ this.getCategoria() + ".";
    }

}
