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
        // 클라이언트에서 데이터 변경 요청이 들어 올때 마다 생성되고 소멸됨
        EntityManager em = emf.createEntityManager();


        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = em.find(Member.class,1L);

            //team을 사용하는 경우와 사용하지 않는 경우
            //사용할 경우 find에서 함께 조회
            //사용하지 않을경우 team을 조회하지 않도록 최적화 하는 것이 좋음.
            printMember(member);

//            printMemberAndTeam(member);

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }

    public static void printMember(Member member) {
        System.out.println("회원 이름: " + member.getUsername());
    }

    public static void printMemberAndTeam(Member member) {
        System.out.println("회원 이름: " + member.getUsername());
        Team team = member.getTeam();
        System.out.println("소속팀: " + team.getName());
    }
}
