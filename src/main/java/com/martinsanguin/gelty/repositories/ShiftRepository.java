package com.martinsanguin.gelty.repositories;

import com.martinsanguin.gelty.domain.Shift;
import com.martinsanguin.gelty.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftRepository extends JpaRepository<Shift,Long> {

}
