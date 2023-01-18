
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GenreFilter implements Filter {
    private String filterGenre;
    public GenreFilter(String genre){
        filterGenre=genre;
    }
    
    @Override
    public boolean satisfies(String id){
        return MovieDatabase.getGenres(id).indexOf(filterGenre)!=-1;
    }
}
