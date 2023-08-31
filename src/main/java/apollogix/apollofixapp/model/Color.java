package apollogix.apollofixapp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="colors")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
}
