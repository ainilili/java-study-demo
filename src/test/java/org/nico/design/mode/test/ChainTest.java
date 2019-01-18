package org.nico.design.mode.test;

import org.junit.Test;
import org.nico.design.mode.chain.ChainHandler;
import org.nico.design.mode.chain.Request;

public class ChainTest {

    @Test
    public void test() {
        
        String payload = "GET /search?hl=zh-CN&source=hp&q=domety&aq=f&oq= HTTP/1.1  \n" + 
                "Accept: image/gif \n" + 
                "Referer: <a href=\"http://www.google.cn/\">http://www.google.cn/</a>  \n" + 
                "Accept-Language: zh-cn  \n" + 
                "Accept-Encoding: gzip, deflate";
        
        Request request = ChainHandler.handler(payload);
        
        System.out.println(request.getUrl());
        System.out.println(request.getType());
        request.getHeaders().forEach(System.out::println);
        
    }
}
