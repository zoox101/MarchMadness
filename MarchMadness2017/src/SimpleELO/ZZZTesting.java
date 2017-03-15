package SimpleELO;

public class ZZZTesting {
	
	public static void main(String[] args) {
		
		Team team1 = new Team("Team1,560");
		Team team2 = new Team("Team2,512");
		System.out.println(team1.calcWinProb(team2));
		System.out.println(team2.calcWinProb(team1));
		
		//System.out.println(Math.pow(2, 64));
		//System.out.println(Math.pow(Math.pow(2, 32), 2));
		
	}

}
