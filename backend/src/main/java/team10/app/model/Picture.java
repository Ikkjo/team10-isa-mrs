package team10.app.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Picture {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "type")
    private String type;
    @Column(name = "pic_byte", length = 1000)
    private byte[] picByte;

    public Picture(String type, byte[] bytes) {
        this.type = type;
        this.picByte = bytes;
    }
}
