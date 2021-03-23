package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class threadBanco extends Thread{
	private int id;
	private Semaphore semaforo;
	private int saldo;
	private boolean trans;




	public threadBanco(int id, Semaphore semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		saldo = randomizar(4001,1001);
		trans = depOrSaq();
		if(trans==true) { // deposito
			try {
				semaforo.acquire();
				transacao(id,saldo,randomizar(1001,1),true);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo.release();
			}
		} else {
			try {
				semaforo.acquire();
				transacao(id,saldo,randomizar(1001,1),false); // saque
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo.release();
			}
		}
	}
	
	private void transacao(int codConta, int saldo, int valorTrans, boolean depOrSaq) {
		if(depOrSaq) {
			System.out.println("Conta#"+codConta+":Saldo Atual de "+ saldo+" reais!");
			System.out.println("Conta#"+codConta+":"+ saldo+" reais" + " foi transferido para sua conta!");
			saldo += valorTrans;
			System.out.println("Conta#"+codConta+":Saldo Atual de "+ saldo+" reais!");
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Conta#"+codConta+":Saldo Atual de "+ saldo+" reais!");
			System.out.println("Conta#"+codConta+":Você sacou "+valorTrans+" reais!");
			saldo -= valorTrans;
			System.out.println("Conta#"+codConta+":Saldo Atual de "+ saldo+" reais!");
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int randomizar(int min,int max) {
		Random r = new Random();
		int x = r.nextInt(max);
		while(x<min) {
			x = r.nextInt(max);
		}
		return x;
	}
	
	public boolean depOrSaq() {
		boolean x = Math.random() < 0.5;
		return x;
	}
	
	
	
}
