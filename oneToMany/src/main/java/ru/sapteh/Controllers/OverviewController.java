package ru.sapteh.Controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.model.Role;
import ru.sapteh.model.User;
import ru.sapteh.model.UserRole;
import ru.sapteh.service.RoleDaoImp;
import ru.sapteh.service.UserDaoImp;
import ru.sapteh.service.UserRoleDaoImp;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class OverviewController {
    SessionFactory factory = new Configuration().configure().buildSessionFactory();
    DAO<User,Integer> userDaoImp = new UserDaoImp(factory);


    @FXML
    private TableView<User> tableUser;

    @FXML
    private TableColumn<User,Integer> columnId;
    @FXML
    private TableColumn<User,String> columnLastName;
    @FXML
    private TableColumn<User,String> columnFirstName;
    @FXML
    private TableColumn<User,String> columnRegDate;
    @FXML
    private TableColumn<User,Integer> columnCount;
    @FXML
    private Button fxButtonGiveRole;
    @FXML
    private Button fxButtonNewUser;
    @FXML
    private Label LabelID;
    @FXML
    private Label LabelFirstName;
    @FXML
    private Label LabelLastName;
    @FXML
    private Label LabelCount;
    @FXML
    private Label LabelRegistrationDate;


    ObservableList<User> listUser = FXCollections.observableArrayList();
    ObservableList<Role> listRole = FXCollections.observableArrayList();
    ObservableList<UserRole> listUserRole = FXCollections.observableArrayList();


    public void initialize(){
        List<User> list = userDaoImp.findByAll();

        listUser.addAll(list);

        columnId.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getId()));
        columnLastName.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getLastName()));
        columnFirstName.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getFirstName()));
        columnRegDate.setCellValueFactory(p ->
                    new SimpleObjectProperty<>(p.getValue().getUser_roles().iterator().next().getRegistrationDate()));
        columnCount.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().sizeRoleMethod()));
        tableUser.setItems(listUser);
        fxButtonNewUser.setOnAction(event -> {
            fxButtonNewUser.getScene().getWindow();
            try {
                URL url = new File("c:/oneToMany/src/main/java/ru/sapteh/view/buttonNewUser.fxml").toURI().toURL();
                Parent root = null;
                root = FXMLLoader.load(url);
                Stage stage = new Stage();
                stage.setTitle("newUser");
                stage.setScene(new Scene(root));
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        factory.close();
    }
    public void showUserDetails(User user) throws NoSuchFieldException {
        if (user != null) {
//            LabelID.setText(user.getId());
//            LabelFirstName.setText(user.getFirstName());
//            LabelLastName.setText(user.getLastName());
//            LabelCount.setText(user.getSizeRole());
//            LabelRegistrationDate.setText(UserRole.class, getClass().getField("registrationDate"));
        } else {
//            firstNameLabel.setText("");
//            lastNameLabel.setText("");
//            streetLabel.setText("");
//            postalCodeLabel.setText("");
//            cityLabel.setText("");
//            birthdayLabel.setText("");
        }
    }
}
