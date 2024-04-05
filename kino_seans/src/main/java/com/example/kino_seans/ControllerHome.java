package com.example.kino_seans;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControllerHome implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button close;

    @FXML
    private Button dobav_Btn;

    @FXML
    private TextField dobav_actorField;

    @FXML
    private Button dobav_clearBtn;

    @FXML
    private TableColumn<filmsData, String> dobav_col_Janr;

    @FXML
    private TableColumn<filmsData, String> dobav_col_Kinoteatr;

    @FXML
    private TableColumn<filmsData, String> dobav_col_Name;

    @FXML
    private TableColumn<filmsData, String> dobav_col_Actors;

    @FXML
    private TableColumn<filmsData, String> dobav_col_Operator;

    @FXML
    private TableColumn<filmsData, String> dobav_col_Rejiser;

    @FXML
    private TableColumn<filmsData, String> dobav_col_Studio;

    @FXML
    private ComboBox<String> dobav_vibor_Kinoteatr;

    @FXML
    private Button dobav_deleteBtn;

    @FXML
    private TextField dobav_janrField;

    @FXML
    private TextField dobav_nameField;

    @FXML
    private TextField dobav_orepField;

    @FXML
    private TextField dobav_rejiserField;

    @FXML
    private TextField dobav_studioField;

    @FXML
    private Button dobav_submitBtn;

    @FXML
    private TableView<filmsData> dobav_tableView;

    @FXML
    private Button dobav_updateBtn;

    @FXML
    private AnchorPane dobav_window;

    @FXML
    private Button exit_to_login_Btn;

    @FXML
    private Button film_Btn;

    @FXML
    private Button film_clearBtn_janr;

    @FXML
    private Button film_clearBtn_rejiser;

    @FXML
    private TableView<fullFilmsData> films_tableView;

    @FXML
    private TableColumn<fullFilmsData,String> film_col_Kinoteatr;

    @FXML
    private TableColumn<fullFilmsData,String> film_col_Actor;

    @FXML
    private TableColumn<fullFilmsData,String> film_col_Film;

    @FXML
    private TableColumn<fullFilmsData,String> film_col_Janr;

    @FXML
    private TableColumn<fullFilmsData,String> film_col_Oper;

    @FXML
    private TableColumn<fullFilmsData,String> film_col_Rejiser;

    @FXML
    private TableColumn<fullFilmsData,String> film_col_Studio;

    @FXML
    private Button film_submitBtn_all;

    @FXML
    private Button film_submitBtn_janr;

    @FXML
    private Button film_submitBtn_rejiser;

    @FXML
    private ComboBox<String> film_vibor_janr;

    @FXML
    private ComboBox<String> film_vibor_rejiser;

    @FXML
    private AnchorPane film_window;

    @FXML
    private Button kinoteatr_Btn;

    @FXML
    private Button kinoteatr_clearBtn;

    @FXML
    private TableView<kinoteatrsData> kinoteatr_tableView;

    @FXML
    private TableColumn<kinoteatrsData, String> kinoteatr_col_Adres;

    @FXML
    private TableColumn<kinoteatrsData, String> kinoteatr_col_Category;

    @FXML
    private TableColumn<kinoteatrsData, String> kinoteatr_col_Mest;

    @FXML
    private TableColumn<kinoteatrsData, String> kinoteatr_col_Status;

    @FXML
    private TableColumn<kinoteatrsData, String> kinoteatr_col_Zalov;

    @FXML
    private Button kinoteatr_submitBtn;

    @FXML
    private ComboBox<String> kinoteatr_vibor;

    @FXML
    private AnchorPane kinoteatr_window;

    @FXML
    private Button minimaze;

    @FXML
    private Button seans_Btn;

    @FXML
    private Button seans_clearBtn_kinoteatr;

    @FXML
    private Button seans_clearBtn_seans;

    @FXML
    private TableView<seansData> seans_tableView;

    @FXML
    private TableColumn<seansData, String> seans_col_Data;

    @FXML
    private TableColumn<seansData, String> seans_col_Film;

    @FXML
    private TableColumn<seansData, String> seans_col_Kinoteatr;

    @FXML
    private TableColumn<seansData, String> seans_col_MestSv;

    @FXML
    private TableColumn<seansData, String> seans_col_Price;

    @FXML
    private TableColumn<seansData, String> seans_col_Seans;

    @FXML
    private Button seans_submitBtn_All;


    @FXML
    private Button seans_submitBtn_Seans;

    @FXML
    private Button seans_submitBtn_kinoteatr;

    @FXML
    private ComboBox<String> seans_vibor_kinoteatr;

    @FXML
    private ComboBox<String> seans_vibor_seans;

    @FXML
    private AnchorPane seans_window;

    @FXML
    private AnchorPane topForm;

    @FXML
    private Label username;


    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    //---------------------------------------------------------------------------------------------------------------------------//
    //---------------------------------------Добавление, удаление, обновление фильма--------------------------------------------//

    public void filmId(){

        String sql = "SELECT count(id_films) FROM films";

        connect = Database.connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                getData.filmId = result.getInt("count(id_films)");
            }

        }catch (Exception e){e.printStackTrace();}

    }

    public void insertAddFilm(){

        String sql1 = "SELECT * FROM films WHERE kinoteatr_name = '"
                +dobav_vibor_Kinoteatr.getSelectionModel().getSelectedItem()+"' and film_name = '"
                +dobav_nameField.getText()+"'";

        connect = Database.connectDb();

        Alert alert;

        try{

            statement = connect.createStatement();
            result = statement.executeQuery(sql1);

            if(result.next()) {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText(dobav_nameField.getText() + " уже существует!!!");
                alert.showAndWait();

            } else {
                if(dobav_vibor_Kinoteatr.getSelectionModel().getSelectedItem().equals("Выберите")||
                        dobav_nameField.getText().isEmpty()||
                        dobav_rejiserField.getText().isEmpty()||
                        dobav_orepField.getText().isEmpty()||
                        dobav_actorField.getText().isEmpty()||
                        dobav_janrField.getText().isEmpty()||
                        dobav_studioField.getText().isEmpty()){

                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Ошибка!");
                    alert.setHeaderText(null);
                    alert.setContentText("Заполните все поля!!!");
                    alert.showAndWait();

                } else {

                    String sql = "INSERT INTO `films`(id_films,kinoteatr_name,film_name,film_director,film_operator,film_actors,film_style,film_studio) values (?,?,?,?,?,?,?,?);";

                    filmId();

                    String fId = String.valueOf(getData.filmId+1);

                    prepare = connect.prepareStatement(sql);

                    prepare.setString(1,fId);
                    prepare.setString(2,dobav_vibor_Kinoteatr.getSelectionModel().getSelectedItem());
                    prepare.setString(3,dobav_nameField.getText());
                    prepare.setString(4,dobav_rejiserField.getText());
                    prepare.setString(5,dobav_orepField.getText());
                    prepare.setString(6,dobav_actorField.getText());
                    prepare.setString(7,dobav_janrField.getText());
                    prepare.setString(8,dobav_studioField.getText());

                    prepare.execute();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Информация");
                    alert.setHeaderText(null);
                    alert.setContentText("Успешно добавлен новый фильм!");
                    alert.showAndWait();

                    clearAddMovieList();

                    showAddFilmsList();
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }

    public void deleteAddMovies(){

        String sql = "DELETE FROM films WHERE id_films = '"+String.valueOf(getData.filmId)+"';";

        connect = Database.connectDb();

        Alert alert;

        try {

            statement = connect.createStatement();

            if(dobav_vibor_Kinoteatr.getSelectionModel().isEmpty()||
                    dobav_nameField.getText().isEmpty()||
                    dobav_rejiserField.getText().isEmpty()||
                    dobav_orepField.getText().isEmpty()||
                    dobav_actorField.getText().isEmpty()||
                    dobav_janrField.getText().isEmpty()||
                    dobav_studioField.getText().isEmpty()){

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Заполните все поля!!!");
                alert.showAndWait();

            } else {

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Подтверждение удаления");
                alert.setHeaderText(null);
                alert.setContentText("Вы уверены, что хотите удалить фильм "
                        +dobav_nameField.getText()+"?");

                Optional<ButtonType> option = alert.showAndWait();

                if (ButtonType.OK.equals(option.get())){

                    statement.executeUpdate(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Информация!");
                    alert.setHeaderText(null);
                    alert.setContentText("Фильм успешно удалён!");
                    alert.showAndWait();

                    clearAddMovieList();

                    showAddFilmsList();

                }else{
                    return;
                }
            }

        }catch (Exception e){e.printStackTrace();}

    }
    public void clearAddMovieList(){
        dobav_nameField.setText("");
        dobav_rejiserField.setText("");
        dobav_orepField.setText("");
        dobav_actorField.setText("");
        dobav_janrField.setText("");
        dobav_studioField.setText("");
    }

    public void updateAddMovies(){

        String sql = "UPDATE films SET kinoteatr_name = '"+dobav_vibor_Kinoteatr.getSelectionModel().getSelectedItem()
                +"', film_name = '" + dobav_nameField.getText()
                +"', film_director = '" + dobav_rejiserField.getText()
                +"', film_operator = '" + dobav_orepField.getText()
                +"', film_actors = '" + dobav_actorField.getText()
                +"', film_style = '" + dobav_janrField.getText()
                +"', film_studio = '" + dobav_studioField.getText()+"'" +
                "WHERE id_films = '"+ String.valueOf(getData.filmId)+"';";

        connect = Database.connectDb();

        try{
            statement = connect.createStatement();

            Alert alert;

            if(dobav_vibor_Kinoteatr.getSelectionModel().isEmpty()||
                    dobav_nameField.getText().isEmpty()||
                    dobav_rejiserField.getText().isEmpty()||
                    dobav_orepField.getText().isEmpty()||
                    dobav_actorField.getText().isEmpty()||
                    dobav_janrField.getText().isEmpty()||
                    dobav_studioField.getText().isEmpty()){

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Сначала выделите фильм, который хотите изменить и заполните все поля");
                alert.showAndWait();

            } else {

                statement.executeUpdate(sql);

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Информация!");
                alert.setHeaderText(null);
                alert.setContentText("Фильм успешно изменён!");
                alert.showAndWait();

                clearAddMovieList();

                showAddFilmsList();
            }

        }catch (Exception e){e.printStackTrace();}

    }

    public ObservableList<filmsData> addMoviesList(){

        ObservableList<filmsData> listDATA = FXCollections.observableArrayList();

        String sql = "SELECT * FROM films";

        connect = Database.connectDb();

        try{

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            filmsData filmsD;

            while (result.next()){
                filmsD = new filmsData(result.getInt("id_films"),
                        result.getString("film_name"),
                        result.getString("film_director"),
                        result.getString("film_operator"),
                        result.getString("film_actors"),
                        result.getString("film_style"),
                        result.getString("film_studio"),
                        result.getString("kinoteatr_name"));

                listDATA.add(filmsD);
            }


        }catch (Exception e){e.printStackTrace();}
        return listDATA;
    }

    private ObservableList<filmsData> listAddMovies;
    public  void showAddFilmsList(){
        listAddMovies = addMoviesList();

        dobav_col_Name.setCellValueFactory(new PropertyValueFactory<>("film_name"));
        dobav_col_Janr.setCellValueFactory(new PropertyValueFactory<>("film_style"));
        dobav_col_Rejiser.setCellValueFactory(new PropertyValueFactory<>("film_director"));
        dobav_col_Studio.setCellValueFactory(new PropertyValueFactory<>("film_studio"));
        dobav_col_Kinoteatr.setCellValueFactory(new PropertyValueFactory<>("kinoteatr_name"));
        dobav_col_Actors.setCellValueFactory(new PropertyValueFactory<>("film_actors"));
        dobav_col_Operator.setCellValueFactory(new PropertyValueFactory<>("film_operator"));

        dobav_tableView.setItems(listAddMovies);

    }

    private final String[] kinoteatrsList = {"Киноплекс","Киномакс","Imax","Синема","Рубин"};

    public void comboBox(){

        List<String> listKinoteatrs = new ArrayList<>(Arrays.asList(kinoteatrsList));

        ObservableList<String> listK = FXCollections.observableArrayList(listKinoteatrs);
        dobav_vibor_Kinoteatr.setItems(listK);

    }
    //--------------------------------------------Все кинотеатры--------------------------------------------//
    public void comboBoxKinoteatr(){

        List<String> listKinoteatrs = new ArrayList<>(Arrays.asList(kinoteatrsList));

        ObservableList<String> listK = FXCollections.observableArrayList(listKinoteatrs);
        kinoteatr_vibor.setItems(listK);

    }



    public ObservableList<kinoteatrsData> addKinoteatrsList(){

        ObservableList<kinoteatrsData> listDATA = FXCollections.observableArrayList();

        String sql = "SELECT * FROM kinoteatrs;";
        String sql1 = "SELECT * FROM kinoteatrs WHERE kinoteatr_name='Киноплекс';";
        String sql2 = "SELECT * FROM kinoteatrs WHERE kinoteatr_name='Киномакс';";
        String sql3 = "SELECT * FROM kinoteatrs WHERE kinoteatr_name='Imax';";
        String sql4 = "SELECT * FROM kinoteatrs WHERE kinoteatr_name='Синема';";
        String sql5 = "SELECT * FROM kinoteatrs WHERE kinoteatr_name='Рубин';";

        connect = Database.connectDb();

        try{
            if(kinoteatr_vibor.getSelectionModel().isEmpty()){
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();
            } else if(kinoteatr_vibor.getSelectionModel().getSelectedItem().equals("Киноплекс")){
                prepare = connect.prepareStatement(sql1);
                result = prepare.executeQuery();
            } else if (kinoteatr_vibor.getSelectionModel().getSelectedItem().equals("Киномакс")) {
                prepare = connect.prepareStatement(sql2);
                result = prepare.executeQuery();
            }else if (kinoteatr_vibor.getSelectionModel().getSelectedItem().equals("Imax")) {
                prepare = connect.prepareStatement(sql3);
                result = prepare.executeQuery();
            }else if (kinoteatr_vibor.getSelectionModel().getSelectedItem().equals("Синема")) {
                prepare = connect.prepareStatement(sql4);
                result = prepare.executeQuery();
            }else if (kinoteatr_vibor.getSelectionModel().getSelectedItem().equals("Рубин")) {
                prepare = connect.prepareStatement(sql5);
                result = prepare.executeQuery();
            }

            kinoteatrsData kinoteatrsD;

            while (result.next()){
                kinoteatrsD = new kinoteatrsData(result.getInt("id_kinoteatrs"),
                        result.getString("kinoteatr_name"),
                        result.getString("kinoteatr_adres"),
                        result.getString("kinoteatr_category"),
                        result.getString("kinoteatr_sum_places"),
                        result.getString("kinoteatr_sum_room"),
                        result.getString("kinoteatr_status")
                        );

                listDATA.add(kinoteatrsD);
            }


        }catch (Exception e){e.printStackTrace();}
        return listDATA;
    }
    private ObservableList<kinoteatrsData> listAddKinoteatrs;
    public void showAddKinoteatrsList(){

        listAddKinoteatrs = addKinoteatrsList();

        kinoteatr_col_Adres.setCellValueFactory(new PropertyValueFactory<>("kinoteatr_adres"));
        kinoteatr_col_Category.setCellValueFactory(new PropertyValueFactory<>("kinoteatr_category"));
        kinoteatr_col_Mest.setCellValueFactory(new PropertyValueFactory<>("kinoteatr_sum_places"));
        kinoteatr_col_Zalov.setCellValueFactory(new PropertyValueFactory<>("kinoteatr_sum_room"));
        kinoteatr_col_Status.setCellValueFactory(new PropertyValueFactory<>("kinoteatr_status"));

        kinoteatr_tableView.setItems(listAddKinoteatrs);

    }

    public void viborKinoteatr(){

        connect = Database.connectDb();

        Alert alert;

        try{
                if(kinoteatr_vibor.getSelectionModel().isEmpty()){
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Ошибка!");
                    alert.setHeaderText(null);
                    alert.setContentText("Выберите кинотеатр!!!");
                    alert.showAndWait();

                } else {

                    showAddKinoteatrsList();

                }
        }catch (Exception e){e.printStackTrace();}
    }

    public void cancelKinoteatr(){

        connect = Database.connectDb();

        kinoteatr_vibor.setValue("");

        try{

            showAddKinoteatrsList();

        }catch (Exception e){e.printStackTrace();}
    }
    //------------------------------------------------------------------------------------------------------------------//
    //---------------------------------------Вывод информации о фильмах------------------------------------------------//

    //СПИСОК ЖАНРОВ ДЛЯ ВЫПАДАЮЩЕГО СПИСКА
    private final String[] janrList = {"Боевик","Драма","Хоррор","Комедия"};
    public void comboBoxFilms1(){

        List<String> listlJanr = new ArrayList<>(Arrays.asList(janrList));

        ObservableList<String> listK = FXCollections.observableArrayList(listlJanr);
        film_vibor_janr.setItems(listK);

    }

    //СПИСОК ЖАНРОВ ДЛЯ ВЫПАДАЮЩЕГО СПИСКА

    private final String[] directorList = {"И.В.Купряшов","О.А.Си","Б.С.Ельцин","А.А.Петросян","П.Е.Анисимов"};

    public void comboBoxFilms2(){

        List<String> listDirector = new ArrayList<>(Arrays.asList(directorList));

        ObservableList<String> listK = FXCollections.observableArrayList(listDirector);
        film_vibor_rejiser.setItems(listK);

    }



    public ObservableList<fullFilmsData> addJanrFullFilmsList(){

        ObservableList<fullFilmsData> listDATA = FXCollections.observableArrayList();

        String sql = "SELECT * FROM films;";
        String sql1 = "SELECT * FROM films WHERE film_style='Боевик';";
        String sql2 = "SELECT * FROM films WHERE film_style='Драма';";
        String sql3 = "SELECT * FROM films WHERE film_style='Хоррор';";
        String sql4 = "SELECT * FROM films WHERE film_style='Комедия';";

        connect = Database.connectDb();

        try{
            if(film_vibor_janr.getSelectionModel().isEmpty()){
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();
            } else if(film_vibor_janr.getSelectionModel().getSelectedItem().equals("Боевик")){
                prepare = connect.prepareStatement(sql1);
                result = prepare.executeQuery();
            } else if (film_vibor_janr.getSelectionModel().getSelectedItem().equals("Драма")) {
                prepare = connect.prepareStatement(sql2);
                result = prepare.executeQuery();
            } else if (film_vibor_janr.getSelectionModel().getSelectedItem().equals("Хоррор")) {
                prepare = connect.prepareStatement(sql3);
                result = prepare.executeQuery();
            } else if (film_vibor_janr.getSelectionModel().getSelectedItem().equals("Комедия")) {
                prepare = connect.prepareStatement(sql4);
                result = prepare.executeQuery();
            }

            fullFilmsData fullFilmsD;

            while (result.next()){
                fullFilmsD = new fullFilmsData(result.getInt("id_films"),
                        result.getString("kinoteatr_name"),
                        result.getString("film_name"),
                        result.getString("film_director"),
                        result.getString("film_operator"),
                        result.getString("film_actors"),
                        result.getString("film_style"),
                        result.getString("film_studio")
                );

                listDATA.add(fullFilmsD);
            }


        }catch (Exception e){e.printStackTrace();}
        return listDATA;
    }

    public ObservableList<fullFilmsData> addJanrSearchFullFilmsList(){

        ObservableList<fullFilmsData> listDATA = FXCollections.observableArrayList();

        String sql = "SELECT * FROM films;";

        connect = Database.connectDb();

        try{
            String sqlAll = "SELECT * FROM films WHERE film_style='"+film_vibor_janr.getEditor().getText()+"';";

            prepare = connect.prepareStatement(sqlAll);
            result = prepare.executeQuery();

            fullFilmsData fullFilmsD;

            while (result.next()){
                fullFilmsD = new fullFilmsData(result.getInt("id_films"),
                        result.getString("kinoteatr_name"),
                        result.getString("film_name"),
                        result.getString("film_director"),
                        result.getString("film_operator"),
                        result.getString("film_actors"),
                        result.getString("film_style"),
                        result.getString("film_studio")
                );

                listDATA.add(fullFilmsD);
            }


        }catch (Exception e){e.printStackTrace();}
        return listDATA;
    }

    public ObservableList<fullFilmsData> addDirectorFullFilmsList(){

        ObservableList<fullFilmsData> listDATA = FXCollections.observableArrayList();

        String sql = "SELECT * FROM films;";

        connect = Database.connectDb();

        try{
            if(film_vibor_rejiser.getSelectionModel().isEmpty()){
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();
            } else {
                String sqlAll = "SELECT * FROM films WHERE film_director='"+film_vibor_rejiser.getSelectionModel().getSelectedItem()+"';";

                prepare = connect.prepareStatement(sqlAll);
                result = prepare.executeQuery();
            }

            fullFilmsData fullFilmD;

            while (result.next()){
                fullFilmD = new fullFilmsData(result.getInt("id_films"),
                        result.getString("kinoteatr_name"),
                        result.getString("film_name"),
                        result.getString("film_director"),
                        result.getString("film_operator"),
                        result.getString("film_actors"),
                        result.getString("film_style"),
                        result.getString("film_studio")
                );

                listDATA.add(fullFilmD);
            }


        }catch (Exception e){e.printStackTrace();}
        return listDATA;
    }

    public ObservableList<fullFilmsData> addDirectorSearchFullFilmsList(){

        ObservableList<fullFilmsData> listDATA = FXCollections.observableArrayList();

        String sql = "SELECT * FROM films;";

        connect = Database.connectDb();

        try{
            String sqlAll = "SELECT * FROM films WHERE film_director='"+film_vibor_rejiser.getEditor().getText()+"';";

            prepare = connect.prepareStatement(sqlAll);
            result = prepare.executeQuery();

            fullFilmsData fullFilmD;

            while (result.next()){
                fullFilmD = new fullFilmsData(result.getInt("id_films"),
                        result.getString("kinoteatr_name"),
                        result.getString("film_name"),
                        result.getString("film_director"),
                        result.getString("film_operator"),
                        result.getString("film_actors"),
                        result.getString("film_style"),
                        result.getString("film_studio")
                );

                listDATA.add(fullFilmD);
            }


        }catch (Exception e){e.printStackTrace();}
        return listDATA;
    }

    public ObservableList<fullFilmsData> addAllFullFilmsList(){

        ObservableList<fullFilmsData> listDATA = FXCollections.observableArrayList();

        String sql = "SELECT * FROM films;";

        connect = Database.connectDb();

        Alert alert;

        try{
            if(film_vibor_rejiser.getSelectionModel().isEmpty()&&film_vibor_janr.getSelectionModel().isEmpty()){

                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();

            }else if (film_vibor_rejiser.getSelectionModel().isEmpty()||film_vibor_janr.getSelectionModel().isEmpty()){

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Заполните оба поля!!!");
                alert.showAndWait();

            } else{

                String sqlAll = "SELECT * FROM films WHERE film_director='"+film_vibor_rejiser.getSelectionModel().getSelectedItem()+"' and film_style ='"+film_vibor_janr.getSelectionModel().getSelectedItem()+"';";

                prepare = connect.prepareStatement(sqlAll);
                result = prepare.executeQuery();

            }

            fullFilmsData fullFilmD;

            while (result.next()){
                fullFilmD = new fullFilmsData(result.getInt("id_films"),
                        result.getString("kinoteatr_name"),
                        result.getString("film_name"),
                        result.getString("film_director"),
                        result.getString("film_operator"),
                        result.getString("film_actors"),
                        result.getString("film_style"),
                        result.getString("film_studio")
                );

                listDATA.add(fullFilmD);
            }


        }catch (Exception e){e.printStackTrace();}
        return listDATA;
    }

    public ObservableList<fullFilmsData> addSearchAllFullFilmsList(){

        ObservableList<fullFilmsData> listDATA = FXCollections.observableArrayList();

        String sql = "SELECT * FROM films;";

        connect = Database.connectDb();

        Alert alert;

        try{

            String sqlAll = "SELECT * FROM films WHERE film_director='"+film_vibor_rejiser.getEditor().getText()+"' and film_style ='"+film_vibor_janr.getEditor().getText()+"';";

            prepare = connect.prepareStatement(sqlAll);
            result = prepare.executeQuery();

            fullFilmsData fullFilmD;

            while (result.next()){
                fullFilmD = new fullFilmsData(result.getInt("id_films"),
                        result.getString("kinoteatr_name"),
                        result.getString("film_name"),
                        result.getString("film_director"),
                        result.getString("film_operator"),
                        result.getString("film_actors"),
                        result.getString("film_style"),
                        result.getString("film_studio")
                );

                listDATA.add(fullFilmD);
            }


        }catch (Exception e){e.printStackTrace();}
        return listDATA;
    }
    private ObservableList<fullFilmsData> listJanrFullFilm;
    private ObservableList<fullFilmsData> listJanrSearchFullFilm;
    private ObservableList<fullFilmsData> listDirectorFullFilm;
    private ObservableList<fullFilmsData> listDirectorSearchFullFilm;
    private ObservableList<fullFilmsData> listAllFullFilm;
    private ObservableList<fullFilmsData> listAllSearchFullFilm;

    public void showJanrFullFilmsList(){

        listJanrFullFilm = addJanrFullFilmsList();

        film_col_Kinoteatr.setCellValueFactory(new PropertyValueFactory<>("kinoteatr_name"));
        film_col_Film.setCellValueFactory(new PropertyValueFactory<>("film_name"));
        film_col_Janr.setCellValueFactory(new PropertyValueFactory<>("film_style"));
        film_col_Rejiser.setCellValueFactory(new PropertyValueFactory<>("film_director"));
        film_col_Oper.setCellValueFactory(new PropertyValueFactory<>("film_operator"));
        film_col_Actor.setCellValueFactory(new PropertyValueFactory<>("film_actors"));
        film_col_Studio.setCellValueFactory(new PropertyValueFactory<>("film_studio"));

        films_tableView.setItems(listJanrFullFilm);
        films_tableView.setPlaceholder(new Label("Не найдено совпадений"));

    }

    public void showJanrSearchFullFilmsList(){

        listJanrSearchFullFilm = addJanrSearchFullFilmsList();

        film_col_Kinoteatr.setCellValueFactory(new PropertyValueFactory<>("kinoteatr_name"));
        film_col_Film.setCellValueFactory(new PropertyValueFactory<>("film_name"));
        film_col_Janr.setCellValueFactory(new PropertyValueFactory<>("film_style"));
        film_col_Rejiser.setCellValueFactory(new PropertyValueFactory<>("film_director"));
        film_col_Oper.setCellValueFactory(new PropertyValueFactory<>("film_operator"));
        film_col_Actor.setCellValueFactory(new PropertyValueFactory<>("film_actors"));
        film_col_Studio.setCellValueFactory(new PropertyValueFactory<>("film_studio"));

        films_tableView.setItems(listJanrSearchFullFilm);
        films_tableView.setPlaceholder(new Label("Не найдено совпадений"));

    }

    public void showDirectorFullFilmsList(){

        listDirectorFullFilm = addDirectorFullFilmsList();

        film_col_Kinoteatr.setCellValueFactory(new PropertyValueFactory<>("kinoteatr_name"));
        film_col_Film.setCellValueFactory(new PropertyValueFactory<>("film_name"));
        film_col_Janr.setCellValueFactory(new PropertyValueFactory<>("film_style"));
        film_col_Rejiser.setCellValueFactory(new PropertyValueFactory<>("film_director"));
        film_col_Oper.setCellValueFactory(new PropertyValueFactory<>("film_operator"));
        film_col_Actor.setCellValueFactory(new PropertyValueFactory<>("film_actors"));
        film_col_Studio.setCellValueFactory(new PropertyValueFactory<>("film_studio"));

        films_tableView.setItems(listDirectorFullFilm);
        films_tableView.setPlaceholder(new Label("Не найдено совпадений"));

    }

    public void showDirectorSearchFullFilmsList(){

        listDirectorSearchFullFilm = addDirectorSearchFullFilmsList();

        film_col_Kinoteatr.setCellValueFactory(new PropertyValueFactory<>("kinoteatr_name"));
        film_col_Film.setCellValueFactory(new PropertyValueFactory<>("film_name"));
        film_col_Janr.setCellValueFactory(new PropertyValueFactory<>("film_style"));
        film_col_Rejiser.setCellValueFactory(new PropertyValueFactory<>("film_director"));
        film_col_Oper.setCellValueFactory(new PropertyValueFactory<>("film_operator"));
        film_col_Actor.setCellValueFactory(new PropertyValueFactory<>("film_actors"));
        film_col_Studio.setCellValueFactory(new PropertyValueFactory<>("film_studio"));

        films_tableView.setItems(listDirectorSearchFullFilm);
        films_tableView.setPlaceholder(new Label("Не найдено совпадений"));

    }

    public void showAllFullFilmsList(){

        listAllFullFilm = addAllFullFilmsList();

        film_col_Kinoteatr.setCellValueFactory(new PropertyValueFactory<>("kinoteatr_name"));
        film_col_Film.setCellValueFactory(new PropertyValueFactory<>("film_name"));
        film_col_Janr.setCellValueFactory(new PropertyValueFactory<>("film_style"));
        film_col_Rejiser.setCellValueFactory(new PropertyValueFactory<>("film_director"));
        film_col_Oper.setCellValueFactory(new PropertyValueFactory<>("film_operator"));
        film_col_Actor.setCellValueFactory(new PropertyValueFactory<>("film_actors"));
        film_col_Studio.setCellValueFactory(new PropertyValueFactory<>("film_studio"));

        films_tableView.setItems(listAllFullFilm);
        films_tableView.setPlaceholder(new Label("Не найдено совпадений"));

    }

    public void showAllSearchFullFilmsList(){

        listAllSearchFullFilm = addSearchAllFullFilmsList();

        film_col_Kinoteatr.setCellValueFactory(new PropertyValueFactory<>("kinoteatr_name"));
        film_col_Film.setCellValueFactory(new PropertyValueFactory<>("film_name"));
        film_col_Janr.setCellValueFactory(new PropertyValueFactory<>("film_style"));
        film_col_Rejiser.setCellValueFactory(new PropertyValueFactory<>("film_director"));
        film_col_Oper.setCellValueFactory(new PropertyValueFactory<>("film_operator"));
        film_col_Actor.setCellValueFactory(new PropertyValueFactory<>("film_actors"));
        film_col_Studio.setCellValueFactory(new PropertyValueFactory<>("film_studio"));

        films_tableView.setItems(listAllSearchFullFilm);
        films_tableView.setPlaceholder(new Label("Не найдено совпадений"));

    }

    public void viborJanrFullFilms(){

        connect = Database.connectDb();

        Alert alert;

        try{
            if(!film_vibor_janr.getEditor().getText().isEmpty()){

                showJanrSearchFullFilmsList();

            } else if(film_vibor_janr.getSelectionModel().isEmpty()){

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Выберите жанр!!!");
                alert.showAndWait();

            } else {

                showJanrFullFilmsList();
            }
        }catch (Exception e){e.printStackTrace();}
    }

    public void viborOperatorFullFilms(){

        connect = Database.connectDb();

        Alert alert;

        try{
            if(!film_vibor_rejiser.getEditor().getText().isEmpty()){

                showDirectorSearchFullFilmsList();

            } else if(film_vibor_rejiser.getSelectionModel().isEmpty()){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Выберите режиссера!!!");
                alert.showAndWait();

            } else {

                showDirectorFullFilmsList();

            }
        }catch (Exception e){e.printStackTrace();}
    }

    public void viborAllFullFilms(){

        connect = Database.connectDb();

        Alert alert;

        try{
            if(!film_vibor_rejiser.getEditor().getText().isEmpty()&&!film_vibor_janr.getEditor().getText().isEmpty()){

                showAllSearchFullFilmsList();

            } else if(film_vibor_rejiser.getEditor().getText().isEmpty()||film_vibor_janr.getEditor().getText().isEmpty()){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Заполните оба поля!!!");
                alert.showAndWait();

            } else if(film_vibor_rejiser.getSelectionModel().isEmpty()||film_vibor_janr.getSelectionModel().isEmpty()){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Заполните оба поля!!!");
                alert.showAndWait();

            } else {

                showAllFullFilmsList();

            }
        }catch (Exception e){e.printStackTrace();}
    }

    public void cancelJanrFilms(){

        connect = Database.connectDb();

        film_vibor_janr.setValue("");

        try{

            showJanrFullFilmsList();

        }catch (Exception e){e.printStackTrace();}
    }

    public void cancelDirectorFilms(){

        connect = Database.connectDb();

        film_vibor_rejiser.setValue("");

        try{

            showDirectorFullFilmsList();

        }catch (Exception e){e.printStackTrace();}
    }
    //------------------------------------------------------------------------------------------------------------------//
    //------------------------------------------------------------------------------------------------------------------//
    //-------------------------------------------------Все сеансы-------------------------------------------------------//
    private final String[]  kinoteatrsSeansList = {"Киноплекс","Киномакс","Imax","Синема","Рубин"};;
    public void comboBoxSeansKino(){

        List<String> listlKinoteatrsSeans = new ArrayList<>(Arrays.asList(kinoteatrsSeansList));

        ObservableList<String> listK = FXCollections.observableArrayList(listlKinoteatrsSeans);
        seans_vibor_kinoteatr.setItems(listK);

    }

    private final String[] dateSeansList = {"31.05.23","01.06.23","02.06.23"};

    public void comboBoxSeansSeans(){

        List<String> listDateSeans = new ArrayList<>(Arrays.asList(dateSeansList));

        ObservableList<String> listK = FXCollections.observableArrayList(listDateSeans);
        seans_vibor_seans.setItems(listK);

    }

    public ObservableList<seansData> addKinoteatrsSeansList(){

        ObservableList<seansData> listDATA = FXCollections.observableArrayList();

        String sql = "SELECT * FROM sessions;";
        String sql1 = "SELECT * FROM sessions WHERE kino_name='Киноплекс';";
        String sql2 = "SELECT * FROM sessions WHERE kino_name='Киномакс';";
        String sql3 = "SELECT * FROM sessions WHERE kino_name='Imax';";
        String sql4 = "SELECT * FROM sessions WHERE kino_name='Синема';";
        String sql5 = "SELECT * FROM sessions WHERE kino_name='Рубин';";

        connect = Database.connectDb();

        try{
            if (seans_vibor_kinoteatr.getSelectionModel().isEmpty()){
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();
            } else if(seans_vibor_kinoteatr.getSelectionModel().getSelectedItem().equals("Киноплекс")){
                prepare = connect.prepareStatement(sql1);
                result = prepare.executeQuery();
            } else if (seans_vibor_kinoteatr.getSelectionModel().getSelectedItem().equals("Киномакс")) {
                prepare = connect.prepareStatement(sql2);
                result = prepare.executeQuery();
            }else if (seans_vibor_kinoteatr.getSelectionModel().getSelectedItem().equals("Imax")) {
                prepare = connect.prepareStatement(sql3);
                result = prepare.executeQuery();
            }else if (seans_vibor_kinoteatr.getSelectionModel().getSelectedItem().equals("Синема")) {
                prepare = connect.prepareStatement(sql4);
                result = prepare.executeQuery();
            } else if (seans_vibor_kinoteatr.getSelectionModel().getSelectedItem().equals("Рубин")) {
                prepare = connect.prepareStatement(sql5);
                result = prepare.executeQuery();
            }

            seansData seansD;

            while (result.next()){
                seansD = new seansData(result.getInt("id_sessions"),
                        result.getString("kino_name"),
                        result.getString("film_name"),
                        result.getString("session_date"),
                        result.getString("session_time"),
                        result.getString("session_freePlace"),
                        result.getString("session_price")
                );

                listDATA.add(seansD);
            }


        }catch (Exception e){e.printStackTrace();}
        return listDATA;
    }

    public ObservableList<seansData> addDateSeansList(){

        ObservableList<seansData> listDATA = FXCollections.observableArrayList();

        String sql = "SELECT * FROM sessions;";
        String sql1 = "SELECT * FROM sessions WHERE session_date='31.05.23';";
        String sql2 = "SELECT * FROM sessions WHERE session_date='01.06.23';";
        String sql3 = "SELECT * FROM sessions WHERE session_date='02.06.23';";

        connect = Database.connectDb();

        try{
            if(seans_vibor_seans.getSelectionModel().isEmpty()){
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();
            } else if(seans_vibor_seans.getSelectionModel().getSelectedItem().equals("31.05.23")){
                prepare = connect.prepareStatement(sql1);
                result = prepare.executeQuery();
            } else if (seans_vibor_seans.getSelectionModel().getSelectedItem().equals("01.06.23")) {
                prepare = connect.prepareStatement(sql2);
                result = prepare.executeQuery();
            }else if (seans_vibor_seans.getSelectionModel().getSelectedItem().equals("02.06.23")) {
                prepare = connect.prepareStatement(sql3);
                result = prepare.executeQuery();
            }

            seansData seansD;

            while (result.next()){
                seansD = new seansData(result.getInt("id_sessions"),
                        result.getString("kino_name"),
                        result.getString("film_name"),
                        result.getString("session_date"),
                        result.getString("session_time"),
                        result.getString("session_freePlace"),
                        result.getString("session_price")
                );

                listDATA.add(seansD);
            }


        }catch (Exception e){e.printStackTrace();}
        return listDATA;
    }

    public ObservableList<seansData> addAllSeansList(){

        ObservableList<seansData> listDATA = FXCollections.observableArrayList();

        String sqlSearch = "SELECT * FROM sessions WHERE sessions.kino_name='"+seans_vibor_kinoteatr.getSelectionModel().getSelectedItem()+"' and sessions.session_date='"+seans_vibor_seans.getSelectionModel().getSelectedItem()+"';";

        connect = Database.connectDb();

        try{

            prepare = connect.prepareStatement(sqlSearch);
            result = prepare.executeQuery();

            seansData seansD;

            while (result.next()){
                seansD = new seansData(result.getInt("id_sessions"),
                        result.getString("kino_name"),
                        result.getString("film_name"),
                        result.getString("session_date"),
                        result.getString("session_time"),
                        result.getString("session_freePlace"),
                        result.getString("session_price")
                );

                listDATA.add(seansD);
            }


        }catch (Exception e){e.printStackTrace();}
        return listDATA;
    }
    private ObservableList<seansData> listKinotrSeansData;
    private ObservableList<seansData> listDateSeansData;
    private ObservableList<seansData> listAllSeansData;

    public void showKinoSeansDataList(){

        listKinotrSeansData = addKinoteatrsSeansList();

        seans_col_Kinoteatr.setCellValueFactory(new PropertyValueFactory<>("kino_name"));
        seans_col_Data.setCellValueFactory(new PropertyValueFactory<>("session_date"));
        seans_col_Seans.setCellValueFactory(new PropertyValueFactory<>("session_time"));
        seans_col_Film.setCellValueFactory(new PropertyValueFactory<>("film_name"));
        seans_col_MestSv.setCellValueFactory(new PropertyValueFactory<>("session_freeplace"));
        seans_col_Price.setCellValueFactory(new PropertyValueFactory<>("session_price"));

        seans_tableView.setItems(listKinotrSeansData);

    }

    public void showDateSeansDataList(){

        listDateSeansData = addDateSeansList();

        seans_col_Kinoteatr.setCellValueFactory(new PropertyValueFactory<>("kino_name"));
        seans_col_Data.setCellValueFactory(new PropertyValueFactory<>("session_date"));
        seans_col_Seans.setCellValueFactory(new PropertyValueFactory<>("session_time"));
        seans_col_Film.setCellValueFactory(new PropertyValueFactory<>("film_name"));
        seans_col_MestSv.setCellValueFactory(new PropertyValueFactory<>("session_freeplace"));
        seans_col_Price.setCellValueFactory(new PropertyValueFactory<>("session_price"));

        seans_tableView.setItems(listDateSeansData);

    }

    public void showAllSeansDataList(){

        listAllSeansData = addAllSeansList();

        seans_col_Kinoteatr.setCellValueFactory(new PropertyValueFactory<>("kino_name"));
        seans_col_Data.setCellValueFactory(new PropertyValueFactory<>("session_date"));
        seans_col_Seans.setCellValueFactory(new PropertyValueFactory<>("session_time"));
        seans_col_Film.setCellValueFactory(new PropertyValueFactory<>("film_name"));
        seans_col_MestSv.setCellValueFactory(new PropertyValueFactory<>("session_freeplace"));
        seans_col_Price.setCellValueFactory(new PropertyValueFactory<>("session_price"));

        seans_tableView.setItems(listAllSeansData);

    }

    public void viborKinoteatrsSeansList(){

        connect = Database.connectDb();

        Alert alert;

        try{
            if(seans_vibor_kinoteatr.getSelectionModel().isEmpty()){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Выберите кинотеатр!!!");
                alert.showAndWait();

            } else {

                showKinoSeansDataList();
            }
        }catch (Exception e){e.printStackTrace();}
    }
    public void viborDateSeansList(){

        connect = Database.connectDb();

        Alert alert;

        try{
            if(seans_vibor_seans.getSelectionModel().isEmpty()){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Выберите Дату!!!");
                alert.showAndWait();

            } else {

                showDateSeansDataList();

            }
        }catch (Exception e){e.printStackTrace();}
    }

    public void viborAllSeansList(){

        connect = Database.connectDb();

        Alert alert;

        try{
            if(seans_vibor_kinoteatr.getSelectionModel().isEmpty()||seans_vibor_seans.getSelectionModel().isEmpty()){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Выберите кинотеатр и дату!!!");
                alert.showAndWait();

            } else {

                showAllSeansDataList();
            }
        }catch (Exception e){e.printStackTrace();}
    }

    public void cancelKinoteatrsSeans(){

        connect = Database.connectDb();

        seans_vibor_kinoteatr.setValue("");

        try{

            showDateSeansDataList();

        }catch (Exception e){e.printStackTrace();}
    }

    public void cancelDateSeansFilms(){

        connect = Database.connectDb();

        seans_vibor_seans.setValue("");

        try{

            showDateSeansDataList();

        }catch (Exception e){e.printStackTrace();}
    }
    //------------------------------------------------------------------------------------------------------------------//
    //------------------------------------------------------------------------------------------------------------------//
    //------------------------------------------------------------------------------------------------------------------//

    public void selectAddFilmsList(){

        filmsData filmD = dobav_tableView.getSelectionModel().getSelectedItem();
        int num = dobav_tableView.getSelectionModel().getSelectedIndex();

        if((num-1)< -1){
            return;
        }

        getData.filmId = filmD.getId();

        dobav_nameField.setText(filmD.getFilm_name());
        dobav_studioField.setText(filmD.getFilm_studio());
        dobav_janrField.setText(filmD.getFilm_style());
        dobav_rejiserField.setText(filmD.getFilm_director());
        dobav_actorField.setText(filmD.getFilm_actors());
        dobav_orepField.setText(filmD.getFilm_operator());

        /*switch (filmD.getKinoteatr_name()) {
            case "Киноплекс" ->
                    dobav_vibor_Kinoteatr.getSelectionModel().select(0);
            case "Киномакс" ->
                    dobav_vibor_Kinoteatr.getSelectionModel().select(1);
            case "Imax" ->
                    dobav_vibor_Kinoteatr.getSelectionModel().select(2);
            case "Синема" ->
                    dobav_vibor_Kinoteatr.getSelectionModel().select(3);
            case "Рубин" ->
                    dobav_vibor_Kinoteatr.getSelectionModel().select(4);
        }*/

    }

    public void logout(){

        exit_to_login_Btn.getScene().getWindow().hide();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            AtomicReference<Double> x = new AtomicReference<>((double) 0);
            AtomicReference<Double> y = new AtomicReference<>((double) 0);
            root.setOnMousePressed((javafx.scene.input.MouseEvent event) ->{

                x.set(event.getSceneX());
                y.set(event.getSceneY());

            });

            root.setOnMouseDragged((MouseEvent event) -> {

                stage.setX(event.getScreenX() - x.get());
                stage.setY(event.getScreenY() - y.get());
            });


            stage.initStyle(StageStyle.TRANSPARENT);

            stage.setScene(scene);
            stage.show();


        } catch (Exception e){e.printStackTrace();}
    }

    public void switchForm(ActionEvent event){

        if(event.getSource()== kinoteatr_Btn){

            kinoteatr_window.setVisible(true);
            film_window.setVisible(false);
            seans_window.setVisible(false);
            dobav_window.setVisible(false);

            kinoteatr_Btn.setStyle("-fx-background-color:#2727f9a8");
            film_Btn.setStyle("-fx-background-color:transparent");
            seans_Btn.setStyle("-fx-background-color:transparent");
            dobav_Btn.setStyle("-fx-background-color:transparent");


        } else if(event.getSource() == film_Btn){

            kinoteatr_window.setVisible(false);
            film_window.setVisible(true);
            seans_window.setVisible(false);
            dobav_window.setVisible(false);

            kinoteatr_Btn.setStyle("-fx-background-color:transparent");
            film_Btn.setStyle("-fx-background-color:#2727f9a8");
            seans_Btn.setStyle("-fx-background-color:transparent");
            dobav_Btn.setStyle("-fx-background-color:transparent");
        } else if(event.getSource() == seans_Btn){

            kinoteatr_window.setVisible(false);
            film_window.setVisible(false);
            seans_window.setVisible(true);
            dobav_window.setVisible(false);

            kinoteatr_Btn.setStyle("-fx-background-color:transparent");
            film_Btn.setStyle("-fx-background-color:transparent");
            seans_Btn.setStyle("-fx-background-color:#2727f9a8");
            dobav_Btn.setStyle("-fx-background-color:transparent");

        } else if(event.getSource() == dobav_Btn){

            kinoteatr_window.setVisible(false);
            film_window.setVisible(false);
            seans_window.setVisible(false);
            dobav_window.setVisible(true);

            kinoteatr_Btn.setStyle("-fx-background-color:transparent");
            film_Btn.setStyle("-fx-background-color:transparent");
            seans_Btn.setStyle("-fx-background-color:transparent");
            dobav_Btn.setStyle("-fx-background-color:#2727f9a8");

        }
    }

    public void displayUsername(){
        username.setText(getData.username);
        dobav_vibor_Kinoteatr.getSelectionModel().select(0);
    }

    public void close(){
        System.exit(0);
    }

    public void minimaze(){
        Stage stage =(Stage)topForm.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        displayUsername();

        showAddFilmsList();

        showAddKinoteatrsList();

        showJanrFullFilmsList();

        showDirectorFullFilmsList();

        showDateSeansDataList();

        showKinoSeansDataList();

        showJanrSearchFullFilmsList();

        showDirectorSearchFullFilmsList();

        showAllSearchFullFilmsList();

        comboBox();

        comboBoxKinoteatr();

        comboBoxFilms1();

        comboBoxFilms2();

        comboBoxSeansKino();

        comboBoxSeansSeans();
    }
}
