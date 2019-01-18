package org.nico.design.mode.chain;

import java.util.LinkedList;

public abstract class ChainHandler {

    private final static LinkedList<ChainHandler> CHAIN_HANDLERS = new LinkedList<ChainHandler>();

    private ChainHandler next;

    public final static void add(ChainHandler handler) {
        if(! CHAIN_HANDLERS.isEmpty()) {
            CHAIN_HANDLERS.getLast().next = handler;
        }
        CHAIN_HANDLERS.add(handler);
    }

    public final static Request handler(String payload) {
        return CHAIN_HANDLERS.getFirst().handler(new Request(), payload);
    }

    static {
        add(new InfoChainHandler());
        add(new HeaderChainHandler());
    }

    public abstract Request handler(Request request, String payload);

    protected String[] lines(String payload) {
        return payload.split("\n");
    }

    protected Request next(Request request, String payload) {
        if(next != null) {
            return next.handler(request, payload);
        }else {
            return request;
        }
    }
}
