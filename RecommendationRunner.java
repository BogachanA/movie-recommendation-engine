
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class RecommendationRunner implements Recommender {
    public ArrayList<String> getItemsToRate(){
        Random rand = new Random();
        ArrayList<String> moviesToRate=new ArrayList<String>();
        
        String moviefile="ratedmoviesfull.csv";
        MovieDatabase.initialize(moviefile);
        ArrayList<String> allMovies=MovieDatabase.filterBy(new TrueFilter());
        
        int prevIndex=0;
        for(int i=0;i<15;i++){
            int randomIndex=rand.nextInt((i) + 1) + prevIndex+1;
            moviesToRate.add(allMovies.get(randomIndex));
            prevIndex=randomIndex;
        }
        return moviesToRate;
    }
    public void printRecommendationsFor(String webRaterID){
        String moviefile="ratedmoviesfull.csv";
        String raterfile="ratings.csv";
        
        MovieDatabase.initialize(moviefile);
        
        RaterDatabase.initialize(raterfile);
        
        FourthRatings fourth=new FourthRatings();
        
        String raterID=webRaterID;
        int minimalRaters=1;
        int topSimilarRaters=5;
        ArrayList<Rating> similarRatings=fourth.getSimilarRatings(raterID,topSimilarRaters,minimalRaters);
        
        if(similarRatings.size()==0){
            System.out.println("<h1 style='margin-top:50px;'>Sorry,no recommendations for you today!</h1>");
            System.out.println("<h4 style='margin-top:50px;'>Please try to change the minimalRaters variable.</h4>");
        }
        else{
            System.out.println("<style>th{border:2px solid black;} td{border:1px solid black;}</style>");
            System.out.println("<h1 style='color:red;margin:40px 20px;'>Here are your personalized recommendations:</h1>");
            System.out.println("<table style='border: 3px dashed black;'>");
            System.out.println("<tr style='color:green'>");
            System.out.println("<th>Movie Title</th>");
            System.out.println("<th>Genres</th>");
            System.out.println("<th>Director(s)</th>");
            System.out.println("</tr>");
            for(int i=0;i<12;i++){
                System.out.println("<tr style='color:gray'>");
                Rating curr=similarRatings.get(i);
                System.out.println("<td style='color:black'>"+MovieDatabase.getTitle(curr.getItem())+"</td>");
                System.out.println("<td>"+MovieDatabase.getCountry(curr.getItem())+"</td>");
                System.out.println("<td>"+MovieDatabase.getGenres(curr.getItem())+"</td>");
                System.out.println("</tr>");
            }
            System.out.println("</table>");
        }
    }
}
