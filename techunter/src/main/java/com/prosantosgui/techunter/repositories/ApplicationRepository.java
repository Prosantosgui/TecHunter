package com.prosantosgui.techunter.repositories;

import com.prosantosgui.techunter.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
