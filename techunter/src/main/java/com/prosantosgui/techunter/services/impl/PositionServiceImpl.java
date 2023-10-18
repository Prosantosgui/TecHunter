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
        Optional<Position> obj = positionRepository.findById(id);
        if(obj.isPresent()){
            return obj.get();
        }else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public ResponseEntity<Position> savePosition(Position position) {
        Position positionSaved = positionRepository.save(position);
        return new ResponseEntity<>(positionSaved, HttpStatus.CREATED);
    }
}
