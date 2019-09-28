package ru.ifmo.volunteer.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import ru.ifmo.volunteer.model.Achievement;
import ru.ifmo.volunteer.repository.AchievementRepository;

@Service
public class AchievementService {
  private final AchievementRepository achievementRepository;


  public AchievementService(
      AchievementRepository achievementRepository) {
    this.achievementRepository = achievementRepository;
  }


  public List<Achievement> getAllByUserId(Long userId) {
    return achievementRepository.getAllByUserId(userId);
  }

  public void awardUser(Long userId, Long achievementId) {
    Long date = System.currentTimeMillis();
    achievementRepository.awardUser(userId, achievementId, date);
  }
}
