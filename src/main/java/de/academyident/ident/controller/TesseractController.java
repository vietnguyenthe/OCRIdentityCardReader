package de.academyident.ident.controller;

import de.academyident.ident.model.TesseractFile;
import de.academyident.ident.util.SaveFile;
import de.academyident.ident.util.TesseractIdent;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class TesseractController {


    @PostMapping(value = "/fileUpload")
    public String bearbeiteDaten(Model model,
                                 @ModelAttribute("tessImage") TesseractFile tesseractFile){


        SaveFile.saveFileOnDisk(tesseractFile, "testestestes.jpg");



        return "tessPruefung";
    }
}
