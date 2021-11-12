package vista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.Cliente;
import utilidades.Utilidad;

import servicio.ArchivoServicio;
import servicio.ClienteServicio;
import servicio.ExportadorCsv;
import servicio.ExportadorTxt;

public class MenuImpl implements Menu{
	
	private ClienteServicio clienteServicio;
	private ArchivoServicio archivoServicio;
	private ExportadorCsv exportadorcsv;
	private ExportadorTxt exportadortxt;
	//para exportar el archivo
	private static String fileName = "Clientes";
	//para importar el archivo
	private static String fileName1 = "DBClientes.csv";
	private Scanner sc;
	private List<String> datosImportadosEnLista;	
	private List<Cliente> listaClientes;
	private Utilidad utilidad = new Utilidad();
	private Cliente cliente;
	
	
	public MenuImpl() {		
		super();	
	}

	@Override
	public void listarCliente() {
		
		Scanner eleccion = new Scanner(System.in);
		String opcion=null;
		String nombrecliente=null;

		do{
			System.out.println("\n1.- Listar 1 cliente específico.");
			System.out.println("2.- Listar todos los clientes.");
			System.out.println("3.- Salir de listar");
			System.out.println("Ingrese elección para proceso de listar: ");
			
			opcion = eleccion.nextLine();
			System.out.println("Ha seleccionado " + opcion);
			
			switch(opcion) {
			
				case "1":
					
					//no hay regla de negocio que establezca instancia para buscar, por lo que buscar por nombre estaría bien
					System.out.println("Ingrese nombre del cliente a buscar");
					nombrecliente = eleccion.nextLine();
					boolean almenosuno = false;
					int j=0;
					
					ArrayList<Integer> contiene = new ArrayList<Integer>();

					for(int i=0; i<this.getListaClientes().size(); i++) {
						
						if(this.getListaClientes().get(i).getNombreCliente().equalsIgnoreCase(nombrecliente)) {
							contiene.add(i);
							j++;
							almenosuno=true;
						}
					}
					
					if(almenosuno) {
						utilidad.aplicaUtilidadExtendida();
						System.out.print(nombrecliente + " existe en la(s) posicion(es): [ ");
						
						for(int k = 0; k <contiene.size(); k++) {
							if(k==0) {
								System.out.print(contiene.get(k).toString());
							}else {
								System.out.print(", " + contiene.get(k).toString());
							}
							
						}
						
						System.out.print(" ].\n");
						utilidad.aplicaUtilidadExtendida();
						
					}else {
						System.out.println( String.valueOf(j) + " coincidencias");
					}
					
				break;
				
				case "2":
					utilidad.aplicaUtilidadExtendida();
					System.out.println("\nLista actual ( "+(this.getListaClientes().size())+" clientes)");
					for(Cliente cliente: this.getListaClientes()) {
						System.out.println(cliente.toString());
					}
				break;
				
				case "3":
					System.out.println("Ha seleccionado salir del listar.");
				break;
				
				default:
					System.out.println("Opción incorrecta.");
				break;				
			}
			
		}while(!opcion.equalsIgnoreCase("3"));

		
		
	}

	@Override
	public boolean agregarCliente() {

		Cliente cliente = new Cliente();
		Scanner sccliente = new Scanner(System.in);
		String valortemporal = null;
		System.out.println("Ingrese datos para el nuevo cliente");
		utilidad.aplicaUtilidadExtendida();
		
		System.out.println("Nombre");
		valortemporal = sccliente.nextLine();
		cliente.setNombreCliente(valortemporal);
		
		System.out.println("Apellido");
		valortemporal = sccliente.nextLine();
		cliente.setApellidoCliente(valortemporal);
		
		System.out.println("Años");
		valortemporal = sccliente.nextLine();
		cliente.setAniosCliente(valortemporal);
		
		System.out.println("Run");
		valortemporal = sccliente.nextLine();
		cliente.setRunCliente(valortemporal);
		
		valortemporal = cliente.setEnumEspecifico(true);
		cliente.setCategoria(valortemporal);
		
		this.getListaClientes().add(cliente);		
		
		return true;
	}

