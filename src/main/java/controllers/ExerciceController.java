package controllers;

import models.domains.MediaDTO;
import models.domains.ExerciceDTO;
import models.enums.Difficulty;
import models.enums.MediaType;
import models.exercices.CustomExercice;
import models.exercices.Exercice;
import models.enums.ExerciceType;
import features.managers.FeatureManager;
import features.FeaturesEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExerciceController{
    private Logger logger = Logger.getLogger("ExerciceController");
    private Exercice exerciceModel;
    private CustomExercice customExerciceModel;
    private FeatureManager featureManager;

    public ExerciceController() {
        exerciceModel = new Exercice();
        customExerciceModel = new CustomExercice();
        featureManager = FeatureManager.getInstance();
    }

    public ExerciceDTO addExercice(ExerciceDTO exerciceDTO, Object... extraParams) {
        if (exerciceDTO.isCustom() && !featureManager.isActive(FeaturesEnum.EXERCICE_CUSTOM_ADD.getFeature())) {
            logger.warning("exercice-custom-add feature is disabled.");
            return null;
        }

        if (exerciceDTO.getName() == null || exerciceDTO.getName().isEmpty()) {
            logger.log(Level.WARNING, "Add Exercice failed: Fields missing or empty");
            return null;
        }

        if (exerciceModel.getExerciceByName(exerciceDTO.getName()) != null) {
            logger.log(Level.WARNING, "Add Exercice failed: Exercice already exists - " + exerciceDTO.getName());
            return null;
        }

        ExerciceDTO ex = exerciceModel.addExercice(exerciceDTO, extraParams);
        if (exerciceDTO.isCustom()) {
            customExerciceModel.addCustomExercice(ex.getId());
        }

        return ex;
    }

    public List<ExerciceDTO> getAllNoCustomExercices() {
        return exerciceModel.getAllNoCustomExercices(getCurrentDifficulty(), getCurrentTypes());
    }

    public boolean deleteExercice(String id) {
        return exerciceModel.deleteExercice(id);
    }

    public void setExerciceDifficulty(Difficulty difficulty) {
        String featureName = difficulty.getFeatureName();
        featureManager.activate(featureName);
    }

    public void setExerciceTypes(List<ExerciceType> types) {
        List<ExerciceType> currentTypes = getCurrentTypes();

        for (ExerciceType type : ExerciceType.values()) {
            String featureName = type.getFeatureName();
            if (types.contains(type) && !currentTypes.contains(type)) {
                featureManager.activate(featureName);
            } else if (!types.contains(type) && currentTypes.contains(type)){
                featureManager.deactivate(featureName);
            }
        }
    }

    public Difficulty getCurrentDifficulty() {
        for (Difficulty difficulty : Difficulty.values()) {
            if (featureManager.isActive(difficulty.getFeatureName())) {
                return difficulty;
            }
        }
        return null;
    }

    public List<ExerciceType> getCurrentTypes() {
        List<ExerciceType> types = new ArrayList<>();
        for (ExerciceType type : ExerciceType.values()) {
            if (featureManager.isActive(type.getFeatureName())) {
                types.add(type);
            }
        }
        return types;
    }

    public List<MediaDTO> getFilteredMedias(List<MediaDTO> medias) {
        List<MediaDTO> filteredMedias = new ArrayList<>();

        boolean imageActive = featureManager.isActive(FeaturesEnum.MEDIA_IMAGE.getFeature());
        boolean videoActive = featureManager.isActive(FeaturesEnum.MEDIA_VIDEO.getFeature());

        for (MediaDTO media : medias) {
            if ((media.getType().equals(MediaType.IMAGE) && imageActive) || (media.getType().equals(MediaType.VIDEO) && videoActive)) {
                filteredMedias.add(media);
            }
        }

        return filteredMedias;
    }

    public List<MediaDTO> getFilteredImages(List<MediaDTO> medias) {
        List<MediaDTO> filteredImages = new ArrayList<>();
        boolean imageActive = featureManager.isActive(FeaturesEnum.MEDIA_IMAGE.getFeature());

        for (MediaDTO media : medias) {
            if (media.getType().equals(MediaType.IMAGE) && imageActive) {
                filteredImages.add(media);
            }
        }

        return filteredImages;
    }

    public List<MediaDTO> getFilteredVideos(List<MediaDTO> medias) {
        List<MediaDTO> filteredVideos = new ArrayList<>();
        boolean videoActive = featureManager.isActive(FeaturesEnum.MEDIA_VIDEO.getFeature());

        for (MediaDTO media : medias) {
            if (media.getType().equals(MediaType.VIDEO) && videoActive) {
                filteredVideos.add(media);
            }
        }

        return filteredVideos;
    }
}