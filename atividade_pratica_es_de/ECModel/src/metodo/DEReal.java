package metodo;

import java.util.Random;

import problema.Problema;
import solucao.Individuo;
import solucao.IndividuoDouble;
import solucao.PopulacaoDouble;

public class DEReal implements Metodo {

	private Double minimo;
	private Double maximo;
	private Problema problema;
	
	// Critério de parada
	private Integer gmax;
	// Número de variáveis
	private Integer D;
	//Tamanho da população
	private Integer Np;
	// Coeficiente de mutação
	private Double F;
	// Coeficiente de Crossover
	private Double Cr;

	public DEReal(Double minimo, Double maximo, Problema problema, Integer gmax, Integer d, Integer np, Double f,
			Double cr) {
		super();
		this.minimo = minimo;
		this.maximo = maximo;
		this.problema = problema;
		this.gmax = gmax;
		this.D = d;
		this.Np = np;
		this.F = f;
		this.Cr = cr;
	}

	@Override
	public Individuo executar() {
		
		// Criação da população inicial - X
		PopulacaoDouble populacao = new PopulacaoDouble(this.problema, this.minimo, this.maximo, this.D, this.Np);
		populacao.criar();
		
		// Avaliar a população inicial
		populacao.avaliar();
		
		// Nova população
		PopulacaoDouble novaPopulacao = new PopulacaoDouble();
		
		IndividuoDouble melhorSolucao = (IndividuoDouble) ((IndividuoDouble) populacao.getMelhorIndividuo()).clone();
		
		// Enquanto o critério de parada não for atingido
		for (int g = 1; g <= this.gmax;g++) {
			
			// Para cadas vetor da população
			for (int i = 0; i < this.Np; i++) {
				
				// Selecionar r0, r1, r2
				Random rnd = new Random();
				int r0, r1, r2;
				
				do {
					r0 = rnd.nextInt(this.Np);
				} while (r0 == i);
				
				do {
					r1 = rnd.nextInt(this.Np);
				} while (r1 == r0);
				
				do {
					r2 = rnd.nextInt(this.Np);
				} while (r2 == r1 || r2 == r0);
				
				IndividuoDouble trial = new IndividuoDouble(minimo, maximo, this.D);
				
				IndividuoDouble xr0 = (IndividuoDouble) populacao.getIndividuos().get(r0);
				IndividuoDouble xr1 = (IndividuoDouble) populacao.getIndividuos().get(r1);
				IndividuoDouble xr2 = (IndividuoDouble) populacao.getIndividuos().get(r2);
				
				// Gerar pertubação - diferença
				gerarPerturbacao(trial, xr1, xr2);
				// Mutação - r0 + F * pertubação
				mutacao(trial, xr0);
				// Target
				IndividuoDouble target = (IndividuoDouble) populacao.getIndividuos().get(i);
				// Crossover
				crossover(trial, target);
				// Seleção
				problema.calcularFuncaoObjetivo(trial);
				
				if (trial.getFuncaoObjetivo() <= target.getFuncaoObjetivo()) {
					novaPopulacao.getIndividuos().add(trial);
				} else {
					novaPopulacao.getIndividuos().add(target.clone());
				}
			}
			
			// População para a geração seguinte
			populacao.getIndividuos().clear();
			populacao.getIndividuos().addAll(novaPopulacao.getIndividuos());
			
			IndividuoDouble melhorDaPopulacao = (IndividuoDouble) populacao.getMelhorIndividuo();
			
			if (melhorDaPopulacao.getFuncaoObjetivo() <= melhorSolucao.getFuncaoObjetivo()) {
				melhorSolucao = (IndividuoDouble) melhorDaPopulacao.clone();
			}
			
//			System.out.println("G = " + g + "\t" + melhorSolucao.getFuncaoObjetivo());
		}
		
		return melhorSolucao;
	}
	
	private void gerarPerturbacao(IndividuoDouble trial, IndividuoDouble xr1, IndividuoDouble xr2) {
		// trial <- Diferença entre r1 e r2
		for (int i = 0; i < this.D; i++) {
			Double diferenca = xr1.getCromossomos().get(i) - xr2.getCromossomos().get(i);
			trial.getCromossomos().add(reparaValor(diferenca));
		}
	}
	
	private void mutacao(IndividuoDouble trial, IndividuoDouble xr0) {
		
		// trial <- r0 + F * perturbacao (trial)
		for (int i = 0; i < this.D; i++) {
			Double valor = this.F * xr0.getCromossomos().get(i) + this.F * trial.getCromossomos().get(i);
			trial.getCromossomos().set(i, reparaValor(valor));
		}
	}
	
	private void crossover(IndividuoDouble trial, IndividuoDouble target) {
		Random rnd = new Random();
		int j = rnd.nextInt(this.D);
		
		for (int i = 0; i < this.D; i++) {
			if (!(rnd.nextDouble() <= this.Cr || i == j)) {
				// Target
				trial.getCromossomos().set(i, target.getCromossomos().get(i));
			}
		}
	}
	
	private Double reparaValor(Double valor) {
		if (valor < this.minimo) {
			valor = this.minimo;
		} else if (valor > this.maximo) {
			valor = this.maximo;
		}
		
		return valor;
	}
}
