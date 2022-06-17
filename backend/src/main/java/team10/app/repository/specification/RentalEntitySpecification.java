package team10.app.repository.specification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import team10.app.model.RentalEntity;
import team10.app.repository.specification.search.SearchCriteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
@Getter
public class RentalEntitySpecification implements Specification<RentalEntity> {

    private final SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<RentalEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return criteriaBuilder.greaterThanOrEqualTo(
                        root.<String> get(criteria.getKey()), criteria.getValue().toString());
            } else if (root.get(criteria.getKey()).getJavaType() == long.class){
                return criteriaBuilder.greaterThanOrEqualTo(
                        root.<Long>get(criteria.getKey()), (long)criteria.getValue());
            }

        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        } else if (root.get(criteria.getKey()).getJavaType() == long.class){
            return criteriaBuilder.lessThanOrEqualTo(
                    root.<Long>get(criteria.getKey()), (long)criteria.getValue());
        }
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return criteriaBuilder.like(
                        root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}
