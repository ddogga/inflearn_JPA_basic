package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 클라이언트에서 데이터 변경 요청이 들어 올때 마다 생성되고 소멸됨
        EntityManager em = emf.createEntityManager();


        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Movie movie = new Movie();
            movie.setDirector("aaaa");
            movie.setActor("bbbb");
            movie.setName("바람과 함께 사라지다.");
            movie.setPrice(10000);

            em.persist(movie);

            em.flush();
            em.clear();

            //구현 클래스마다 테이블 전략 사용시
            //부모 타입으로 조회할 경우
            Item item = em.find(Item.class, movie.getId());
            System.out.println("item = " + item);


            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }
}
