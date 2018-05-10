/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agtestes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


/**
 *
 * @author fernando
 */
public class AGTestes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        agreal.Problema problemaReal = new agreal.Problema();
        agreal.Crossover operador = null;
        
        Double pCrossover = 0.01;
        Double pMutacao = 0.0008;
        Double minimo = -5.12;
        Double maximo = 5.12;
        Integer nVariaveis = 100;
        Integer tamanho = 100;
        Integer geracoes = 300;
        int repeticoes = 30;
        
        // Casos de teste
        // 1 - Crossover Aritmético - 1 ponto; 2 - Crossover Blendex
        ArrayList<String> nomes = new ArrayList<>(Arrays.asList("Aritmético", "Blender(BLX-a)"));
        for (int i = 1; i <= repeticoes; i++) {
        	
            ArrayList<Integer> casos = new ArrayList<>(Arrays.asList(1, 2));
            Collections.shuffle(casos);

            for (int c = 1; c <= casos.size(); c++) {               

                agreal.AlgoritmoGenetico agReal;

                Double result = 0.0;
                long startTime = System.currentTimeMillis();

                int teste = casos.get(c-1);

                switch (teste) {

                    case 1:
                        operador = new agreal.UmPonto();
                        break;

                    case 2:
                    	operador = new agreal.Blender();
                        break;
                }
                
                agReal = new agreal.AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, problemaReal, minimo, maximo, nVariaveis, operador);
                result = agReal.executar();
                
                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;

                System.out.println(nomes.get(teste - 1) + "\t" + result + "\t" + totalTime);
                System.out.flush();
            }

        }

    }

}
