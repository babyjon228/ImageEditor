package com.example.njrat;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import java.awt.image.BufferedImage;
import java.nio.IntBuffer;

public class process {

    public static Image grayscale(Image a) {
        int width = (int) a.getWidth();
        int height = (int) a.getHeight();

        WritableImage rs = new WritableImage(width, height);
        PixelWriter pw = rs.getPixelWriter();
        PixelReader pr = a.getPixelReader();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color c = pr.getColor(x , y);
                Pixel Pixel = new Pixel((int) (c.getRed() * 255), (int) (c.getGreen() * 255), (int) (c.getBlue() * 255));
                int gr = (Pixel.getRed() + Pixel.getGreen() + Pixel.getBlue()) / 3;
                Color gray = Color.rgb(gr , gr , gr);
                pw.setColor(x, y, gray);
            }
        }
        return rs;
    }


    public static Image inv(Image a) {
        int width = (int) a.getWidth();
        int height = (int) a.getHeight();
        PixelReader pr = a.getPixelReader();
        WritableImage result = new WritableImage(width, height);
        PixelWriter pw = result.getPixelWriter();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color c = pr.getColor(x , y);
                Pixel Pixel = new Pixel((int) (c.getRed() * 255), (int) (c.getGreen() * 255), (int) (c.getBlue() * 255));
                int inRed = 255 - Pixel.getRed();
                int inGreen = 255 - Pixel.getGreen();
                int inBlue = 255 - Pixel.getBlue();
                Color invertedcs = Color.rgb(inRed, inGreen, inBlue);
                pw.setColor(x, y, invertedcs );
            }
        }
        return result;
    }




    public static BufferedImage convToBuff (Image a) {
        int width = (int) a.getWidth();
        int height = (int) a.getHeight();
        PixelReader pr = a.getPixelReader();
        BufferedImage bfIm = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        WritablePixelFormat<IntBuffer> pf = WritablePixelFormat.getIntArgbInstance();
        int[] b = new int[width];

        for (int y = 0; y < height; y++) {
            pr.getPixels(0, y, width, 1, pf, b, 0, width);
            bfIm.setRGB(0, y, width, 1, b, 0, width);
        }

        return bfIm;
    }
}
