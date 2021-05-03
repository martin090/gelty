package com.martinsanguin.gelty.services;

import com.martinsanguin.gelty.api.v1.model.ShiftDTO;
import com.martinsanguin.gelty.domain.Specialitation;

import java.util.List;

public interface ShiftService {
    List<ShiftDTO> getAllStudies();
    ShiftDTO getStudyById(Long id) throws ServiceException;
    List<ShiftDTO> findBySpecialitation(Specialitation specialitation);
}
