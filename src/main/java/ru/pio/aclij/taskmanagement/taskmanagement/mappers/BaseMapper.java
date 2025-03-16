package ru.pio.aclij.taskmanagement.taskmanagement.mappers;

import java.util.List;

public interface BaseMapper <E, T>{
    E toEntity(T dto);
    List<E> toEntityList(List<T> dtos);
    T toDto(E entity);
    List<T> toDtoList(List<E> entities);
}
