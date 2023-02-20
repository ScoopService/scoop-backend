package com.tomtom.scoop.domain.user.repository;

import com.tomtom.scoop.domain.user.model.entity.User;
import com.tomtom.scoop.domain.user.model.entity.UserKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserKeywordRepository extends JpaRepository<UserKeyword, Long> {
    List<UserKeyword> findByUser(User user);

    void deleteAllByUser(User user);

}
