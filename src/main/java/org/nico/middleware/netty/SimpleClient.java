package org.nico.middleware.netty;

import java.io.IOException;
import java.util.Scanner;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class SimpleClient {

	public static void main(String[] args) throws InterruptedException, IOException {
		EventLoopGroup group = new NioEventLoopGroup();
		Scanner in = null;
		try {
			Bootstrap bootstrap = new Bootstrap()
					.group(group)
					.channel(NioSocketChannel.class)
					.handler(new SimpleClientChannelInitializer());
			
			//这一步指定连接服务端的地址和端口并连接
			Channel channel = bootstrap.connect("127.0.0.1", 8080).sync().channel();
			
			in = new Scanner(System.in);
			while(in.hasNext()){
				channel.writeAndFlush(in.nextLine());
			}
			
			channel.closeFuture().sync();
		} finally {
			group.shutdownGracefully().sync();
			if(in != null) in.close();
		}
	}

}