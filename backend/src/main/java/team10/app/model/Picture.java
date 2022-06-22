package team10.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Base64;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Picture {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    private UUID id;
    @Column(name = "type")
    private String type;
    @Column(name = "pic_byte", length = 10000000)
    private byte[] bytes;

    public Picture(String type, byte[] bytes) {
        this.type = type;
        this.bytes = bytes;
    }

    public String asString() {
        return this.type + "," + Base64.getEncoder().encodeToString(this.bytes);
    }
}
