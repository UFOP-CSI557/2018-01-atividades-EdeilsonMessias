package problema;

import solucao.Individuo;

public class ProblemaSchwefel implements Problema{

	private Integer nVariaveis;

    public ProblemaSchwefel(Integer nVariaveis) {
        this.nVariaveis = nVariaveis;
    }
    
    @Override
	public void calcularFuncaoObjetivo(Individuo individuo) {
    	
		double penalidade = 0.0;
		double soma = 0.0;
		
		for(int i = 0; i < individuo.getCromossomos().size(); i++) {
			double tmp = Math.abs((double) individuo.getCromossomos().get(i)) - 500.0;
			
			if (tmp > 0.0) {
				penalidade += tmp*tmp;
			}
		}
		
		double x;
		for (int i = 0; i < individuo.getCromossomos().size(); i++) {
			x = (double) individuo.getCromossomos().get(i);
			soma += x * Math.sin(Math.sqrt(Math.abs(x)));
		}
		
		individuo.setFuncaoObjetivo(0.01 * (penalidade + 418.9828872724339 - soma / individuo.getCromossomos().size()));
	}

	@Override
	public int getDimensao() {
		return this.nVariaveis;
	}
}
