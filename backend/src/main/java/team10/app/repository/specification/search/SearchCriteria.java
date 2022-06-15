package team10.app.repository.specification.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SearchCriteria {
    private String key;
    private String operation;
    private Object value;
}
