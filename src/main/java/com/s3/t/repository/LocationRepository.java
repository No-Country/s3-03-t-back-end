package com.s3.t.repository;

import com.s3.t.model.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByLocationOrProvince(String location, String province);

    Location findByLocation(String location);
}
