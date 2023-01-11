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



            //회원 저장
            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            //팀 저장
            Team team = new Team();
            team.setName("TeamA");
            //연관관계 주인이 아닌 Team에만 member값 입력
            team.getMembers().add(member);
            em.persist(team);


            //쿼리 바로 실행
            em.flush();
            em.clear();

            //Team에서 member 조회
            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();
            
            for (Member m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }

            System.out.println("===================================================");
            tx.commit();
            System.out.println("===================================================");
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }
}
