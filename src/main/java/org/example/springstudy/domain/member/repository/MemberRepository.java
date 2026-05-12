package org.example.springstudy.domain.member.repository;

import org.example.springstudy.domain.member.entity.MemberEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemberRepository {
    private static final ConcurrentHashMap<Long, MemberEntity> memberStore = new ConcurrentHashMap<>();

    public MemberEntity save(MemberEntity member) {
        memberStore.put(member.getId(), member);
        return member;
    }

    public Optional<MemberEntity> findByUsername(String username) {
        for (MemberEntity entity : memberStore.values()) {
            if (entity.getUsername().equals(username)) {
                return Optional.of(entity);
            }
        }
        return Optional.empty();
    }

    public Optional<MemberEntity> findById(Long memberId) {
        return Optional.ofNullable(memberStore.get(memberId));
    }
}
