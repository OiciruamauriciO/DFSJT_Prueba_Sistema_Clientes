package servicio;

import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;

public interface Importador {
	
	public void importarArchivo(String fileName);
	public ArrayList<String> leerArchivo(String nombreArchivo);
}
