package team10.app.model;

import lombok.Data;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import java.util.List;

@Data
public abstract class RentalEntity {
    protected String name;
    protected String description;
    @ElementCollection
    @CollectionTable(name = "my_pictures", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "pictures", nullable = false)
    protected List<String> pictures;
    @ElementCollection
    @CollectionTable(name = "my_rulesOfConduct", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "rulesOfConduct", nullable = false)
    protected List<String> rulesOfConduct;
    protected double price;
    protected boolean deleted = false;
}
