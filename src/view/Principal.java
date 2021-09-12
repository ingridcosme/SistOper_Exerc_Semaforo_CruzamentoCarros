/*
 * Fazer uma aplica��o que gerencie 4 carros em um cruzamento:
 * Para tal, usar uma vari�vel sentido, que ser� alterado pela Thread que con_
 * trola cada carro com a movimenta��o do carro. Quando a Thread tiver a possi_
 * bilidade de ser executada, ela deve imprimir em console o sentido que o car_
 * ro est� passando. S� pode passar um carro por vez no cruzamento.
 */

package view;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

import controller.ThreadCruzamento;

public class Principal {

	public static void main(String[] args) {
		int permissoes = 1;
		Semaphore mutex = new Semaphore(permissoes);
		
		List<String> sentido = Arrays.asList("Norte", "Sul", "Oeste", "Leste");
		/*Garantindo "aleatoriedade" na atribui��o de sentido para os carros 
		 *quando rodar a aplica��o.*/
		Collections.shuffle(sentido);  //Embaralha a lista
		
		for(int idCarro = 1; idCarro <= 4; idCarro++) {
			Thread tCruzamento = new ThreadCruzamento(idCarro, sentido.get(idCarro-1), mutex);
			tCruzamento.start();
		}
	}

}
