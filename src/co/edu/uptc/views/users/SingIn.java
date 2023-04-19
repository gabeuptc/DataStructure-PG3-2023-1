package co.edu.uptc.views.users;

import co.edu.uptc.views.Globals.ValuesGlobals;

import javax.swing.*;
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
        setSize(400, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(ValuesGlobals.COLOR_DIALOG_SIGNIN_LOGIN);
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
        add(login.getConfiguratedLabel(new JLabel("Registro"),36,0,50));
    }
    private void addUserField(){
        add(login.getConfiguratedLabel(new JLabel("Usuario"),12,1,30));
        add(login.createIconMode("assets/usuario.png",1));
        userText = (JTextField) login.getConfiguratedText(new JTextField(),14,1,270);
        add(userText);
    }
    private void addEmailField(){
        add(login.getConfiguratedLabel(new JLabel("Correo"),12,2,30));
        add(login.createIconMode("assets/correo.png",2));
        email = (JTextField) login.getConfiguratedText(new JTextField(),14,2,175);
        JLabel ext = new JLabel("@uptc.edu.co");
        ext.setBounds(245,90*2+30,100,30);
        add(email);
        add(ext);
    }
    private void addPasswordField(){
        add(login.getConfiguratedLabel(new JLabel("Contraseña"),12,3,30));
        add(login.createIconMode("assets/candado.png",3));
        passwordText = (JPasswordField) login.getConfiguratedText(new JPasswordField(),14,3,230);
        JLabel iconSee = login.createIconModeSmaller("assets/ver.png",3);
        add(passwordText);
        add(iconSee);
        iconSee.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (isOcultedFirst){
                    passwordText.setEchoChar((char) 0);
                    iconSee.setIcon(login.getIconSmaller("assets/ocultar.png"));
                    isOcultedFirst = false;
                } else {
                    passwordText.setEchoChar('•');
                    iconSee.setIcon(login.getIconSmaller("assets/ver.png"));
                    isOcultedFirst = true;
                }
            }
        });
    }
    private void addPasswordConfirmField(){
        add(login.getConfiguratedLabel(new JLabel("Confirmar contraseña"),12,4,30));
        add(login.createIconMode("assets/candado.png",4));
        passwordConfirm = (JPasswordField) login.getConfiguratedText(new JPasswordField(),14,4,230);
        JLabel iconSeeConfirm = login.createIconModeSmaller("assets/ver.png",4);
        add(passwordConfirm);
        add(iconSeeConfirm);
        iconSeeConfirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (isOcultedConfirm){
                    passwordConfirm.setEchoChar((char) 0);
                    iconSeeConfirm.setIcon(login.getIconSmaller("assets/ocultar.png"));
                    isOcultedConfirm = false;
                } else {
                    passwordConfirm.setEchoChar('•');
                    iconSeeConfirm.setIcon(login.getIconSmaller("assets/ver.png"));
                    isOcultedConfirm = true;
                }
            }
        });
    }
    private void addRegister(){
        JLabel register = login.getConfiguratedLabel(new JLabel("Registrarse"),12,5,30);
        register.addMouseListener(new MouseAdapter() {
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
                login.setComponentForegroundColor(register,ValuesGlobals.COLOR_LABELS_FIELDS_ENTERED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                login.setComponentForegroundColor(register,ValuesGlobals.COLOR_LABELS_FIELDS);
            }
        });
        add(register);
    }
    private void addBack(){
        JLabel back = login.getConfiguratedLabel(new JLabel("volver a Inicio de sesión"),12,6,30);
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
                login.setComponentForegroundColor(back,ValuesGlobals.COLOR_LABELS_FIELDS_ENTERED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                login.setComponentForegroundColor(back,ValuesGlobals.COLOR_LABELS_FIELDS);
            }
        });
        add(back);
    }
    private JDialog getInstance(){
        return this;
    }

    public void clearContent(){
        userText.setText("");
        email.setText("");
        passwordText.setText("");
        passwordConfirm.setText("");
    }
}
