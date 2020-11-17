package com.dzs.dtms2.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "device")
@Data
public class DeviceEntity extends AuditingEntity implements Serializable {

    @Id
    @Column
    private String serial;

    @Column
    private String modelName;

    @Column
    private String softwareVersion;

    @Column
    private String status;

    @Column
    private Long isDeleted;
}
