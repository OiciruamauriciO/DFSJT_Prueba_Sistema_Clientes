package vista;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import servicio.ArchivoServicio;
import servicio.ExportadorCsv;
import servicio.ExportadorTxt;
import utilidades.Utilidad;

public class MenuTemplate {
	
	private String opcion;
	private Scanner scopcion;
	private Utilidad utilidad;
	private int opciontemporal;
	private List<String> datosImportadosEnLista;
	private ArchivoServicio archivoServicio;
	private ExportadorTxt exportarTxt;
	private ExportadorCsv exportarCsv;
	private MenuImpl menuimpl;
	
	private boolean primeravez = false;
	private boolean procesoimportacion = false;
	
	public MenuTemplate() {
		
	}
	
	public MenuTemplate(String opcion) {
		super();
		this.opcion = opcion;
	}
	
	public void menuTemplate() throws InputMismatchException{		
		
		this.utilidad = new Utilidad();
		this.scopcion = new Scanner(System.in);
		this.setScopcion(this.scopcion);
		this.vistaMenu();
		this.primeravez = true;
		this.setPrimeravez(this.primeravez);
		this.cicloMenu();	
	}
	
	public void cicloMenu() throws NumberFormatException{	
		
		try {
			
			do {			

				if(!this.isPrimeravez()) {
					this.vistaMenu();
				}
				
				System.out.println("Ingrese una opción: ");
				
				opcion = scopcion.nextLine();
				opciontemporal = Integer.parseInt(opcion);
				
				switch(opciontemporal) {
				
					case 1:
						if(this.isProcesoimportacion()) {
							System.out.println("Iniciado proceso para listar clientes");
							utilidad.aplicaUtilidadExtendida();
							this.getMenuimpl().listarCliente();
							utilidad.aplicaUtilidadExtendida();
						}else{
							System.out.println("No es posible listar clientes si no se ha importado la data previamente.\n");
							this.setPrimeravez(false);
							utilidad.aplicaUtilidadExtendida();						
						}
					break;
					
					case 2:
						if(this.isProcesoimportacion()) {
							System.out.println("Iniciado proceso para agregar cliente");
							utilidad.aplicaUtilidadExtendida();						
							boolean isAgregado = this.getMenuimpl().agregarCliente();
							if(isAgregado) {
								System.out.println("Agregado exitosamente");
							}else {
								System.out.println("No ha sido posible agregar al cliente");
							}							
							utilidad.aplicaUtilidadExtendida();
						}else {
							System.out.println("No es posible agregar cliente si no se ha importado la data previamente\n");
							this.setPrimeravez(false);
							utilidad.aplicaUtilidadExtendida();							
						}
					break;
					
					case 3:
						if(this.isProcesoimportacion()) {
							System.out.println("Iniciado proceso para editar cliente");
							utilidad.aplicaUtilidadExtendida();			
							boolean iseditado = this.getMenuimpl().editarCliente();
							if(iseditado) {
								System.out.println("Editado exitosamente");
							}else {
								System.out.println("No ha sido posible editar al cliente");
							}
						}else {
							System.out.println("No es posible editar cliente si no se ha importado la data previamente\n");
							this.setPrimeravez(false);
							utilidad.aplicaUtilidadExtendida();							
						}						
					break;
					
					case 4:
						utilidad.presionaTecla();
						this.menuimpl = new MenuImpl();
						this.setMenuimpl(this.menuimpl);
						if(this.getMenuimpl().importarDatos()==true) {
							System.out.println("Proceso de importación completado.");
							utilidad.aplicaUtilidadExtendida();
							this.procesoimportacion = true;
							this.setProcesoimportacion(this.procesoimportacion);
						}else {
							System.out.println("Proceso de importación no se ha podido realizar.");
							utilidad.aplicaUtilidadExtendida();
							this.procesoimportacion = false;
							this.setProcesoimportacion(this.procesoimportacion);
						}
						
						this.setPrimeravez(false);
						
					break;
					
					case 5:
						if(this.isProcesoimportacion()) {
							System.out.println("Iniciado proceso para exportar datos");
							utilidad.aplicaUtilidadExtendida();
							
							Scanner scexportar = new Scanner(System.in);
							String opcionexportar = null;
							
							do {
									System.out.println("Formato a exportar? (1.- txt , 2.- csv, 3.- No exportar): ");
									opcionexportar=scexportar.nextLine();
									
									switch(opcionexportar) {
									
										case "1": 
											System.out.println("Inicializando proceso de exportación en formato txt");
											utilidad.aplicaUtilidadExtendida();
											this.exportarTxt = new ExportadorTxt();
											this.setExportarTxt(this.exportarTxt);
											this.getMenuimpl().setExportadortxt(this.getExportarTxt());
											this.getMenuimpl().exportarDatos();
											utilidad.aplicaUtilidadExtendida();											
										break;
										
										case "2": 
											System.out.println("Inicializando proceso de exportación en formato csv");
											utilidad.aplicaUtilidadExtendida();	
											this.exportarCsv = new ExportadorCsv();
											this.setExportarCsv(this.exportarCsv);
											System.out.println("Ingrese nombre de archivo a exportar (proceso csv): ");
											String nombretemporalcsv = scopcion.nextLine();
											this.getExportarCsv().exportarArchivo(nombretemporalcsv, this.getMenuimpl().getArchivoServicio().getListaClientes());											
											utilidad.aplicaUtilidadExtendida();												
										break;
										
										case "3":
											System.out.println("Ha cancelado la exportación de la información almacenada.\n");
											utilidad.aplicaUtilidadExtendida();
										break;
										
										default:
											System.out.println("Opción incorrecta.\n");
										break;
										
									}
							}while(!opcionexportar.equals("3"));

							
						}else {
							System.out.println("No es posible exportar datos si no se ha importado la data previamente\n");
							this.setPrimeravez(false);
							utilidad.aplicaUtilidadExtendida();						
						}						
					break;
					
					case 6:
						this.getMenuimpl().terminarPrograma();
					break;

					default:
						System.out.println("Opciones válidas: 1,2,3,4,5 o 6.\n");
						utilidad.aplicaUtilidadExtendida();
					break;			
				}	
				
			}while(opciontemporal!=6);			
			
		}catch(NumberFormatException nfe) {
			System.out.println("Sólo están permitidos números");
			System.out.println("Traza de la pila en la excepción" + nfe.getMessage());
			nfe.printStackTrace();
		}
		
	}

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public Scanner getScopcion() {
		return scopcion;
	}

