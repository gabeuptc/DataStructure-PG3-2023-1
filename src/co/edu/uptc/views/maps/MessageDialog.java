package co.edu.uptc.views.maps;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MessageDialog extends JDialog {
    private JPanel mainPanel;
    private JPanel footPanel;
    private JLabel errorLabel;
    private JTextField messageTextField;
    private JTextField  valueTextField;
    private JTextField idTextField;
    private Color color;
    private JButton saveButton;

    private int[] possitionX = {30,130};
    private int[] possitionY = {30,60,90,120,150};

    public MessageDialog( ActionListener listener) {
        //super(frame, true);
        //this.frame = frame;
        initialize();
        createComponents(listener);
    }

    private void initialize(){
        setTitle("New");
     //   setBackground(GlobalConfig.PANEL_COLOR);
        setSize(400,280);
      //  setLocationRelativeTo(frame);
        setResizable(false);
    }


    private void createComponents(ActionListener listener){
        createMainComponent();
        createComponentFoot(listener);
    }

    public void createMainComponent(){
        createMainPanel();
        createErrorLabel();
        createIdLabel();
        createMessageLabel();
        createValueLabel();
        createColorLabel();

        createIdTextField();
        createMessageTextField();
        createValueTextField();
        createSelectButton();
    }

    private void createMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
      //  mainPanel.setBackground(GlobalConfig.PANEL_COLOR);
        add(mainPanel, BorderLayout.CENTER);

    }

    private void createIdLabel() {
        JLabel idLabel = new JLabel("id: ");
        idLabel.setBounds(possitionX[0],possitionY[0],100,20);
        mainPanel.add(idLabel);
    }

    private void createMessageLabel() {
        JLabel messageLabel = new JLabel("Message: ");
        messageLabel.setBounds(possitionX[0],possitionY[1],100,20);
        mainPanel.add(messageLabel);
    }

    private void createValueLabel() {
        JLabel valueLabel = new JLabel("Value: ");
        valueLabel.setBounds(possitionX[0],possitionY[2],100,20);
        mainPanel.add(valueLabel);
    }

    private void createColorLabel() {
        JLabel colorLabel = new JLabel("Color:");
        colorLabel.setBounds(possitionX[0],possitionY[3],100,20);
        mainPanel.add(colorLabel);
    }

    private void createSelectButton() {
        JButton  colorButton = new JButton("Select");
        colorButton.setBounds(possitionX[1],possitionY[3],100,20);
        mainPanel.add(colorButton);
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JColorChooser selectorColor=new JColorChooser();
              //  color= selectorColor.showDialog(frame, "Select one Color", Color.red);
                if (color!=null) {
                    enabledComponent();
                    colorButton.setBackground(color);
                }
            }
        });
    }


    public void createIdTextField(){
        idTextField = new JTextField("");
        idTextField.setBounds(possitionX[1],possitionY[0],70,20);
     //   Util.addKeyListenerNumber(idTextField,errorLabel,65000);
        mainPanel.add(idTextField);
        idTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                enabledComponent();
            }
        });
    }


    private void createMessageTextField(){
        messageTextField = new JTextField("");
        messageTextField.setBounds(possitionX[1],possitionY[1],130,20);
        mainPanel.add(messageTextField);

        messageTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                enabledComponent();
            }
        });
    }

    public void createValueTextField(){
        valueTextField = new JTextField("");
        valueTextField.setBounds(possitionX[1],possitionY[2],70,20);
       // Util.addKeyListenerNumber(valueTextField,errorLabel,65000);
        mainPanel.add(valueTextField);
        valueTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                enabledComponent();
            }
        });
    }

    private void createErrorLabel() {
        errorLabel = new JLabel("");
        errorLabel.setBounds(possitionX[0],possitionY[4],200,20);
        errorLabel.setForeground(Color.red);
        mainPanel.add(errorLabel);
    }

    private void createComponentFoot(ActionListener listener){
        createFootPanel();
        createButtonSave(listener);
        addButtonCancel();
        enabledComponent();
    }


    private void createFootPanel(){
        footPanel = new JPanel();
        footPanel.setLayout(null);
       // footPanel.setBackground(GlobalConfig.PANEL_BAR_BUTTON);
        footPanel.setPreferredSize(new Dimension(100,40));
        add(footPanel, BorderLayout.SOUTH);
        footPanel.setLayout(null);
    }


    private void createButtonSave(ActionListener listener){
        saveButton = new JButton("Save");
        saveButton.setBounds(170,10,120,25);
        footPanel.add(saveButton);
        saveButton.addActionListener(listener);
    }
    
 /*   public Message getMessage() {
    	return  new Message(
                Integer.parseInt(idTextField.getText()),
                messageTextField.getText()
                ,Integer.parseInt(valueTextField.getText())
                ,color.getRGB());
    }

  */
    public void addButtonCancel(){
        JButton buttonCancel = new JButton("Cancel");
        buttonCancel.setBounds(300,10,90,25);
        footPanel.add(buttonCancel);
        buttonCancel.addActionListener(e -> this.dispose());
    }

    private void enabledComponent(){
        if (idTextField.getText().length()>0 &&
                messageTextField.getText().length()>0 &&
                valueTextField.getText().length()>0 &&
                color!=null  ){
            saveButton.setEnabled(true);
        } else {
            saveButton.setEnabled(false);
        }
    }


    public void setNotify(String value){
        errorLabel.setText(value);
    }


}
