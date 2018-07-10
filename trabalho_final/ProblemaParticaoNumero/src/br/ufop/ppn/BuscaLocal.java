package br.ufop.ppn;

import java.util.Random;

public class BuscaLocal {
	
	private ProblemaParticaoNumero problema;

	public BuscaLocal(ProblemaParticaoNumero problema) {
		super();
		this.problema = problema;
	}
	
	/* São considerados dois tipos de movimento.
		Escolhe-se aleatoriamente um elemento do conjunto maior (conjunto no qual a soma dos números é maior).
		Caso a diferença entre os dois conjuntos seja maior do que este elemento, o movimento caracteriza-se
		por transferir este elemento para o conjunto menor e, caso a diferença seja menor do que o elemento,
		o movimento consiste em trocar este elemento do conjunto maior com o menor elemento do conjunto menor.
	*/
	public void executar(Individuo individuo) {
		
		int somaParticao0 = 0, somaParticao1 = 0;

		// Inicializando o índice da menor variável com o primeiro índice encontrado para cada partição
		int indiceMenorVariavelParticao0 = individuo.getCromossomos().indexOf(0);
		int indiceMenorVariavelParticao1 = individuo.getCromossomos().indexOf(1);
		
		for (int i = 0; i < individuo.getNumeroVariaveis(); i++) {
			int variavel = problema.getVariaveis().get(i);
			
			if (individuo.getCromossomos().get(i) == 0) {
				somaParticao0 += variavel;
				
				if (variavel < indiceMenorVariavelParticao0) {
					indiceMenorVariavelParticao0 = i;
				}
				
			} else {
				somaParticao1 += variavel;
				
				if (variavel < indiceMenorVariavelParticao1) {
					indiceMenorVariavelParticao1 = i;
				}
			}
		}
		
		int maiorParticao, menorParticao, menorVariavelParticaoMenor;
		if (somaParticao0 > somaParticao1) {
			maiorParticao = 0;
			menorParticao = 1;
			menorVariavelParticaoMenor = indiceMenorVariavelParticao1;
		} else {
			maiorParticao = 1;
			menorParticao = 0;
			menorVariavelParticaoMenor = indiceMenorVariavelParticao0;
		}
		
		Random randomico = new Random();
		int aleatorio;
		
		do {
			aleatorio = randomico.nextInt(individuo.getNumeroVariaveis());
		} while (individuo.getCromossomos().get(aleatorio) != maiorParticao);
		
		individuo.getCromossomos().set(aleatorio, menorParticao);
		if (individuo.getFuncaoObjetivo() < problema.getVariaveis().get(aleatorio)) {
			individuo.getCromossomos().set(menorVariavelParticaoMenor, maiorParticao);
		}
		
		problema.calcularFuncaoObjetivo(individuo);
	}
}
