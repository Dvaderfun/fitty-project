package com.politechnic.fittyproject;


import com.politechnic.fittyproject.entity.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserTest, Long> {}