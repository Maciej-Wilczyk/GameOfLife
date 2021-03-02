package functions;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.concurrent.ThreadLocalRandom;

public class InitialSetting {

    public Image setBeginning(Image image, int case_, int bound) {
        WritableImage wImage = new WritableImage(
                (int) image.getWidth(),
                (int) image.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();
        
        int middleX = 3;
        int middleY = 3;

        int step = 1;

        int randomNum;

        int i = 0, j = 0;
        for (int k = 0; k < 100; k++) {
            for (int l = 0; l < 100; l++) {

                if (case_ == 1) {

                    pixelWriter.setColor(middleX + step + j, middleY + i, Color.BLACK);
                    pixelWriter.setColor(middleX + 2 * step + j, middleY + i, Color.BLACK);

                    pixelWriter.setColor(middleX + j, middleY + step + i, Color.BLACK);
                    pixelWriter.setColor(middleX + 3 * step + j, middleY + step + i, Color.BLACK);

                    pixelWriter.setColor(middleX + step + j, middleY + 2 * step + i, Color.BLACK);
                    pixelWriter.setColor(middleX + 2 * step + j, middleY + 2 * step + i, Color.BLACK);
                }


                if (case_ == 2) {

                    pixelWriter.setColor(middleX + step + j, middleY + i, Color.BLACK);
                    pixelWriter.setColor(middleX + 2 * step + j, middleY + i, Color.BLACK);

                    pixelWriter.setColor(middleX + j, middleY + step + i, Color.BLACK);
                    pixelWriter.setColor(middleX + step + j, middleY + step + i, Color.BLACK);

                    pixelWriter.setColor(middleX + 2 * step + j, middleY + 2 * step + i, Color.BLACK);
                }

                if (case_ == 3) {
                    pixelWriter.setColor(middleX + j, middleY + i, Color.BLACK);
                    pixelWriter.setColor(middleX + j, middleY + step + i, Color.BLACK);
                    pixelWriter.setColor(middleX + j, middleY + 2 * step + i, Color.BLACK);

                }
                middleX += 8;
            }
            middleX = 3;
            middleY += 8;
        }
        if (case_ == 4) {
            for (int hi = 1; hi < image.getHeight() - 1; hi++) {

                for (int wi = 1; wi < image.getWidth() - 1; wi++) {
                    randomNum = ThreadLocalRandom.current().nextInt(0, bound + 1);
                    if (randomNum == 0)
                        pixelWriter.setColor(wi, hi, Color.BLACK);
                    else
                        pixelWriter.setColor(wi, hi, Color.WHITE);
                }
            }
        }
        return wImage;
    }

    public Image unChanging(Image image) {
        return setBeginning(image, 1, 0);
    }

    public Image glider(Image image) {
        return setBeginning(image, 2, 0);
    }

    public Image oscillator(Image image) {
        return setBeginning(image, 3, 0);
    }

    public Image random(Image image, int bound) {
        return setBeginning(image, 4, bound);
    }
}
