import java.util.ArrayList;

public class SimpleVector extends ArrayList<Double> implements Comparable<SimpleVector> {
	private static final long serialVersionUID = -7303979323869482804L;
	
	public double cost = Double.MAX_VALUE;

	@Override
	public int compareTo(SimpleVector that) {
		if(this.cost < that.cost) {return -1;}
		else if(this.cost > that.cost) {return 1;}
		else {return 0;}
	}
	
	public double absDiff(SimpleVector that) {
		double counter = 0.0;
		for(int i=0; i<this.size(); i++) {
			counter += Math.abs(this.get(i) - that.get(i));}
		return counter;
	}
	
	public SimpleVector deepCopy() {
		SimpleVector newvector = new SimpleVector();
		for(Double value: this) {newvector.add(value);}
		return newvector;
	}
	
	
}
