package com.dzs.dtms2.repository;

import com.dzs.dtms2.domain.dto.PaginationDto;
import com.dzs.dtms2.domain.entity.DeviceEntity;
import com.dzs.dtms2.utils.CommonUtils;
import com.dzs.dtms2.utils.PaginationUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class DeviceRepositoryImpl implements DeviceRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public PaginationDto filter(String serial, int pageSize, int pageNumber) {
        List<Object> paramList = new ArrayList<>();
        String filterSql = this.createFilterSqlAndAddParam(paramList, serial);
        String countSql = "SELECT COUNT(*) " + filterSql;
        String objectSql = "SELECT * " + filterSql;

        Query countQuery = entityManager.createNativeQuery(countSql);
        Query objectQuery = entityManager.createNativeQuery(objectSql, DeviceEntity.class);

        return PaginationUtils.createPagination(paramList, countQuery, objectQuery, pageSize, pageNumber);

    }

    private String createFilterSqlAndAddParam(List<Object> paramList, String serial) {
        StringBuilder filterSql = new StringBuilder(" FROM device " +
                " WHERE is_deleted = 0");
        if (!CommonUtils.isNullOrEmpty(serial)) {
            filterSql.append(" AND serial LIKE ?");
            paramList.add("%" + serial + "%");
        }
        return filterSql.toString();
    }
}
