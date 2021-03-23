package view;

import java.util.concurrent.Semaphore;

import controller.threadBanco;

public class Principal {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);
		for(int id = 1;id<=20;id++) {
			Thread banco = new threadBanco(id,semaforo);
			banco.start();
		}
	}

}
