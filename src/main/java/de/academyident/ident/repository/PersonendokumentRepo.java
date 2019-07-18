package de.academyident.ident.repository;

import de.academyident.ident.model.Personendokument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonendokumentRepo extends JpaRepository<Personendokument, Integer> {
}
