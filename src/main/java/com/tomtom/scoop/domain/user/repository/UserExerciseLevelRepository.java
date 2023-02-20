package com.tomtom.scoop.domain.user.repository;

import com.tomtom.scoop.domain.user.model.entity.ExerciseLevel;
import com.tomtom.scoop.domain.user.model.entity.User;
import com.tomtom.scoop.domain.user.model.entity.UserExerciseLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserExerciseLevelRepository extends JpaRepository<UserExerciseLevel, Long> {
    Optional<UserExerciseLevel> findByExerciseLevelAndUser(ExerciseLevel exerciseLevel, User user);

    List<UserExerciseLevel> findByUser(User user);

    void deleteAllByUser(User user);
}
