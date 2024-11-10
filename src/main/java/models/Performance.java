package models;

import models.domains.PerformanceDTO;
import models.domains.UserDTO;
import utils.JsonDBUtil;
import utils.UserSessionManager;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Performance {

    private static final String PERFORMANCE_FILE_PATH = "src/main/java/data/performances.json";
    private UserDTO userDTO;

    public Performance() {
        this.userDTO = UserSessionManager.currentUser;
    }

    public void addExercicePerformance(String exerciceId, String performanceText) {
        List<PerformanceDTO> list = JsonDBUtil.readFromJson(PERFORMANCE_FILE_PATH, PerformanceDTO.class);

        Optional<PerformanceDTO> existingPerformanceOpt = list.stream()
                .filter(performanceDTO -> performanceDTO.getExerciceId().toString().equals(exerciceId) &&
                        performanceDTO.getUserId().equals(this.userDTO.getId()))
                .findFirst();

        existingPerformanceOpt.ifPresent(list::remove);

        PerformanceDTO newPerformance = new PerformanceDTO(performanceText, this.userDTO.getId(), UUID.fromString(exerciceId));
        list.add(newPerformance);

        JsonDBUtil.writeToJson(PERFORMANCE_FILE_PATH, list);
    }

    public String getExercicePerformanceTextOfUser(String exerciceId) {
        List<PerformanceDTO> list =  JsonDBUtil.readFromJson(PERFORMANCE_FILE_PATH, PerformanceDTO.class);

        Optional<PerformanceDTO> performanceOpt = list.stream()
                .filter(performanceDTO -> performanceDTO.getExerciceId().toString().equals(exerciceId) &&
                        performanceDTO.getUserId().equals(this.userDTO.getId()))
                .findFirst();

        return performanceOpt.map(PerformanceDTO::getText).orElse("");
    }
}
