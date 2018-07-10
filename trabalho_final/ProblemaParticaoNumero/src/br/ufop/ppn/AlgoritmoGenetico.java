package br.ufop.ppn;

import java.util.Collections;
import java.util.Random;

public class AlgoritmoGenetico {

    Integer tamanho;
    Double pCrossover;
    Double pMutacao;
    Integer geracoes;
    ProblemaParticaoNumero problema;
    Integer numeroVariaveis;

    Populacao populacao;
    Populacao novaPopulacao;
    Individuo melhorSolucao;
    Boolean buscaLocal;
    
    public AlgoritmoGenetico(Integer tamanho, Double pCrossover, Double pMutacao, Integer geracoes, ProblemaParticaoNumero problema, Integer numeroVariaveis, Boolean buscaLocal) {
        this.tamanho = tamanho;
        this.pCrossover = pCrossover;
        this.pMutacao = pMutacao;
        this.geracoes = geracoes;
        this.problema = problema;
        this.numeroVariaveis = numeroVariaveis;
        this.buscaLocal = buscaLocal;
    }
    
    public Individuo getMelhorSolucao() {
        return melhorSolucao;
    }

    public Integer executar() {

        populacao = new Populacao(this.tamanho, this.numeroVariaveis, this.problema);
        novaPopulacao = new Populacao(this.tamanho, this.numeroVariaveis, this.problema);

        populacao.criar();
        populacao.avaliar();

        Random randomico = new Random();
        int ind1, ind2;
        
        BuscaLocal buscaLocal = new BuscaLocal(this.problema);

        for (int g = 1; g <= geracoes; g++) {
        	
            for (int i = 0; i < this.tamanho; i++) {
            	
                if (randomico.nextDouble() <= this.pCrossover) {
                
                    ind1 = randomico.nextInt(this.tamanho);

                    do {
                        ind2 = randomico.nextInt(this.tamanho);
                    } while (ind1 == ind2);

                    Individuo desc1 = new Individuo(this.numeroVariaveis);
                    Individuo desc2 = new Individuo(this.numeroVariaveis);

                    Individuo p1 = populacao.getIndividuos().get(ind1);
                    Individuo p2 = populacao.getIndividuos().get(ind2);

                    int corte = randomico.nextInt(p1.getCromossomos().size());

                    // Descendente 1 -> Ind1_1 + Ind2_2;
                    crossoverUmPonto(p1, p2, desc1, corte);

                    // Descendente 2 -> Ind2_1 + Ind1_2;
                    crossoverUmPonto(p2, p1, desc2, corte);

                    // Descendente 1
                    mutacaoPorBit(desc1);
                    // Descendente 2
                    mutacaoPorBit(desc2);

                    // Avaliar as novas soluções
                    problema.calcularFuncaoObjetivo(desc1);
                    problema.calcularFuncaoObjetivo(desc2);
                    
                    if (this.buscaLocal) {
	                    buscaLocal.executar(desc1);
	                    buscaLocal.executar(desc2);
                    }

                    // Inserir na nova população
                    novaPopulacao.getIndividuos().add(desc1);
                    novaPopulacao.getIndividuos().add(desc2);
                }
            }

            // Definir sobreviventes -> populacao + descendentes
            // Merge: combinar pop+desc
            populacao.getIndividuos().addAll(novaPopulacao.getIndividuos());
            // Ordenar populacao;
            Collections.sort(populacao.getIndividuos());

            // Eliminar os demais individuos - criterio: tamanho da população
            populacao.getIndividuos()
                    .subList(this.tamanho,
                       populacao.getIndividuos().size())
                    .clear();

            // Limpa a nova população para a geração seguinte
            novaPopulacao.getIndividuos().clear();

            // Imprimir a situacao atual
//            System.out.println("Gen = " + g +
//                    "\tCusto = "
//                    + populacao.getIndividuos().get(0).getFuncaoObjetivo());

        }
        
        return populacao.getIndividuos().get(0).getFuncaoObjetivo();
    }

    private void crossoverUmPonto(Individuo ind1, Individuo ind2, Individuo descendente, int corte) {
    	
        // Ind1_1
    	descendente.getCromossomos()
                .addAll(ind1.getCromossomos().subList(0, corte));

        // Ind2_2
        descendente.getCromossomos()
                .addAll(ind2.getCromossomos().subList(corte, ind2.getCromossomos().size()));

    }

    private void mutacaoPorBit(Individuo individuo) {

        Random rnd = new Random();

        for (int i = 0; i < individuo.getCromossomos().size(); i++) {
            if (rnd.nextDouble() <= this.pMutacao) {
                int bit = individuo.getCromossomos().get(i);
                if (bit == 0) {
                    bit = 1;
                } else {
                    bit = 0;
                }

                individuo.getCromossomos().set(i, bit);
            }
        }
    }
}