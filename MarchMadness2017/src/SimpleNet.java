import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//A simple neural net with limited functionality
public class SimpleNet {

	//A static queue used when cascading
	private static Queue<SimpleNet> bfsqueue = new LinkedList<SimpleNet>();
	
	//A list of all the nodes that have been created
	public static ArrayList<SimpleNet> allnodes = new ArrayList<SimpleNet>();
	public static ArrayList<SimpleNet> startnodes = new ArrayList<SimpleNet>(); 

	//The nodes weights and output nodes
	public ArrayList<Double> weights;
	public ArrayList<SimpleNet> children;
	
	private final double offset;

	//The value of the current node
	private double value;

	//Creates a new blank SimpleNet node
	public SimpleNet(boolean startnode, double offset) {
		this.offset = offset;
		this.weights = new ArrayList<Double>();
		this.children = new ArrayList<SimpleNet>();
		allnodes.add(this);
		if(startnode) {startnodes.add(this);}
	}

	//Connects two SimpleNet nodes
	public void connect(SimpleNet node) {
		this.children.add(node);
		this.weights.add(0.0);
	}

	//Disconnects two SimpleNet nodes
	public void disconnect(SimpleNet node) {
		int index = this.children.indexOf(node);
		children.remove(index);
		weights.remove(index);
	}

	//Primes a SimpleNet node to fire
	public void prime(Double value) {
		this.value = value;
		bfsqueue.add(this);
	}	
	
	//Primes the whole net
	public static void prime(ArrayList<Double> values) {
		for(int i=0; i<startnodes.size(); i++) {
			startnodes.get(i).prime(values.get(i));
		}
	}

	//Cascades the net
	public static SimpleVector cascade() {

		//Initializing sets
		SimpleVector answers = new SimpleVector();
		HashSet<SimpleNet> hit = new HashSet<SimpleNet>();

		//While there is still cascading to do
		while(!bfsqueue.isEmpty()) {

			//Get the next node in the queue
			SimpleNet node = bfsqueue.remove();

			//If the node has already been hit, ignore it
			if(!hit.contains(node)) {
				
				//Add the current node to the list of hit nodes
				hit.add(node);
				
				//If the node doesn't have any children, it is an answer node
				if(node.children.isEmpty()) {
					answers.add(node.value);}

				//For every child of the node...
				for(int i=0; i<node.children.size(); i++) {
					
					//Getting the child node and weight
					SimpleNet nextnode = node.children.get(i);
					double nextweight = node.weights.get(i);

					//If the child is in the middle of the net, run a sigmoid function
					if(!nextnode.children.isEmpty()) {
						nextnode.value = nextnode.calcSigmoid(nextnode.value);}
					
					//Add the next node to the queue
					nextnode.value += node.value * nextweight;
					bfsqueue.add(nextnode);
				}
				
				//Cleaning the node value
				node.value = 0.0;
			}
		}
		
		//Return the list of valid answers
		return answers; 
	}
	
	//Gets the list of all weights
	public static ArrayList<Double> getWeights() {
		ArrayList<Double> weights = new ArrayList<Double>();
		for(SimpleNet node: allnodes) {
			for(Double weight: node.weights) {
				weights.add(weight);}}
		return weights;
	}
	
	//Sets all the weights
	public static void setWeights(ArrayList<Double> weights) {
		int counter = 0; 
		for(SimpleNet node: allnodes) {
			for(int i=0; i<node.weights.size(); i++) {
				node.weights.set(i, weights.get(counter++));}}
	}

	//Returns the value of the sigmoid function
	private double calcSigmoid(double value) {
		//return value;
		return 1.0 / (1.0 + Math.exp(-(value+offset))); 
	}

}
