package com.example.demohe4;

import com.example.demohe4.domain.Comment;
import com.example.demohe4.domain.Post;
import com.example.demohe4.domain.User;
import jakarta.persistence.*;

import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("demohe4");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();


        try{
            Save(tx, em);
            query(em,tx,1L);
            //getPost(em,1L);
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

    public static void query(EntityManager em, EntityTransaction tx, Long userId) {
        tx.begin();
        User user = em.find(User.class, userId);
        System.out.println("print : " + user.toString());
        List<Post> postList = em.createQuery("select p from Post p where p.user.id=:userId", Post.class).setParameter("userId", userId).getResultList();
        List<Post> postList2 = em.createQuery("select p from Post p where p.title=:title and p.content=:content", Post.class).setParameter("title", "제목").setParameter("content", "내용입니다~~").getResultList();
        for (Post post : postList2) {
            System.out.println(post.toString());
            List<Comment> commentList = em.createQuery("select c from Comment c where c.post.title=:postTitle", Comment.class).setParameter("postTitle", post.getTitle()).getResultList();
            /*
            Query 내부에서 객체로 조회할 때, id 값 처럼 객체의 필드로 찾아 낼 수도 있고, Entity 관점에서 직접 Entity로도 찾아 낼 수 있음
             */
            for (Comment comment : commentList) {
                System.out.println(comment.toString());
            }
        }


        tx.commit();
    }
}
