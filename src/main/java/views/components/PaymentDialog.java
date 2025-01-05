package views.components;

import controllers.PaymentMethodController;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import models.PaymentMethod.PaymentMethod;
import views.utils.UserStore;

public class PaymentDialog extends JPanel {

    PaymentMethodController paymentMethodController;
    NavBar navBar;

    public PaymentDialog(NavBar navBar, PaymentMethodController paymentMethodController) {
        super();
        this.navBar = navBar;
        this.paymentMethodController = paymentMethodController;
    }



    public void showPaymentDialog() {
        List<PaymentMethod> availableMethods = paymentMethodController.getAvailablePaymentMethods();
        if (availableMethods.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No payment methods are available.");
            return;
        }

        String[] options = availableMethods.stream().map(PaymentMethod::getName).toArray(String[]::new);
        int choice = JOptionPane.showOptionDialog(
            this,
            "Choose a payment method:",
            "Payment Method",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]
        );

        if (choice != -1) {
            PaymentMethod selectedMethod = availableMethods.get(choice);
            boolean success = paymentMethodController.upgradeAccount(selectedMethod);
            if (success) {
                JOptionPane.showMessageDialog(this, "Payment successful! You are now a premium user.");
                UserStore.getCurrentUser().setPremium(true);

                navBar.getPremiumButton().setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Payment failed. Please try again.");
            }
        }
    }

}
