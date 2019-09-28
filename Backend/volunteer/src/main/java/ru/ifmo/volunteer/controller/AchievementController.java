package ru.ifmo.volunteer.controller;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ifmo.volunteer.model.Achievement;
import ru.ifmo.volunteer.service.AchievementService;

@RestController
@RequestMapping("api/v1/achievement")
public class AchievementController {
  private final AchievementService achievementService;


  public AchievementController(AchievementService achievementService) {
    this.achievementService = achievementService;
  }

  @ApiOperation(
      value = "Получение список ачиков по id юзера",
      produces = "application/json",
      response = Achievement.class,
      responseContainer = "List"
  )
  @GetMapping("{userId}")
  public List<Achievement> getAllByUserId(@PathVariable Long userId) {
    return achievementService.getAllByUserId(userId);
  }

}
