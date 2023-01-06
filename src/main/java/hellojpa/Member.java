package hellojpa;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Member {



    @Id
    private Long id;
    private String name;

    //JPA는 동적으로 객체를 생성해내기 때문에 기본 생성자가 필요함.
    public Member() {}



    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
