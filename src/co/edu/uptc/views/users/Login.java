package co.edu.uptc.views.users;

import co.edu.uptc.views.board.DashBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JDialog {
    private JPasswordField passwordText;
    private JTextField userText;
    private boolean isOculted = true;
    private final ManagerEncoding managerEncoding;
    private final ManagerUsers managerUsers;
    private final SingIn singIn;
    private final DashBoard dashBoard;

    public Login(DashBoard dashBoard) {
        this.dashBoard = dashBoard;
        managerEncoding = ManagerEncoding.getInstance();
        managerUsers = ManagerUsers.getInstance();
        setTitle("Registro");
        setSize(400, 360);
        setLocationRelativeTo(dashBoard);
        //como cerrar todas las ventanas desde aqui?
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setModal(true);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(Color.orange);
        createComponents();
        singIn = new SingIn(this);
    }

    private void createComponents() {
        addTitle();
        addUserField();
        addPasswordField();
        addButton();
        addRegister();
    }

    private void addTitle(){
        JLabel loginLabel = getConfiguratedLabel(new JLabel("Iniciar sesión"),36);
        loginLabel.setBounds(70, 0, 300, 50);
        add(loginLabel);
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

    private void addPasswordField(){
        JLabel passwordLabel = getConfiguratedLabel(new JLabel("Contraseña"),12);
        passwordLabel.setBounds(70,140,100,30);
        JLabel passwordIcon = new JLabel(createIconMode("assets/candado.png"));
        passwordIcon.setBounds(20,160,50,50);
        passwordText = (JPasswordField) getConfiguratedText(new JPasswordField(),14);
        passwordText.setBounds(70,170,230,30);
        JLabel iconSee = new JLabel(createIconModeSmaller("assets/ver.png"));
        iconSee.setBounds(310,170,30,30);
        add(passwordLabel);
        add(passwordIcon);
        add(passwordText);
        add(iconSee);
        iconSee.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (isOculted){
                    passwordText.setEchoChar((char) 0);
                    iconSee.setIcon(createIconModeSmaller("assets/ocultar.png"));
                    isOculted = false;
                } else {
                    passwordText.setEchoChar('•');
                    iconSee.setIcon(createIconModeSmaller("assets/ver.png"));
                    isOculted = true;
                }
            }
        });
    }

    private void addButton(){
        JLabel login = new JLabel(createIconModeButton("assets/enter.png"));
        login.setBounds(70,220,285,50);
        add(login);
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                User user = managerUsers.getUser(userText.getText());
                if (user!=null){
                    if (managerEncoding.decode(user.getPassword()).equals(new String(passwordText.getPassword()))){
                        getInstance().setVisible(false);
                        dashBoard.setVisible(true);
                        clearContent();
                    }else {
                        JOptionPane.showMessageDialog(null,"Contraseña incorrecta");
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"Este usuario no esta registrado");
                }
            }
        });
    }

    private void addRegister(){
        JLabel registerLabel = getConfiguratedLabel(new JLabel("Registrarse"),12);
        registerLabel.setBounds(70,280,100,30);
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                singIn.setVisible(true);
                singIn.clearContent();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                registerLabel.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                registerLabel.setForeground(Color.WHITE);
            }
        });
        add(registerLabel);
    }

    private Login getInstance(){
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
    private ImageIcon createIconModeButton(String fileName){
        ImageIcon icon = new ImageIcon(fileName);
        Image image = icon.getImage();
        Image scaledInstance = image.getScaledInstance(285, 50, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledInstance);
        return  icon;
    }
    public void clearContent(){
        userText.setText("");
        passwordText.setText("");
    }
}
