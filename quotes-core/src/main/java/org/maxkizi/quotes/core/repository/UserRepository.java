package org.maxkizi.quotes.core.repository;

import org.maxkizi.quotes.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
