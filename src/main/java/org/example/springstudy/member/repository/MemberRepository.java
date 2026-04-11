package org.example.springstudy.member.repository;

import org.example.springstudy.member.entity.MemberEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemberRepository {

    private static Map<Long, MemberEntity> memberStore = new ConcurrentHashMap<>();

    public MemberEntity save(MemberEntity member) {
        memberStore.put(member.getId(), member);
        return member;
    }

    public Optional<MemberEntity> findByLoginId(String loginId) {
        return memberStore.values().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    public Optional<MemberEntity> findById(Long id) {
        return Optional.ofNullable(memberStore.get(id));
    }
}
