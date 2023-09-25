package com.cross.jp.projectmanage;

import com.cross.jp.projectmanage.entity.Order;
import org.springframework.data.jpa.domain.Specification;


public class ProjectSpecification {
    public Specification<Order> deliveryDateContains(String date){
        return date == null ? null : (root, query, criteriaBuilder) -> {
            query.orderBy(criteriaBuilder.asc(root.get("deliveryDate"))); //昇順で取り出す　order by asc deliveryDate
            return criteriaBuilder.like(root.get("deliveryDate"), date + "%");
        };
    }

    public Specification<Order> itemContains(Integer item){
        return item == null ? null : (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("itemCategory"), item);
        };
    }
    public Specification<Order> checkContains(Boolean endCheck){
        return endCheck == null ? null : (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("endCheck"), endCheck);
        };
    }
}
