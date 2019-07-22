package de.academyident.ident.controller;

import de.academyident.ident.model.TesseractFile;
import de.academyident.ident.util.TesseractIdent;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.io.IOException;

@Controller
public class TesseractController {

    @PostMapping(value = "/fileUpload")
    public String bearbeiteDaten(Model model,
                                 @ModelAttribute("tessImage") TesseractFile tesseractFile){


        File image = new File("tessfile.jpg");

        try {
            tesseractFile.getTessImage().transferTo(image); // FEHLER
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fullText = TesseractIdent.leseTextaus(image);

        System.out.println(fullText);

        return "tessPruefung";
    }
}
