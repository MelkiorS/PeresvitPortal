package ua.peresvit.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.peresvit.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findOneByEmail(String email);
}