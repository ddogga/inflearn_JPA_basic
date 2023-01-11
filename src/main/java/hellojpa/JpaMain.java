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


            //팀 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);


            //회원 저장
            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);


//            team.getMembers().add(member);

            //쿼리 바로 실행
//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            //Team에서 member 조회
            System.out.println("===================================================");
            for (Member m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }
            System.out.println("===================================================");

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }
}
