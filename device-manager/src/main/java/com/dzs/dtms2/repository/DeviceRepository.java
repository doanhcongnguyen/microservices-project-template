package com.dzs.dtms2.repository;

import com.dzs.dtms2.domain.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long>, DeviceRepositoryCustom {

    @Query("FROM DeviceEntity WHERE isDeleted = 0 ")
    List<DeviceEntity> findAll();

    @Query("FROM DeviceEntity WHERE serial = ?1 AND isDeleted = 0 ")
    DeviceEntity findOneBySerial(String serial);

    @Modifying
    @Query("UPDATE DeviceEntity SET isDeleted = 1 WHERE serial = ?1")
    void deleteBySerial(String serial);
}
