
public class ZZZTesting2 {
	
	public static void main(String[] args) {
		
		SimpleMatrix matrix = new SimpleMatrix(3,4);
		for(int i=0; i<4; i++) {
			for(int j=0; j<3; j++) {
				matrix.add(i, j, 3*i+j);}}
		System.out.println(matrix);

	}

}
