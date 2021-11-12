package servicio;

import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;

public class ClienteServicio {
	
	private List<Cliente> listaCliente;
	//tengo el último cliente apuntado en memoria disponible
	private Cliente cliente;
	
	private ArrayList<String> clientesspliteados = new ArrayList<String>();
	private String strclientesspliteados[];
	
	public ClienteServicio() {
		
	}
	
	public ClienteServicio(ArrayList<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}
	
	public ClienteServicio(ArrayList<Cliente> listaCliente, Cliente cliente) {
		this.listaCliente = listaCliente;
		this.cliente = cliente;
	}

	public List<Cliente> getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	//métodos personalizados según contrato
	
	public ArrayList<String> getClientesspliteados() {
		return clientesspliteados;
	}

	public void setClientesspliteados(ArrayList<String> clientesspliteados) {
		this.clientesspliteados = clientesspliteados;
	}

	public String[] getStrclientesspliteados() {
		return strclientesspliteados;
	}

	public void setStrclientesspliteados(String[] strclientesspliteados) {
		this.strclientesspliteados = strclientesspliteados;
	}

	public void retornarListaClientes() {
		//implementar lógica
	}
	
	public boolean agregarCliente(String runCliente, String nombreCliente, String apellidoCliente, String aniosCliente) throws Exception{
		
		//implementar lógica para agregar cliente en la list del tipo tipo que tenga
		boolean clienteagregado = false;
		try {
			Cliente cliente = new Cliente();
			cliente.setNombreCliente(nombreCliente);
			cliente.setApellidoCliente(apellidoCliente);
			cliente.setAniosCliente(aniosCliente);
			cliente.setRunCliente(runCliente);
			cliente.setCategoria(cliente.setEnumEspecifico(true));
			this.getListaCliente().add(cliente);	
			clienteagregado = true;
		}catch(Exception e) {
			System.out.println("No es posible agregar cliente debido a excepcion encontrada en el proceso");
			e.printStackTrace();
		}
		
		return clienteagregado;
	}
	
	public void editarCliente(String runCliente, String nombreCliente, String apellidoCliente, String aniosCliente) {
		//implementar lógica para editar cliente en la list del tipo tipo que tenga
	}
	
	//metodo personalizado para trabajar la lista leída desde el archivo importado y disponibilizarla en el servicio
	public void procesarClientesImportados(ArrayList<String> clientesleidos) {
		
		this.listaCliente = new ArrayList<Cliente>();
		String strclientesspliteadosTemporal[];
		
		if(clientesleidos != null) {
			
			for(String leido: clientesleidos) {
				
				this.cliente = new Cliente();
				
				strclientesspliteadosTemporal = leido.split(",");			
				
				this.cliente.setRunCliente(strclientesspliteadosTemporal[0]);
				this.cliente.setNombreCliente(strclientesspliteadosTemporal[1]);
				this.cliente.setApellidoCliente(strclientesspliteadosTemporal[2]);
				
				String straniospliteado[] =  strclientesspliteadosTemporal[3].split(" ");
				this.cliente.setAniosCliente(straniospliteado[0]);
				
				String enumstring = strclientesspliteadosTemporal[4];
				
				if(enumstring.equalsIgnoreCase("Activo")) {
					this.cliente.setCategoria(this.cliente.setEnumEspecifico(true));
				}else {
					this.cliente.setCategoria(this.cliente.setEnumEspecifico(false));
				}	
				
				this.listaCliente.add(this.cliente);
			}
			
		}else {
			System.out.println("No hay lectura de clientes.");
		}
		
		this.setListaCliente(this.listaCliente);
		
		for(Cliente cliente: this.getListaCliente()) {
			cliente.toString();
			System.out.println("\n");
		}
		
	}
	
	//método personalizado para visualizar por pantalla los clientes por rut ya enlistados en el arraylist del tipo Cliente
	public void visualizarRunClientesImportados() {
		if(this.getListaCliente()!=null) {
			for(Cliente cliente: this.getListaCliente()) {
				System.out.println(cliente.getRunCliente());
			}
		}
	}

}
