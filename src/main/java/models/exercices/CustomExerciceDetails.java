package models.exercices;

import models.domains.CustomExerciceDTO;
import models.domains.CustomExerciceDetailsDTO;
import models.domains.ExerciceDTO;
import models.domains.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class CustomExerciceDetails {

    public CustomExerciceDetails() {}

    public List<CustomExerciceDetailsDTO> getCustomExercicesWithDetails(List<CustomExerciceDTO> customExercices, List<UserDTO> users, List<ExerciceDTO> exercices) {
        return customExercices.stream()
            .map(customEx -> {
                UserDTO user = users.stream().filter(u -> u.getId().equals(customEx.getUserId())).findFirst().orElse(null);
                ExerciceDTO exercice = exercices.stream().filter(e -> e.getId().equals(customEx.getExerciceId())).findFirst().orElse(null);
                return new CustomExerciceDetailsDTO(user, exercice);
            })
            .collect(Collectors.toList());
    }
}
