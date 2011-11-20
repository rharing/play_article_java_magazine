package controllers;

import models.Pet;
import models.PetOwner;
import models.PetType;
import play.classloading.enhancers.ControllersEnhancer;
import play.data.validation.Valid;
import play.data.validation.Validation;

import java.util.List;

/**
 * This is the controller for the pets. Unlike the petOwners, this controller
 * will not issue a redirect on a bad formatted save object, but will just
 * redisplay the data again. This means however that the url will be changed to
 * savePet instead of /addPet or /editPet. However by showing the same page
 * immediately again to the end user, there is no redirect and no storing of
 * data in cookies. Since the space is only limited to 4k in a cookie, using it
 * without a redirect (through the @ControllersEnhancer.byPass annotation),
 * we can still re-display large amounts of data
 */
public class Pets extends Application {

    /**
     * @param id of the petOwner.
     */
    public static void addPet(Long id) {
        PetOwner petOwner = PetOwner.findById(id);
        notFoundIfNull(petOwner);
        Pet pet = new Pet();
        pet.petOwner = petOwner;
        modifyPet(pet);
    }

    /**
     * @param pet that must be shown with the given pet and all the pettypes
     *            The annotation makes sure that there will not be a redirect issued to the
     *            client yet the other action behaviour (stopping immediately after a render method)
     *            will still apply.
     */
    @ControllersEnhancer.ByPass
    private static void modifyPet(Pet pet) {
        List<PetType> petTypes = PetType.find("order by name").fetch();
        render("Pets/editPet.html", pet, petTypes);
    }

    /**
     * @param id of the pet for editing
     */
    public static void editPet(Long id) {
        Pet pet = Pet.findById(id);
        notFoundIfNull(pet);
        modifyPet(pet);
    }

    /**
     * @param pet to save. The @Valid annotation makes sure that the validation will
     *            contain the errors if the pet is not valid.
     */
    public static void savePet(@Valid Pet pet) {
        if (Validation.hasErrors()) {
            modifyPet(pet);
            // Since modifyPet has a render method, the lines following the modifyPet(pet)
            // will not be called. No need here to save the validations and the params since
            // there is no redirect here.
        }
        pet.save();
        flash.put("message", "pet.saved");
        PetOwners.showPetOwner(pet.petOwner.id);
    }
    
    public static void delete(Long id) {
        Pet pet = Pet.findById(id);
        PetOwner owner = pet.petOwner;
        pet.delete();
        flash.put("message", "pet.deleted");
        PetOwners.showPetOwner(owner.id);
    }
}
