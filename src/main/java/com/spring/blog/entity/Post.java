package com.spring.blog.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/*@Data (Removed this because @Data comes with additional features such as toString and more,
* in this case we don't need toString because we have added Set<Comment> and that will not
* give us the expected response so we are using @Getter and @Setter*/

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = "title")}
)
public class Post {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "content", nullable = false)
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    @JsonIgnore
    Set<Comment> comments = new HashSet<>();

}
/**

In this example, the post entity has an id field annotated with
@Id, indicating it as the primary key.The @GeneratedValue annotation with
GenerationType.IDENTITY strategy instructs the persistence provider
to rely on the database to generate unique primary key values for post entities.

*/

/**
 * 1.The Post entity has a one-to-many relationship with the Comment entity. An author can have multiple books.
 * 2.In the Post entity, the books field represents the collection of associated books.
 * It's annotated with @OneToMany to establish the relationship.
 * 3. The mappedBy attribute of @OneToMany is used to specify the field in the Comment entity
 * that owns the relationship (in this case, the posts field).
 * 4. The cascade attribute specifies that operations on the Post entity
  *(such as persist, remove, etc.) should cascade to the associated Comment entities.
 * 5. The orphanRemoval attribute specifies that when a Comment entity is removed
 * from the collection in Post, it should be deleted from the database as well.*/
