package de.academyident.ident.TesterKlassen;

import de.academyident.ident.util.SubbildErsteller;

public class SubbildTester {
    public static void main(String[] args) {
        SubbildErsteller subbildErsteller = new SubbildErsteller();
        subbildErsteller.erstelleAdresse("src\\main\\resources\\tesseract\\ich_BACK.jpg");
    }
}
