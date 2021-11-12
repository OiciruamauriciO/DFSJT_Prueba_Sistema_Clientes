package servicio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import modelo.Cliente;

public class ExportadorCsv extends Exportador{

	private final String FILENAME = "Clientes";
	private Cliente cliente;
	private String nombreTemporal = "";
	private Scanner paraRuta = new Scanner(System.in);
	
	public ExportadorCsv() {
		
	}

	@Override
	public void exportarArchivo(String fileName, List<Cliente> listaClientes) {
		
		System.out.println("Ingrese el nombre del directorio en donde desea exportar el archivo " + fileName + ".csv");
		Scanner sc = new Scanner(System.in);
		String nombrecarpeta = sc.nextLine();	
		System.out.println(nombrecarpeta);
		
		String ruta_archivo = null;
		
		boolean forzado = false;
		
		String usuario = System.getProperty("user.name");
		String so = System.getProperty("os.name");
		String soname[] = so.split(" ");
		
		if(soname[0].equalsIgnoreCase("Windows")) {
			
			if(fileName.equalsIgnoreCase(this.getFILENAME())) {
				ruta_archivo = "C:\\Users\\" + usuario + "\\Desktop\\" + nombrecarpeta +  "\\" + fileName + ".csv";
				System.out.println("La ruta es: " + ruta_archivo);
			}else {
				
				do {
					System.out.println("Ingrese nombre de archivo (Cli****s): ");
					this.nombreTemporal = this.paraRuta.nextLine();
					this.setNombreTemporal(this.nombreTemporal);
				
					System.out.println("Nombre archivo: " + this.nombreTemporal);
					
					if(this.nombreTemporal.equalsIgnoreCase(this.getFILENAME())){
						System.out.println("Iguales");
						forzado=true;
					}
					
				}while(!this.nombreTemporal.equalsIgnoreCase(this.getFILENAME()));
			}
			
		}else {
			//se asume linux, pero no se ha probado en linux, por que se tiene instalado windows 10 en la máquina de desarrollo de esta prueba
		}
		
		if(!forzado) {
			System.out.println("Valor de forzado: "+ forzado);
			ruta_archivo = "C:\\Users\\" + usuario + "\\Desktop\\" + nombrecarpeta +  "\\" + this.getFILENAME() + ".csv";			
		}else {
			ruta_archivo = "C:\\Users\\" + usuario + "\\Desktop\\" + nombrecarpeta +  "\\" + this.getNombreTemporal() + ".csv";	
		}
		
		System.out.println(ruta_archivo);
		File archivoFile = new File(ruta_archivo);  		
		File directorioFile = new File("C:\\Users\\" + usuario + "\\Desktop\\" + nombrecarpeta);  		
		
		if (directorioFile.mkdirs()) {	
			
			try {
				
				archivoFile.createNewFile();
				FileWriter fileW = new FileWriter(archivoFile);
				BufferedWriter bufferedWriter = new BufferedWriter(fileW);
				
				for(Cliente cliente: listaClientes) {
					
					bufferedWriter.write(cliente.getRunCliente()+","+cliente.getNombreCliente()+","
					+cliente.getApellidoCliente()+","+cliente.getAniosCliente()+","
							+cliente.getCategoria()+"\n");
				}
				System.out.println("Datos de clientes exportados correctamente en formato .csv");
				System.out.println("Exportado -> " + ruta_archivo);
				System.out.println("----------------------------------------");
				bufferedWriter.close();						
			}catch (IOException e) {
				e.printStackTrace();
			}		
			
		}else {
			System.out.println("Error al crear el directorio");
		}	
		
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getNombreTemporal() {
		return nombreTemporal;
	}

	public void setNombreTemporal(String nombreTemporal) {
		this.nombreTemporal = nombreTemporal;
	}

	public Scanner getParaRuta() {
		return paraRuta;
	}

	public void setParaRuta(Scanner paraRuta) {
		this.paraRuta = paraRuta;
	}

	public String getFILENAME() {
		return FILENAME;
	}

}
