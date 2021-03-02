package functions;

import cell.Cell;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class MatrixCell {

    Cell[][] tab;
    List list;
    int pomW = -1;
    int pomH = -1;

    int wiPosition, hiPosition;
    PixelReader pixelReader;


    public List<Cell[][]> makeMatrixList(Image image) {
        list = new ArrayList();
        pixelReader = image.getPixelReader();

        for (int hi = 0; hi < image.getHeight(); hi++) {
            for (int wi = 0; wi < image.getWidth(); wi++) {

                tab = new Cell[3][];
                for (int i = 0; i < 3; i++) {
                    tab[i] = new Cell[3];
                    for (int j = 0; j < 3; j++) {
                        tab[i][j] = new Cell();
                    }
                }

                for (int i = 0; i < tab[0].length; i++) {
                    for (int j = 0; j < tab.length; j++) {

                        wiPosition = wi + pomW;
                        hiPosition = hi + pomH;

                        if(wiPosition == -1){
                            wiPosition = (int) image.getWidth()-1;
                        }
                       else if(wiPosition == (int) image.getWidth()){
                            wiPosition = 0;
                        }

                        if (hiPosition == -1) {
                            hiPosition = (int) image.getHeight() -1;
                        } else if (hiPosition == (int) image.getHeight()) {
                            hiPosition = 0;
                        }


                        if (pixelReader.getColor(wiPosition, hiPosition).equals(Color.BLACK)) {

                            tab[i][j].setColor(Color.BLACK);

                        } else
                            tab[i][j].setColor(Color.WHITE);

                        pomW++;
                    }

                    pomW = -1;
                    pomH++;
                }

                pomH = -1;
                tab[1][1].setWi(wi);
                tab[1][1].setHi(hi);

                list.add(tab);
            }
        }
        return list;
    }

    public Color lifeCheck(List<Cell[][]> list, int b) {

        int counter = 0;
        Color mainCell = null;


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (list.get(b)[i][j].getColor().equals(Color.WHITE) && i == 1 && j == 1) {//martwa
                    mainCell = Color.WHITE;
                    continue;
                }
                if (list.get(b)[i][j].getColor().equals(Color.BLACK) && i == 1 && j == 1) {//zywa
                    mainCell = Color.BLACK;
                    continue;
                }

                if (list.get(b)[i][j].getColor().equals(Color.BLACK)) {
                    counter++;
                }

            }
        }
        if (mainCell.equals(Color.WHITE) && counter == 3) {


            mainCell = Color.BLACK; // ozywa

        }
        if (mainCell.equals(Color.BLACK)) {
            if (counter == 2 || counter == 3) {
                mainCell = Color.BLACK;//pozostaje zywa
            }
            if (counter > 3) {
                mainCell = Color.WHITE; // umiera z natloku
            }
            if (counter < 2) {
                mainCell = Color.WHITE; //umiera z samotnosci
            }
        }


        return mainCell;
    }

    public Image lifeGame(Image image, List<Cell[][]> list) {
        WritableImage wImage = new WritableImage(
                (int) image.getWidth(),
                (int) image.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();
        Color a;
        int help = 0;
        for (int hi = 0; hi < image.getHeight() ; hi++) {
            for (int wi = 0; wi < image.getWidth() ; wi++) {
                a = lifeCheck(list, help);
                pixelWriter.setColor(wi, hi, a);
                help += 1;
            }
        }
        return wImage;
    }

}
