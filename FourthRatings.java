
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class FourthRatings {
    public FourthRatings(){}
    public double getAverageByID(String id, int minimalRaters){
        double totalPoints=0;
        int raterCount=0;
        
        for(Rater rater:RaterDatabase.getRaters()){
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
    private int dotProduct(Rater me, Rater r){
        int totalDotProduct=0;
        for(String movieID:me.getItemsRated()){
            if(r.hasRating(movieID)){
                int myRatingScaled=((int)me.getRating(movieID))-5;
                int rRatingScaled=((int)r.getRating(movieID))-5;
                totalDotProduct+=myRatingScaled*rRatingScaled;
            }
        }
        return totalDotProduct;
    }
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> ratingOfOtherRaters=new ArrayList<Rating>();
        Rater me=RaterDatabase.getRater(id);
        for(Rater r:RaterDatabase.getRaters()){
            String raterID=r.getID();
            if(!raterID.equals(id)){
                int similarityResult=dotProduct(me,r);
                if(similarityResult>0){
                    ratingOfOtherRaters.add(new Rating(raterID,similarityResult));
                }
            }
        }
        Collections.sort(ratingOfOtherRaters, Collections.reverseOrder());
        return ratingOfOtherRaters;
    }
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> weightedMovies=new ArrayList<Rating>();
        ArrayList<Rating> similarRaters=getSimilarities(id);
        for(String movieID:MovieDatabase.filterBy(new TrueFilter())){
            double weightedAvg=0;
            int minimalSimilarRaterCounter=0;
            for(int i=0;i<numSimilarRaters;i++){
                Rater similarRater=RaterDatabase.getRater(similarRaters.get(i).getItem());
                if(similarRater.hasRating(movieID)){
                    minimalSimilarRaterCounter++;
                    weightedAvg+=similarRater.getRating(movieID)*similarRaters.get(i).getValue();
                }
            }
            if(minimalSimilarRaterCounter>=minimalRaters){
                weightedAvg/=minimalSimilarRaterCounter;
                weightedMovies.add(new Rating(movieID,weightedAvg));
            }
        }
        Collections.sort(weightedMovies, Collections.reverseOrder());
        return weightedMovies;
    }
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters,Filter f){
        ArrayList<Rating> weightedMovies=new ArrayList<Rating>();
        ArrayList<Rating> similarRaters=getSimilarities(id);
        for(String movieID:MovieDatabase.filterBy(f)){
            double weightedAvg=0;
            int minimalSimilarRaterCounter=0;
            for(int i=0;i<numSimilarRaters;i++){
                Rater similarRater=RaterDatabase.getRater(similarRaters.get(i).getItem());
                if(similarRater.hasRating(movieID)){
                    minimalSimilarRaterCounter++;
                    weightedAvg+=similarRater.getRating(movieID)*similarRaters.get(i).getValue();
                }
            }
            if(minimalSimilarRaterCounter>=minimalRaters){
                weightedAvg/=minimalSimilarRaterCounter;
                weightedMovies.add(new Rating(movieID,weightedAvg));
            }
        }
        Collections.sort(weightedMovies, Collections.reverseOrder());
        return weightedMovies;
    }
}
