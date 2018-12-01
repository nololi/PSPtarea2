package productor;

import AlmacenCaracteres.AlmacenCaracteres;

public class Productor extends Thread {
	private AlmacenCaracteres almacenCaracteres;
	
	//constructor 
	public Productor(AlmacenCaracteres almacenCaracteres) {
		this.almacenCaracteres =almacenCaracteres;
	}
	
	@Override
    public void run() {
        for (int i = 0; i < almacenCaracteres.getTamanoLista(); i++) {
            almacenCaracteres.agregarLetra(); //agrego la letra
            yield(); //que java elija otro hilo al azar
        } 
	}

}
