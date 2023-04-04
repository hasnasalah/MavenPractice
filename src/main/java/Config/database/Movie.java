package Config.database;

public class Movie {

    private int movieId;
    private String movieTitle;
    private int releaseYear;
    private String movieGenre;
    private String movieRating;

    public Movie() {
    }

    public Movie(int movieId) {
        this.movieId = movieId;
    }

    public Movie(int movieId, String movieTitle) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
    }

    public Movie(int movieId, String movieTitle, int releaseYear) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.releaseYear = releaseYear;
    }

    public Movie(int movieId, String movieTitle, int releaseYear, String movieGenre) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.releaseYear = releaseYear;
        this.movieGenre = movieGenre;
    }

    public Movie(int movieId, String movieTitle, int releaseYear, String movieGenre, String movieRating) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.releaseYear = releaseYear;
        this.movieGenre = movieGenre;
        this.movieRating = movieRating;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public String getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(String movieRating) {
        this.movieRating = movieRating;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieTitle=" + movieTitle +'\''+
                ", releaseYear=" + releaseYear +
                ", movieGenre=" + movieGenre +'\''+
                ", movieRating=" + movieRating  +'\''+
                '}';
    }


    public static void main(String[] args) {
        Movie movie= new Movie();
        System.out.println(movie.toString());

        String n= "Movie{movieId=0, movieTile=null', releaseYear=0, movieGenre=null', movieRating=null'}";
    }




}
