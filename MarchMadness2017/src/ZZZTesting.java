
public class ZZZTesting {

	public static void main(String[] args) {

		SimpleNet dummynode = new SimpleNet(true, 0.0);
		SimpleNet start1 = new SimpleNet(true, 0.0);
		SimpleNet start2 = new SimpleNet(true, 0.0);

		SimpleNet mid1 = new SimpleNet(false, 0.0);
		SimpleNet mid2 = new SimpleNet(false, 1.0);

		SimpleNet end1 = new SimpleNet(false, 0.0);

		dummynode.connect(mid1);
		dummynode.connect(mid2);
		
		start1.connect(mid1);
		start1.connect(mid2);
		start2.connect(mid1);
		start2.connect(mid2);
		mid1.connect(end1);
		mid2.connect(end1);

		SimpleMatrix inputs = new SimpleMatrix(4, 3);
		inputs.add(0, 0, 1); inputs.add(0, 1, 0); inputs.add(0, 2, 0);
		inputs.add(1, 0, 1); inputs.add(1, 1, 0); inputs.add(1, 2, 1);
		inputs.add(2, 0, 1); inputs.add(2, 1, 1); inputs.add(2, 2, 0);
		inputs.add(3, 0, 1); inputs.add(3, 1, 1); inputs.add(3, 2, 1);

		SimpleMatrix outputs = new SimpleMatrix(4, 1);
		outputs.add(0, 0, 0);
		outputs.add(1, 0, 1);
		outputs.add(2, 0, 1);
		outputs.add(3, 0, 0);
		
		SimpleGA ga = new SimpleGA(inputs, outputs);

		SimpleVector testvector = new SimpleVector();
		for(int i=0; i<SimpleNet.getWeights().size(); i++) {testvector.add(0.05*i);}
		
		System.out.println(ga.getCost(testvector));

		/*
		System.out.println(ga.run());

		for(int i=0; i<inputs.size(); i++) {
			System.out.print(inputs.get(i) + " -- ");
			SimpleNet.prime(inputs.get(i));
			System.out.println(SimpleNet.cascade());
		}
		*/


	}
}