	public void setScopcion(Scanner scopcion) {
		this.scopcion = scopcion;
	}

	public Utilidad getUtilidad() {
		return utilidad;
	}

	public void setUtilidad(Utilidad utilidad) {
		this.utilidad = utilidad;
	}

	public int getOpciontemporal() {
		return opciontemporal;
	}

	public void setOpciontemporal(int opciontemporal) {
		this.opciontemporal = opciontemporal;
	}

	public ArchivoServicio getArchivoServicio() {
		return archivoServicio;
	}

	public void setArchivoServicio(ArchivoServicio archivoServicio) {
		this.archivoServicio = archivoServicio;
	}
	
	public ExportadorTxt getExportarTxt() {
		return exportarTxt;
	}

	public void setExportarTxt(ExportadorTxt exportarTxt) {
		this.exportarTxt = exportarTxt;
	}

	public List<String> getDatosImportadosEnLista() {
		return datosImportadosEnLista;
	}

	public void setDatosImportadosEnLista(List<String> datosImportadosEnLista) {
		this.datosImportadosEnLista = datosImportadosEnLista;
	}

	public MenuImpl getMenuimpl() {
		return menuimpl;
	}

	public void setMenuimpl(MenuImpl menuimpl) {
		this.menuimpl = menuimpl;
	}

	public boolean isProcesoimportacion() {
		return procesoimportacion;
	}

	public void setProcesoimportacion(boolean procesoimportacion) {
		this.procesoimportacion = procesoimportacion;
	}
	
	public void vistaMenu() {
		System.out.println("1.- Listar Clientes");
		System.out.println("2.- Agregar Cliente");
		System.out.println("3.- Editar Cliente");
		System.out.println("4.- Cargar Datos");
		System.out.println("5.- Exportar Datos");
		System.out.println("6.- Salir");	
	}

	public boolean isPrimeravez() {
		return primeravez;
	}

	public void setPrimeravez(boolean primeravez) {
		this.primeravez = primeravez;
	}

	public ExportadorCsv getExportarCsv() {
		return exportarCsv;
	}

	public void setExportarCsv(ExportadorCsv exportarCsv) {
		this.exportarCsv = exportarCsv;
	}
	
}
