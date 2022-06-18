package team10.app.repository.specification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import team10.app.model.*;
import team10.app.repository.specification.search.SearchCriteria;
import team10.app.repository.specification.search.SearchOperation;

import javax.persistence.criteria.*;

@AllArgsConstructor
@Getter
public class RentalEntitySpecification implements Specification<RentalEntity> {

    private final SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<RentalEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        SearchOperation operation = criteria.getOperation();

        switch(operation) {
            case EQUALS:
                return getEqualsPredicate(root, query, criteriaBuilder);
            case LESS_THAN:
                return getLessThanPredicate(root, query, criteriaBuilder);
            case GREATER_THAN:
                return getGreaterThanPredicate(root, query, criteriaBuilder);
            case GREATER_OR_EQ:
            case LESS_OR_EQ:
            default:
                return null;
        }

//        if (criteria.getOperation().equalsIgnoreCase(">")) {
//            if (root.get(criteria.getKey()).getJavaType() == String.class) {
//
//            } else if (root.get(criteria.getKey()).getJavaType() == long.class){
//                return criteriaBuilder.greaterThanOrEqualTo(
//                        root.<Long>get(criteria.getKey()), (long)criteria.getValue());
//            }
//
//        }
//        else if (criteria.getOperation().equalsIgnoreCase("<")) {
//            return criteriaBuilder.lessThanOrEqualTo(
//                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
//        } else if (root.get(criteria.getKey()).getJavaType() == long.class){
//            return criteriaBuilder.lessThanOrEqualTo(
//                    root.<Long>get(criteria.getKey()), (long)criteria.getValue());
//        }
//        else if (criteria.getOperation().equalsIgnoreCase(":")) {
//            if (root.get(criteria.getKey()).getJavaType() == String.class) {
//                return criteriaBuilder.like(
//                        root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
//            } else {
//                return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
//            }
//        }
//        return null;
    }

    private Predicate getEqualsPredicate(Root<RentalEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
        String field = criteria.getKey();
        Join<RentalEntity, Address> addressJoin = root.join("address");
        Join<RentalEntity, Availability> availabilityJoin = root.join("availability");
        switch(field) {
            case "title":
            case "description":
            case "additional_services":
                return criteriaBuilder.like(criteriaBuilder.lower(
                        root.<String>get(criteria.getKey())), (String) criteria.getValue());
            case "city":
            case "country":
            case "address":
                return criteriaBuilder.like(criteriaBuilder.lower(
                        addressJoin.<String>get(criteria.getKey())), (String) criteria.getValue());
            case "fromDate":
            case "toDate":
            case "date":
                return criteriaBuilder.equal(
                        availabilityJoin.<Long>get(criteria.getKey()), (Long) criteria.getValue());
            case "owner":
                return criteriaBuilder.equal(root.get("owner_id"), criteria);
        }
        return null;
    }

    private Predicate getGreaterThanPredicate(Root<RentalEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
        String field = criteria.getKey();
        Join<RentalEntity, Availability> availabilityJoin = root.join("availability");
        switch(field) {
            case "title":
            case "description":
            case "additional_services":
                return criteriaBuilder.greaterThan(
                        root.<String> get(criteria.getKey()), criteria.getValue().toString());
            case "fromDate":
            case "toDate":
            case "date":
                return null;//criteriaBuilder.greaterThan(availabilityJoin.<Long>get(criteria.getKey()), criteria.getValue());
        }
        return null;
    }

    private Predicate getLessThanPredicate(Root<RentalEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        String field = criteria.getKey();
        switch(field) {
            case "city":
            case "country":
            case "address":
                break;
            case "title":
            case "description":
            case "additional_services":
                break;
            case "owner":
                break;
        }
        return null;
    }
}
