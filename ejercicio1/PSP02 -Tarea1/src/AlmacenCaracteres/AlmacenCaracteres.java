package AlmacenCaracteres;


import java.util.List;
import java.util.Stack;

import consumidor.Consumidor;
import productor.Productor;

public class AlmacenCaracteres {
	// recurso compartido  a través del objeto: letras del buffer
	private Stack<Character> datosBuffer = new Stack<Character>();
	private int tamanoLista = 15;
//	private int contadorActual = 0;

	public int getTamanoLista() {
		return tamanoLista;
	}	

	public List<Character> getDatosBuffer() {
		return this.datosBuffer;
	}

	// Añadir letras
	public synchronized void agregarLetra() {
//		while (contadorActual == tamanoLista) {// mientras tenga el buffer lleno espero
//			try {
//				this.wait();// el hilo invoca wait() y se pone en la cola de espera wait del objeto
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		contadorActual++;

		// agrego valor
		char valor = (char) (Math.random() * 25 + 65);
		datosBuffer.push(valor);
		System.out.println("Depositado el carácter " + valor + " en el buffer");
		this.notifyAll();// notifica que ha agregado un carácter

	}

	/*
	 * Método que retira las letras de la pila
	 */
	public synchronized void sacar() {
		if(datosBuffer.size()==0) {
			try {
				wait();
			} catch (InterruptedException e) {				
			}
		}

		/*
		 * si no estoy esperando es porque hay valores y el consumidor recoge los
		 * valores del buffer
		 */
		char valor = datosBuffer.pop(); // la pila ya quita el último valor introducido
		System.out.println("Recogido el carácter " + valor + " del buffer");
		this.notifyAll();

	}

	public static void main(String[] args) {
		// recurso compartido por los hilos, el almacén
		AlmacenCaracteres almacen = new AlmacenCaracteres();

		// se crean e inician los hilos
		Productor productor = new Productor(almacen);
		Consumidor consumidor = new Consumidor(almacen);
		productor.start();
		consumidor.start();

	}

}
