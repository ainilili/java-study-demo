package org.nico.design.mode.chain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class HeaderChainHandler extends ChainHandler{

    @Override
    public Request handler(Request request, String payload) {
        String[] lines = lines(payload);
        
        List<Map<String, String>> headers = new ArrayList<>();
        for(int index = 1; index < lines.length; index ++) {
            String line = lines[index];
            String[] datas = line.split("[:]", 2);
            
            Map<String, String> header = new TreeMap<String, String>();
            header.put(datas[0], datas[1]);
            headers.add(header);
        }
        request.setHeaders(headers);
        return next(request, payload);
    }

}
