package org.nico.middleware.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/** 
 * 
 * @author nico
 * @email ainililia@163.com
 */

public class SimpleServerEchoHandler extends ChannelInboundHandlerAdapter{

	Logger logger = LoggerFactory.getLogger(SimpleServerEchoHandler.class);
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.info("Server Received：{}", String.valueOf(msg));
		ctx.writeAndFlush(msg);
	}

}
