package br.ufop.ppn;

import java.util.ArrayList;

public class Populacao {
	
	private Integer tamanho;
	private Integer numeroVariaveis;
	private ArrayList<Individuo> individuos;
	private ProblemaParticaoNumero problema;
    
    public Populacao(Integer tamanho, Integer numeroVariaveis, ProblemaParticaoNumero problema) {
		super();
		this.tamanho = tamanho;
		this.numeroVariaveis = numeroVariaveis;
		this.problema = problema;
		this.individuos = new ArrayList<>();
	}
	
	public Integer getTamanho() {
		return tamanho;
	}

	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}

	public Integer getNumeroVariaveis() {
		return numeroVariaveis;
	}

	public void setNumeroVariaveis(Integer numeroVariaveis) {
		this.numeroVariaveis = numeroVariaveis;
	}

	public ArrayList<Individuo> getIndividuos() {
		return individuos;
	}

	public void setIndividuos(ArrayList<Individuo> individuos) {
		this.individuos = individuos;
	}

	public ProblemaParticaoNumero getProblema() {
		return problema;
	}

	public void setProblema(ProblemaParticaoNumero problema) {
		this.problema = problema;
	}

	public void criar() {
		
		for (int i = 0; i < this.tamanho; i++) {
            
            Individuo individuo = new Individuo(this.numeroVariaveis);
            individuo.criar();
            
            this.individuos.add(individuo);
        }
	}

	public void avaliar() {
        
        for (Individuo individuo : this.individuos) {
            problema.calcularFuncaoObjetivo(individuo);
        }
    }
}
