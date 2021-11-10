package com.antra.videomanager.repository.impl;

import com.antra.videomanager.domain.entity.Video;
import com.antra.videomanager.domain.entity.VideoTag;
import com.antra.videomanager.repository.VideoTagRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class VideoTagRepositoryCustomImpl implements VideoTagRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Video> findVideos(String tagValue) {
//        String query =  "select distinct v from Video v left join v.videoTagSet vts " +
//                "left join vts.tag where (v.archivedFlag is null or v.archivedFlag <> true)";
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Video> criteriaQuery = criteriaBuilder.createQuery(Video.class);
        Root<Video> from = criteriaQuery.from(Video.class);
        Join<Object, Object> f1 = (Join<Object, Object>)from.fetch("videoTagSet", JoinType.LEFT);
        f1.fetch("tag", JoinType.LEFT);


        Predicate exp1 = null; //   table.tagValue = xx
        if(tagValue != null && !tagValue.isEmpty()) {
            Join<Object, Object> f2 = (Join<Object, Object>)f1.fetch("videoTagValueSet", JoinType.LEFT);
            exp1 = criteriaBuilder.equal(f2.get("tagValue"), tagValue);
        }

        Predicate exp2 = criteriaBuilder.isNull(from.get("archivedFlag")); // table.archivedFlag is null
        Predicate exp3 = criteriaBuilder.equal(from.get("archivedFlag"), false);
        Predicate or = criteriaBuilder.or(exp2, exp3);
        // or = table.archivedFlag is null or table.archivedFlag = false
        criteriaQuery.select(from).distinct(true);

        if(exp1 != null) {
            criteriaQuery.where(criteriaBuilder.and(exp1, or));
            //where table.tagValue = xx and (table.archivedFlag is null or table.archivedFlag = false)
        } else {
            criteriaQuery.where(or);
            //where table.archivedFlag is null or table.archivedFlag = false
        }

        Query q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }
}
