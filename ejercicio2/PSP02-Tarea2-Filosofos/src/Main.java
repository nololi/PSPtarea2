import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {
		java.util.concurrent.Semaphore[] semaforoPalillo = new java.util.concurrent.Semaphore[5]; // 0,1,2,3,4.		
		/*
		 * en la doc adjunta pone numerar de 0 al 4 
		 */
		for (int i = 0; i < 5; i++) { // crear los semáforos = palillos
			// vector de semáforos (uno para cada palillo).
			semaforoPalillo[i] = new Semaphore(1);
		}

		// instancio e inicializo los filósofos
		for (int id = 0; id < 5; id++) {
			new Filosofo(id, semaforoPalillo).start();

		}
	}

}
