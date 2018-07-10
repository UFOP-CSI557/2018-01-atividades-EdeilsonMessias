package br.ufop.ppn;

import java.util.ArrayList;
import java.util.Random;

public class Individuo implements Comparable<Individuo> {
	
	private ArrayList<Integer> cromossomos;
	private ArrayList<Integer> fenotipo;
	private Integer funcaoObjetivo;
	private Integer numeroVariaveis;
	
	public Individuo(Integer numeroVariaveis) {
		super();
		this.numeroVariaveis = numeroVariaveis;
		this.cromossomos = new ArrayList<>();
	}

	public void criar() {
		Random randomico = new Random();
		
		for (int i = 0; i < this.numeroVariaveis; i++) {
			this.cromossomos.add(randomico.nextInt(2));
		}
	}

    @Override
    public int compareTo(Individuo individuo) {
        return this.funcaoObjetivo
        		.compareTo(individuo.getFuncaoObjetivo());
    }

	public ArrayList<Integer> getCromossomos() {
		return cromossomos;
	}

	public void setCromossomos(ArrayList<Integer> cromossomos) {
		this.cromossomos = cromossomos;
	}

	public ArrayList<Integer> getFenotipo() {
		return fenotipo;
	}

	public void setFenotipo(ArrayList<Integer> fenotipo) {
		this.fenotipo = fenotipo;
	}

	public Integer getFuncaoObjetivo() {
		return funcaoObjetivo;
	}

	public void setFuncaoObjetivo(Integer funcaoObjetivo) {
		this.funcaoObjetivo = funcaoObjetivo;
	}

	public Integer getNumeroVariaveis() {
		return numeroVariaveis;
	}

	public void setNumeroVariaveis(Integer numeroVariaveis) {
		this.numeroVariaveis = numeroVariaveis;
	}
}