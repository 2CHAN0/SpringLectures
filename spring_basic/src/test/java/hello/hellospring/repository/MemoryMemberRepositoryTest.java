package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository reposiroty = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        reposiroty.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        reposiroty.save(member);

        Member result = reposiroty.findById(member.getId()).get(); // optional에서 값을 꺼내는 방법
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        reposiroty.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        reposiroty.save(member2);

        Member result = reposiroty.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        reposiroty.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        reposiroty.save(member2);

        List<Member> result = reposiroty.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
