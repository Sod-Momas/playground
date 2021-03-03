package cc.momas.aop;

import cc.momas.aop.model.Book;
import cc.momas.aop.service.AopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class AopApplication implements CommandLineRunner {

    private final AopService aopService;
    private final Logger log = LoggerFactory.getLogger(AopApplication.class);

    public AopApplication(AopService aopService) {
        this.aopService = aopService;
    }

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Book book = new Book();
        book.setId(new Random().nextLong());
        book.setIsbn(UUID.randomUUID().toString().substring(0, 8));
        book.setTitle(Long.toHexString(System.currentTimeMillis()));

        final var add = aopService.add(book);
        log.info("add book : {}", add);

        final var exists = aopService.exists(book);
        log.info("test exists : {}", exists);

        final var get = aopService.get(book.getId());
        log.info("get book : {}", get);
    }
}
