package com.prosantosgui.techunter.services.impl;

import com.prosantosgui.techunter.exceptions.AuthenticationException;
import com.prosantosgui.techunter.model.Position;
import com.prosantosgui.techunter.model.Recruiter;
import com.prosantosgui.techunter.repositories.PositionRepository;
import com.prosantosgui.techunter.services.PositionService;
import com.prosantosgui.techunter.services.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.prosantosgui.techunter.exceptions.NoSuchElementException;
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
        throw new NoSuchElementException("No Recruiter for this ID!");
    }
    @Override
    public Position findById(Long id) {
        Optional<Position> position = positionRepository.findById(id);
        if(position.isPresent()){
            return position.get();
        }else{
            throw new NoSuchElementException("No position for this ID!");
        }
    }

    @Override
    public ResponseEntity<Position> savePosition(Position position) {
        String userLoggedName = SecurityContextHolder.getContext().getAuthentication().getName();
        Recruiter recruiterLogged = recruiterService.findById(userLoggedName);

        if(recruiterLogged != null){
            position.setRecruiter(recruiterLogged);
            Position positionSaved = positionRepository.save(position);
            return new ResponseEntity<>(positionSaved, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(position, HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        String userLoggedName = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Position> position = positionRepository.findById(id);

        Collection<? extends GrantedAuthority> userAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        boolean admin = userAuthorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
        boolean userIsPositionOwner = position.orElseThrow(()-> new NoSuchElementException("No position for this ID!")).getRecruiter().getLogin().equals(userLoggedName);

        if(userIsPositionOwner || admin){
            positionRepository.deleteById(id);
            return new ResponseEntity<>("Position deleted successfully!", HttpStatus.OK);
        }else {
            throw new AuthenticationException("You have no permission to delete this position!");
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
        Collection<? extends GrantedAuthority> userAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        boolean admin = userAuthorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
        boolean userIsPositionOwner = positionSaved.getRecruiter().getLogin().equals(userLoggedName);

        if(userIsPositionOwner || admin){
            Position updatedPosition = this.mapNewPosition(id, positionSaved,modifiedPosition);
            positionRepository.save(updatedPosition);
            return new ResponseEntity<>(updatedPosition, HttpStatus.OK);
        }else {
                return new ResponseEntity<>(modifiedPosition, HttpStatus.FORBIDDEN);
        }

    }
}
