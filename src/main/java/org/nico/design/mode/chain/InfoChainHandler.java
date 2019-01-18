package org.nico.design.mode.chain;

public class InfoChainHandler extends ChainHandler{

    @Override
    public Request handler(Request request, String payload) {
        String[] lines = lines(payload);
        
        String[] infos = lines[0].split(" ");
        request.setType(infos[0]);
        request.setUrl(infos[1]);
        
        return next(request, payload);
    }

}
