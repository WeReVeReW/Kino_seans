package com.example.kino_seans;

public class kinoteatrsData {
    private Integer id;
    private String kinoteatr_name;
    private String kinoteatr_adres;
    private String kinoteatr_category;

    private String kinoteatr_sum_places;
    private String kinoteatr_sum_room;
    private String kinoteatr_status;

    public  kinoteatrsData(Integer id, String kinoteatr_name, String kinoteatr_adres, String kinoteatr_category, String kinoteatr_sum_places, String kinoteatr_sum_room, String kinoteatr_status){

        this.id=id;
        this.kinoteatr_name=kinoteatr_name;
        this.kinoteatr_adres=kinoteatr_adres;
        this.kinoteatr_category=kinoteatr_category;
        this.kinoteatr_sum_places=kinoteatr_sum_places;
        this.kinoteatr_sum_room=kinoteatr_sum_room;
        this.kinoteatr_status=kinoteatr_status;

    }

    public String getKinoteatr_name() {
        return kinoteatr_name;
    }

    public String getKinoteatr_adres() {
        return kinoteatr_adres;
    }

    public String getKinoteatr_category() {
        return kinoteatr_category;
    }

    public Integer getId() {
        return id;
    }

    public String getKinoteatr_sum_places() {
        return kinoteatr_sum_places;
    }

    public String getKinoteatr_sum_room() {
        return kinoteatr_sum_room;
    }

    public String getKinoteatr_status() {
        return kinoteatr_status;
    }
}
