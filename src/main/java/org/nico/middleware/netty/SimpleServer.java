package org.nico.middleware.netty;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/** 
 * 
 * @author nico
 * @email ainililia@163.com
 */
public class SimpleServer {

	static Logger logger = LoggerFactory.getLogger(SimpleServer.class);
	
	public static void main(String[] args) throws InterruptedException {

		EventLoopGroup parentGroup = new NioEventLoopGroup();
		EventLoopGroup childGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap()
			.group(parentGroup, childGroup)
			.channel(NioServerSocketChannel.class) //指定启动一个NIO类型的服务器
			.localAddress(new InetSocketAddress(8080)) //指定端口
			.childHandler(new SimpleServerChannelInitializer()); //指定一个ChannelHandler类型的处理器，在服务初始化之后调用处理器中的方法

			ChannelFuture f = bootstrap.bind().sync();
			
			logger.info("Server Initialized On Port 8080 !!");
			
			f.channel().closeFuture().sync(); //阻塞直到服务关闭
		} finally {
			parentGroup.shutdownGracefully();
			childGroup.shutdownGracefully();
		}

	}
}
