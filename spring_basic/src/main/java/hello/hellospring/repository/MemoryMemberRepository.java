package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryMemberRepository implements MemberReposiroty {
    private static Map<Long, Member> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()//람다 사용하는 방법(mapper)
                .filter(member -> member.getName().equals(name))
                .findAny();//1개라도 찾는 것
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //값을 쓰기 쉽게 map이 아니라 list로 만들어서 반환해줌.
    }

    public void clearStore(){
        store.clear();
    }
}
