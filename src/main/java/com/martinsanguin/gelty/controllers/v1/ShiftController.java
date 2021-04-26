package com.martinsanguin.gelty.controllers.v1;

import com.martinsanguin.gelty.api.v1.model.ShiftDTO;
import com.martinsanguin.gelty.services.ServiceException;
import com.martinsanguin.gelty.services.ShiftService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/shifts")
public class ShiftController {
    private final ShiftService shiftService;

    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @GetMapping()
    public ResponseEntity<List<ShiftDTO>> getAllShits(){
        return new ResponseEntity<List<ShiftDTO>>(shiftService.getAllStudies(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getShiftById(@PathVariable String id){
        try {
            return new ResponseEntity<ShiftDTO>(shiftService.getStudyById(Long.valueOf(id)), HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<String>("There was an exception. Details: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
