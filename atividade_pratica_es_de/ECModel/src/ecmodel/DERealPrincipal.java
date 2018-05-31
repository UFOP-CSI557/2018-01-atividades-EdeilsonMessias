package ecmodel;

import metodo.DEReal;
import problema.Problema;
import problema.ProblemaDeJong;
import solucao.Individuo;

public class DERealPrincipal {

	public static void main(String[] args) {
		
		Double minimo = -100.0;
		Double maximo = 100.0;
		
		int D = 100;
		Problema problema = new ProblemaDeJong(D);
		
		int gmax = 100;
		int Np = 100;
		
		double F = 0.001;
		double Cr = 0.8;
		
		DEReal deReal = new DEReal(minimo, maximo, problema, gmax, D, Np, F, Cr);
		
		Individuo resultado = deReal.executar();
		System.out.println(resultado);
	}
}
