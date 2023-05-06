/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author josia
 */
public class MovieSeries {
    private String title;
    private int episodes;
    private String type;
    private int watchStatus;
    private String genre;
    private String synopsis;

    public MovieSeries(String title, int episodes, String type, String genre, String synopsis) {
        this.title = title;
        this.episodes = episodes;
        this.type = type;
        this.watchStatus = 0;
        this.genre = genre;
        this.synopsis = synopsis;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public int getWatchStatus() {
        return watchStatus;
    }

    public void setWatchStatus(int watchStatus) {
        this.watchStatus = watchStatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
}
