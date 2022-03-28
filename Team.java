/*
 * when add equals method I must add hashCode()
 */
package quidditch;

import java.util.Arrays;
import java.util.Objects;


public class Team {
  private String  house, keeper, seeker;
  private String[]chasers;
  private static final String POSITION_CHASER = "chaser";
  private static final String POSITION_SEEKER = "seeker";
  private static final String POSITION_KEEPER = "keeper";

  
  public Team(String  house,String keeper,String seeker,String[] chasers){
      if(house == null || keeper == null || seeker == null){
          throw new IllegalArgumentException("feilds cannot be null");
          
      }
      if(house.isEmpty() || keeper.isEmpty() || seeker.isEmpty()){
          throw new IllegalArgumentException("feilds cannot be empty");   
      }
      
      if(chasers.length != 3){
          throw new IllegalArgumentException("must have three chasers");   

      }
      
      if(hasNull(chasers) || isNull(chasers)){
          throw new IllegalArgumentException("Illegal Elements");   

      }
      this.house = house;
      this.keeper = keeper;
      this.seeker = seeker;
      this.chasers = Arrays.copyOf(chasers, chasers.length);
       
  }
  
   public Team(Team source){
      this.house = source.house;
      this.keeper = source.keeper;
      this.seeker = source.seeker;
      this.chasers = Arrays.copyOf(source.chasers, source.chasers.length);
       
  }
   
   public String getHouse(){
       return this.house;
   }
   
   public String getKeeper(){
       return this.keeper;
   }
   
    public String getSeeker(){
       return this.seeker;
   }
    
    public String[] getChasers(){
       return Arrays.copyOf(chasers, chasers.length);
   }
    
    public void setHouse(String house){
       checkParameter(house);
        this.house = house;
    }
    
    public void setKeeper(String keeper){
       checkParameter(keeper);
        this.keeper = keeper;
    
    }
    
    public void setSeeker(String seeker){
        checkParameter(seeker);
        this.seeker = seeker;
    }
    
    
    public void setChasers(String[] chasers){
        if(chasers.length != 3 || hasNull(chasers) || isNull(chasers)){
          throw new IllegalArgumentException("must have three chasers");   

      }
        this.chasers = Arrays.copyOf(chasers, chasers.length);
    }
    
    public static boolean hasNull(String[] array){
     return Arrays.stream(array).anyMatch((element) -> element == null);
    }
    
    public static boolean isNull(String[] array){
     return Arrays.stream(array).anyMatch((element) -> element.isEmpty());
    }
    
    public void checkParameter(String field){
        if(field == null || field.isEmpty()){
            throw new IllegalArgumentException(field+" cannot be null or empty");
        }
    }
    public String toString(){
        return "House: " + this.house + "\n" +
          "Keeper: " + this.keeper + "\n" +         
          "Seeker: "  + this.seeker + "\n" +         
          "Chasers: " + Arrays.toString(this.chasers) + "\n";
    }
    
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(!(obj instanceof Team)){
         return false;
       }
        
       Team team =(Team) obj;
       return this.house.equals(team.house) && this.keeper.equals(team.keeper) && this.seeker.equals(team.seeker)
               && Arrays.toString(this.chasers).equals(Arrays.toString(team.chasers));
    }
    
     public int hashCode(){//it does not work with arrays , so use the string representation of the arrays..
        return Objects.hash(house,keeper,seeker,Arrays.toString(this.chasers));
    }
     
     public static String getPositionChaser(){
         return POSITION_CHASER;
     }
     
     public static String getPositionSeeker(){
         return POSITION_SEEKER;
     }
     
     public static String getPositionKeeper(){
         return POSITION_KEEPER;
     }
}
