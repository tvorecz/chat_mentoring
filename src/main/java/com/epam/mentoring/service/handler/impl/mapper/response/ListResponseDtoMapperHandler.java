package com.epam.mentoring.service.handler.impl.mapper.response;

import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.service.handler.Handler;

import java.util.ArrayList;
import java.util.List;


public class ListResponseDtoMapperHandler<E, Dto> implements Handler<List<E>, List<Dto>> {
    private Handler<E, Dto> nextHandler;

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public List<Dto> handle(List<E> entityList, ServiceStatusResponseDto status) {
        List<Dto> responseList = new ArrayList<>(entityList.size());

        for (E entity : entityList) {
            responseList.add(nextHandler.handle(entity, status));
        }

        return responseList;
    }
}
