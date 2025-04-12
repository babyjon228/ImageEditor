package com.example.njrat;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;


public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        HBox root = new HBox();
        HBox v1 = new HBox();
        VBox v2 = new VBox();
        root.getChildren().add(v1);
        root.getChildren().add(v2);



        Button openButton = new Button("Open image");
        ComboBox<String> choiceBox = new ComboBox<>();
        choiceBox.setValue("Choose");
        Button Process = new Button("Process");
        Button save = new Button("Save");

        choiceBox.getItems().addAll("BLur" , "GrayScale","Inversion");

        ImageView original = new ImageView();
        ImageView result = new ImageView();
        original.setFitHeight(350);
        original.setFitWidth(320);
        result.setFitHeight(350);
        result.setFitWidth(320);


        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter( "*.png", "*.jpg", "*.bmp"));
        v1.getChildren().addAll(openButton, choiceBox, Process, save);
        v2.getChildren().addAll(original, result);

        openButton.setOnMouseEntered(event -> {
            if(openButton.hoverProperty().getValue()) {
                openButton.setStyle("-fx-background-color: #989898");
            }
        });
        openButton.setOnMouseExited(event -> {
            openButton.setStyle("");
        });
        Process.setOnMouseEntered(event -> {
            if(Process.hoverProperty().getValue()) {
                Process.setStyle("-fx-background-color: #989898");
            }
        });
        Process.setOnMouseExited(event -> {
                Process.setStyle("");
        });
        save.setOnMouseEntered(event -> {
            if(save.hoverProperty().getValue()) {
                save.setStyle("-fx-background-color: #989898");
            }
        });
        save.setOnMouseExited(event -> {
                save.setStyle("");
        });


        openButton.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(stage);
            if(file != null) {
                Image a = new Image(file.toURI().toString());
                original.setImage(a);
            }
        });


        Process.setOnAction(e -> {
          switch(choiceBox.getValue()) {
              case "GrayScale" :
                  Image grayImage = process.grayscale(original.getImage());
                  result.setImage(grayImage);
                  break;

              case "Inversion" :
                Image invert = process.inv(original.getImage());
                result.setImage(invert);
                break;

              case "Blur" :

          }
        });

        save.setOnAction(e -> {
            Image saveIm = result.getImage();
            if (saveIm != null) {
                FileChooser saveFileChooser = new FileChooser();
                saveFileChooser.setTitle("Save Image");
                saveFileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG Files", "*.png" , "*.jpg" , "*.bmp"));
                File file = saveFileChooser.showSaveDialog(stage);
                if (file != null) {
                    try {
                        BufferedImage bufferedImage = process.convToBuff(saveIm);
                        ImageIO.write(bufferedImage, "png", file);
                    }
                    catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });


         v1.setAlignment(Pos.TOP_CENTER);
         v1.setSpacing(25);
         v1.setPadding(new Insets(10));
         v2.setSpacing(25);


        Scene scene = new Scene(root, 920, 720);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}

