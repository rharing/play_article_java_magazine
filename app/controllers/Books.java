package controllers;

import models.Book;
import play.data.validation.Valid;
import play.mvc.Controller;

import java.util.List;

/**
 * User: ronald
 * Date: 10/11/11
 * Time: 8:17 PM
 */
public class Books extends Controller {
    public static void index() {
        List<Book> books = Book.all().fetch(10);
        render(books);
    }

    public static void show(Long id) {
        Book book = Book.findById(id);
        render(book);
    }

    public static void edit(Long id) {
        Book book = Book.findById(id);
        render(book);
    }

    public static void add() {
        Book book = new Book();
        render("Books/edit.html", book);
    }

    public static void save(@Valid Book book) {
        if (validation.hasErrors()) {
            params.flash();
            validation.keep();
            if (book.id == null) {
                add();
            }
            edit(book.id);
        }
        book.save();
        index();
    }
}
