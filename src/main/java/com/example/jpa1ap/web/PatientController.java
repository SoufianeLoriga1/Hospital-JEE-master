package com.example.jpa1ap.web;

import com.example.jpa1ap.entities.Patient;
import com.example.jpa1ap.repositories.PatientRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;

    @GetMapping(path="/user/index")
     public String patients(Model model,
                            @RequestParam(name ="page", defaultValue = "0") int page,
                            @RequestParam(name ="size", defaultValue = "5") int size,
                            @RequestParam(name ="keyword", defaultValue = "") String keyword){
         Page<Patient> pagePatients=patientRepository.findByNomContains(keyword,PageRequest.of(page,size));


         model.addAttribute("listPatients",pagePatients.getContent());

         model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
         model.addAttribute("currentPage",page);
         model.addAttribute("keyword",keyword);


         return "Patients";
    }
    @GetMapping("/admin/delete")
    //@PreAuthorize("hasRole('ROLE ADMIN')")
    public String delete(Long id, String keyword, int page) {
        patientRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/")
    public String home() {
        return "redirect:/user/index";
    }

    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> lisPatients(){
        return patientRepository.findAll();
    }

    @GetMapping("/admin/formPatients")
    //@PreAuthorize("hasRole('ROLE ADMIN')")
    public String formPatients(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatients";

    }

    @PostMapping(path = "/admin/save")
    //@PreAuthorize("hasRole('ROLE ADMIN')")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword){
        if (bindingResult.hasErrors()) return "formPatients";
        patientRepository.save(patient);
        return "redirect:/user/index?page"+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/editPatient")
    //@PreAuthorize("hasRole('ROLE ADMIN')")
    public String editPatient(Model model, Long id, String keyword, int page){
        Patient patient=patientRepository.findById(id).orElse(null); //Cherche moi ce patient s\il n'existe pas tu vas retourner null
        if(patient==null) throw new RuntimeException("Patient introuvable");
        model.addAttribute("patient",patient);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editPatient";

    }




}
