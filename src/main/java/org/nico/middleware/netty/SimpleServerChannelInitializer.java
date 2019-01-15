package org.nico.middleware.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/** 
 * 
 * @author nico
 * @email ainililia@163.com
 */
public class SimpleServerChannelInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline()
		.addLast(new StringDecoder())
        .addLast(new StringEncoder())
        .addLast(new SimpleServerEchoHandler());
	}

}
