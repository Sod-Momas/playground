package cc.momas.mybatisplus;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@SpringBootTest
public class PlaygroundMybatisPlusSpringBootApplicationTests {

    @Autowired
    private BlogMapper blogMapper;
    private static Blog blog;

    public void insertBlog() {
        var id = IdUtil.getSnowflake(1L, 1L).nextId();
        blog = new Blog();
        blog.setId(id);
        blog.setContent("this is cotenten");
        blog.setPublished(new Random(100).nextInt());

        final var insert = blogMapper.insert(blog);
        System.out.println("insert result:" + insert);
    }

//    @Test
    public void selectById() {
        if (blog == null) {
            insertBlog();
        }
        final var fromSelect = blogMapper.selectById(blog.getId());
        Assertions.assertEquals(fromSelect, blog);
    }

//    @Test
    public void updateById() {
        if (blog == null) {
            insertBlog();
        }


        Blog where = new Blog();
        where.setPublished(blog.getPublished());
//        final var blog = Optional.of(new QueryWrapper<>(where))
//                .map(blogMapper::selectList)
//                .stream()
//                .findFirst();
        final var blogs = blogMapper.selectList(new QueryWrapper<>(where));
        if (blogs.isEmpty()) {
            return;
        }
        blog = blogs.stream().findAny().get();

        var dest = new Blog();
        dest.setId(blog.getId());
        dest.setCommentabled(1);
        dest.setFlag("this is flag");

        final var update = blogMapper.updateById(dest);
        System.out.println(update);

        final var fromUpdateQuery = blogMapper.selectOne(new QueryWrapper<>(dest));
        Assertions.assertEquals(dest, fromUpdateQuery);

    }
    @Test
    public void updateByEntity(){

        var where = new Blog();
//        where.setId(123123L);
        where.setFlag("12983198");

        var dest = new Blog();
        dest.setCommentabled(1);
        dest.setFlag("this is flag");

        blogMapper.update(dest, new QueryWrapper<>(where));
    }

}
