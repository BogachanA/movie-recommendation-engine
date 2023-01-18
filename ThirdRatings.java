
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    public ThirdRatings(String ratingsfile){
        FirstRatings first=new FirstRatings();
        myRaters=first.loadRaters(ratingsfile);
    }
    public int getRaterSize(){
        return myRaters.size();
    }
    public double getAverageByID(String id, int minimalRaters){
        double totalPoints=0;
        int raterCount=0;
        
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String movID:movies){
            double avgRating=getAverageByID(movID,minimalRaters);
            if(avgRating!=0.0){
                Rating ratedMovieRating=new Rating(movID,avgRating);
                averages.add(ratedMovieRating);
            }
        }
        return averages;
    }
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> averages=new ArrayList<Rating>();
        ArrayList<String> filteredMovies=MovieDatabase.filterBy(filterCriteria);
        for(String movID:filteredMovies){
            double avgRating=getAverageByID(movID,minimalRaters);
            if(avgRating!=0.0){
                Rating ratedMovieRating=new Rating(movID,avgRating);
                averages.add(ratedMovieRating);
            }
        }
        return averages;
    }
}
