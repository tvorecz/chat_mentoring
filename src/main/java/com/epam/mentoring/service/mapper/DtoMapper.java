package com.epam.mentoring.service.mapper;

public interface DtoMapper<E, DTO> {
    DTO convert(E entity);
}
