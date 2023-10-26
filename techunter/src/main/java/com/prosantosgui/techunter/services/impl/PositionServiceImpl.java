package com.prosantosgui.techunter.services.impl;

import com.prosantosgui.techunter.model.Position;
import com.prosantosgui.techunter.repositories.PositionRepository;
import com.prosantosgui.techunter.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository positionRepository;
    @Override
    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    @Override
    public Position findById(Long id) {
        Optional<Position> position = positionRepository.findById(id);
        if(position.isPresent()){
            return position.get();
        }else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public ResponseEntity<Position> savePosition(Position position) {
        Position positionSaved = positionRepository.save(position);
        return new ResponseEntity<>(positionSaved, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        Optional<Position> position = positionRepository.findById(id);

        if(position.isPresent()){
            positionRepository.deleteById(id);
            return new ResponseEntity<>("Position deleted successfully!",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Position not found!",HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Position mapNewPosition(Position positionSaved, Position modifiedPosition) {
        positionSaved.setType(modifiedPosition.getType());
        positionSaved.setRequiredEducation(modifiedPosition.getRequiredEducation());
        positionSaved.setDescription(modifiedPosition.getDescription());
        positionSaved.setStacks(modifiedPosition.getStacks());
        positionSaved.setEmploymentType(modifiedPosition.getEmploymentType());
        positionSaved.setBenefits(modifiedPosition.getBenefits());
        positionSaved.setSalaryRanges(modifiedPosition.getSalaryRanges());
        positionSaved.setRequiredEducation(modifiedPosition.getRequiredEducation());
        positionSaved.setCountry(modifiedPosition.getCountry());
        positionSaved.setState(modifiedPosition.getState());
        positionSaved.setDate(modifiedPosition.getDate());
        positionSaved.setPositionStatus(modifiedPosition.getPositionStatus());
        return positionSaved;
    }
}
