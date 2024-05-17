package co.develhope.ServiziFileUpload.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(indexes={
        @Index(unique = true, name = "email_idx", columnList = "email")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String profilePictures;
}
