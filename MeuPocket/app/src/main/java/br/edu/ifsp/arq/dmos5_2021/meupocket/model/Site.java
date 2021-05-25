package br.edu.ifsp.arq.dmos5_2021.meupocket.model;

public class Site {
    private String title;
    private String url;
    private boolean favorite;

    public Site(String title, String url) {
        this.title = title;
        this.url = url;
        this.favorite = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title.toUpperCase();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url.toUpperCase();
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
