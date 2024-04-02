package com.oguzhan_gundogan.whitegoods_project.Repository;

import com.oguzhan_gundogan.whitegoods_project.entity.User;
import com.oguzhan_gundogan.whitegoods_project.entity.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRep extends JpaRepository<User,Long> {
    Optional<User> findByFirstName(String firstName);
}