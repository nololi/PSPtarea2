package consumidor;

import AlmacenCaracteres.AlmacenCaracteres;

public class Consumidor extends Thread {
	private AlmacenCaracteres almacenCaracteres;

	public Consumidor(AlmacenCaracteres almacenCaracteres) {
		this.almacenCaracteres = almacenCaracteres;
	}

	@Override
	public void run() {
		for(int i=0;i<almacenCaracteres.getTamanoLista();i++) {
			almacenCaracteres.sacar();
			 yield(); //que java elija otro hilo al azar
		}
		
		

	}

}
