package SimpleELO;

import java.util.HashMap;

public class Team {
	
	private static HashMap<String, Team> allteams = new HashMap<String, Team>();
	
	public final String name;
	public final int elo;
		
	public Team(String string) {
		String[] split = string.split(",");
		name = split[0];
		elo = Integer.parseInt(split[1]);
		allteams.put(name, this);
	}
	
	public String toString() {return name;}
	
	public double calcWinProb(Team that) {
		double CONST = 400.0; //400.0 by default. Higher numbers are more random
		return 1.0 / (1.0 + Math.pow(10.0, ((that.elo-this.elo)/CONST)));	//Elo win prob
		//if(this.elo > that.elo) {return 1.0;} else {return 0.0;}			//Elo win guarantee
	}
	
	public static Team match(String name) {
		return allteams.get(name);
	}

}
