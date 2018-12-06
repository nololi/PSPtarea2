import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Filosofo extends Thread {
	private String nombreFilosofo; // nombre filos�fo
	private Semaphore palilloDerecha;
	private Semaphore palilloIzquierda;
	private boolean hambriento = false;
	private List<Semaphore> semaforoPalillo;

	public Filosofo(int miIndice, java.util.concurrent.Semaphore[] semaforoPalillo) {
		this.nombreFilosofo = this.getClass().getSimpleName() + " " + (miIndice+1);
		this.palilloIzquierda = semaforoPalillo[miIndice];
		if (miIndice == 0) {
			this.palilloDerecha = semaforoPalillo[4];// para el primer fil�sofo su palillo derecho es el �ltimo
		} else {
			this.palilloDerecha = semaforoPalillo[miIndice - 1];
		}
		this.semaforoPalillo = Arrays.asList(semaforoPalillo); //para la b�squeda posici�n de los palillos 
	}

	public void comer() {
		if (palilloDerecha.tryAcquire()) { // Intentamos coger el palillo derecho
			if (palilloIzquierda.tryAcquire()) { // Intentamos coger el palillo izquierdo
				System.out.println(this.nombreFilosofo + " est� comiendo");//
				try {
					sleep((int) (Math.random() * 5000));// tiempo aleatorio que tarda en comer
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//utilizo la posici�n en el array de los sem�foros para saber el n�mero de palillo
				System.out.println(this.nombreFilosofo + " ha terminado de comer. Libres palillos "
						+ (semaforoPalillo.indexOf(palilloIzquierda)+1) + " , " + (semaforoPalillo.indexOf(palilloDerecha)+1)
						+ " .");
				hambriento = false;// Deja de estar hambriento
				palilloIzquierda.release();// Liberamos palillo izquierdo
			}
			palilloDerecha.release();// Liberamos palillo derecho

		} else {
			if (!hambriento) {
				System.out.println(this.nombreFilosofo + " est� hambriento");
				hambriento = true;// no ha comido ni ha podido adquirir palillos est� hambriento
			}
		}

	}

	public void pensar() {
		if (this.hambriento) //si est� hambriento salgo
			return;
		System.out.println(this.nombreFilosofo + " est� pensando");
		try {
			sleep((int) (Math.random() * 5000)); // dormir el hilo un tiempo aleatorio hasta 5s
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		do {//bucle infinito
			pensar();
			comer();
		} while (true);

	}

}
