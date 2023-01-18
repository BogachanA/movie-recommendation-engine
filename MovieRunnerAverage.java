import java.util.*;
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerAverage {
    
    private ArrayList<Rating> sortRatingList(ArrayList<Rating> allRatings){
        ArrayList<Rating> ratings=new ArrayList<Rating>(allRatings);
        boolean swapOccured=true; //to check if there is any more sorting to do
        while(swapOccured){
            swapOccured=false;
            for(int i=0;i<ratings.size()-1;i++){
                if(ratings.get(i).getValue()>ratings.get(i+1).getValue()){
                    Rating currRating=ratings.get(i);
                    ratings.set(i,ratings.get(i+1));
                    ratings.set(i+1,currRating);
                    swapOccured=true;
                }
            }
        }
        return ratings;
    }
    public void printAverageRatings(){
        String moviefile="data/"+"ratedmoviesfull.csv";
        String raterfile="data/"+"ratings.csv";
        SecondRatings sec=new SecondRatings(moviefile,raterfile);
        //System.out.println("Movie count: "+sec.getMovieSize()+" | Rating count: "+sec.getRaterSize()+"\n");
        
        int minimumRating=12;
        ArrayList<Rating> sortedAvgRatings=sortRatingList(sec.getAverageRatings(minimumRating));
        /*for(int index=0;index<sortedAvgRatings.size();index++){
            Rating rate=sortedAvgRatings.get(index);
            System.out.println(rate.getValue()+" "+sec.getTitle(rate.getItem()));
        }
        */
       System.out.println("Movies with at least "+minimumRating+" raters: "+sortedAvgRatings.size());
       System.out.println(sec.getTitle(sortedAvgRatings.get(0).getItem()));
       System.out.println();
    }
    public void getAverageRatingOneMovie(){
        String moviefile="data/"+"ratedmoviesfull.csv";
        String raterfile="data/"+"ratings.csv";
        SecondRatings sec=new SecondRatings(moviefile,raterfile);
        
        String movieTitle="Vacation";
        System.out.println("The average rating for movie \""+movieTitle+"\" is: "+
                           sec.getAverageByID(sec.getID(movieTitle),1)+"\n");
    }
}
