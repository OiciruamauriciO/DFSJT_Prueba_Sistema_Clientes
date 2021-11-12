package servicio;

import java.util.List;

import modelo.Cliente;

public abstract class Exportador {

	abstract void exportarArchivo(String fileName, List<Cliente> listaClientes);
}
