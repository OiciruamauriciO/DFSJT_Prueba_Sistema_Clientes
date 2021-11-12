package servicio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import utilidades.Utilidad;

public class ImportadorCsv implements Importador{
	
	private Utilidad utilidad = new Utilidad();
	private List<String> datoscargados;
	private List<String> listatemporal;
	
	public ImportadorCsv() {
		
	}
	
	@Override
	public void importarArchivo(String fileName) {
		
		System.out.println("Procesando");
		listatemporal = new ArrayList<String>();
		datoscargados = new ArrayList<String>();		
		datoscargados = leerArchivo(fileName);
		
		for(String linea: datoscargados) {
			
			String separador = Pattern.quote(",");
			String[] partes = linea.split(separador);
			
			for(int i=0; i<partes.length; i++) {
				listatemporal.add(partes[i]);
			}
		}	
		
		System.out.println("Datos cargados correctamente en la lista");
		utilidad.presionaTecla();
		utilidad.limpiarPantallaDesplazamiento();
	}

	@Override
	public ArrayList<String> leerArchivo(String nombreArchivo) {
		
		ArrayList<String> lineas = new ArrayList<String>();
		
		BufferedReader br = null;
		FileReader fr = null;
		
					File archivoFile = new File(nombreArchivo+".csv");    
					
					String ruta_archivo = "respaldo/"+archivoFile;
					System.out.println(ruta_archivo);					
					
					try {
						
						fr = new FileReader(ruta_archivo);
						br = new BufferedReader(fr);
						
			            String texto = "";
			            
			            while (texto != null){
			            	
			            	texto = br.readLine();	

			            	if(texto!=null) {
			            		lineas.add(texto);
			            	}
			    						    			
			            }
			            						
					} catch (FileNotFoundException fnfe) {
						
						//excepción supeditada a FileReader
						System.out.println("No se puede encontrar el archivo especificado");
						fnfe.printStackTrace();
						
					}catch(IOException ioe) {
						
						//excepción supeditada BufferedReader
						System.out.println("No se puede leer el archivo especificado");
						ioe.printStackTrace();
						
					}finally{
						
			            try {
			                if(br != null) {br.close();}
			                if(fr != null) {fr.close();}
			            }
			            catch (Exception e) {
			                System.out.println("Error al cerrar el fichero");
			                System.out.println(e.getMessage());
			            }						
					}
	
		
		return lineas;
	}
	
	

}
