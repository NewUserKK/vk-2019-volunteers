package ru.ifmo.volunteer.controller;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ifmo.volunteer.model.Achievement;
import ru.ifmo.volunteer.service.AchievementService;

@RestController
@RequestMapping("api/v1/achievement")
public class AchievementController {
  private final AchievementService achievementService;

  public AchievementController(final AchievementService achievementService) {
    this.achievementService = achievementService;
  }

  @ApiOperation(value = "Присвоить ачивку юзеру", produces = "application/json")
  @PostMapping("{userId}/award/")
  public void awardUser(@PathVariable final long userId, @RequestParam final long achievementId) {
    achievementService.awardUser(userId, achievementId);
  }

  @ApiOperation(
      value = "Получение список ачиков по id юзера",
      produces = "application/json",
      response = Achievement.class,
      responseContainer = "List")
  @GetMapping("{userId}")
  public List<Achievement> getAllByUserId(@PathVariable final long userId) {
    return achievementService.getAllByUserId(userId);
  }
}
