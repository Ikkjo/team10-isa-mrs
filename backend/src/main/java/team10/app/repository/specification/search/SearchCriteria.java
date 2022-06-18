package team10.app.repository.specification.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

@Getter
@Setter
public class SearchCriteria {
    private String key;
    private SearchOperation operation;
    private Object value;

    public SearchCriteria(String key, SearchOperation operation, Object value) {
        this.key =  key.toLowerCase(Locale.getDefault());
        this.operation = operation;
        this.value = value;
    }

    public SearchCriteria(String key, String operation, Object value) {
        this.key =  key.toLowerCase(Locale.getDefault());
        this.operation = SearchOperation.stringToSearchOperation(operation);
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
