// File: src/main/java/views/components/NavBar.java
package views.components;

import controllers.PaymentMethodController;
import features.FeaturesEnum;
import features.managers.FeatureManager;
import views.CommunityView;
import views.CustomExerciceCreationView;
import views.ProfileView;
import views.utils.BaseView;
import views.utils.UserStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavBar extends JPanel {

    private JButton profileButton;
    private JButton premiumButton;
    private JButton customExerciceButton;
    private JButton communityButton;
    private FeatureManager featureManager;

    private PaymentDialog paymentDialog;
    private BaseView parentFrame;

    public NavBar(JFrame parentFrame) {
        super();
        featureManager = new FeatureManager();
        this.paymentDialog = new PaymentDialog(this, new PaymentMethodController());
        this.parentFrame = (BaseView) parentFrame;
    }


    public JPanel createNavBar() {
        JPanel navBar = new JPanel();
        navBar.setLayout(new FlowLayout(FlowLayout.LEFT));

        profileButton = new JButton("Voir Profil");
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProfileView().setVisible(true);
            }
        });
        navBar.add(profileButton);

        premiumButton = new JButton("Become Premium");
        premiumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paymentDialog.showPaymentDialog();
                customExerciceButton.setVisible(UserStore.getCurrentUser().getPremium() && featureManager.isActive(FeaturesEnum.EXERCICE_CUSTOM_ADD.getFeature()));
                communityButton.setVisible(UserStore.getCurrentUser().getPremium() && featureManager.isActive(FeaturesEnum.COMMUNITY.getFeature()));
            }
        });
        premiumButton.setVisible(!UserStore.getCurrentUser().getPremium() && featureManager.isActive(FeaturesEnum.PREMIUM.getFeature()));
        navBar.add(premiumButton);

        customExerciceButton = new JButton("Add Custom Exercice");
        customExerciceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CustomExerciceCreationView().setVisible(true);
                parentFrame.dispose();
            }
        });
        customExerciceButton.setVisible(UserStore.getCurrentUser().getPremium() && featureManager.isActive(FeaturesEnum.EXERCICE_CUSTOM_ADD.getFeature()));
        navBar.add(customExerciceButton);

        communityButton = new JButton("Community");
        communityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CommunityView().setVisible(true);
                parentFrame.dispose();
            }
        });
        communityButton.setVisible(UserStore.getCurrentUser().getPremium() && featureManager.isActive(FeaturesEnum.COMMUNITY.getFeature()));
        navBar.add(communityButton);

        return navBar;
    }

    public JButton getProfileButton() {
        return profileButton;
    }

    public void setProfileButton(JButton profileButton) {
        this.profileButton = profileButton;
    }

    public JButton getPremiumButton() {
        return premiumButton;
    }

    public void setPremiumButton(JButton premiumButton) {
        this.premiumButton = premiumButton;
    }

    public JButton getCustomExerciceButton() {
        return customExerciceButton;
    }

    public void setCustomExerciceButton(JButton customExerciceButton) {
        this.customExerciceButton = customExerciceButton;
    }

    public JButton getCommunityButton() {
        return communityButton;
    }

    public void setCommunityButton(JButton communityButton) {
        this.communityButton = communityButton;
    }

    public FeatureManager getFeatureManager() {
        return featureManager;
    }

    public void setFeatureManager(FeatureManager featureManager) {
        this.featureManager = featureManager;
    }

    public PaymentDialog getPaymentDialog() {
        return paymentDialog;
    }

    public void setPaymentDialog(PaymentDialog paymentDialog) {
        this.paymentDialog = paymentDialog;
    }
}