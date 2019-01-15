package org.nico.middleware.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/** 
 * 
 * @author nico
 * @email ainililia@163.com
 */
public class SimpleClientChannelInitializer extends ChannelInitializer<SocketChannel>{

	Logger logger = LoggerFactory.getLogger(SimpleClientChannelInitializer.class);
	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		
		ch.pipeline()
		.addLast(new StringDecoder())
        .addLast(new StringEncoder())
        .addLast(new SimpleClientEchoHandler());
		
		logger.info("Client Connected Server Successful !!");
	}

}
