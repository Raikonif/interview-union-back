package raikonif.apicrud.interview_union_back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import raikonif.apicrud.interview_union_back.entity.Client;


public interface ClientDao extends JpaRepository<Client, Long> {
}
