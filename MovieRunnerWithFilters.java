
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerWithFilters {
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
        String raterfile="data/"+"ratings.csv";
        
        ThirdRatings third=new ThirdRatings(raterfile);
        System.out.println("Rater count: "+third.getRaterSize());
        
        MovieDatabase.initialize(moviefile);
        System.out.println("Movie database size: "+MovieDatabase.size());
        
        int minimumRating=35;
        ArrayList<Rating> sortedAvgRatings=sortRatingList(third.getAverageRatings(minimumRating));
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
    public void printAverageRatingsByYear(){
        String moviefile="ratedmoviesfull.csv";
        String raterfile="data/"+"ratings.csv";
        
        ThirdRatings third=new ThirdRatings(raterfile);
        System.out.println("Rater count: "+third.getRaterSize());
        
        MovieDatabase.initialize(moviefile);
        System.out.println("Movie database size: "+MovieDatabase.size());
        
        int yearAfter=2000;
        YearAfterFilter yf=new YearAfterFilter(yearAfter);
        
        int minimumRating=20;
        ArrayList<Rating> sortedAvgRatings=sortRatingList(third.getAverageRatingsByFilter(minimumRating,yf));
        System.out.println("Found movie count with the active filter(s): "+sortedAvgRatings.size());
        /*
        for(int index=0;index<sortedAvgRatings.size();index++){
            Rating rate=sortedAvgRatings.get(index);
            System.out.println(rate.getValue()+" "+MovieDatabase.getYear(rate.getItem())+" "+MovieDatabase.getTitle(rate.getItem()));
        }
        */
        
        System.out.println();//For clearer reading of the output
    }
    public void printAverageRatingsByGenre(){
        String moviefile="ratedmoviesfull.csv";
        String raterfile="data/"+"ratings.csv";
        
        ThirdRatings third=new ThirdRatings(raterfile);
        System.out.println("Rater count: "+third.getRaterSize());
        
        MovieDatabase.initialize(moviefile);
        System.out.println("Movie database size: "+MovieDatabase.size());
        
        String targetGenre="Comedy";
        GenreFilter gf=new GenreFilter(targetGenre);
        
        int minimumRating=20;
        ArrayList<Rating> sortedAvgRatings=sortRatingList(third.getAverageRatingsByFilter(minimumRating,gf));
        System.out.println("Found movie count with the active filter(s): "+sortedAvgRatings.size());
        /*
        for(int index=0;index<sortedAvgRatings.size();index++){
            Rating rate=sortedAvgRatings.get(index);
            System.out.println(rate.getValue()+" "+MovieDatabase.getTitle(rate.getItem()));
            System.out.println("   "+MovieDatabase.getGenres(rate.getItem()));
        }
        */
        System.out.println();//For clearer reading of the output
    }
    public void printAverageRatingsByMinutes(){
        String moviefile="ratedmoviesfull.csv";
        String raterfile="data/"+"ratings.csv";
        
        ThirdRatings third=new ThirdRatings(raterfile);
        System.out.println("Rater count: "+third.getRaterSize());
        
        MovieDatabase.initialize(moviefile);
        System.out.println("Movie database size: "+MovieDatabase.size());
        
        int minDuration=105;
        int maxDuration=135;
        MinutesFilter mf=new MinutesFilter(minDuration,maxDuration);
        
        int minimumRating=5;
        ArrayList<Rating> sortedAvgRatings=sortRatingList(third.getAverageRatingsByFilter(minimumRating,mf));
        System.out.println("Found movie count with the active filter(s): "+sortedAvgRatings.size());
        /*
        for(int index=0;index<sortedAvgRatings.size();index++){
            Rating rate=sortedAvgRatings.get(index);
            System.out.println(rate.getValue()+" -("+MovieDatabase.getMinutes(rate.getItem())+" min)- "+MovieDatabase.getTitle(rate.getItem()));
        }
        */
        System.out.println();//For clearer reading of the output
    }
    public void printAverageRatingsByDirectors(){
        String moviefile="ratedmoviesfull.csv";
        String raterfile="data/"+"ratings.csv";
        
        ThirdRatings third=new ThirdRatings(raterfile);
        System.out.println("Rater count: "+third.getRaterSize());
        
        MovieDatabase.initialize(moviefile);
        System.out.println("Movie database size: "+MovieDatabase.size());
        
        String commaDirectors="Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        DirectorsFilter df=new DirectorsFilter(commaDirectors);
        
        int minimumRating=4;
        ArrayList<Rating> sortedAvgRatings=sortRatingList(third.getAverageRatingsByFilter(minimumRating,df));
        System.out.println("Found movie count with the active filter(s): "+sortedAvgRatings.size());
        /*
        for(int index=0;index<sortedAvgRatings.size();index++){
            Rating rate=sortedAvgRatings.get(index);
            System.out.println(rate.getValue()+MovieDatabase.getTitle(rate.getItem()));
            System.out.println("    "+MovieDatabase.getDirector(rate.getItem()));
        }
        */
        System.out.println();//For clearer reading of the output
    }
    public void printAverageRatingsByYearAfterAndGenre(){
        String moviefile="ratedmoviesfull.csv";
        String raterfile="data/"+"ratings.csv";
        
        ThirdRatings third=new ThirdRatings(raterfile);
        System.out.println("Rater count: "+third.getRaterSize());
        
        MovieDatabase.initialize(moviefile);
        System.out.println("Movie database size: "+MovieDatabase.size());
        
        int yearMin=1990;
        YearAfterFilter yf=new YearAfterFilter(yearMin);
        
        String targetGenre="Drama";
        GenreFilter gf=new GenreFilter(targetGenre);
        
        AllFilters allF=new AllFilters();
        allF.addFilter(yf);
        allF.addFilter(gf);
        
        int minimumRating=8;
        ArrayList<Rating> sortedAvgRatings=sortRatingList(third.getAverageRatingsByFilter(minimumRating,allF));
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
    public void printAverageRatingsByDirectorsAndMinutes (){
        String moviefile="ratedmoviesfull.csv";
        String raterfile="data/"+"ratings.csv";
        
        ThirdRatings third=new ThirdRatings(raterfile);
        System.out.println("Rater count: "+third.getRaterSize());
        
        MovieDatabase.initialize(moviefile);
        System.out.println("Movie database size: "+MovieDatabase.size());
        
        int minMin=90;
        int maxMin=180;
        MinutesFilter mf=new MinutesFilter(minMin,maxMin);
        
        String targetDirectors="Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        DirectorsFilter df=new DirectorsFilter(targetDirectors);
        
        AllFilters allF=new AllFilters();
        allF.addFilter(df);
        allF.addFilter(mf);
        
        int minimumRating=3;
        ArrayList<Rating> sortedAvgRatings=sortRatingList(third.getAverageRatingsByFilter(minimumRating,allF));
        System.out.println("Found movie count with the active filter(s): "+sortedAvgRatings.size());
        /*
        for(int index=0;index<sortedAvgRatings.size();index++){
            Rating rate=sortedAvgRatings.get(index);
            System.out.println(rate.getValue()+" ("+MovieDatabase.getMinutes(rate.getItem())+" min) "+MovieDatabase.getTitle(rate.getItem()));
            System.out.println("    "+MovieDatabase.getDirector(rate.getItem()));
        }
        */
        System.out.println();//For clearer reading of the output
    }
}
