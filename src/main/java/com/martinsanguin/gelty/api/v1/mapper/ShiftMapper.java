package com.martinsanguin.gelty.api.v1.mapper;

import com.martinsanguin.gelty.api.v1.model.ShiftDTO;
import com.martinsanguin.gelty.domain.Shift;
import com.martinsanguin.gelty.domain.Study;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ShiftMapper {
    ShiftMapper INSTANCE = Mappers.getMapper(ShiftMapper.class);

    ShiftDTO shiftToShiftDTO(Shift shift);
}
