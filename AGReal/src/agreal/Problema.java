/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agreal;

/**
 *
 * @author fernando
 */
public class Problema {
    
    public void calcularFuncaoObjetivo(Individuo individuo) {
        
    	Double rastrigin = 10.0 * individuo.getVariaveis().size();
                
        for( Double var : individuo.getVariaveis()) {
        	rastrigin += Math.pow(var, 2) - 10.0 * Math.cos(2 * Math.PI * var);
        }
        
        individuo.setFuncaoObjetivo(rastrigin);
        
    }
    
}
