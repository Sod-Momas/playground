package cc.momas.spring.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheManager = "cacheManager")
public class BookServiceImpl implements IBookService {

    private final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);
    /**
     * 内存数据库
     */
    private final Map<String, Book> library = new LinkedHashMap<>();

    /**
     * 根据 book.isbn 把对象添加到缓存 library 中
     *
     * @param book 新增加的对象
     * @return 新增加的对象
     */
    @CachePut(cacheNames = {"book"}, key = "'isbn'.concat(#book.isbn)")
    @Override
    public Book add(Book book) {
        log.info("db ops add");
        library.put(book.getIsbn(), book);
        return book;
    }

    /**
     * 根据条件查询, 当 book.isbn 为 null 时不缓存查询结果
     *
     * @param book 查询条件, 当条件为空的时候会返回第一个值
     * @return 查找到的条件,
     */
    @Cacheable(
            cacheNames = {"book"},
            key = "#book.isbn == null ? ('title' + #book.title) : ('isbn' + #book.isbn)",
            unless = "#result == null"
    )
    @Override
    public Book get(Book book) {
        log.info("db ops get");
        final var isbn = book.getIsbn();
        final var title = book.getTitle();
        if (isbn != null) {
            return library.get(isbn);
        }

        var bookStream = library.values().parallelStream();

        if (title != null) {
            // 过滤掉不符合的对象
            bookStream = bookStream.filter(t -> t.getTitle().equals(title));
        }

        return bookStream
                .findFirst()
                .orElse(null);
    }

    /**
     * 辅助方法, 根据所给条件查询所有添加的对象
     *
     * @param book  查询条件
     * @param start 分页参数 -开始下标
     * @param rows  分页参数 - 查询数量
     * @return 符合条件的结果列表
     */
    @Cacheable(cacheNames = {"bookList"}, key = "('title' + #book.title) + ('isbn' + #book.isbn) + ('start' + #start) + ('rows' + #rows) ")
    @Override
    public List<Book> list(Book book, int start, int rows) {
        log.info("db ops list");
        final var isbn = book.getIsbn();
        final var title = book.getTitle();
        var bookStream = library.entrySet().stream();
        if (isbn != null) {
            bookStream = bookStream.filter(item -> item.getKey().equals(isbn));
        }
        if (title != null) {
            bookStream = bookStream.filter(item -> item.getKey().equals(title));
        }
        return bookStream.map(Map.Entry::getValue)
                .skip(start)
                .limit(rows)
                .collect(Collectors.toList());
    }

    /**
     * 编辑对象,当 book.isbn 为空时不会操作
     *
     * @param book 要修改的对象
     * @return 修改后的对象
     */
    @Caching(evict = {
            @CacheEvict(cacheNames = {"book"}, key = "'isbn'.concat(#book.isbn)"),
            @CacheEvict(cacheNames = {"bookList"}, allEntries = true)
    })
    @Override
    public Book edit(Book book) {
        log.info("db ops edit");
        if (library.containsKey(book.getIsbn())) {
            return library.put(book.getIsbn(), book);
        }
        return null;
    }

    /**
     * 删除对象,当对象不存在时返回null
     *
     * @param book 要删除的对象
     * @return 删除的对象
     */
    @Caching(evict = {
            @CacheEvict(cacheNames = {"book"}, key = "'isbn'.concat(#book.isbn)"),
            @CacheEvict(cacheNames = {"bookList"}, allEntries = true)
    })
    @Override
    public Book del(Book book) {
        log.info("db ops del");
        return library.remove(book.getIsbn());
    }
}
