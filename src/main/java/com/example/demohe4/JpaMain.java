package com.example.demohe4;

import com.example.demohe4.domain.Comment;
import com.example.demohe4.domain.Post;
import com.example.demohe4.domain.User;
import jakarta.persistence.*;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("demohe4");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();


        try{
            Save(tx, em);
            getPost(em,1L);
        }catch (Exception e){
            tx.rollback();  
        }finally {
            em.close();
        }
        emf.close();

    }
    public static Post logic(String title, String content, User user){
        Post post = new Post(title, content, user);
        return post;
    }

    public static void Save(EntityTransaction tx, EntityManager em){
        tx.begin();
        User user1 = new User("he4");
        em.persist(user1);
        User user2 = new User("he5");
        em.persist(user2);
        Post post=logic("제목", "내용입니다~~", user1);
        em.persist(post);
        Comment comment=new Comment(user1, "댓글임", post);
        em.persist(comment);
        tx.commit();
    }

    public static void getPost(EntityManager em, Long postId){
        Post post=em.find(Post.class, postId);
        System.out.println("조회된 게시글 제목 : " + post.getTitle());
    }
}
