package com.company.view;
import com.company.model.HracPloch;
import com.company.model.Kostka;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.fxml.FXML;

import java.awt.*;
import java.util.Random;

public class MainController {

    HracPloch hraciplocha;

    int[][] mara = new int[][] {
            {0 , 0 , 0 , 0 , 1 , 2 , 3 , 0 , 0 , 0 , 0},
            {0 , 0 , 0 , 0 , 40, 0 , 4 , 0 , 0 , 0 , 0},
            {0 , 0 , 0 , 0 , 39, 0 , 5 , 0 , 0 , 0 , 0},
            {0 , 0 , 0 , 0 , 3 , 0 , 6 , 0 , 0 , 0 , 0},
            {33, 34, 35, 36, 37, 0 , 7 , 8 , 9 , 10, 11},
            {32, 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 12},
            {31, 30, 29, 28, 27, 0 , 17, 16, 15, 14, 13},
            {0 , 0 , 0 , 0 , 26, 0 , 18, 0 , 0 , 0 , 0},
            {0 , 0 , 0 , 0 , 25, 0 , 19, 0 , 0 , 0 , 0},
            {0 , 0 , 0 , 0 , 24, 0 , 20, 0 , 0 , 0 , 0},
            {0 , 0 , 0 , 0 , 23, 22, 21, 0 , 0 , 0 , 0},
    };

    int[][][] domecky = new int[][][] {
            {{0, 0}, {1, 0}, {1, 1}, {0, 1}},
            {{9, 0}, {10, 0}, {10, 1}, {9, 1}},
            {{9, 9}, {10, 9}, {10, 10}, {9, 10}},
            {{0, 9}, {1, 9}, {1, 10}, {0, 10}}
    };

    @FXML
    public FlowPane Standa;

    @FXML
    public GridPane Lada;

    @FXML
    public void novaplocha(){

        hraciplocha = new HracPloch(40, 4, 4, new Kostka(6));

        for (int i=0; i<11; i++){
            for(int i1=0; i1<11; i1++){
                if(mara[i1][i] > 0){
                    Button tlaca = new Button(String.valueOf(mara[i1][i]));
                    tlaca.setPrefSize(Lada.getWidth() / 11, Lada.getHeight() / 11);
                    tlaca.setFocusTraversable(false);

                    Lada.add(tlaca,i,i1);
                }
            }
        }


        String[] barvy = new String[] {
                "ff0000",
                "0000ff",
                "00cc00",
                "ddcc00",
        };

        for(int a=0; a<4; a++) {
            for (int b=0; b<4; b++) {
                Button pepik = new Button(String.valueOf(b + 1));
                pepik.setPrefSize(Lada.getWidth() / 11, Lada.getHeight() / 11);
                pepik.setFocusTraversable(false);
                pepik.setStyle("-fx-border-color: #" + barvy[a] + ";" +
                        "-fx-border-width: 2" + ";" +
                        "-fx-border-insets: 0" + ";");

                Lada.add(pepik, domecky[a][b][0], domecky[a][b][1]);
            }
        }


        Button kostka = new Button("X");
        kostka.setPrefSize(Lada.getWidth() / 11, Lada.getHeight() / 11);
        kostka.setFocusTraversable(false);
        kostka.setOnAction(actionEvent ->
        {
            kostka.setText(String.valueOf(new Random().nextInt(6) + 1));
        });

        Lada.add(kostka, 5, 5);
    }

    public void pohyb(int pole){

    }

    @FXML
    public void vytvoreniPlochy(){
        Button[] policka=new Button[40];
        for (int i=0; i<40; i++){
            policka [i]= new Button();
            policka[i].setOnAction(e -> System.out.println("Hello"));
        }
        Standa.getChildren().addAll(policka);
    }

    public void vytovreniStartDomecku(){
        Button[] policka=new Button[16];
        for (int i=0; i<16; i++) {
            policka[i] = new Button();
        }
        Standa.getChildren().addAll(policka);
    }
    public void vytvoreniFinalDomecku(){
        Button[] policka=new Button[16];
        for (int i=0; i<16; i++) {
            policka[i] = new Button();
        }
        Standa.getChildren().addAll(policka);
    }

}