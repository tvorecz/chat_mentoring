package com.epam.mentoring.service.handler.chainbuilder;

import com.epam.mentoring.service.handler.Handler;

public class HandlerChainBuilder {
    private Handler handler;
    private Handler nextHandler;

    public static HandlerChainBuilder builder(){
        return new HandlerChainBuilder();
    }

    public HandlerChainBuilder startHandler(Handler handler) {
        this.handler = this.nextHandler = handler;

        return this;
    }

    public HandlerChainBuilder nextHandler(Handler nextHandler) {
        this.nextHandler.setNextHandler(nextHandler);

        this.nextHandler = nextHandler;

        return this;
    }

    public Handler build() {
        return handler;
    }
}
