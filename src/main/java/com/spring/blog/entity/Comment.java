package com.spring.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name = "comments")
public class Comment {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;
    private String name;
    private String email;
    private String body;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    @JsonManagedReference
    @JsonIgnore
    private Post post;

 /*

 FetchType.LAZY : This means that the associated entity will not be loaded from the
 database until it is explicitly accessed. In other words, the related entity is fetched lazily,
 i.e., on-demand when it's needed. This can be beneficial for performance,
 especially if the related entity is large or not always needed.
 It helps to reduce unnecessary database queries and can improve application performance.

 */

    /**
     *     In the Comment entity, the post field is annotated with @ManyToOne to represent
     *  the many-to-one relationship with the Post entity.
     *
     * The @JoinColumn annotation is used to specify the column name
     *  (post_id) in the Book table that will act as the foreign key referencing
     *  the primary key column ('id') of the Author table.
      */


}
