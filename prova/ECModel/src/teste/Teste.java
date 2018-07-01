package teste;

import metodo.ESAGReal;
import problema.Problema;
import problema.ProblemaSchwefel;
import solucao.Individuo;

public class Teste {

	public static void main(String[] args) {
		
		Double minimo = -500.0;
        Double maximo = 500.0;
        Integer nVariaveis = 50;
        Problema problema = new ProblemaSchwefel(nVariaveis);
        
        Integer mu = 100; // Tamanho da populacao
        Integer lambda = 100; // numero de descendentes
        Integer geracoes = 300; // criterio de parada
        Double pMutacao = 0.99; // mutacao - aplicacao ao descendente - variacao/perturbacao

		ESAGReal esReal = new ESAGReal(minimo, maximo, nVariaveis, problema, mu, lambda, geracoes, pMutacao);
        Individuo melhorIndividuo;
        
		for (int i = 1; i <= 30; i++) {
            Double result = 0.0;
            long startTime = System.currentTimeMillis();
        	
            melhorIndividuo = esReal.executar();
    		result = melhorIndividuo.getFuncaoObjetivo();
            
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;

            System.out.println(i + ";" + result + ";" + totalTime);
            System.out.flush();
        }
	}
}