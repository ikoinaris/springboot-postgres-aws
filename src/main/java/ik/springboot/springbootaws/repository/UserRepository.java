package ik.springboot.springbootaws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ik.springboot.springbootaws.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}