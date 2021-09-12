/*
 * Fazer uma aplica��o que gerencie 4 carros em um cruzamento:
 * Para tal, usar uma vari�vel sentido, que ser� alterado pela Thread que con_
 * trola cada carro com a movimenta��o do carro. Quando a Thread tiver a possi_
 * bilidade de ser executada, ela deve imprimir em console o sentido que o car_
 * ro est� passando. S� pode passar um carro por vez no cruzamento.
 */

package controller;

import java.util.concurrent.Semaphore;

public class ThreadCruzamento extends Thread {

	private int idCarro;
	private String sentido = "";
	private Semaphore semaforo;
	
	public ThreadCruzamento(int idCarro, String sentido, Semaphore semaforo) {
		this.idCarro = idCarro;
		this.sentido = sentido;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		/*In�cio Se��o Cr�tica*/
		try {
			semaforo.acquire();
			cruzando();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			semaforo.release();
		}
		/*Fim Se��o Cr�tica*/
	}
		
	private void cruzando() {
		/*Indicar o sentido para onde o carro est� indo*/
		if(sentido == "Norte") {
			sentido += " para Sul";
		} else if(sentido == "Sul") {
			sentido += " para Norte";
		} else if(sentido == "Oeste") {
			sentido += " para Leste";
		} else {
			sentido += " para Oeste";
		}
		
		/*Simular o tempo que o carro demora para atravessar o cruzamento*/
		int tempoCruzar = (int)((Math.random() * 1001) + 1000);  //Gerando um valor entre 1 e 2 s
		try {
			sleep(tempoCruzar);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("O carro #"+idCarro+" est� passando pelo cruzamento "
				+ "no sentido "+sentido+".");
	}

}
