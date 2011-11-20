import models.Pet;
import play.Play;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

/**
 * This will be called once the application starts and will load the data
 * as defined in the load_db.yml file into the database, if the number of pets
 * is zero and the system is not running in test mode.
 */
@OnApplicationStart
public class Bootstrap extends Job {
    @Override
    public void doJob() throws Exception {
        if (Pet.count() == 0 && !Play.runingInTestMode()) {
            Fixtures.loadModels("load_db.yml");
        }
    }

}
