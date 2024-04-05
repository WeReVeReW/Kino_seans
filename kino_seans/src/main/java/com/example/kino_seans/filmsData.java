package com.example.kino_seans;

public class filmsData {

    private Integer id;
    private String kinoteatr_name;
    private String film_name;
    private String film_director;

    private String film_operator;
    private String film_actors;
    private String film_style;
    private String film_studio;

    public  filmsData(Integer id, String film_name, String film_director, String film_operator, String film_actors, String film_style, String film_studio, String kinoteatr_name){

        this.id=id;
        this.kinoteatr_name=kinoteatr_name;
        this.film_name=film_name;
        this.film_director=film_director;
        this.film_operator=film_operator;
        this.film_actors=film_actors;
        this.film_style=film_style;
        this.film_studio=film_studio;

    }

    public Integer getId() {
        return id;
    }

    public String getKinoteatr_name() {
        return kinoteatr_name;
    }

    public String getFilm_name() {
        return film_name;
    }

    public String getFilm_director() {
        return film_director;
    }

    public String getFilm_operator() {
        return film_operator;
    }

    public String getFilm_actors() {
        return film_actors;
    }

    public String getFilm_style() {
        return film_style;
    }

    public String getFilm_studio() {
        return film_studio;
    }
}
