package com.formacionbdi.springboot.app.users.models.repository;

import com.formacionbdi.springboot.app.commons.users.models.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    @RestResource(path = "find-username")
    public User findByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.username =?1")
    public User getByUsername(String username);

}
