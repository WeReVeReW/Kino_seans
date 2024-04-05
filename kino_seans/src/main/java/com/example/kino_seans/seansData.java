package com.example.kino_seans;

public class seansData {
    private Integer id;
    private String kino_name;
    private String film_name;
    private String session_date;
    private String session_time;

    public Integer getId() {
        return id;
    }

    public String getKino_name() {
        return kino_name;
    }

    public String getFilm_name() {
        return film_name;
    }

    public String getSession_date() {
        return session_date;
    }

    public String getSession_time() {
        return session_time;
    }

    public String getSession_freeplace() {
        return session_freeplace;
    }

    public String getSession_price() {
        return session_price;
    }

    private String session_freeplace;
    private String session_price;


    public  seansData(Integer id, String kino_name,String film_name, String session_date, String session_time, String session_freeplace, String session_price){

        this.id=id;
        this.kino_name=kino_name;
        this.film_name=film_name;
        this.session_date=session_date;
        this.session_time=session_time;
        this.session_freeplace=session_freeplace;
        this.session_price=session_price;

    }
}
