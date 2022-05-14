package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberReposiroty memberReposiroty;

    public MemberService(MemberReposiroty memberReposiroty) {
        this.memberReposiroty = memberReposiroty;
    }

    /**
     * 회원가입
     * @param member
     * @return
     */
    public Long join(Member member){
        //중복 회원 검증
        validateDuplicateMember(member);
        memberReposiroty.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberReposiroty.findByName(member.getName())
                .ifPresent(m -> { // 반환 값이 optional 이라서 사용 가능한 메서드
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체회원조회
     */
    public List<Member> findMembers(){
        return memberReposiroty.findAll();
    }

    /**
     * 한 회원 조회
     */
    public Optional<Member> findOne(Long memberid){
        return memberReposiroty.findById(memberid);
    }
}
