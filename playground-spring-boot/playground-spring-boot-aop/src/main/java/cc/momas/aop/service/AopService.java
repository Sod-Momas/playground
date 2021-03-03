package cc.momas.aop.service;

import cc.momas.aop.model.Book;

public interface AopService {

    Book add(Book book);

    Book get(long id);

    long exists(Book book);
}
