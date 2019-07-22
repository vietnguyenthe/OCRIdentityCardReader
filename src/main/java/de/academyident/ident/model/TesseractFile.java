package de.academyident.ident.model;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class TesseractFile {

    MultipartFile tessImage;

    public MultipartFile getTessImage() {
        return tessImage;
    }

    public void setTessImage(MultipartFile tessImage) {
        this.tessImage = tessImage;
    }
}
