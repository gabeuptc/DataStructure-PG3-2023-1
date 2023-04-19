package co.edu.uptc.views.users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SingIn extends JDialog {
    private final Login login;
    private JPasswordField passwordText;
    private JTextField userText;
    private JTextField email;
    private JPasswordField passwordConfirm;
    private boolean isOcultedFirst = true;
    private boolean isOcultedConfirm = true;
    private final ManagerEncoding managerEncoding;
    private final ManagerUsers managerUsers;

    public SingIn(Login login) {
        managerEncoding = ManagerEncoding.getInstance();
        managerUsers = ManagerUsers.getInstance();
        this.login = login;
        setTitle("Registro");
        setSize(400, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(Color.orange);
        createComponents();

    }

    private void createComponents() {
        addTitle();
        addUserField();
        addEmailField();
        addPasswordField();
        addPasswordConfirmField();
        addRegister();
        addBack();
    }

    private void addTitle(){
        JLabel registerLabel = getConfiguratedLabel(new JLabel("Registro"),36);
        registerLabel.setBounds(70, 0, 300, 50);
        add(registerLabel);
    }
    private void addUserField(){
        JLabel userLabel = getConfiguratedLabel(new JLabel("Usuario"),12);
        userLabel.setBounds(70, 50, 100, 30);
        JLabel iconUser = new JLabel(createIconMode("assets/usuario.png"));
        iconUser.setBounds(20,70,50,50);
        userText = (JTextField) getConfiguratedText(new JTextField(),14);
        userText.setBounds(70,80,270,30);
        add(userLabel);
        add(iconUser);
        add(userText);
    }
    private void addEmailField(){
        JLabel emailLabel = getConfiguratedLabel(new JLabel("Correo"),12);
        emailLabel.setBounds(70, 140, 100, 30);
        JLabel iconEmail = new JLabel(createIconMode("assets/correo.png"));
        iconEmail.setBounds(20,160,50,50);
        email = (JTextField) getConfiguratedText(new JTextField(),14);
        email.setBounds(70,170,175,30);
        JLabel ext = getConfiguratedLabel(new JLabel("@uptc.edu.co"),12);
        ext.setBounds(245,170,100,30);
        add(emailLabel);
        add(iconEmail);
        add(email);
        add(ext);
    }
    private void addPasswordField(){
        JLabel passwordLabel = getConfiguratedLabel(new JLabel("Contraseña"),12);
        passwordLabel.setBounds(70,230,100,30);
        JLabel passwordIcon = new JLabel(createIconMode("assets/candado.png"));
        passwordIcon.setBounds(20,250,50,50);
        passwordText = (JPasswordField) getConfiguratedText(new JPasswordField(),14);
        passwordText.setBounds(70,260,230,30);
        JLabel iconSee = new JLabel(createIconModeSmaller("assets/ver.png"));
        iconSee.setBounds(310,260,30,30);
        add(passwordLabel);
        add(passwordIcon);
        add(passwordText);
        add(iconSee);
        iconSee.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (isOcultedFirst){
                    passwordText.setEchoChar((char) 0);
                    iconSee.setIcon(createIconModeSmaller("assets/ocultar.png"));
                    isOcultedFirst = false;
                } else {
                    passwordText.setEchoChar('•');
                    iconSee.setIcon(createIconModeSmaller("assets/ver.png"));
                    isOcultedFirst = true;
                }
            }
        });
    }
    private void addPasswordConfirmField(){
        JLabel passwordConfLabel = getConfiguratedLabel(new JLabel("Confirmar contraseña"),12);
        passwordConfLabel.setBounds(70,320,200,30);
        JLabel passwordConfIcon = new JLabel(createIconMode("assets/candado.png"));
        passwordConfIcon.setBounds(20,340,50,50);
        passwordConfirm = (JPasswordField) getConfiguratedText(new JPasswordField(),14);
        passwordConfirm.setBounds(70,350,230,30);
        JLabel iconSeeConfirm = new JLabel(createIconModeSmaller("assets/ver.png"));
        iconSeeConfirm.setBounds(310,350,30,30);
        add(passwordConfLabel);
        add(passwordConfIcon);
        add(passwordConfirm);
        add(iconSeeConfirm);
        iconSeeConfirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (isOcultedConfirm){
                    passwordConfirm.setEchoChar((char) 0);
                    iconSeeConfirm.setIcon(createIconModeSmaller("assets/ocultar.png"));
                    isOcultedConfirm = false;
                } else {
                    passwordConfirm.setEchoChar('•');
                    iconSeeConfirm.setIcon(createIconModeSmaller("assets/ver.png"));
                    isOcultedConfirm = true;
                }
            }
        });
    }
    private void addRegister(){
        JLabel register = getConfiguratedLabel(new JLabel("Registrarse"),12);
        register.setBounds(70,400,100,50);register.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String password =new String(passwordText.getPassword());
                boolean isValid = (!password.equals("")) && (!userText.getText().equals("")) && (!email.getText().equals(""));
                if (isValid && password.equals(new String(passwordConfirm.getPassword()))){
                    User user = new User(userText.getText(),managerEncoding.encode(
                            new String(passwordText.getPassword())),email.getText() + "@uptc.edu.co");
                    if (!managerUsers.isExist(user)){
                        managerUsers.addUser(user);
                        JOptionPane.showMessageDialog(null,"Registrado " + userText.getText());
                    }else {
                        JOptionPane.showMessageDialog(null,"Este usuario y/o correo ya se ha registrado antes");
                    }
                    clearContent();
                }else{
                    JOptionPane.showMessageDialog(null,"no se registro, los campos estan vacios " +
                            "o la contraseña no coincide");
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                register.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                register.setForeground(Color.WHITE);
            }
        });
        add(register);
    }
    private void addBack(){
        JLabel back = getConfiguratedLabel(new JLabel("volver a Inicio de sesión"),12);
        back.setBounds(70,460,200,30);
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                getInstance().setVisible(false);
                login.clearContent();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                back.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                back.setForeground(Color.WHITE);
            }
        });
        add(back);
    }
    private JDialog getInstance(){
        return this;
    }
    private JLabel getConfiguratedLabel(JLabel comp, int size){
        comp.setFont(new Font("Tahoma", Font.BOLD, size));
        comp.setForeground(Color.white);
        return comp;
    }
    private Component getConfiguratedText(Component comp, int size){
        comp.setFont(new Font("Tahoma", Font.BOLD, size));
        comp.setForeground(Color.black);
        return comp;
    }
    private ImageIcon createIconMode(String fileName){
        ImageIcon icon = new ImageIcon(fileName);
        Image image = icon.getImage();
        Image scaledInstance = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledInstance);
        return  icon;
    }

    private ImageIcon createIconModeSmaller(String fileName){
        ImageIcon icon = new ImageIcon(fileName);
        Image image = icon.getImage();
        Image scaledInstance = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledInstance);
        return  icon;
    }
    public void clearContent(){
        userText.setText("");
        email.setText("");
        passwordText.setText("");
        passwordConfirm.setText("");
    }
}
