package cc.momas.spring.cache;

import cc.momas.spring.cache.Book;

import java.util.List;

public interface IBookService {

    Book add(Book book);

    Book get(Book book);

    List<Book> list(Book book, int start, int end);

    Book edit(Book book);

    Book del(Book book);
}
