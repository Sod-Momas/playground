package cc.momas.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * @author Sod-Momas
 * @since 2021-01-17
 */
public class NettyClient {
    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        final ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(new NettyClientHandler());
                    }
                });

        try {
            final ChannelFuture cf = bootstrap.connect("localhost", 9000).sync();
            final Channel channel = cf.channel();
            System.out.println("netty client start, my address is " + channel.localAddress() + ". (type 'exit' to exit)");
            final Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                channel.writeAndFlush(line);
                if ("exit".equals(line)) {
                    channel.close().sync();
                    break;
                }
            }
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            group.shutdownGracefully();
        }
    }
}
