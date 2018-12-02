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
		int contador=0;
        for (int i = 0; i < almacenCaracteres.getTamanoLista(); i++) {
        	if(contador%6==0) {//si el tamaño es 6
        		try {
					sleep(1000);
				} catch (InterruptedException e) {					
				}
        	}
        	contador++;
            almacenCaracteres.agregarLetra(); //agrego la letra
            yield(); //que java elija otro hilo al azar
        } 
	}

}
