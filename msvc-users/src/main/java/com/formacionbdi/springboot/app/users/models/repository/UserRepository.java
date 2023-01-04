package com.formacionbdi.springboot.app.users.models.repository;

import com.formacionbdi.springboot.app.users.models.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    public User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username =?1")
    public User getByUsername(String username);

}
