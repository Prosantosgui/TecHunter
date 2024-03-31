package com.prosantosgui.techunter.services.impl;

import com.prosantosgui.techunter.model.Position;
import com.prosantosgui.techunter.model.Recruiter;
import com.prosantosgui.techunter.repositories.PositionRepository;
import com.prosantosgui.techunter.services.PositionService;
import com.prosantosgui.techunter.services.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private RecruiterService recruiterService;

    @Autowired
    private PositionRepository positionRepository;
    @Override
    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    @Override
    public List<Position> findAllByRecruiter(String idRecruiter) {
        Recruiter recruiter = recruiterService.findById(idRecruiter);
        if(recruiter != null){
            return recruiter.getPositions();
        }
        throw new NoSuchElementException();
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
        String userLoggedName = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<Position> position = positionRepository.findById(id);
        if(position.isPresent() && position.get().getRecruiter().getLogin().equals(userLoggedName)){
            positionRepository.deleteById(id);
            return new ResponseEntity<>("Position deleted successfully!", HttpStatus.OK);

        }else {

            if (position.isEmpty()){
                return new ResponseEntity<String>("Position not found!", HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<String>("You have no permission to delete this Position!", HttpStatus.FORBIDDEN);
            }

        }
    }

    @Override
    public Position mapNewPosition(Long id, Position positionSaved, Position modifiedPosition) {
        positionSaved.setId(id);
        positionSaved.setType(modifiedPosition.getType());
        positionSaved.setWorkDuration(modifiedPosition.getWorkDuration());
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

    @Override
    public ResponseEntity<Position> updatePosition(Long id, Position positionSaved, Position modifiedPosition) {

        String userLoggedName = SecurityContextHolder.getContext().getAuthentication().getName();

        if(positionSaved.getRecruiter().getLogin().equals(userLoggedName)){
            Position updatedPosition = this.mapNewPosition(id, positionSaved,modifiedPosition);
            positionRepository.save(updatedPosition);
            return new ResponseEntity<>(updatedPosition, HttpStatus.OK);
        }else {
                return new ResponseEntity<>(modifiedPosition, HttpStatus.FORBIDDEN);
        }

    }
}
