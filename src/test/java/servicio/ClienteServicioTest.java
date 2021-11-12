package servicio;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import modelo.Cliente;

class ClienteServicioTest {

	public Logger logger;
	private Cliente cliente;
	private ClienteServicio clienteservicio;
	private List<Cliente> listaCliente;
	
	public ClienteServicioTest() {
		logger = LogManager.getLogger(ClienteServicioTest.class);	
		logger.info(" ");
		logger.info("----------------------------------------------------------------------------");
		logger.info("::: Evaluación del servicio ClienteServicio ::: ");
		logger.info(" ");
		inicializarCliente();
	}
	
	@Test
	@DisplayName(":::: Test agregar cliente ::::")
	void agregarClienteTest() {

		try {
			logger.info("Instancia de la lista del tipo Cliente para que el objeto cliente del tipo Cliente pueda ser creado");
			this.clienteservicio = new ClienteServicio();
			this.setClienteservicio(clienteservicio);
			this.getClienteservicio().setListaCliente(new ArrayList<Cliente>());
			this.listaCliente = this.getClienteservicio().getListaCliente();
			this.setListaCliente(this.listaCliente);
			boolean resultado = this.getClienteservicio().agregarCliente("12.345.678-9", "Nombre", "Apellido", "100");
			logger.info("Procesado resultado en método agregarClienteTest, valor: " + resultado);
			assertNotNull(this.getListaCliente());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	@DisplayName(":::: Test agregar cliente null::::")
	void agregarClienteNullTest() {
		this.clienteservicio = new ClienteServicio();
		this.setClienteservicio(clienteservicio);
		try {
			boolean resultado = this.getClienteservicio().agregarCliente("12.345.678-9", "Nombre", "Apellido", "100");
			logger.info("Procesado resultado en método agregarClienteNullTest, valor: " + resultado);
			assertNull(this.getClienteservicio().getCliente());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
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

	public ClienteServicio getClienteservicio() {
		return clienteservicio;
	}

	public void setClienteservicio(ClienteServicio clienteservicio) {
		this.clienteservicio = clienteservicio;
	}

	public void inicializarCliente() {
		logger.info("Seteando cliente");		
		this.cliente = new Cliente();		
		this.setCliente(this.cliente);	
	}

}
