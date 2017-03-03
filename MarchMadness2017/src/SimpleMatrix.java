import java.util.ArrayList;

public class SimpleMatrix extends ArrayList<SimpleVector> {
	private static final long serialVersionUID = -7318910434424150267L;
	
	public final int height;
	public final int width; 

	public SimpleMatrix(int width, int height) {
		
		this.height = height;
		this.width = width;
		
		//matrix = new ArrayList<SimpleVector>();
		for(int i=0; i<width; i++) {
			this.add(new SimpleVector());
			for(int j=0; j<height; j++) {
				this.get(i).add(0.0);
			}
		}
	}

	public void add(int x, int y, double data) {
		this.get(x).set(y, data);
	}
	
	public double get(int x, int y) {
		return this.get(x).get(y);
	}

	public String toString() {
		String string = "";
		for(int j=0; j<height; j++) {
			for(int i=0; i<width; i++) {
				string += this.get(i, j) + "\t";}
			string += "\n";}
		return string;
	}
}
