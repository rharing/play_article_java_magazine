package controllers;

import models.PetOwner;
import play.data.validation.Required;
import play.data.validation.Valid;

import java.util.List;

/**
 * Controller for the petowners methods
 */
public class PetOwners extends Application {

    /**
     * Only used to show the find screen
     */
    public static void findPetOwners() {
        render();
    }

    /**
     * @param search this is the value that needs to be searched on. Since this field
     *               is annotated with @Required, an empty value for the search will yield in a
     *               validation error that this field is required.
     */
    public static void listPetOwners(@Required String search) {
        redirectOnErrors("PetOwners.findPetOwners");
        List<PetOwner> petOwners = PetOwner.search(search);
        if (petOwners.size() > 1) {
            // more then one found? Then show the list. Since we are not referring to another
            // action, there will be no redirect and the page listPetOwners will be shown
            render(petOwners);
        }
        else if (petOwners.size() == 1) {
            // only 1 to show, then show the correct petowner right away. Since this is another
            // action this will result in a redirect to petowners/show/<id>
            showPetOwner(petOwners.get(0).id);
        }
        else {
            validation.addError("search", "error.nothing.found");
            redirectOnErrors("PetOwners.findPetOwners");
        }
    }

    /**
     * Shows an overview of the petOwner.
     * @param id that is the current petOwner's id. If the petOwner is not found a 404 will be returned to the client
     *           If the playframework is running in dev modus (the default) then an overview of the routes will be shown.
     */
    public static void showPetOwner(Long id) {
        PetOwner petOwner = PetOwner.findById(id);
        notFoundIfNull(petOwner);
        render(petOwner);
    }

    /**
     * @param id of the petowner that must be edited.
     */
    public static void editPetOwner(Long id) {
        PetOwner petOwner = PetOwner.findById(id);
        notFoundIfNull(petOwner);
        render(petOwner);
    }

    /**
     * Sets a new petOwner on the view and then shows the editPetowner template. Doing this
     * without calling another method will not cause a redirect, yet this way the same template
     * can be used again. Another way to handle editing and adding can be seen in the Pets controller
     */
    public static void addPetOwner() {
        PetOwner petOwner = new PetOwner();
        render("PetOwners/editPetOwner.html", petOwner);
    }

    /**
     * @param petOwner that must be saved. By putting a @Valid annotation on the parameter,
     *                 playframework will call all the validation annotations that are defined in the
     *                 PetOwner class. If an error is found, the correct redirect will be called based
     *                 on the petowner's id.
     */
    public static void savePetOwner(@Valid PetOwner petOwner) {
        if (petOwner.id == null) {
            Application.redirectOnErrors("PetOwners.addPetOwner");
        }
        else {
            Application.redirectOnErrors("PetOwners.editPetOwner");
        }
        petOwner.save();
        renderArgs.put("message", "owner.saved");
        showPetOwner(petOwner.id);
    }

}