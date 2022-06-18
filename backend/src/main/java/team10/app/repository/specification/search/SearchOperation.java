package team10.app.repository.specification.search;

import java.util.Locale;

public enum SearchOperation {
    EQUALS,
    LESS_THAN,
    GREATER_THAN,
    LESS_OR_EQ,
    GREATER_OR_EQ;

    public static SearchOperation stringToSearchOperation(String operationStr) {
        switch (operationStr.toLowerCase(Locale.ROOT)) {
            case "equals":
            case "equal":
            case "eq":
            case "=":
                return SearchOperation.EQUALS;
            case "less_than":
            case "less than":
            case "less-than":
            case "lt":
            case "<":
                return SearchOperation.LESS_THAN;
            case "greater_than":
            case "greater than":
            case "greater-than":
            case "gt":
            case ">":
                return SearchOperation.GREATER_THAN;
            case "less or eq":
            case "less_or_eq":
            case "less-or-eq":
            case "leq":
            case "<=":
                return SearchOperation.LESS_OR_EQ;
            case "greater or eq":
            case "greater_or_eq":
            case "greater-or-eq":
            case "geq":
            case ">=":
                return SearchOperation.GREATER_OR_EQ;
            default:
                return null;
        }
    }
}
