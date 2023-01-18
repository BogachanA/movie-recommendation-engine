
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter {
    private String[] directors;
    
    public DirectorsFilter(String commaDirectors){
        directors=commaDirectors.split(",");
    }
    
    @Override
    public boolean satisfies(String id){
        String currentDirectors=MovieDatabase.getDirector(id);
        for(String director:directors){
            if(currentDirectors.indexOf(director)!=-1){
                return true;
            }
        }
        return false;
    }
}
