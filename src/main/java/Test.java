import controllers.ControllerInterface;
import controllers.MainController;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        ControllerInterface controller = new MainController();

        String[] activations = {"TreeRoot-payment_methods-difficulty-type-authentication-bancontact-profile-media_video-media-difficulty_intermediate-exercise-type_cardio-Feature-media_image-premium-type_strength".replace("-", ",")};
        String[] deactivations = {"difficulty_beginner,type_flexibility,paypal,difficulty_advanced"};
        controller.activate(deactivations, activations);

        //path0-2
        //step 0

//        activations = new String[]{"difficulty_beginner"};
//        deactivations = new String[]{"payment_methods,bancontact,difficulty_intermediate"};
//        controller.activate(deactivations, activations);
//
//        activations = new String[]{"difficulty_advanced"};
//        deactivations = new String[]{"premium,type_strength,difficulty_beginner,media_image"};
//        controller.activate(deactivations, activations);
//
//        activations = new String[]{"type_flexibility"};
//        deactivations = new String[]{"type_cardio"};
//        controller.activate(deactivations, activations);

        //step 1
        activations = new String[]{"payment_methods,paypal"};
        deactivations = new String[]{""};
        controller.activate(deactivations, activations);

        activations = new String[]{"premium,type_cardio,type_strength,media_image,difficulty_intermediate"};
        deactivations = new String[]{"difficulty_advanced,media_video"};
        controller.activate(deactivations, activations);

        activations = new String[]{"difficulty_beginner,bancontact"};
        deactivations = new String[]{"difficulty_intermediate,type_flexibility"};
        controller.activate(deactivations, activations);


        System.out.println(Arrays.toString(controller.getStateAsLog()));



        //paths0-0
        //step 0

//        activations = new String[]{"type_flexibility-difficulty_advanced".replace("-", ",")};
//        deactivations = new String[]{"payment_methods-bancontact-difficulty_intermediate-type_cardio-media_image-premium-type_strength".replace("-", ",")};
//        controller.activate(deactivations, activations);

        //step 1
        activations = new String[]{"payment_methods-bancontact-difficulty_beginner-paypal-type_cardio-media_image-premium-type_strength".replace("-", ",")};
        deactivations = new String[]{"media_video-type_flexibility-difficulty_advanced".replace("-", ",")};
        controller.activate(deactivations, activations);

        System.out.println(Arrays.toString(controller.getStateAsLog()));
    }

}
