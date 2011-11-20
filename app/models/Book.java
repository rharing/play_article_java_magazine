package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.Date;

/**
 * User: ronald
 * Date: 10/11/11
 * Time: 8:03 PM
 */
@Entity
public class Book extends Model {
   @Required
   public String name;
   @Required
   public String author;
   @Required
   public String isbn;

   public Date published;

   public static Book find(String name, String author) {
      return Book.find("byNameAndAuthor", name, author).first();
   }
}
