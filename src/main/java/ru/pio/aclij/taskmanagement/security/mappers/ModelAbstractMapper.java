package ru.pio.aclij.taskmanagement.security.mappers;

import java.util.List;

public abstract class ModelAbstractMapper <MODEL, DTO> {
    DTO toDto(MODEL model) {
        return null;
    }

    MODEL toModel(DTO dto) {
        return null;
    }
    List<DTO> toListDto(List<MODEL> models) {
        return null;
    }
    List<MODEL> toListModel(List<DTO> dtos){
        return null;
    }

}
