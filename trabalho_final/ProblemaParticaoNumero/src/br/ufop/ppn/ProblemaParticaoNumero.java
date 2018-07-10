package br.ufop.ppn;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ProblemaParticaoNumero {
	
	private String nomeArquivo;
	private Integer numeroVariaveis;
	private ArrayList<Integer> variaveis;
	
	public ProblemaParticaoNumero(String nomeArquivo) {
		super();
		this.nomeArquivo = nomeArquivo;
		lerArquivo();
	}
	
	public ArrayList<Integer> getVariaveis() {
		return variaveis;
	}

	public void setVariaveis(ArrayList<Integer> variaveis) {
		this.variaveis = variaveis;
	}

	public Integer getNumeroVariaveis() {
		return numeroVariaveis;
	}

	public void setNumeroVariaveis(Integer numeroVariaveis) {
		this.numeroVariaveis = numeroVariaveis;
	}

	public void lerArquivo() {

		this.variaveis = new ArrayList<>();
		BufferedReader bufferedReader = null;
        
        try {
        	bufferedReader = new BufferedReader(new FileReader(this.nomeArquivo));

            String linha;
            
            linha = bufferedReader.readLine();
            this.numeroVariaveis = Integer.parseInt(linha.trim());
            
            // Ignorar separador (~)
            bufferedReader.readLine();
            
            while((linha = bufferedReader.readLine()) != null) {
            	this.variaveis.add(Integer.parseInt(linha.trim()));
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
        	ex.printStackTrace();
        }
	}

	public void calcularFuncaoObjetivo(Individuo individuo) {
		
		Integer funcaoObjetivo = 0;
		for (int i = 0; i < individuo.getNumeroVariaveis(); i++) {
			if (individuo.getCromossomos().get(i) == 1) {
				funcaoObjetivo += this.variaveis.get(i);
			} else {
				funcaoObjetivo -= this.variaveis.get(i);
			}
		}
		
		individuo.setFuncaoObjetivo(Math.abs(funcaoObjetivo));
	}
}