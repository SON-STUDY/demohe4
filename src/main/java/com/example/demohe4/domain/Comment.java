package com.example.demohe4.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CollectionIdMutability;

@Entity
@Table(name="comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter @Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String content;

    @JoinColumn(name="post_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public Comment(User user, String content, Post post) {
        this.user = user;
        this.content = content;
        this.post = post;
    }
}
