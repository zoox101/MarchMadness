package SimpleELO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AAADriver {

	public final static int TOTAL_SIMS = 5;

	public final static String PREFIX = "2017";
	public final static String DIRECTORY = "data/";
	public final static String ELO_FILE_NAME = DIRECTORY + PREFIX + "TeamsElo.csv";
	public final static String BRACKET_FILE_NAME = DIRECTORY + PREFIX + "Bracket.csv";

	
	public static void main(String[] args) throws IOException {
		for(int i=0; i<TOTAL_SIMS; i++) {
			runSim(); System.out.println();}
	}
	
	public static void runSim() throws IOException {

		//Getting the teams from the file
		BufferedReader eloreader = new BufferedReader(new FileReader(new File(ELO_FILE_NAME)));

		//Adding all the teams to the array
		String eloline; 
		while((eloline = eloreader.readLine()) != null) {
			new Team(eloline);}
		eloreader.close();

		//Getting the bracket from the file
		BufferedReader bracketreader = new BufferedReader(new FileReader(new File(BRACKET_FILE_NAME)));

		//Adding the team names to the bracket
		ArrayList<String> teamnames = new ArrayList<String>();
		String bracketline; 
		while((bracketline = bracketreader.readLine()) != null) {
			teamnames.add(bracketline);}
		bracketreader.close();

		//Adding the teams to the bracket
		ArrayList<Team> teams = new ArrayList<Team>();
		for(String teamname: teamnames) {
			teams.add(Team.match(teamname));}

		//Running the tournament and printing the results
		while(teams.size() > 1) {
			teams = run(teams);
			System.out.println(teams);}
		
	}

	//Run a single round of the tournament
	public static ArrayList<Team> run(ArrayList<Team> teams) {

		//Creating an array to store the next round of teams
		ArrayList<Team> nextteams = new ArrayList<Team>();

		//For every match in the current array of teams...
		for(int i=0; i<teams.size(); i+=2) {

			//Get the two teams
			Team team1 = teams.get(i);
			Team team2 = teams.get(i+1);

			//Get the probability that the first team wins
			double prob = team1.calcWinProb(team2);
			double random = Math.random();

			//Randomly check if that team wins
			if(random < prob) {nextteams.add(team1);}
			else{nextteams.add(team2);}
		}

		//Return the results of the round
		return nextteams;
	}



}
