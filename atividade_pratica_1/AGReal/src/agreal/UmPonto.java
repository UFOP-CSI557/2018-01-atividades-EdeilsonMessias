package agreal;

import java.util.Random;

public class UmPonto implements Crossover {

	@Override
	public void aplicarOperador(Individuo proj1, Individuo proj2, Individuo desc1, Individuo desc2) {
		// TODO Auto-generated method stub
		
		// Crossover aritmetico - 1 ponto de corte
        Random rnd = new Random();
        Double alpha = rnd.nextDouble();
        
        // Ponto de corte
        int corte = rnd.nextInt(proj1.getVariaveis().size() - 1) + 1;

        // Ind1_1
        // alpha * P1
        for (int i = 0; i < corte; i++) {
            Double valor = alpha * proj1.getVariaveis().get(i);
            desc1.getVariaveis().add(valor);
            
            valor = alpha * proj2.getVariaveis().get(i);
            desc2.getVariaveis().add(valor);
        }

        // Ind2_2
        // (1 - alpha) * P2
        for (int i = corte; i < proj1.getVariaveis().size(); i++) {
            Double valor = (1.0 - alpha) * proj2.getVariaveis().get(i);
            desc1.getVariaveis().add(valor);
            
            valor = (1.0 - alpha) * proj1.getVariaveis().get(i);
            desc2.getVariaveis().add(valor);
        }
	}
}