package de.academyident.ident.ocr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SubbildErsteller {


    public BufferedImage erhalteRueckseite(String path) {
        BufferedImage rueckseite = null;
        try {
            rueckseite = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rueckseite;
    }

    public BufferedImage erhalteVorderseite(String path) {
        BufferedImage vorderseite = null;
        try {
            vorderseite = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vorderseite;
    }

    //Adresse wird gespeichert als Bild "adresse.jpg" im Ordner Tesseract
    public void erstelleAdresse(String path){
        BufferedImage rueckseite = erhalteRueckseite(path);
        try {
            BufferedImage adresse = rueckseite.getSubimage(
                    (int) (rueckseite.getWidth()*0.4),
                    (int) (rueckseite.getHeight() * 0),
                    (int) (rueckseite.getWidth()*0.4),
                    (int) (rueckseite.getHeight() * 0.3));
            File adresseBeispiel =
                    new File("src\\main\\resources\\static\\img\\adresse.jpg");
            ImageIO.write(adresse, "jpg", adresseBeispiel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Maschinenlesbare Zone wird gespeichert als Bild "maschinenLesbareZone.jpg" im Ordner Tesseract
    public void erstelleMaschinenlesbareZone(String path) {
        BufferedImage rueckseite = erhalteRueckseite(path);
        try {
            BufferedImage maschinenLesbareZone = rueckseite.getSubimage(0, (int) (rueckseite.getHeight() * 0.6),
                    rueckseite.getWidth(), (int) (rueckseite.getHeight() * 0.4));
            File maschinenLesbareZoneBeispiel =
                    new File("src\\main\\resources\\static\\img\\maschinenLesbareZone.jpg");
            ImageIO.write(maschinenLesbareZone, "jpg", maschinenLesbareZoneBeispiel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //AusweisID wird gespeichert als Bild "ausweisID.jpg" im Ordner Tesseract
    public void erstelleAusweisID(String path){
        BufferedImage vorderseite = erhalteVorderseite(path);
        try {
            BufferedImage ausweisID = vorderseite.getSubimage((int) (vorderseite.getWidth() * 0.65),
                    (int) (vorderseite.getHeight() * 0),
                    (int) (vorderseite.getWidth() * 0.35),
                    (int) (vorderseite.getHeight() * 0.15));
            File ausweisIDBeispiel =
                    new File("src\\main\\resources\\static\\img\\ausweisID.jpg");
            ImageIO.write(ausweisID, "jpg", ausweisIDBeispiel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Nachname wird gespeichert als Bild "nachname.jpg" im Ordner Tesseract
    public void erstelleNachname(String path) {
        BufferedImage vorderseite = erhalteVorderseite(path);
        try {
            BufferedImage nachname = vorderseite.getSubimage((int) (vorderseite.getWidth() * 0.38),
                    (int) (vorderseite.getHeight() * 0.1),
                    (int) (vorderseite.getWidth() * 0.46),
                    (int) (vorderseite.getHeight() * 0.2));
            File nachnameBeispiel =
                    new File("src\\main\\resources\\static\\img\\nachname.jpg");
            ImageIO.write(nachname, "jpg", nachnameBeispiel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Vorname wird gespeichert als Bild "nachname.jpg" im Ordner Tesseract
    public void erstelleVorname(String path) {
        BufferedImage vorderseite = erhalteVorderseite(path);
        try {
            BufferedImage vorname = vorderseite.getSubimage((int) (vorderseite.getWidth() * 0.38),
                    (int) (vorderseite.getHeight() * 0.28),
                    (int) (vorderseite.getWidth() * 0.46),
                    (int) (vorderseite.getHeight() * 0.12));
            File vornameBeispiel =
                    new File("src\\main\\resources\\static\\img\\vorname.jpg");
            ImageIO.write(vorname, "jpg", vornameBeispiel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Geburtstag wird gespeichert als Bild "geburtstag.jpg" im Ordner Tesseract
    public void erstelleGeburtstag(String path) {
        BufferedImage vorderseite = erhalteVorderseite(path);
        try {
            BufferedImage geburtstag = vorderseite.getSubimage(
                    (int) (vorderseite.getWidth() * 0.42),
                    (int) (vorderseite.getHeight() * 0.4),
                    (int) (vorderseite.getWidth() * 0.26),
                    (int) (vorderseite.getHeight() * 0.12));
            File geburtstagBeispiel =
                    new File("src\\main\\resources\\static\\img\\geburtstag.jpg");
            ImageIO.write(geburtstag, "jpg", geburtstagBeispiel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Staatsangehoerigkeit gespeichert als Bild "staatsangehoerigkeit.jpg" im Ordner Tesseract
    public void erstelleStaatsangehoerigkeit(String path) {
        BufferedImage vorderseite = erhalteVorderseite(path);
        try {
            BufferedImage staatsangehoerigkeit = vorderseite.getSubimage(
                    (int) (vorderseite.getWidth() * 0.42),
                    (int) (vorderseite.getHeight() * 0.52),
                    (int) (vorderseite.getWidth() * 0.26),
                    (int) (vorderseite.getHeight() * 0.12));
            File staatsangehoerigkeitBeispiel =
                    new File("src\\main\\resources\\static\\img\\staatsangehoerigkeit.jpg");
            ImageIO.write(staatsangehoerigkeit, "jpg", staatsangehoerigkeitBeispiel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Geburtsort gespeichert als Bild "geburtsort.jpg" im Ordner Tesseract
    public void erstelleGeburtsort(String path) {
        BufferedImage vorderseite = erhalteVorderseite(path);
        try {
            BufferedImage geburtsort = vorderseite.getSubimage(
                    (int) (vorderseite.getWidth() * 0.42),
                    (int) (vorderseite.getHeight() * 0.54),
                    (int) (vorderseite.getWidth() * 0.5),
                    (int) (vorderseite.getHeight() * 0.12));
            File geburtsortBeispiel =
                    new File("src\\main\\resources\\static\\img\\geburtsort.jpg");
            ImageIO.write(geburtsort, "jpg", geburtsortBeispiel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
