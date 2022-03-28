/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quidditch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Amcِ
 */
public class Quidditch {

     // when add equal method , add hashCode() --> this assign every obj new integer 
    
   static Game game;
   static final String TEAMS_FILE = "RELATIVE PATH";
   static final String PLAYS_FILE = "RELATIVE PATH";

    public static void main(String[] args) {
        try{
             String[][] data = getData();
        
        game = new Game(
        new Team(data[0][0], data[0][1], data[0][2], new String[] {data[0][3], data[0][4], data[0][5]}),
        new Team(data[1][0], data[1][1], data[1][2], new String[] {data[1][3], data[1][4], data[1][5]})
          );
        startGame();
        printResult();
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
       
        
   /*     
      Team  home = new Team("GRYFFINDOR", "Oliver", "Harry", 
    new String[] {"Angelina", "Ginny", "Katie"});
  
     Team away = new Team("SLYTHERIN", "Vincent",  "Draco", 
    new String[] {"Bridget", "Harper", "Malcolm"});
     Game game = new Game(home,away);
    System.out.println(game.getScore(home));// its null , but after adding the hashCode in Team class its 0 !! since the hashcodes are identical now
    System.out.println(game.getTeam("GRYFFINDOR"));  
    Game game1 = new Game(home,away);
    Game game2 = new Game(home,away);
    Game game3 = new Game(home,away);
    System.out.println(Game.getGameCounte());
    */
    /* Game obj=new Game("nada");
     System.out.println("nada :"+ obj.hashCode());
     
      Game obj1=new Game("nada");
     System.out.println("nada1 :"+ obj1.hashCode());
     
      Game obj2=new Game("nada");
     System.out.println("nada2 :"+ obj2.hashCode());
    */ 
    // Game obj2=new Game(obj);
    //equal objects must have the same code !, otherwise you will get bugs with hash-based collection !
    //so here obj2 and obj have different hashcodes .. thats not good ,so use the hashCode() function in Game class
    //hashMap use the hash code to find the bucket of each object ,so even if the 2 obj have the same key 
    //its not enough to find the bucket they still need the hashCodes to be identical
    
    
    }
    
    public static String[][] getData() throws FileNotFoundException {
     FileInputStream fis = new FileInputStream(TEAMS_FILE) ;
     Scanner scanfile = new Scanner(fis);
     String[] lines = new String[]{scanfile.nextLine(),scanfile.nextLine()};
     scanfile.close();
     return new String[][] {lines[0].split(","),lines[1].split(",")};
             
    }
    
    public static void startGame() throws FileNotFoundException{
     FileInputStream fis = new FileInputStream(PLAYS_FILE) ;
     Scanner scanfile = new Scanner(fis);
     while(scanfile.hasNextLine()){
         wait(3);
         System.out.println("\n" + game.simulate(scanfile.nextLine()) + "\n");
         
     }
     
     scanfile.close();
    }
    
    public static void printResult(){
        Team gryffindor = game.getTeam("GRYFFINDOR");
        Team slytherin = game.getTeam("SLYTHERIN"); 
        Team winner = game.getScore(gryffindor) > game.getScore(slytherin) ? gryffindor : slytherin;
        System.out.println("\nGRYFFINDOR: " + game.getScore(gryffindor) + " SLYTHERIN: " + game.getScore(slytherin)+
                "\n" + winner.getHouse() + " WINS!");
        
    }   
    
    public static void wait(int sec) {
        try{
                    TimeUnit.SECONDS.sleep(sec);

        }catch(InterruptedException e){
            System.out.println(e.getMessage());
    }
   
}
    
}
