package co.edu.uptc.views.users;

import co.edu.uptc.views.Globals.ValuesGlobals;
import co.edu.uptc.views.board.DashBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
        setSize(400, 550);
        setLocationRelativeTo(dashBoard);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        addComponentListener();
        setModal(true);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(ValuesGlobals.COLOR_DIALOG_LOGIN);
        createComponents();
        singIn = new SingIn(this);
    }
    private void addComponentListener(){
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                super.componentHidden(e);
                System.exit(0);
            }
        });
    }

    private void createComponents() {
        addTitle();
        addUserField();
        addPasswordField();
        addButton();
        addRegister();
        addCancelOption();
    }

    private void addTitle(){
        add(getConfiguratedLabel(new JLabel("Iniciar sesión"),36,0,50));
    }

    private void addUserField(){
        add(getConfiguratedLabel(new JLabel("Usuario"),12,1,30));
        add(createIconMode("assets/usuario.png",1));
        userText = (JTextField) getConfiguratedText(new JTextField(),14,1,270);
        add(userText);
    }

    private void addPasswordField(){
        add(getConfiguratedLabel(new JLabel("Contraseña"),12,2,30));
        add(createIconMode("assets/candado.png",2));
        passwordText = (JPasswordField) getConfiguratedText(new JPasswordField(),14,2,230);
        add(passwordText);
        JLabel iconSee = createIconModeSmaller("assets/ver.png",2);
        add(iconSee);
        iconSee.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (isOculted){
                    passwordText.setEchoChar((char) 0);
                    iconSee.setIcon(getIconSmaller("assets/ocultar.png"));
                    isOculted = false;
                } else {
                    passwordText.setEchoChar('•');
                    iconSee.setIcon(getIconSmaller("assets/ver.png"));
                    isOculted = true;
                }
            }
        });
    }

    private void addButton(){
        JLabel login = new JLabel(createIconModeButton());
        login.setBounds(70,90*3,285,50);
        add(login);
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                User user = managerUsers.getUser(userText.getText());
                if (user!=null){
                    if (managerEncoding.decode(user.getPassword()).equals(new String(passwordText.getPassword()))){
                        getInstance().setVisible(false);
                        getInstance().dispose();
                        singIn.dispose();
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
        JLabel registerLabel = getConfiguratedLabel(new JLabel("Registrarse"),12,4,30);
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
                setComponentForegroundColor(registerLabel,ValuesGlobals.COLOR_LABELS_FIELDS_ENTERED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setComponentForegroundColor(registerLabel,ValuesGlobals.COLOR_LABELS_FIELDS);
            }
        });
        add(registerLabel);
    }
    private void addCancelOption(){
        JLabel cancel = getConfiguratedLabel(new JLabel("Cancelar Ingreso"),12,5,30);
        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                getInstance().setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setComponentForegroundColor(cancel,ValuesGlobals.COLOR_LABELS_FIELDS_ENTERED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setComponentForegroundColor(cancel,ValuesGlobals.COLOR_LABELS_FIELDS);
            }
        });
        add(cancel);
    }
    public void setComponentForegroundColor(Component component,Color color){
        component.setForeground(color);
    }

    private Login getInstance(){
        return this;
    }
    public JLabel getConfiguratedLabel(JLabel comp, int size, int line, int heigth){
        comp.setFont(new Font("Tahoma", Font.BOLD, size));
        comp.setBounds(70,line*90,300,heigth);
        comp.setForeground(ValuesGlobals.COLOR_LABELS_FIELDS);
        return comp;
    }
    public Component getConfiguratedText(Component comp, int size,int line,int width){
        comp.setFont(new Font("Tahoma", Font.BOLD, size));
        comp.setBounds(70,line * 90 + 30,width,30);
        comp.setForeground(ValuesGlobals.COLOR_TEXT_FIELDS);
        return comp;
    }
    public JLabel createIconMode(String fileName, int line){
        ImageIcon icon = new ImageIcon(fileName);
        Image image = icon.getImage();
        Image scaledInstance = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledInstance);
        JLabel label = new JLabel(icon);
        label.setBounds(20,90*line + 20,50,50);
        return  label;
    }

    public JLabel createIconModeSmaller(String fileName, int line){
        ImageIcon icon = getIconSmaller(fileName);
        JLabel label = new JLabel(icon);
        label.setBounds(310,90*line + 30,30,30);
        return  label;
    }
    public ImageIcon getIconSmaller(String fileName){
        ImageIcon icon = new ImageIcon(fileName);
        Image image = icon.getImage();
        Image scaledInstance = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledInstance);
        return icon;
    }
    private ImageIcon createIconModeButton(){
        ImageIcon icon = new ImageIcon("assets/enter.png");
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
