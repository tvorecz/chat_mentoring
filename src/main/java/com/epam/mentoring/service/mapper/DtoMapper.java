package com.epam.mentoring.service.mapper;

public interface DtoMapper<E1, E2> {
    E2 handle(E1 object);
}
