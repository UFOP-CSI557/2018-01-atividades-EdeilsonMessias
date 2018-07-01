/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecmodel;

import metodo.ESAGReal;
import problema.Problema;
import problema.ProblemaSchwefel;
import solucao.Individuo;

/**
 *
 * @author fernando
 */
public class ESAGRealPrincipal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Double minimo = -500.0;
        Double maximo = 500.0;
        Integer nVariaveis = 50;
        Problema problema = new ProblemaSchwefel(nVariaveis);
        
        // Parametros - ES
        Integer mu = 100; // Tamanho da populacao
        Integer lambda = 100; // numero de descendentes
        Integer geracoes = 300; // criterio de parada
        Double pMutacao = 0.99; // mutacao - aplicacao ao descendente - variacao/perturbacao

        ESAGReal esReal = new ESAGReal(minimo, maximo, nVariaveis, problema, mu, lambda, geracoes, pMutacao);
        Individuo melhor = esReal.executar();
       
        System.out.println(melhor);
        
        
    }

}
