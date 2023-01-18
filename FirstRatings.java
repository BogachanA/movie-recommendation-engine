/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> moviesFromFile=new ArrayList<Movie>();
        FileResource file=new FileResource(filename);
        for (CSVRecord rec: file.getCSVParser(true)){
            String id=rec.get("id");
            String title=rec.get("title");
            String year=rec.get("year");
            String country=rec.get("country");
            String genres=rec.get("genre");
            String director=rec.get("director");
            int minutes=Integer.parseInt(rec.get("minutes"));
            String posterURL=rec.get("poster");
            
            Movie newMovie=new Movie(id,title,year,
                                     genres,director,country,posterURL,minutes);
            moviesFromFile.add(newMovie);
        }
        
        return moviesFromFile;
    }
    
    public ArrayList<Rater> loadRaters(String filename){
        FileResource file=new FileResource(filename);
        ArrayList<Rater> ratersFromFile=new ArrayList<Rater>();
        HashMap<String,Rater> usedIds=new HashMap<String,Rater>();
        for (CSVRecord rec:file.getCSVParser(true)){
            String raterID=rec.get("rater_id");
            String ratedItem=rec.get("movie_id");
            double rating=Double.parseDouble(rec.get("rating"));
            if(!usedIds.containsKey(raterID)){
                Rater newRater=new EfficientRater(raterID);
                newRater.addRating(ratedItem, rating);
                usedIds.put(raterID, newRater);
            }else{
                Rater oldRater=usedIds.get(raterID);
                oldRater.addRating(ratedItem,rating);
            }
        }
        for (Rater rat:usedIds.values()){
            ratersFromFile.add(rat);
        }
        return ratersFromFile;
    }
    
    public void testLoadMovies(){
        String movieFile="data/" + "ratedmoviesfull.csv";
        ArrayList<Movie> movies=loadMovies(movieFile);
        
        int countForGenre=0;
        String searchGenre="Comedy";
        int longerMovies=0;
        int longerThan=150;
        HashMap<String,Integer> directorMovies=new HashMap<String,Integer>();
        for(Movie mov: movies){
            //System.out.println(mov);
            if(mov.getGenres().indexOf(searchGenre)!=-1){
                countForGenre++;
            }
            if(mov.getMinutes()>longerThan){
                longerMovies++;
            }
            
            String[] directors=mov.getDirector().split(", ");
            for(String director:directors){
                if(!directorMovies.containsKey(director)){
                    directorMovies.put(director,1);
                } else {
                    directorMovies.put(director,directorMovies.get(director)+1); 
                }
            }
        }
        
        //Get the director with the greatest amount of movie
        int maxMovieCount=0;
        int maxMovieDirectorCount=0;
        ArrayList<String> maxMovieDirectors=new ArrayList<String>();
        for (int movieCount: directorMovies.values()){
            if(movieCount>maxMovieCount){
                maxMovieCount=movieCount;
            }
        }
        for (String director: directorMovies.keySet()){
            int directorMovieCount=directorMovies.get(director);
            if(directorMovieCount==maxMovieCount){
                maxMovieDirectors.add(director);
                maxMovieDirectorCount++;
            }
        }
        
        System.out.println("\nMovie count: "+movies.size()+
                           "\nNumber of movies of the genre "+searchGenre+
                           ": "+countForGenre+"\nMovies longer than "+
                           longerThan+" minutes: "+longerMovies+
                           "\nMaximum number of movies from a director: "+
                           maxMovieCount+"\nNumber of directors with that much movie:"+
                           maxMovieDirectorCount+"\n Directors with that amount of film:");
        for(String dir:maxMovieDirectors){
            System.out.print(dir);
        }
        System.out.println("\n");
    }
    
    public void testLoadRaters(){
        String raterFile="data/"+"ratings.csv";
        ArrayList<Rater> allRaters=loadRaters(raterFile);
        
        //Filter rater
        String raterID="193";
        
        //Get maximum rate count
        int maxRateCount=0;
        ArrayList<Rater> manyRaters=new ArrayList<Rater>();
        
        //Filter ratings by movie
        String movieID="1798709";
        int ratingOfMovie=0;
        
        //Find how many different movies were rated
        ArrayList<String> ratedMovies=new ArrayList<String>();
        for (Rater rater:allRaters){
            //System.out.println("Rater id: "+rater.getID()+" | Rating count: "+rater.numRatings());
            
            ArrayList<String> ratedItemsOfRater=rater.getItemsRated();
            for (String item:ratedItemsOfRater){
                if(item.equals(movieID)){
                    ratingOfMovie++;
                }
                if(!ratedMovies.contains(item)){
                    ratedMovies.add(item);
                }
                
                /* Print all ratings of rater
                double currRating=rater.getRating(item);
                System.out.println("ID of movie rated: "+item+" § Rating given: "+currRating);
                */
            }
            
            
            if(rater.numRatings()>maxRateCount){
               maxRateCount=rater.numRatings();
            }
            if(rater.getID().equals(raterID)){
               System.out.println("Rate count for rater with id "+raterID+": "+rater.numRatings());
            }   
        }
        
        for(Rater rater:allRaters){
            if(rater.numRatings()==maxRateCount){
                manyRaters.add(rater);
            }
        }
        System.out.println("\nNumber or raters total: "+allRaters.size());
        System.out.println("Total of different movies rated: "+ratedMovies.size());
        System.out.println("The movie with id '"+movieID+"' has been rated by "+ratingOfMovie+" raters");
        System.out.println("Maximum rate count: "+maxRateCount+" Ω Number of raters with that many ratings: "+
                           manyRaters.size()+" \nRater id's with that many ratings:");
        for(Rater manyRater:manyRaters){
            System.out.print(manyRater.getID());
        }
        System.out.println("\n");
    }
}