	@Override
	public boolean editarCliente() {
		String nombrecliente = null;
		Scanner eleccion = new Scanner(System.in);
		
		System.out.println("Ingrese nombre del cliente a buscar");
		nombrecliente = eleccion.nextLine();
		boolean almenosuno = false;
		int j=0;
		
		ArrayList<Integer> contiene = new ArrayList<Integer>();

		for(int i=0; i<this.getListaClientes().size(); i++) {
			
			if(this.getListaClientes().get(i).getNombreCliente().equalsIgnoreCase(nombrecliente)) {
				contiene.add(i);
				j++;
				almenosuno=true;
			}
		}
		
		if(almenosuno==true && j==1) {
			
			System.out.println("El cliente encontrado es el siguiente");
			int posicionencontrado = contiene.get(0);
			System.out.println(this.getListaClientes().get(posicionencontrado).toString());
			System.out.println("¿Qué campo quiere editar?");
			System.out.println("1.- Nombre");
			System.out.println("2.- Apellido");
			System.out.println("3.- Años");
			System.out.println("4.- Run");
			System.out.println("5.- Categoría/Estado");
			String eleccionedicion = eleccion.nextLine();
			
			switch(eleccionedicion) {
			
				case "1":
					System.out.println("Ingrese nuevo nombre");
					String nuevonombre = eleccion.nextLine();
					this.getListaClientes().get(posicionencontrado).setNombreCliente(nombrecliente);
					utilidad.aplicaUtilidadExtendida();
				break;
				
				case "2":
					System.out.println("Ingrese nuevo apellido");
					String nuevoapellido = eleccion.nextLine();
					this.getListaClientes().get(posicionencontrado).setApellidoCliente(nuevoapellido);
					utilidad.aplicaUtilidadExtendida();
				break;
				
				case "3":
					System.out.println("Ingrese nuevo valor para los años");
					String nuevoaño = eleccion.nextLine();
					this.getListaClientes().get(posicionencontrado).setAniosCliente(nuevoaño);
					utilidad.aplicaUtilidadExtendida();
				break;
				
				case "4":
					System.out.println("Ingrese nuevo run");
					String nuevorun = eleccion.nextLine();
					this.getListaClientes().get(posicionencontrado).setRunCliente(nuevorun);
					utilidad.aplicaUtilidadExtendida();
				break;
				
				case "5":
					System.out.println("Ingrese nuevo estado");
					String nuevacategoria = eleccion.nextLine();
					if(nuevacategoria.equalsIgnoreCase("Activo") || nuevacategoria.equalsIgnoreCase("Inactivo")) {
						this.getListaClientes().get(posicionencontrado).setCategoria(nuevacategoria);
					}else {
						System.out.println("No es posible asignar tal categoría/estado");
					}
					utilidad.aplicaUtilidadExtendida();
				break;
				
				default:
					System.out.println("Opción incorrecta");
					utilidad.aplicaUtilidadExtendida();
				break;				
			}
			
		}else {
			
			//una versión mejorada de esto puede extender la funcionalidad, pero el objetivo de la prueba es otro, así que se deja hasta aquí
			System.out.println("Existe más de un cliente con ese nombre");
			System.out.println("Los clientes encontrados con el mismo nombre son los siguientes (" + contiene.size() + " clientes encontrados)");

			for(int i=0; i<contiene.size(); i++) {
				System.out.println(this.getListaClientes().get(contiene.get(i).intValue()).toString());
			}
			utilidad.aplicaUtilidadExtendida();
		}
		
		return true;
	}

	@Override
	public boolean importarDatos() {
		
		archivoServicio = ArchivoServicio.getSingletoncall();
		Scanner sctemporal = new Scanner(System.in);
		System.out.println("Ingrese nombre de archivo (DB*******s)");
		String temporalstring = sctemporal.nextLine();
		archivoServicio.cargarDatos(temporalstring);
		utilidad.presionaTecla();
		utilidad.limpiarPantallaDesplazamiento();
		
		this.datosImportadosEnLista = archivoServicio.getDatoscargados();
		this.setDatosImportadosEnLista(this.datosImportadosEnLista);
		
		if(this.getDatosImportadosEnLista()!=null) {
			this.clienteServicio = new ClienteServicio();
			this.setClienteServicio(this.clienteServicio);
			this.getClienteServicio().procesarClientesImportados((ArrayList<String>) this.getDatosImportadosEnLista());
			this.archivoServicio.setListaClientes(this.getClienteServicio().getListaCliente());
			//se disponibiliza referencia de la lista de cliente en Menuimpl de manera local, una vez importada la data desde respaldo
			this.listaClientes = this.archivoServicio.getListaClientes();
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public void exportarDatos() {
		Scanner scexportar = new Scanner(System.in);
		System.out.println("Ingrese nombre de archivo a exportar (proceso txt): ");
		String nombretemporaltxt = scexportar.nextLine();
		this.getExportadortxt().exportarArchivo(nombretemporaltxt, this.getArchivoServicio().getListaClientes());
	}

	@Override
	public void terminarPrograma() {
		utilidad.limpiarPantallaDesplazamiento();
		System.out.println("Gracias por usar Sistema de Clientes v1.0.0\n");
		utilidad.aplicaUtilidadExtendida();		
	}

	public ExportadorCsv getExportadorcsv() {
		return exportadorcsv;
	}

	public void setExportadorcsv(ExportadorCsv exportadorcsv) {
		this.exportadorcsv = exportadorcsv;
	}

	public ExportadorTxt getExportadortxt() {
		return exportadortxt;
	}

	public void setExportadortxt(ExportadorTxt exportadortxt) {
		this.exportadortxt = exportadortxt;
	}

	public static String getFileName() {
		return fileName;
	}

	public static void setFileName(String fileName) {
		MenuImpl.fileName = fileName;
	}

	public static String getFileName1() {
		return fileName1;
	}

	public static void setFileName1(String fileName1) {
		MenuImpl.fileName1 = fileName1;
	}

	public Scanner getSc() {
		return sc;
	}

	public void setSc(Scanner sc) {
		this.sc = sc;
	}

	public ClienteServicio getClienteServicio() {
		return clienteServicio;
	}

	public void setClienteServicio(ClienteServicio clienteServicio) {
		this.clienteServicio = clienteServicio;
	}

	public ArchivoServicio getArchivoServicio() {
		return archivoServicio;
	}

	public void setArchivoServicio(ArchivoServicio archivoServicio) {
		this.archivoServicio = archivoServicio;
	}

	public List<String> getDatosImportadosEnLista() {
		return datosImportadosEnLista;
	}

	public void setDatosImportadosEnLista(List<String> datosImportadosEnLista) {
		this.datosImportadosEnLista = datosImportadosEnLista;
	}

	public Utilidad getUtilidad() {
		return utilidad;
	}

	public void setUtilidad(Utilidad utilidad) {
		this.utilidad = utilidad;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
}
