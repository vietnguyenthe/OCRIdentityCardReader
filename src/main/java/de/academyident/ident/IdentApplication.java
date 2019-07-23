package de.academyident.ident;

import de.academyident.ident.model.BundesDatenbank;
import de.academyident.ident.repository.BundesDatenbankRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class IdentApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdentApplication.class, args);
    }

    @Autowired
    private BundesDatenbankRepo bundesDatenbankRepo;

    @PostConstruct
    public void ausfuellenDB(){
        BundesDatenbank florianWeiss = new BundesDatenbank("Florian", "Weiss","blabla",
        2, 11111, "Koeln", "deutsch", "00000", "13.01.1990",
                "Hamburg");
        BundesDatenbank vietNg = new BundesDatenbank("Viet", "Ng","blabla",
                2, 11111, "Koeln", "deutsch", "00000", "13.01.1990",
                "Hamburg");
        BundesDatenbank oliverHock= new BundesDatenbank("Oliver", "Hock","blabla",
                2, 11111, "Koeln", "deutsch", "00000", "13.01.1990",
                "Hamburg");
        BundesDatenbank peterMaier = new BundesDatenbank("Peter", "Maier","blabla",
                2, 11111, "Koeln", "deutsch", "00000", "13.01.1990",
                "Hamburg");
        BundesDatenbank peterWeiss = new BundesDatenbank("Peter", "Weiss","blabla",
                2, 11111, "Koeln", "deutsch", "00000", "13.01.1990",
                "Hamburg");
        BundesDatenbank erikaMustermann = new BundesDatenbank("ERIKA", "MUSTERMANN","blabla",
                2, 11111, "Koeln", "deutsch", "00000", "13.01.1990",
                "Hamburg");
        bundesDatenbankRepo.save(florianWeiss);
        bundesDatenbankRepo.save(vietNg);
        bundesDatenbankRepo.save(oliverHock);
        bundesDatenbankRepo.save(peterWeiss);
        bundesDatenbankRepo.save(peterMaier);
        bundesDatenbankRepo.save(erikaMustermann);
    }

}
