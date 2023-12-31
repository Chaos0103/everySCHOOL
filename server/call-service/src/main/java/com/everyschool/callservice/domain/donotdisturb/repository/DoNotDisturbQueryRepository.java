package com.everyschool.callservice.domain.donotdisturb.repository;

import com.everyschool.callservice.api.controller.donotdisturb.response.DoNotDisturbResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.everyschool.callservice.domain.donotdisturb.QDoNotDisturb.doNotDisturb;

@Repository
public class DoNotDisturbQueryRepository {

    private final JPAQueryFactory queryFactory;

    public DoNotDisturbQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public DoNotDisturbResponse findByUserId(Long userId) {
        return queryFactory
                .select(Projections.constructor(
                        DoNotDisturbResponse.class,
                        doNotDisturb.id,
                        doNotDisturb.startTime,
                        doNotDisturb.endTime,
                        doNotDisturb.isActivate
                ))
                .from(doNotDisturb)
                .where(doNotDisturb.teacherId.eq(userId))
                .orderBy(doNotDisturb.id.desc())
                .limit(1)
                .fetchFirst();
    }
}
