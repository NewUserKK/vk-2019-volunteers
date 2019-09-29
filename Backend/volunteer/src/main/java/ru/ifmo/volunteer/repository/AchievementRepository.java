package ru.ifmo.volunteer.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ifmo.volunteer.model.Achievement;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {

  @Query(
      value =
          "SELECT * FROM achievement WHERE id IN "
              + "(SELECT volunteer_to_achievements.achievement "
              + "FROM volunteer_to_achievements WHERE user_id = :userId)",
      nativeQuery = true)
  List<Achievement> getAllByUserId(Long userId);

  @Transactional
  @Modifying
  @Query(
      value = "INSERT INTO volunteer_to_achievements VALUES(:userId, :achId, :date)",
      nativeQuery = true)
  void awardUser(
      @Param("userId") Long userId, @Param("achId") Long achievementId, @Param("date") Long date);
}
