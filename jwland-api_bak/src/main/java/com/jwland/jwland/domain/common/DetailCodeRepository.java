package com.jwland.jwland.domain.common;

import com.jwland.jwland.entity.DetailCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetailCodeRepository extends JpaRepository<DetailCode, Long> {

    @Query("SELECT dc FROM DetailCode dc JOIN FETCH dc.groupCode gc WHERE gc.code = :groupCode ORDER BY dc.code")
    List<DetailCode> findDetailCodeByGroupCode(@Param("groupCode") String groupCode);
}
