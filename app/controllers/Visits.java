package controllers;

import controllers.dto.ResponseContainer;
import models.Pet;
import models.Visit;
import play.data.validation.Valid;
import play.data.validation.Validation;

/**
 * Controller for the visits. The add visit will show the add visit page in a dialog
 * If the user saves the visit, the jsonResponse will be send back to the dialog which
 * will act on this.
 */
public class Visits extends Application {

    public static void addVisit(Long id) {
        Pet pet = Pet.findById(id);
        Visit visit = new Visit();
        visit.pet = pet;
        render(visit);
    }
    
    public static void saveVisit(@Valid Visit visit) {
        if (validation.hasErrors()) {
            renderJSON(new ResponseContainer("error", Validation.errors()));
        }
        visit.save();
        renderJSON(new ResponseContainer("ok", "visit saved"));
    }
}
