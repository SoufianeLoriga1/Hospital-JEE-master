
package com.example.jpa1ap.repositories;

import com.example.jpa1ap.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public interface PatientRepository  extends JpaRepository<Patient, Long> {

    Page<Patient> findByNomContains(String kw, Pageable pageable);
}
