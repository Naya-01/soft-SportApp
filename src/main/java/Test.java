import controllers.ControllerInterface;
import controllers.MainController;
import features.managers.FeatureManager;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        ControllerInterface controller = new MainController();

        String[] activations = "TreeRoot-payment_methods-difficulty-type-authentication-bancontact-profile-media_video-media-difficulty_intermediate-exercise-type_cardio-Feature-media_image-premium-type_strength".split("-");
        String[] deactivations = "difficulty_beginner-type_flexibility-paypal-difficulty_advanced".split("-");
        controller.activate(deactivations, activations);

        //path0-2
        //step 0

        activations = "difficulty_beginner".split(",");
        deactivations = "payment_methods,bancontact,difficulty_intermediate".split(",");
        controller.activate(deactivations, activations);

        activations = "difficulty_advanced".split(",");
        deactivations = "premium,type_strength,difficulty_beginner,media_image".split(",");
        controller.activate(deactivations, activations);

        activations = "type_flexibility".split(",");
        deactivations = "type_cardio".split(",");
        controller.activate(deactivations, activations);

        //step 1
        activations = "payment_methods,paypal".split(",");
        deactivations = new String[]{};
        controller.activate(deactivations, activations);

        activations = "premium,type_cardio,type_strength,media_image,difficulty_intermediate".split(",");
        deactivations = "difficulty_advanced,media_video".split(",");
        controller.activate(deactivations, activations);

        activations = "difficulty_beginner,bancontact".split(",");
        deactivations = "difficulty_intermediate,type_flexibility".split(",");
        controller.activate(deactivations, activations);


        System.out.println(Arrays.toString(controller.getStateAsLog()));



        //paths0-0
        FeatureManager.getInstance().fillFeaturesMap();
        activations = "TreeRoot-payment_methods-difficulty-type-authentication-bancontact-profile-media_video-media-difficulty_intermediate-exercise-type_cardio-Feature-media_image-premium-type_strength".split("-");
        deactivations = "difficulty_beginner-type_flexibility-paypal-difficulty_advanced".split("-");
        controller.activate(deactivations, activations);
        //step 0

        activations = "type_flexibility-difficulty_advanced".split("-");
        deactivations = "payment_methods-bancontact-difficulty_intermediate-type_cardio-media_image-premium-type_strength".split("-");
        controller.activate(deactivations, activations);

        //step 1
        activations = "payment_methods-bancontact-difficulty_beginner-paypal-type_cardio-media_image-premium-type_strength".split("-");
        deactivations = "media_video-type_flexibility-difficulty_advanced".split("-");
        controller.activate(deactivations, activations);

        System.out.println(Arrays.toString(controller.getStateAsLog()));
    }

}
