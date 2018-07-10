package br.ufop.ppn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Teste {

	public static void main(String[] args) {
		
		Integer tamanho = 100;
        Double pCrossover = 0.8;
        Double pMutacao = 0.05;
        Integer geracoes = 300;
        ProblemaParticaoNumero problema = new ProblemaParticaoNumero("instancia.txt");
        Integer numeroVariaveis = problema.getNumeroVariaveis();
        Boolean buscaLocal = null;
		int repeticoes = 30;
		
        ArrayList<String> nomes = new ArrayList<>(Arrays.asList("Algoritmo Genético", "Algoritmo Memético"));
        for (int i = 1; i <= repeticoes; i++) {
        	
            ArrayList<Integer> casos = new ArrayList<>(Arrays.asList(1, 2));
            Collections.shuffle(casos);
            
            for (int c = 1; c <= casos.size(); c++) {

                Integer result = 0;
                long startTime = System.currentTimeMillis();

                int teste = casos.get(c-1);

                switch (teste) {

                    case 1:
                    	buscaLocal = false;
                        break;

                    case 2:
                    	buscaLocal = true;
                        break;
                }
                
                AlgoritmoGenetico algoritmoGenetico = new AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, problema, numeroVariaveis, buscaLocal);
        		result = algoritmoGenetico.executar();
                
                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;

                System.out.println(nomes.get(teste - 1) + ";" + result + ";" + totalTime);
                System.out.flush();
            }
        }
	}
}
