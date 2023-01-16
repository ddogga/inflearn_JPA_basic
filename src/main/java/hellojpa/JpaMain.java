package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();


        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member1 = new Member();
            member1.setUsername("hello1");
            em.persist(member1);


            em.flush();
            em.clear();

            //find로 실제 엔티티를 먼저 조회할 경우

//            Member m1 = em.find(Member.class, member1.getId());
//            System.out.println("m1.getClass() = " + m1.getClass());
//
//            Member reference = em.getReference(Member.class, member1.getId());
//            System.out.println("reference.getClass() = " + reference.getClass());
//
//            System.out.println("a == a: " + (m1 == reference));

            //getReference로 프록시 객체를 먼저 조회할 경우


            Member refMember = em.getReference(Member.class, member1.getId());
            System.out.println("refMember = " + refMember.getClass());

            Member findMember = em.find(Member.class, member1.getId());
            System.out.println("findMember.getClass() = " + findMember.getClass());

            System.out.println("refMember == findMember: " + (refMember == findMember));

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }

}
