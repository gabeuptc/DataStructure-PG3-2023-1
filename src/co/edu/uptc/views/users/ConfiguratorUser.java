package co.edu.uptc.views.users;

import co.edu.uptc.views.Globals.ValuesGlobals;
import co.edu.uptc.views.board.DashBoard;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConfiguratorUser extends JPanel {
    private User currentUser;
    private JLabel userName;
    private JLabel userEmail;
    private final DashBoard dashBoard;
    private boolean isOcultedFirst = true;
    private boolean isOcultedConfirm = true;
    private JPanel firstPage;
    private JPanel changesPage;
    private JPanel changeNamePage;
    private JPanel changeEmailPage;
    private JPanel changePasswordPage;

    public ConfiguratorUser(DashBoard dashBoard) {
        this.dashBoard = dashBoard;
        currentUser = dashBoard.getCurrentUser();
        setLayout(new OverlayLayout(this));
        setBackground(ValuesGlobals.COLOR_DIALOG_LOGIN);
        createInnitComponents();
    }
    private void setToShow(JPanel panel){
        firstPage.setVisible(false);
        changesPage.setVisible(false);
        changeNamePage.setVisible(false);
        changeEmailPage.setVisible(false);
        changePasswordPage.setVisible(false);
        panel.setVisible(true);
    }

    private void createInnitComponents() {
        firstPage = new JPanel(null);
        firstPage.setBackground(ValuesGlobals.COLOR_DIALOG_LOGIN);
        changesPage = new JPanel(null);
        changesPage.setBackground(ValuesGlobals.COLOR_DIALOG_LOGIN);
        changeNamePage = new JPanel(null);
        changeNamePage.setBackground(ValuesGlobals.COLOR_DIALOG_LOGIN);
        changeEmailPage = new JPanel(null);
        changeEmailPage.setBackground(ValuesGlobals.COLOR_DIALOG_LOGIN);
        changePasswordPage = new JPanel(null);
        changePasswordPage.setBackground(ValuesGlobals.COLOR_DIALOG_LOGIN);
        putFirstPageComponents();
        putSetUserContent();
        putSetUserNameFields();
        putSetUserEmailFields();
        putSetUserPasswordFields();
        add(firstPage);
        add(changesPage);
        add(changeNamePage);
        add(changeEmailPage);
        add(changePasswordPage);
        setToShow(firstPage);
    }
    private void putFirstPageComponents(){
        addTitle();
        addUserName();
        addUserEmail();
        addSetUser();
        addDeleteUser();
        fillLabels();
    }

    private void fillLabels() {
        userName.setText("Nombre de Usuario: " + currentUser.getNameUser());
        userEmail.setText("Correo de Usuario: " + currentUser.getEmail());
    }

    private void addDeleteUser() {
        JLabel deleteUser = Login.getConfiguratedLabel(new JLabel("Eliminar usuario"),15,4,200,35);
        deleteUser.setBorder(ValuesGlobals.BORDER_PANEL);
        deleteUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (JOptionPane.showConfirmDialog(null, "¿Quiere eliminar el usuario "
                        + currentUser.getNameUser()+"?","Eliminar Usuario",
                        JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                    ManagerUsers.getInstance().deleteUser(currentUser);
                    dashBoard.showLoginDialog();
                    currentUser = dashBoard.getCurrentUser();
                    fillLabels();
                }
            }
        });
        deleteUser.addMouseListener(getMouseAdapterSelectLabel(deleteUser));
        firstPage.add(deleteUser);
    }

    private void addSetUser() {
        JLabel setUser = Login.getConfiguratedLabel(new JLabel("Cambiar información de usuario"),15,3,300,35);
        setUser.setBorder(ValuesGlobals.BORDER_PANEL);
        setUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setToShow(changesPage);
            }
        });
        setUser.addMouseListener(getMouseAdapterSelectLabel(setUser));
        firstPage.add(setUser);
    }

    private void addUserEmail() {
        userEmail = Login.getConfiguratedLabel(new JLabel(),15,2,400,35);
        firstPage.add(userEmail);
    }

    private void addUserName() {
        userName = Login.getConfiguratedLabel(new JLabel(),15,1,400,35);
        firstPage.add(userName);
    }

    private void addTitle() {
        firstPage.add(Login.getConfiguratedLabel(new JLabel("Configuraciones de Usuario"),40,0,700,55));
    }

    private void putSetUserContent(){
        addSetTitle();
        addLabelMessage();
        addSetUserName();
        addSetUserEmail();
        addSetUserPassword();
        addSaveChanges();
        addGetBack();
    }

    private void addGetBack() {
        JLabel getBack = Login.getConfiguratedLabel(new JLabel("Volver"),15,6,200,35);
        getBack.setBorder(ValuesGlobals.BORDER_PANEL);
        getBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fillLabels();
                setToShow(firstPage);
            }
        });
        getBack.addMouseListener(getMouseAdapterSelectLabel(getBack));
        changesPage.add(getBack);
    }

    private void addLabelMessage() {
        JLabel message = Login.getConfiguratedLabel(new JLabel("*Para aplicar los cambios " +
                "realizados se debe seleccionar guardar cambios"),15,5,700,35);
        message.setForeground(ValuesGlobals.COLOR_LABELS_FIELDS_ENTERED);
        changesPage.add(message);
    }

    private void addSaveChanges() {
        JLabel saveChanges = Login.getConfiguratedLabel(new JLabel("Guardar cambios"),15,4,200,35);
        saveChanges.setBorder(ValuesGlobals.BORDER_PANEL);
        saveChanges.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ManagerUsers.getInstance().setUser(currentUser);
                JOptionPane.showMessageDialog(null,"Se han guardado todos los cambios realizados");
            }
        });
        saveChanges.addMouseListener(getMouseAdapterSelectLabel(saveChanges));
        changesPage.add(saveChanges);
    }

    private void addSetUserPassword() {
        JLabel setPasswordUser = Login.getConfiguratedLabel(new JLabel("Cambiar contraseña de usuario"),15,3,400,35);
        setPasswordUser.setBorder(ValuesGlobals.BORDER_PANEL);
        setPasswordUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setToShow(changePasswordPage);
            }
        });
        setPasswordUser.addMouseListener(getMouseAdapterSelectLabel(setPasswordUser));
        changesPage.add(setPasswordUser);
    }

    private void addSetUserEmail() {
        JLabel setEmailUser = Login.getConfiguratedLabel(new JLabel("Cambiar correo de usuario"),15,2,400,35);
        setEmailUser.setBorder(ValuesGlobals.BORDER_PANEL);
        setEmailUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setToShow(changeEmailPage);
            }
        });
        setEmailUser.addMouseListener(getMouseAdapterSelectLabel(setEmailUser));
        changesPage.add(setEmailUser);
    }

    private void addSetUserName() {
        JLabel setNameUser = Login.getConfiguratedLabel(new JLabel("Cambiar nombre de usuario"),15,1,400,35);
        setNameUser.setBorder(ValuesGlobals.BORDER_PANEL);
        setNameUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setToShow(changeNamePage);
            }
        });
        setNameUser.addMouseListener(getMouseAdapterSelectLabel(setNameUser));
        changesPage.add(setNameUser);
    }

    private void addSetTitle() {
        changesPage.add(Login.getConfiguratedLabel(new JLabel("Cambiar información de Usuario"),40,0,750,55));
    }
    private void putSetUserNameFields(){
        changeNamePage.add(Login.getConfiguratedLabel(new JLabel("Cambio de nombre"),40,0,500,55));
        changeNamePage.add(Login.getConfiguratedLabel(new JLabel("Usuario"),12,1,200,30));
        changeNamePage.add(Login.createIconMode("assets/usuario.png",1));
        JTextField userText = (JTextField) Login.getConfiguratedText(new JTextField(),14,1,270);
        changeNamePage.add(userText);
        JLabel setNameUser = Login.getConfiguratedLabel(new JLabel("Cambiar nombre de usuario"),15,2,400,35);
        setNameUser.setBorder(ValuesGlobals.BORDER_PANEL);
        setNameUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!ManagerUsers.getInstance().isExist(userText.getText(), currentUser.getId())){
                    currentUser.setNameUser(userText.getText());
                    JOptionPane.showMessageDialog(null,"Se ha cambiado el nombre de usuario");
                    setToShow(changesPage);
                }else {
                    JOptionPane.showMessageDialog(null,"Ya se ha registrado este usuario en otro usuario");
                    userText.setText("");
                }
            }
        });
        setNameUser.addMouseListener(getMouseAdapterSelectLabel(setNameUser));
        changeNamePage.add(setNameUser);
    }
    private void putSetUserEmailFields(){
        changeEmailPage.add(Login.getConfiguratedLabel(new JLabel("Cambio de correo"),40,0,500,55));
        changeEmailPage.add(Login.getConfiguratedLabel(new JLabel("Correo"),12,1,200,30));
        changeEmailPage.add(Login.createIconMode("assets/correo.png",1));
        JTextField email = (JTextField) Login.getConfiguratedText(new JTextField(),14,1,175);
        JLabel ext = new JLabel("@uptc.edu.co");
        ext.setBounds(245,90+30,100,30);
        changeEmailPage.add(email);
        changeEmailPage.add(ext);
        JLabel setEmailUser = Login.getConfiguratedLabel(new JLabel("Cambiar correo de usuario"),15,2,400,35);
        setEmailUser.setBorder(ValuesGlobals.BORDER_PANEL);
        setEmailUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!ManagerUsers.getInstance().isExist(email.getText(), currentUser.getId())){
                    currentUser.setEmail(email.getText());
                    JOptionPane.showMessageDialog(null,"Se ha cambiado el correo de usuario");
                    setToShow(changesPage);
                }else {
                    JOptionPane.showMessageDialog(null,"Ya se ha registrado este correo en otro usuario");
                    email.setText("");
                }
            }
        });
        setEmailUser.addMouseListener(getMouseAdapterSelectLabel(setEmailUser));
        changeEmailPage.add(setEmailUser);
    }
    private void putSetUserPasswordFields(){
        changePasswordPage.add(Login.getConfiguratedLabel(new JLabel("Cambio de contraseña"),40,0,500,55));
        changePasswordPage.add(Login.getConfiguratedLabel(new JLabel("Contraseña"),12,1,200,30));
        changePasswordPage.add(Login.createIconMode("assets/candado.png",1));
        JPasswordField passwordText = (JPasswordField) Login.getConfiguratedText(new JPasswordField(),14,1,230);
        JLabel iconSee = Login.createLabelIconModeSmaller("assets/ver.png",1);
        changePasswordPage.add(passwordText);
        changePasswordPage.add(iconSee);
        iconSee.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (isOcultedFirst){
                    passwordText.setEchoChar((char) 0);
                    iconSee.setIcon(Login.getIconSmaller("assets/ocultar.png"));
                    isOcultedFirst = false;
                } else {
                    passwordText.setEchoChar('•');
                    iconSee.setIcon(Login.getIconSmaller("assets/ver.png"));
                    isOcultedFirst = true;
                }
            }
        });

        changePasswordPage.add(Login.getConfiguratedLabel(new JLabel("Confirmar contraseña"),12,2,300,30));
        changePasswordPage.add(Login.createIconMode("assets/candado.png",2));
        JPasswordField passwordConfirm = (JPasswordField) Login.getConfiguratedText(new JPasswordField(),14,2,230);
        JLabel iconSeeConfirm = Login.createLabelIconModeSmaller("assets/ver.png",2);
        changePasswordPage.add(passwordConfirm);
        changePasswordPage.add(iconSeeConfirm);
        iconSeeConfirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (isOcultedConfirm){
                    passwordConfirm.setEchoChar((char) 0);
                    iconSeeConfirm.setIcon(Login.getIconSmaller("assets/ocultar.png"));
                    isOcultedConfirm = false;
                } else {
                    passwordConfirm.setEchoChar('•');
                    iconSeeConfirm.setIcon(Login.getIconSmaller("assets/ver.png"));
                    isOcultedConfirm = true;
                }
            }
        });

        JLabel setPasswordUser = Login.getConfiguratedLabel(new JLabel("Cambiar contraseña de usuario"),15,3,400,35);
        setPasswordUser.setBorder(ValuesGlobals.BORDER_PANEL);
        setPasswordUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String password = new String(passwordText.getPassword());
                if ((!password.equals(""))&&password.equals(new String(passwordConfirm.getPassword()))){
                    currentUser.setPassword(ManagerEncoding.getInstance().encode(new String(passwordText.getPassword())));
                    JOptionPane.showMessageDialog(null,"Se ha cambiado la contraseña de usuario");
                    setToShow(changesPage);
                }else {
                    JOptionPane.showMessageDialog(null,"Las contraseñas no coincide, o estan vacias");
                }
            }
        });
        setPasswordUser.addMouseListener(getMouseAdapterSelectLabel(setPasswordUser));
        changePasswordPage.add(setPasswordUser);
    }

    private MouseAdapter getMouseAdapterSelectLabel(JLabel label){
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                e.getComponent().setForeground(ValuesGlobals.COLOR_LABELS_FIELDS_ENTERED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                e.getComponent().setForeground(ValuesGlobals.COLOR_LABELS_FIELDS);
            }
        };
    }
}
