package gui;

import cell.Cell;
import functions.InitialSetting;
import functions.MatrixCell;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class MainWindow implements Initializable {

    @FXML
    public AnchorPane pane;
    @FXML
    public Canvas canvas;
    @FXML
    public ChoiceBox choiceBox;
    @FXML
    public TextField textField;
    @FXML
    public Label label;
    @FXML
    public Button setBeginning;


    private String imagePath = "assets/board.bmp";
    private Image image;
    GraphicsContext gc;
    InitialSetting initialSetting = new InitialSetting();
    MatrixCell matrixCell;
    private boolean startStop = true;
    // List<Cell[][]>list;
    //Thread thread;
    WritableImage wImage;
    PixelWriter pixelWriter;

    public void click() {
         wImage = new WritableImage(
                (int) image.getWidth(),
                (int) image.getHeight());
         pixelWriter = wImage.getPixelWriter();
        canvas.setOnMouseClicked(event -> {
            double x = event.getX(), y = event.getY();
            pixelWriter.setColor((int) x, (int) y, Color.BLACK);
            System.out.println("dzialam");
            image = wImage;
            gc.drawImage(image, 0, 0);
        });
    }


    public void setBeginning() {
        String choice = choiceBox.getValue().toString();
        if (choice.equals("unchanging"))
            image = initialSetting.unChanging(image);
        if (choice.equals("glider"))
            image = initialSetting.glider(image);
        if (choice.equals("oscillator"))
            image = initialSetting.oscillator(image);
        if (choice.equals("random")) {
            image = initialSetting.random(image, Integer.parseInt(textField.getText()));
        }
        gc.drawImage(image, 0, 0);
        System.out.println("set beginning");
    }


    public void start() {

        startStop = true;

        Thread thread = new Thread(() -> {
            List<Cell[][]> list = null;
            matrixCell = new MatrixCell();


            while (startStop) {
                list = matrixCell.makeMatrixList(image);
                System.out.println("work");
                image = matrixCell.lifeGame(image, list);

                gc.drawImage(image, 0, 0);
            }
        });

        thread.start();

    }

    public void stop() {
        startStop = false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        image = new Image((imagePath));
        gc = canvas.getGraphicsContext2D();
        gc.drawImage(image, 0, 0);
        choiceBox.setValue("unchanging");
        textField.setVisible(false);
        textField.setText("3");
        label.setVisible(false);
    }


    public void reset() {
        gc.clearRect(0, 0, 800, 800);
        image = new Image(imagePath);
        wImage = new WritableImage(
                (int) image.getWidth(),
                (int) image.getHeight());
        pixelWriter = wImage.getPixelWriter();
        gc.drawImage(image, 0, 0);
    }

    public void checkChoiceBoxV() {
        choiceBox.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> {
            int choice = t1.intValue();
            if (choice == 3) {
                textField.setVisible(true);
                label.setVisible(true);
            } else {
                textField.setVisible(false);
                label.setVisible(false);
            }
            if (choice == 4) {
                setBeginning.setVisible(false);
            } else {
                setBeginning.setVisible(true);
            }
        });
    }
}
