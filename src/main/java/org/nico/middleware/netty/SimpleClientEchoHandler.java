package org.nico.middleware.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/** 
 * 
 * @author nico
 * @email ainililia@163.com
 */

public class SimpleClientEchoHandler extends SimpleChannelInboundHandler<String>{

	Logger logger = LoggerFactory.getLogger(SimpleClientEchoHandler.class);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		logger.info("Client Received：{}", String.valueOf(msg));
	}

}
