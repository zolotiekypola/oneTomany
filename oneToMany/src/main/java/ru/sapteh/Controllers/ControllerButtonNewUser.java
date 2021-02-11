package ru.sapteh.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.model.User;
import ru.sapteh.service.UserDaoImp;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ControllerButtonNewUser {
    SessionFactory factory = new Configuration().configure().buildSessionFactory();

    DAO<User,Integer> userDaoImp = new UserDaoImp(factory);

    @FXML
    private TextField txtFirstName;
    @FXML
    private  TextField txtLastName;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonExit;
    @FXML
    private Label labelUser;

    public void initialize() throws Exception{


        buttonAdd.setOnAction(event -> {
            buttonAdd.getScene().getWindow().hide();
                userDaoImp.create(new User(txtFirstName.getText(),txtLastName.getText()));
        });
        buttonExit.setOnAction(event -> {
            buttonExit.getScene().getWindow().hide();
        });
    }
}
