package com.projeto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/main-view.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Gerenciador de Tarefas");
        stage.setScene(scene);
        stage.setWidth(700);
        stage.setHeight(500);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

