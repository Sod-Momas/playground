package cc.momas.spring.cache;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 控制器
 *
 * @author Sod-Momas
 * @since 2021.02.21
 */
@RestController
public class BookController {

    private final IBookService bookService;

    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/book/add")
    public ResponseWrapper<Book> add(@RequestBody @Validated Book book) {

        book = bookService.add(book);

        ResponseWrapper<Book> wrapper = new ResponseWrapper<>();
        wrapper.setData(book);
        return wrapper;
    }

    @RequestMapping("/book/get")
    public ResponseWrapper<Book> get(@RequestBody @Validated Book book) {
        book = bookService.get(book);

        ResponseWrapper<Book> wrapper = new ResponseWrapper<>();
        wrapper.setData(book);
        return wrapper;
    }

    @RequestMapping("/book/list")
    public ResponseWrapper<List<Book>> list(@RequestBody @Validated Book book,
                                            @RequestParam(value = "start", defaultValue = "0") int start,
                                            @RequestParam(value = "rows", defaultValue = "10") int rows) {

        List<Book> list = bookService.list(book, start, rows);

        ResponseWrapper<List<Book>> wrapper = new ResponseWrapper<>();
        wrapper.setData(list);
        wrapper.setTotal(list.size());
        return wrapper;
    }

    @RequestMapping("/book/edit")
    public ResponseWrapper<Book> edit(@RequestBody @Validated Book book) {

        book = bookService.edit(book);

        ResponseWrapper<Book> wrapper = new ResponseWrapper<>();
        wrapper.setData(book);
        return wrapper;
    }

    @RequestMapping("/book/del")
    public ResponseWrapper<Book> del(@RequestBody @Validated Book book) {

        book = bookService.del(book);

        ResponseWrapper<Book> wrapper = new ResponseWrapper<>();
        wrapper.setData(book);
        return wrapper;
    }
}
