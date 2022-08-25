package cc.momas.spring.integ;


import com.rometools.rome.feed.synd.SyndEntryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.endpoint.SourcePollingChannelAdapter;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <ol>
 *     <li>验证适配器是否已停止。</li>
 *     <li>创建测试 。SyndEntry</li>
 *     <li>删除测试输出文件（如果存在）。</li>
 *     <li>发送消息。</li>
 *     <li>验证文件是否存在。</li>
 *     <li>读取文件并验证数据是否符合预期。</li>
 * </ol>
 *
 * @author Sod-Momas
 * @since 2022/8/25
 */
@SpringBootTest({
        // we don't want to start the real feed
        "auto.startup=false",
        // use a different file
        "feed.file.name=Test",
})
public class SpringIntegrationCoreApplicationTests {

    @Autowired
    private SourcePollingChannelAdapter newsAdapter;

    @Autowired
    private MessageChannel news;

    @Test
    public void test() throws Exception {
        assertThat(this.newsAdapter.isRunning()).isFalse();
        SyndEntryImpl syndEntry = new SyndEntryImpl();
        syndEntry.setTitle("Test Title");
        syndEntry.setLink("http://characters/frodo");
        File out = new File("/tmp/si/Test");
        out.delete();
        assertThat(out.exists()).isFalse();
        this.news.send(MessageBuilder.withPayload(syndEntry).build());
        assertThat(out.exists()).isTrue();
        BufferedReader br = new BufferedReader(new FileReader(out));
        String line = br.readLine();
        assertThat(line).isEqualTo("Test Title @ http://characters/frodo");
        br.close();
        out.delete();
    }
}
