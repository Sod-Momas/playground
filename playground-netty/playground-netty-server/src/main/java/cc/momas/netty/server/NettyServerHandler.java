package cc.momas.netty.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * netty聊天室服务器业务处理类
 *
 * @author Sod-Momas
 * @since 2021-01-17
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {
    /**
     * QQ群
     */
    private static final ChannelGroup GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        String msg = "[ " + ctx.channel().remoteAddress() + " ] 上线了";
        GROUP.writeAndFlush(msg);
        System.out.println(msg);
        // 新用户进群
        GROUP.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        // 群员退群
        GROUP.remove(ctx.channel());
        String msg = "[ " + ctx.channel().remoteAddress() + " ] 没了";
        GROUP.writeAndFlush(msg);
        System.out.println(msg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        // 群员发消息
        final Channel channel = ctx.channel();

        GROUP.forEach(ch -> {
            final String displayName;
            if (ch == channel) {
                displayName = "[ 自己 ]";
            } else {
                displayName = "[ " + ch.remoteAddress() + " ]";
            }

            ch.writeAndFlush(displayName + now() + "\r\n" + msg);
        });

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 群员暴毙，立刻踢出群聊
        GROUP.remove(ctx.channel());
        ctx.close();
        String msg = "[ " + ctx.channel().remoteAddress() + " ] 暴毙了";
        GROUP.writeAndFlush(msg);
        System.out.println(msg);
    }

    private String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
