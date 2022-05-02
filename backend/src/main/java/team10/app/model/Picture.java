package team10.app.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Picture {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type")
    private String type;
    @Column(name = "pic_byte", length = 1000)
    private byte[] picByte;

    public Picture(String type, byte[] bytes) {
        this.type = type;
        this.picByte = bytes;
    }
}
