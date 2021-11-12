package servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.Cliente;

public class ArchivoServicio{
	
	private static ArchivoServicio singletoncall;
	private ImportadorCsv importadorcsv;
	private List<String> datoscargados;
	private List<Cliente> listaClientes;
	private static final String FILENAME = "DBClientes";
	
	private ArchivoServicio() {
		
	}
	
	public static ArchivoServicio getSingletoncall() {
		
		if(singletoncall == null) {
			
			synchronized (ArchivoServicio.class) {
				
				if(singletoncall == null) {
					singletoncall = new ArchivoServicio();
				}
			}
		}
		
		return singletoncall;
	}
			
	public void cargarDatos(String fileName) {
		
		Scanner localFileName = new Scanner(System.in);
		boolean nombrecorrecto = false;
		
		if(fileName.equalsIgnoreCase(this.getFilename())) {			
			nombrecorrecto = true;
		}else {
			
			while(!fileName.equalsIgnoreCase(this.getFilename())){
				
				System.out.println("Nombre de archivo no congruente, sistema permite solo un nombre fijo para importar.");
				System.out.println("Ingrese nuevamente el nombre del archivo (DB*******s)");
				fileName = localFileName.nextLine();			
				
				if(fileName.equalsIgnoreCase(this.getFilename())) {
					nombrecorrecto = true;
				}			
			}
		}	
			
		if(nombrecorrecto==true) {
			this.importadorcsv = new ImportadorCsv();
			this.setImportadorcsv(this.importadorcsv);
			List<String> datoscargados = new ArrayList<String>();
			this.datoscargados = this.getImportadorcsv().leerArchivo(this.getFilename());	
			this.setDatoscargados(this.datoscargados);
		}
	}

	public List<String> getDatoscargados() {
		return this.datoscargados;
	}

	public void setDatoscargados(List<String> datoscargados) {
		this.datoscargados = datoscargados;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	
	public static void setSingletoncall(ArchivoServicio singletoncall) {
		ArchivoServicio.singletoncall = singletoncall;
	}

	public ImportadorCsv getImportadorcsv() {
		return importadorcsv;
	}

	public void setImportadorcsv(ImportadorCsv importadorcsv) {
		this.importadorcsv = importadorcsv;
	}

	public static String getFilename() {
		return FILENAME;
	}
	
	//método para visualizar por consola la lista cargada desde el archivo importado
	public void verListaDesdeArchivoImportado() {
		for(String linea: this.getDatoscargados()) {
			System.out.println(linea);
		}
	}

}
