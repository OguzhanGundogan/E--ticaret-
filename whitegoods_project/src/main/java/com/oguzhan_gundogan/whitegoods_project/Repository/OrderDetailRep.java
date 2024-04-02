package com.oguzhan_gundogan.whitegoods_project.Repository;

import com.oguzhan_gundogan.whitegoods_project.entity.OrdersCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRep extends JpaRepository<OrdersCheck, Long> {

}
