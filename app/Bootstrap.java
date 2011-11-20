import models.Book;
import play.Play;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

/**
 * User: ronald
 * Date: 10/11/11
 * Time: 9:06 PM
 */

@OnApplicationStart
public class Bootstrap extends Job {
	@Override
	public void doJob() throws Exception {
		if (Book.count() == 0 && !Play.runingInTestMode()) {
			Fixtures.loadModels("files/books.yml");
		}
   }
}