import java.util.Collections;

//A simple genetic algorithm for optimizing the simple neural net
public class SimpleGA {

	public final int POP_SIZE = 100;
	public final int YEARS = 100; 

	public SimpleMatrix inputs;
	public SimpleMatrix outputs;

	public SimpleGA(SimpleMatrix inputs, SimpleMatrix outputs) {
		this.inputs = inputs;
		this.outputs = outputs;
	}

	public SimpleVector run() {

		int height = SimpleNet.getWeights().size();
		SimpleMatrix population = new SimpleMatrix(POP_SIZE, height);
		for(int i=0; i<POP_SIZE; i++) {population.set(i, randomVector(height));}
		rankAndSort(population);

		for(int year=0; year<YEARS; year++) {
			rankAndSort(population);
			for(int i=50; i<100; i++) {population.remove(population.size()-1);}
			for(int i=0; i<50; i++) {population.add(mutateVector(population.get(i)));}
		}
		
		rankAndSort(population);
		SimpleNet.setWeights(population.get(0));
		return population.get(0);
	}

	private void rankAndSort(SimpleMatrix matrix) {
		for(SimpleVector vector: matrix) {	
			if(vector.cost == Double.MAX_VALUE) {
				vector.cost = getCost(vector);}}
		Collections.sort(matrix);
	}

	public double getCost(SimpleVector vector) {

		double counter = 0.0;
		SimpleNet.setWeights(vector);

		for(int i=0; i<inputs.size(); i++) {
			SimpleNet.prime(inputs.get(i));
			SimpleVector netoutputs = SimpleNet.cascade();
			counter += netoutputs.absDiff(outputs.get(i));			
		}

		return counter; 
	}

	private SimpleVector randomVector(int size) {
		SimpleVector vector = new SimpleVector();
		for(int i=0; i<size; i++) {vector.add(randomElement());}
		return vector;
	}
	
	private SimpleVector mutateVector(SimpleVector vector) {
		SimpleVector newvector = vector.deepCopy();
		for(int i=0; i<newvector.size(); i++) {
			newvector.set(i, randomShift(vector.get(i)));}
		return newvector;
	}
	
	private double randomShift(double element) {return element + (Math.random()-0.5)*3;}
	
	private double randomElement() {return (Math.random()-0.5) * 20;}

}
