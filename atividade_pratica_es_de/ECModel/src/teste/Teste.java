package teste;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import metodo.DEReal;
import metodo.ESReal;
import problema.Problema;
import problema.ProblemaDeJong;
import problema.ProblemaRastrigin;
import solucao.IndividuoDouble;

public class Teste {

	public static void main(String[] args) {
		
		Double minimo = -5.12;
		Double maximo = 5.12;
		ProblemaRastrigin problema;
		int repeticoes = 30;
		
        // Casos de teste
        // 1 - ES (Evolution Strategy); 2 - DE (Differential Evolution)
        ArrayList<String> nomes = new ArrayList<>(Arrays.asList("ES (Evolution Strategy)", "DE (Differential Evolution)"));
        for (int i = 1; i <= repeticoes; i++) {
        	
            ArrayList<Integer> casos = new ArrayList<>(Arrays.asList(1, 2));
            Collections.shuffle(casos);
            
            for (int c = 1; c <= casos.size(); c++) {

                Double result = 0.0;
                long startTime = System.currentTimeMillis();

                int teste = casos.get(c-1);

                switch (teste) {

                    case 1:
                    	// Parametros - ES
                    	Integer nVariaveis = 100;
                        Integer mu = 60; // Tamanho da populacao
                        Integer lambda = 100; // numero de descendentes
                        Integer geracoes = 300; // criterio de parada
                        Double pMutacao = 0.08; // mutacao - aplicacao ao descendente - variacao/perturbacao
                        
                        problema = new ProblemaRastrigin(nVariaveis);
                        
                        ESReal esReal = new ESReal(minimo, maximo, nVariaveis, problema, mu, lambda, geracoes, pMutacao);
                        IndividuoDouble melhor = (IndividuoDouble) esReal.executar();
                        result = melhor.getFuncaoObjetivo();
                        break;

                    case 2:
                    	// Parametros - DE
                    	int D = 100;
                		int gmax = 300;
                		int Np = 60;
                		double F = 0.001;
                		double Cr = 0.8;
                		problema = new ProblemaRastrigin(D);
                		
                		DEReal deReal = new DEReal(minimo, maximo, problema, gmax, D, Np, F, Cr);
                		
                		IndividuoDouble resultado = (IndividuoDouble) deReal.executar();
                		result = resultado.getFuncaoObjetivo();
                        break;
                }
                
                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;

                System.out.println(nomes.get(teste - 1) + ";" + result + ";" + totalTime);
                System.out.flush();
            }
        }
	}
}
