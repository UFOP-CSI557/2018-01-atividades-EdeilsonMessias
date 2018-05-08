package agreal;

import java.util.Random;

public class Blender implements Crossover {

	@Override
	public void aplicarOperador(Individuo proj1, Individuo pai2, Individuo desc1, Individuo desc2) {
		// TODO Auto-generated method stub
		Double alpha = 0.5;
		Double x1, x2, max, min, minAlcance, maxAlcance, random, y1, y2;
		for (int i = 0; i < proj1.getnVar(); i++) {
			
			x1 = proj1.getVariaveis().get(i);
			x2 = pai2.getVariaveis().get(i);
			
			if (x2 > x1) {
				max = x2;
				min = x1;
			} else {
				max = x1;
				min = x2;
			}
			
			minAlcance = min - (max - min) * alpha;
			maxAlcance = max + (max - min) * alpha;
			
			Random rnd = new Random();
			
			random = rnd.nextDouble();
			y1 = minAlcance + random * (maxAlcance - minAlcance);
			
			random = rnd.nextDouble();
			y2 = minAlcance + random * (maxAlcance - minAlcance);
			
			desc1.getVariaveis().add(y1);
			desc2.getVariaveis().add(y2);
		}
	}
}
