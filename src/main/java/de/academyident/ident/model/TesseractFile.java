package de.academyident.ident.model;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class TesseractFile {

    MultipartFile frontImage = null;
    MultipartFile backImage = null;

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
}
