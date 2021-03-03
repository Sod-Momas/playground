package cc.momas.aop.service.impl;

import cc.momas.aop.model.Book;
import cc.momas.aop.service.AopService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public class AopServiceImpl implements AopService {

    private final LinkedHashMap<Long, Book> database = new LinkedHashMap<>();

    @Override
    public Book add(Book book) {
        database.put(book.getId(), book);
        return book;
    }

    @Override
    public Book get(long id) {
        return database.get(id);
    }

    @Override
    public long exists(Book book) {
        final var isbn = book.getIsbn();
        final var title = book.getTitle();

        var stream = database.entrySet().parallelStream();
        if (book.getTitle() != null) {
            stream = stream.filter(entry -> title.equals(entry.getValue().getTitle()));
        }
        if (book.getIsbn() != null) {
            stream = stream.filter(entry -> isbn.equals(entry.getValue().getIsbn()));
        }
        return stream.count();
    }
}
