package de.academyident.ident.util;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class LokaleBilddateien {

    public static void loeschen(List<String> paths) {

        for (String path: paths) {

            Path imagePath = Paths.get(path);

            try {
                Files.delete(imagePath);
            } catch (NoSuchFileException x) {
                System.err.format("%s: no such" + " file or directory%n", imagePath);
            } catch (DirectoryNotEmptyException x) {
                System.err.format("%s not empty%n", imagePath);
            } catch (IOException x) {
                // File permission problems are caught here.
                System.err.println(x);
            }
        }

    }

}
