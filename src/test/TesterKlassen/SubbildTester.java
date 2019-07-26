package de.academyident.ident.TesterKlassen;

import de.academyident.ident.validation.SubbildErsteller;

public class SubbildTester {
    public static void main(String[] args) {
        SubbildErsteller subbildErsteller = new SubbildErsteller();
        subbildErsteller.erstelleAdresse("src\\main\\resources\\tesseract\\ich_BACK.jpg");
    }
}
