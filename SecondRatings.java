
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    public SecondRatings(String moviefile, String ratingsfile){
        FirstRatings first=new FirstRatings();
        myMovies=first.loadMovies(moviefile);
        myRaters=first.loadRaters(ratingsfile);
    }
    public int getMovieSize(){
        return myMovies.size();
    }
    public int getRaterSize(){
        return myRaters.size();
    }
    public double getAverageByID(String id, int minimalRaters){
        double totalPoints=0;
        int raterCount=0;
        Movie rated;
        for(Movie mov:myMovies){
            if(mov.getID().equals(id)){
                rated=mov;
            }
        }
        
        for(Rater rater:myRaters){
            if(rater.hasRating(id)){
                raterCount++;
                totalPoints+=rater.getRating(id);
            }
        }
        
        if(raterCount>=minimalRaters){
            double avgRatings = totalPoints / raterCount;
            return avgRatings;
        }else{
            return 0.0;
        }
    }
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> averages=new ArrayList<Rating>();
        for(Movie mov:myMovies){
            String movID=mov.getID();
            double avgRating=getAverageByID(movID,minimalRaters);
            if(avgRating!=0.0){
                Rating ratedMovieRating=new Rating(movID,avgRating);
                averages.add(ratedMovieRating);
            }
        }
        return averages;
    }
    public String getTitle(String id){
        for(Movie mov:myMovies){
            if(mov.getID().equals(id)){
                return mov.getTitle();
            }
        }
        return "There doesn't exist a movie with id: "+id+"\n";
    }
    public String getID(String title){
        for(Movie mov:myMovies){
            if(mov.getTitle().equals(title)){
                return mov.getID();
            }
        }
        return "NO SUCH TITLE: "+title+"\n";
    }
}
