package de.academyident.ident.repository;

import de.academyident.ident.model.BundesDatenbank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BundesDatenbankRepo extends JpaRepository<BundesDatenbank, Integer> {
}
