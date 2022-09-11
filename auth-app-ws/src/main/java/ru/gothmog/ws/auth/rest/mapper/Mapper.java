package ru.gothmog.ws.auth.rest.mapper;


import ru.gothmog.ws.auth.core.model.AbstractEntity;
import ru.gothmog.ws.auth.rest.dto.AbstractDto;

import java.util.List;

public interface Mapper<E extends AbstractEntity, D extends AbstractDto> {

    E toEntity(D dto);

    D toDto(E entity);

    List<D> toArraysToDto(List<E> inArray);
}
