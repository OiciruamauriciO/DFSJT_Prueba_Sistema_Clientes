package utilidades;

import java.util.Scanner;

public class Utilidad {
	
	public Utilidad() {
		
	}
	
	//método para limpiar pantalla PantallaProcessBuilder
	public void limpiarPantallaProcessBuilder() {
		
        try{
            String operatingSystem = System.getProperty("os.name");
            
            if(operatingSystem.contains("Windows")){  
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } 
        }catch(Exception e){
            System.out.println(e);
        }
    }
	
	//método limpiar pantalla desplazando líneas
	public void limpiarPantallaDesplazamiento() {		
		for(int i=0; i<100; i++) { System.out.println(" "); }	
	}
	
	
	//método para pedir una tecla específica para continuar
	public void presionaTecla() {
		
		String tecla;		
		Scanner sc = new Scanner(System.in);
		System.out.print("Presiona una tecla para continuar");
		tecla = sc.nextLine();				
	}
	
	//método de ajuste para la utilidad Utilidad extendida
	public void aplicaUtilidadExtendida() {
		this.presionaTecla();
		this.limpiarPantallaDesplazamiento();
	}	
	
}
