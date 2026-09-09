package org.booleanuk.app.repository;

import org.springframework.stereotype.Repository;
import org.booleanuk.app.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
