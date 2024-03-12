package com.tondeuse.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tondeuse.api.model.Pelouse;
import com.tondeuse.api.model.Tondeuse;
import com.tondeuse.api.service.PelouseService;
import com.tondeuse.api.service.TondeuseService;
import com.tondeuse.api.utils.FichierUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
public class TondeuseController {

    private final TondeuseService tondeuseService;
    private final PelouseService pelouseService;

    public TondeuseController(TondeuseService tondeuseService, PelouseService pelouseService) {
        this.tondeuseService = tondeuseService;
        this.pelouseService = pelouseService;
    }

    @PostMapping("/executerInstructions")
    public ResponseEntity<?> executerInstructions(@RequestParam("fichier") MultipartFile fichier) {
        if (fichier.isEmpty()) {
            return new ResponseEntity<>("Le fichier est vide", HttpStatus.BAD_REQUEST);
        }

        try {
            // Enregistrer temporairement le fichier reçu
            Path tempFile = Files.createTempFile(null, null);
            Files.copy(fichier.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);
            
            //Lire et traiter le fichier
            Pelouse pelouse = FichierUtil.lireFichier(tempFile.toString());
            pelouseService.setPelouse(pelouse); 
            pelouseService.executerInstructionsPourToutesLesTondeuses(tondeuseService);

            // Supprimer le fichier temporaire
            new File(tempFile.toString()).delete();

            // Retourner les positions finales des tondeuses
            StringBuilder resultat = new StringBuilder();
            for (Tondeuse tondeuse : pelouse.getTondeuses()) {
                resultat.append(tondeuse.getPosition().getX())
                        .append(" ")
                        .append(tondeuse.getPosition().getY())
                        .append(" ")
                        .append(tondeuse.getPosition().getOrientation())
                        .append("\n");
            }

            return new ResponseEntity<>(resultat.toString(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Erreur lors de la lecture du fichier", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur lors du traitement des données du fichier", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}