package net.wajder.network;
/**
 * @author vyder
 *
 */
public class ActivationFunction {
	
	private double beta = 0.5;
	private final double e = Math.E;
	
	/*
	 * funkcja liniowa, zwraca dok³¹dnie to, co podano na wejœcie
	 */
	public double func(double value){
		
		return value;
	}
	
	/*
	 * funkcja unipolarna sigmoidalna, przyjmuje wartoœci od 0 do 1
	 */
	public double sigmo(double s){
		
		return 1 / (1 + Math.pow(e, (-1 * beta * s)));

	}
	
	/*
	 * do testów
	 */
	public void test(){
		
		for (double i=-20; i<=20; i=i+0.1){
			System.out.println(sigmo(i));
		}
		
	}

	public double getBeta() {
		return beta;
	}

	public void setBeta(double beta) {
		this.beta = beta;
	}
	
}
