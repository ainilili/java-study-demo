package org.nico.middleware.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/** 
 * 
 * @author nico
 * @email ainililia@163.com
 */

public class SimpleServerWriteHandler extends ChannelOutboundHandlerAdapter{

	Logger logger = LoggerFactory.getLogger(SimpleServerWriteHandler.class);
	
	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		logger.info("Excute This Method After Writed !!");
	}
}
