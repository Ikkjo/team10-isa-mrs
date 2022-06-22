package team10.app.util;

import org.springframework.data.domain.Sort;

public class Sorting {
    public static Sort.Order getSorting(String[] sortTokens) {
        return new Sort.Order(getSortDirection(sortTokens[1]), sortTokens[0]);
    }
    private static Sort.Direction getSortDirection(String s) {
        return s.contains("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
    }
}
