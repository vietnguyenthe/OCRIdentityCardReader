package de.academyident.ident.util;

import de.academyident.ident.model.TesseractFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveFile {

    public static void saveFrontOnDisk(TesseractFile tesseractFile, String filename) {
        String folder = "src\\main\\resources\\static\\img";

        byte[] bytes = null;
        try {
            bytes = tesseractFile.getFrontImage().getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Path path = Paths.get(folder + "\\" + filename);

        try {
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveBackOnDisk(TesseractFile tesseractFile, String filename) {
        String folder = "src\\main\\resources\\static\\img";

        byte[] bytes = null;
        try {
            bytes = tesseractFile.getBackImage().getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Path path = Paths.get(folder + "\\" + filename);

        try {
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
