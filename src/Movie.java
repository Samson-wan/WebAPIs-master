public class Movie {

    private String title;
    private int id;
    private String posterPath;

    public Movie(String title, int id, String posterPath)
    {
        this.title = title;
        this.id = id;
        this.posterPath = posterPath;
    }

    public String getTitle()
    {
        return title;
    }

    public int getID()
    {
        return id;
    }

    public String getPosterPath()
    {
        return posterPath;
    }
}