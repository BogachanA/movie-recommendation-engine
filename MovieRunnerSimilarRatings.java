
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerSimilarRatings {
    private ArrayList<Rating> sortRatingList(ArrayList<Rating> allRatings){
        //Sorting method with swap sort(?)
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
        String moviefile="ratedmoviesfull.csv";
        String raterfile="ratings.csv";
        
        MovieDatabase.initialize(moviefile);
        System.out.println("Movie database size: "+MovieDatabase.size());
        
        RaterDatabase.initialize(raterfile);
        System.out.println("Rater count: "+RaterDatabase.size());
        
        FourthRatings fourth=new FourthRatings();
        
        int minimumRating=35;
        ArrayList<Rating> sortedAvgRatings=sortRatingList(fourth.getAverageRatings(minimumRating));
        /*
        for(int index=0;index<sortedAvgRatings.size();index++){
            Rating rate=sortedAvgRatings.get(index);
            System.out.println(rate.getValue()+" "+MovieDatabase.getTitle(rate.getItem()));
        }
        */
        
        System.out.println("Movies with at least "+minimumRating+" raters: "+sortedAvgRatings.size());
        //System.out.println(sec.getTitle(sortedAvgRatings.get(0).getItem()));
        
        System.out.println();//For clearer reading of the output
    }
    public void printAverageRatingsByYearAfterAndGenre(){
        String moviefile="ratedmoviesfull.csv";
        String raterfile="ratings.csv";
        
        MovieDatabase.initialize(moviefile);
        System.out.println("Movie database size: "+MovieDatabase.size());
        
        RaterDatabase.initialize(raterfile);
        System.out.println("Rater count: "+RaterDatabase.size());
        
        FourthRatings fourth=new FourthRatings();
        
        int yearMin=1990;
        YearAfterFilter yf=new YearAfterFilter(yearMin);
        
        String targetGenre="Drama";
        GenreFilter gf=new GenreFilter(targetGenre);
        
        AllFilters allF=new AllFilters();
        allF.addFilter(yf);
        allF.addFilter(gf);
        
        int minimumRating=8;
        ArrayList<Rating> sortedAvgRatings=sortRatingList(fourth.getAverageRatingsByFilter(minimumRating,allF));
        System.out.println("Found movie count with the active filter(s): "+sortedAvgRatings.size());
        /*
        for(int index=0;index<sortedAvgRatings.size();index++){
            Rating rate=sortedAvgRatings.get(index);
            System.out.println(rate.getValue()+" ("+MovieDatabase.getYear(rate.getItem())+") "+MovieDatabase.getTitle(rate.getItem()));
            System.out.println("    "+MovieDatabase.getGenres(rate.getItem()));
        }
        */
        System.out.println();//For clearer reading of the output
    }
    public void printSimilarRatings(){
        String moviefile="ratedmoviesfull.csv";
        String raterfile="ratings.csv";
        
        MovieDatabase.initialize(moviefile);
        System.out.println("Movie database size: "+MovieDatabase.size());
        
        RaterDatabase.initialize(raterfile);
        System.out.println("Rater count: "+RaterDatabase.size());
        
        FourthRatings fourth=new FourthRatings();
        
        String raterID="71";
        int minimalRaters=5;
        int topSimilarRaters=20;
        ArrayList<Rating> similarRatings=fourth.getSimilarRatings(raterID,topSimilarRaters,minimalRaters);
        for(int i=0;i<5;i++){
            Rating curr=similarRatings.get(i);
            System.out.println(MovieDatabase.getTitle(curr.getItem())+" "+curr.getValue());
        }
        System.out.println();//For clearer reading of the output
    }
    public void printSimilarRatingsByGenre(){
        String moviefile="ratedmoviesfull.csv";
        String raterfile="ratings.csv";
        
        MovieDatabase.initialize(moviefile);
        System.out.println("Movie database size: "+MovieDatabase.size());
        
        RaterDatabase.initialize(raterfile);
        System.out.println("Rater count: "+RaterDatabase.size());
        
        FourthRatings fourth=new FourthRatings();
        
        String raterID="964";
        int minimalRaters=5;
        int topSimilarRaters=20;
        
        String genreFilter="Mystery";
        GenreFilter gf=new GenreFilter(genreFilter);
        
        ArrayList<Rating> similarRatings=fourth.getSimilarRatingsByFilter(raterID,topSimilarRaters,minimalRaters,gf);
        for(int i=0;i<5;i++){
            Rating curr=similarRatings.get(i);
            System.out.println(MovieDatabase.getTitle(curr.getItem())+" "+curr.getValue());
            System.out.println("    "+MovieDatabase.getGenres(curr.getItem()));
        }
        System.out.println();//For clearer reading of the output
    }
    public void printSimilarRatingsByDirector(){
        String moviefile="ratedmoviesfull.csv";
        String raterfile="ratings.csv";
        
        MovieDatabase.initialize(moviefile);
        System.out.println("Movie database size: "+MovieDatabase.size());
        
        RaterDatabase.initialize(raterfile);
        System.out.println("Rater count: "+RaterDatabase.size());
        
        FourthRatings fourth=new FourthRatings();
        
        String raterID="120";
        int minimalRaters=2;
        int topSimilarRaters=10;
        
        String directors="Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
        DirectorsFilter df=new DirectorsFilter(directors);
        
        ArrayList<Rating> similarRatings=fourth.getSimilarRatingsByFilter(raterID,topSimilarRaters,minimalRaters,df);
        for(int i=0;i<2;i++){
            Rating curr=similarRatings.get(i);
            System.out.println(MovieDatabase.getTitle(curr.getItem())+" "+curr.getValue());
            System.out.println("    "+MovieDatabase.getDirector(curr.getItem()));
        }
        System.out.println();//For clearer reading of the output
    }
    public void printSimilarRatingsByGenreAndMinutes(){
        String moviefile="ratedmoviesfull.csv";
        String raterfile="ratings.csv";
        
        MovieDatabase.initialize(moviefile);
        System.out.println("Movie database size: "+MovieDatabase.size());
        
        RaterDatabase.initialize(raterfile);
        System.out.println("Rater count: "+RaterDatabase.size());
        
        FourthRatings fourth=new FourthRatings();
        
        String raterID="168";
        int minimalRaters=3;
        int topSimilarRaters=10;
        
        int minMin=80;
        int maxMin=160;
        MinutesFilter mf=new MinutesFilter(minMin,maxMin);
        String genre="Drama";
        GenreFilter gf=new GenreFilter(genre);
        
        AllFilters af=new AllFilters();
        af.addFilter(mf);
        af.addFilter(gf);
        
        ArrayList<Rating> similarRatings=fourth.getSimilarRatingsByFilter(raterID,topSimilarRaters,minimalRaters,af);
        for(int i=0;i<2;i++){
            Rating curr=similarRatings.get(i);
            System.out.println(MovieDatabase.getTitle(curr.getItem())+" ("+MovieDatabase.getMinutes(curr.getItem())+" min) "+curr.getValue());
            System.out.println("    "+MovieDatabase.getGenres(curr.getItem()));
        }
        System.out.println();//For clearer reading of the output
    }
    public void printSimilarRatingsByYearAfterAndMinutes(){
        String moviefile="ratedmoviesfull.csv";
        String raterfile="ratings.csv";
        
        MovieDatabase.initialize(moviefile);
        System.out.println("Movie database size: "+MovieDatabase.size());
        
        RaterDatabase.initialize(raterfile);
        System.out.println("Rater count: "+RaterDatabase.size());
        
        FourthRatings fourth=new FourthRatings();
        
        String raterID="314";
        int minimalRaters=5;
        int topSimilarRaters=10;
        
        int minMin=70;
        int maxMin=200;
        MinutesFilter mf=new MinutesFilter(minMin,maxMin);
        int yearafter=1975;
        YearAfterFilter yf=new YearAfterFilter(yearafter);
        
        AllFilters af=new AllFilters();
        af.addFilter(mf);
        af.addFilter(yf);
        
        ArrayList<Rating> similarRatings=fourth.getSimilarRatingsByFilter(raterID,topSimilarRaters,minimalRaters,af);
        for(int i=0;i<2;i++){
            Rating curr=similarRatings.get(i);
            System.out.println(MovieDatabase.getTitle(curr.getItem())+" ("+MovieDatabase.getMinutes(curr.getItem())+" min) "+curr.getValue());
            System.out.println("    Year: "+MovieDatabase.getYear(curr.getItem()));
        }
        System.out.println();//For clearer reading of the output
    }
}
