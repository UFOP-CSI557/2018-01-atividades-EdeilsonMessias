/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodo;

import java.util.Collections;
import java.util.Random;
import problema.Problema;
import solucao.Individuo;
import solucao.IndividuoDouble;
import solucao.PopulacaoDouble;

/**
 *
 * @author fernando
 */
public class ESAGReal implements Metodo {

    // Parametros - problema - DeJong
    private Double minimo;
    private Double maximo;
    private Integer nVariaveis;
    private Problema problema;
    
    // Parametros - ES
    private Integer mu; // Tamanho da populacao
    private Integer lambda; // numero de descendentes
    private Integer geracoes; // criterio de parada
    private Double pMutacao; // mutacao - aplicacao ao descendente - variacao/perturbacao

    public ESAGReal(Double minimo, Double maximo, Integer nVariaveis, Problema problema, Integer mu, Integer lambda, Integer geracoes, Double pMutacao) {
        this.minimo = minimo;
        this.maximo = maximo;
        this.nVariaveis = nVariaveis;
        this.problema = problema;
        this.mu = mu;
        this.lambda = lambda;
        this.geracoes = geracoes;
        this.pMutacao = pMutacao;
    }
    
    @Override
    public Individuo executar() {
        
        // Geracao da populacao inicial - aleatoria - tamanho mu
        PopulacaoDouble populacao = new PopulacaoDouble(problema, minimo, maximo, nVariaveis, mu);     
        populacao.criar();
        
        // Avaliar
        populacao.avaliar();
        
        // Recuperar o melhor indivíduo
        Random rnd = new Random();
        int ind1, ind2;
        
        // Populacao - descendentes
        PopulacaoDouble descendentes = new PopulacaoDouble();
        
        // Criterio de parada - numero de geracoes
        for(int g = 1; g <= this.geracoes; g++) {
            
            // Para cada individuo, gerar lambda/mu descendentes
            for(int i = 0; i < populacao.getIndividuos().size(); i++) {
                
            	// Gerar lambda/mu descendentes
                for(int d = 0; d < lambda / mu; d++) {
                    
                    // Clonar individuo
                    IndividuoDouble copiaPai = (IndividuoDouble)populacao.getIndividuos().get(i).clone();
                    IndividuoDouble pai = (IndividuoDouble)populacao.getIndividuos().get(i).clone();
                    
                    // Aplicar mutacao
                    mutacaoPorVariavel(pai);
                    
                    // descendente
                    Individuo filho = new IndividuoDouble(minimo, maximo, nVariaveis);
                    
                    // Ponto de corte
                    int corte = rnd.nextInt(copiaPai.getCromossomos().size());
                    
                    crossoverUmPonto(copiaPai, pai, filho, corte);
                    
                    // Avaliar
                    problema.calcularFuncaoObjetivo(filho);
                    
                    // Inserir na populacao de descendentes
                    descendentes.getIndividuos().add(filho);
                }
                
            }
            
            // ES(mu, lambda)?
            // Eliminar a populacao corrente
            //populacao.getIndividuos().clear();
            
            // ES(mu + lambda)?
            // Mu + Lambda
            populacao.getIndividuos().addAll(descendentes.getIndividuos());
            // Ordenar Mu+Lambda
            Collections.sort(populacao.getIndividuos());
            // Definir sobreviventes
            populacao.getIndividuos()
                    .subList(this.mu, populacao.getIndividuos().size()).clear();
            // Limpar descendentes
            descendentes.getIndividuos().clear();
            
//            System.out.println("G = " + g 
//                    + "\t"
//                    + populacao.getMelhorIndividuo().getFuncaoObjetivo());
        }
        
      // Retornar o melhor individuo
      return populacao.getMelhorIndividuo();
        
    }

    private void crossoverUmPonto(Individuo ind1, Individuo ind2, Individuo descendente, int corte) {

        // Crossover aritmetico - 1 ponto de corte
        Random rnd = new Random();
        Double alpha = rnd.nextDouble();

        // Ind1_1
        // alpha * P1
        for (int i = 0; i < corte; i++) {
            Double valor = alpha * (double) ind1.getCromossomos().get(i);
            descendente.getCromossomos().add(valor);
        }

        // Ind2_2
        // (1 - alpha) * P2
        for (int i = corte; i < this.nVariaveis; i++) {
            Double valor = (1.0 - alpha) * (double) ind2.getCromossomos().get(i);
            descendente.getCromossomos().add(valor);
        }

    }
    
        private void mutacaoPorVariavel(Individuo individuo) {

        Random rnd = new Random();

        for (int i = 0; i < individuo.getCromossomos().size(); i++) {
            if (rnd.nextDouble() <= this.pMutacao) {

                // Mutacao aritmetica
                // Multiplicar rnd e inverter ou nao o sinal
                Double valor = (Double) individuo.getCromossomos().get(i);
                // Multiplica por rnd
                valor *= rnd.nextDouble();

                // Inverter o sinal
                if (!rnd.nextBoolean()) {
                    valor = -valor;
                }

                if (valor >= this.minimo
                        && valor <= this.maximo) {
                    individuo.getCromossomos().set(i, valor);

                }

            }
        }

    }
    
}
