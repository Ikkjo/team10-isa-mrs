package team10.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EarningsReportDto {
    private long fromDate;
    private long toDate;
    private List<IndividualEarningsDto> individualEarnings;
    private Map<Long, Double> dailyEarnings;


    public EarningsReportDto(long fromDate, long toDate, Collection<IndividualEarningsDto> individualEarningsDtoCollection, Map<Long, Double> dailyEarnings) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.individualEarnings = new ArrayList<>(individualEarningsDtoCollection);
        this.dailyEarnings = dailyEarnings;
    }
}
