package com.martinsanguin.gelty.services;

import com.martinsanguin.gelty.api.v1.mapper.ShiftMapper;
import com.martinsanguin.gelty.api.v1.model.ShiftDTO;
import com.martinsanguin.gelty.domain.Shift;
import com.martinsanguin.gelty.domain.Specialitation;
import com.martinsanguin.gelty.repositories.ShiftRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShiftServiceImpl implements ShiftService {

    private final ShiftMapper shiftMapper;
    private final ShiftRepository shiftRepository;

    public ShiftServiceImpl(ShiftMapper shiftMapper, ShiftRepository shiftRepository) {
        this.shiftMapper = shiftMapper;
        this.shiftRepository = shiftRepository;
    }

    @Override
    public List<ShiftDTO> getAllStudies() {
        return this.shiftRepository.findAll().stream().map(shiftMapper::shiftToShiftDTO).collect(Collectors.toList());
    }

    @Override
    public ShiftDTO getStudyById(Long id) throws ServiceException {
        Optional<Shift> shift = this.shiftRepository.findById(id);
        if(!shift.isPresent())
            throw new ServiceException("Shift with id " + id + " doesn't exists.");
        return shiftMapper.shiftToShiftDTO(shift.get());

    }

    @Override
    public List<ShiftDTO> findBySpecialitation(Specialitation specialitation) {
        return null;
    }
}
