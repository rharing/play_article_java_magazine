package controllers;

import play.mvc.With;

/**
 * The crud for the vets. All of the crudding is done by the CRUD module.
 * Only authenticated users can get in the vets section. This is done by
 * the @With(Secure.class) and by the SecurityHandler which must extend Secure.Security
 */
@With(Secure.class)
public class Vets extends CRUD {
}
