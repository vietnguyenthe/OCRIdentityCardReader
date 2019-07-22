package de.academyident.ident.util;

import de.academyident.ident.model.TesseractFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveFile {

    public static void saveFileOnDisk(TesseractFile tesseractFile, String filename) {
        String folder = "src\\main\\resources\\tesseract";

        byte[] bytes = null;
        try {
            bytes = tesseractFile.getTessImage().getBytes();
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
