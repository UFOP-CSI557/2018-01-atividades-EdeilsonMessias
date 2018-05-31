/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agreal;

/**
 *
 * @author fernando
 */
public class AGReal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Problema problema = new Problema();

        Integer tamanho = 100;
        Double pCrossover = 0.01;
        Double pMutacao = 0.0008;
        Integer geracoes = 300;
        
        Double minimo = -5.12;
        Double maximo = 5.12;
        Integer nVariaveis = 100;
        
//        Crossover operador = new UmPonto();
        Crossover operador = new Blender();

        AlgoritmoGenetico ag = new AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, problema, minimo, maximo, nVariaveis, operador);
        
        System.out.println("Melhor resultado: " + ag.executar());
    }
    
}
