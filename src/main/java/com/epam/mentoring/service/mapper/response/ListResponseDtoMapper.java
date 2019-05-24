package com.epam.mentoring.service.mapper.response;

import com.epam.mentoring.service.mapper.DtoMapper;

import java.util.ArrayList;
import java.util.List;


public class ListResponseDtoMapper<E, Dto> implements DtoMapper<List<E>, List<Dto>> {
    private DtoMapper<E, Dto> oneItemMapper;

    public ListResponseDtoMapper(DtoMapper<E, Dto> oneItemMapper) {
        this.oneItemMapper = oneItemMapper;
    }

    @Override
    public List<Dto> handle(List<E> entityList) {
        if (entityList != null) {
            List<Dto> responseList = new ArrayList<>(entityList.size());

            for (E entity : entityList) {
                responseList.add(oneItemMapper.handle(entity));
            }

            return responseList;
        } else {
            return null;
        }
    }
}
