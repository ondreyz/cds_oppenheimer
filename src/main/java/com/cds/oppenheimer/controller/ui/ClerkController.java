package com.cds.oppenheimer.controller.ui;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

import com.cds.oppenheimer.dto.model.WorkingClassHeroDTO;
import com.cds.oppenheimer.dto.model.WorkingClassHeroMapper;
import com.cds.oppenheimer.exception.DuplicateEntityException;
import com.cds.oppenheimer.model.WorkingClassHero;
import com.cds.oppenheimer.service.WorkingClassHeroService;

import com.opencsv.bean.CsvToBeanBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clerk")
public class ClerkController {
    @Autowired
    WorkingClassHeroService workingClassHeroService;

    @GetMapping()
    public ModelAndView showClerkPage() {
        return new ModelAndView("clerk");
    }

    @PostMapping()
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        String uiFeedbackMessage = null;
        
        try {
            Reader reader = new InputStreamReader(file.getInputStream());
            List<WorkingClassHeroDTO> workingClassHeroesToAdd =
                new CsvToBeanBuilder<WorkingClassHeroDTO>(reader)
                    .withType(WorkingClassHeroDTO.class)
                    .build().parse();

            List<WorkingClassHero> workingClassHeroes =
                workingClassHeroesToAdd
                    .stream()
                    .map(workingClassHeroToAdd -> WorkingClassHeroMapper.toWorkingClassHero(workingClassHeroToAdd))
                    .collect(Collectors.toList());
            workingClassHeroService.addWorkingClassHeroes(workingClassHeroes);

            uiFeedbackMessage = "Successfully created records!";
        } catch (IOException | IllegalStateException processFileException) {
            uiFeedbackMessage = "Error processing file";
        } catch (DuplicateEntityException duplicateEntityException) {
            uiFeedbackMessage = duplicateEntityException.getMessage();
        } catch (Exception generalException) {
            uiFeedbackMessage = generalException.getMessage();
        }

        if (uiFeedbackMessage != null) {
            redirectAttributes.addFlashAttribute("message", uiFeedbackMessage);
        }

        return "redirect:/clerk";
  }
}