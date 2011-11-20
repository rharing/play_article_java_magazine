package controllers;

import models.PetOwner;
import play.Play;
import play.data.validation.Validation;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Util;

import java.util.List;

/**
 * The default controller. This controller adds the dateFormat from the play properties
 * file into the view.
 */
public class Application extends Controller {

    /**
     * This methoid gets called before any method of this controller is called.
     */
    @Before
    public static void addDateFormat() {
        renderArgs.put("dateFormat", Play.configuration.getProperty("date.format"));
    }

    public static void index() {
        List<PetOwner> petOwners = PetOwner.findAll();
        render(petOwners);
    }

    /**
     * Utility method that will check the validation and stores the validation and the parameters
     * in the cookie if any errors were found
     * @param action should be the controller and action that must be invoked in case of errors, e.g. PetOwners.showPets
     * @param args that must be added to the redirect method, e.g. a PetOwner instance
     */
    @Util
    public static void redirectOnErrors(String action, Object... args) {
        if (Validation.hasErrors()) {
            Validation.keep();
            params.flash();
            redirect(action, args);
        }
    }
}