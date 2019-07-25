package de.academyident.ident.model;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class TesseractFile {

    MultipartFile frontImage;
    String pathFrontImage;

    MultipartFile backImage;
    String pathBackImage;

    public MultipartFile getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(MultipartFile frontImage) {
        this.frontImage = frontImage;
    }

    public MultipartFile getBackImage() {
        return backImage;
    }

    public void setBackImage(MultipartFile backImage) {
        this.backImage = backImage;
    }

    public String getPathFrontImage() {
        return pathFrontImage;
    }

    public void setPathFrontImage(String pathFrontImage) {
        this.pathFrontImage = pathFrontImage;
    }

    public String getPathBackImage() {
        return pathBackImage;
    }

    public void setPathBackImage(String pathBackImage) {
        this.pathBackImage = pathBackImage;
    }
}
