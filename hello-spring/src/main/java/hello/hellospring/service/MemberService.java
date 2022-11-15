package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 회원 가입
    public long join(Member member) {
        // 중복 id 비허용
//        Optional<Member> result = memberRepository.findById(member.getId());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 아이디입니다.");
//        });

        vaildateDuplicateMember(member); // 중복 아이디 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void vaildateDuplicateMember(Member member) {
        memberRepository.findById(member.getId())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 아이디입니다.");
                        });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
