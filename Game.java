
package quidditch;

import java.util.HashMap;
import java.util.Objects;


public class Game {
    private HashMap<Team,Integer> scoreBoard;
    private String name;
    private static int gameCount;
    private static final int QUIFFLE_SCORE = 10;
    private static final int CATCH_SCORE = 10;
    String value = "";
    //update or change key is impossible !!
    
   
    public Game(Team home , Team away){
        this.scoreBoard = new HashMap<Team,Integer>();
        this.scoreBoard.put(new Team(home), 0);
        this.scoreBoard.put(new Team(away), 0);
        gameCount++;
    }
    
    public Integer getScore(Team team){
     return  this.scoreBoard.get(team);
 
    }
    
     public static Integer getGameCounte(){
     return gameCount;
 
    }
    
    public void setScore(Team team,Integer score){
        if(team == null){
            throw new IllegalArgumentException("can not add null to the score board");
        }
      this.scoreBoard.put(team,score);
 
    }
    
    public Team getTeam(String name){
        return this.scoreBoard.keySet().stream()
                .filter((key) -> key.getHouse().equals(name))
                .findFirst()
                .orElse(null);
    }
    
    
     public int hashCode(){
        return Objects.hash(name);
    }
     
     public String getPlaceholder(String play){
         return play.substring(play.indexOf("<") + 1,play.indexOf(">"));
     }
     
     public String replacePlaceholder(String play,String placeholder,String value){
         return play.replace("<"+placeholder+">",value);
     }
     
     public void quiffleScore(Team team){
         this.setScore(team, this.getScore(team) + QUIFFLE_SCORE);
         
     }
     
     public int getQuiffleScore(){
         return QUIFFLE_SCORE;
     }
     
      public void catcheScore(Team team){
         this.setScore(team, this.getScore(team) + CATCH_SCORE);
         
     }
     
     public int getCatchScore(){
         return CATCH_SCORE;
     }
     
     public Team getRandomTeam(){
       Object[] teams = scoreBoard.keySet().toArray();
       return (Team)teams[random(teams.length)];
     }
     
     public int random(int range){
         return (int)(Math.random()* range);
     }
     public String simulate(String play){
         String placeholder = getPlaceholder(play);
         Team team = getRandomTeam();
         
         if(placeholder.equals(Team.getPositionChaser())){
             quiffleScore(team);
             value = replacePlaceholder(play,placeholder,team.getChasers()[random(team.getChasers().length)]);
         }else if(placeholder.equals(Team.getPositionSeeker())){
              catcheScore(team);
             value = team.getSeeker();
         }else if(placeholder.equals(Team.getPositionKeeper())){
              catcheScore(team);
             value = team.getKeeper();
         }
         
         return replacePlaceholder(play,placeholder,value);
     } 
    
}
