package co.edu.uptc.views.people;

import co.edu.uptc.pojos.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditPerson extends JDialog {

    private JLabel labelCode;
    private JLabel labelNam;
    protected JTextField nameField;
    protected JTextField codeField;
    protected JButton saveBt;
    private JButton cancelBt;
    private PanelPeople dialogPeople;

    public EditPerson(PanelPeople dialogPeople){
       // super(dialogPeople,true);
        this.dialogPeople = dialogPeople;
        setLayout(null);
        initComponents();
        setSize(new Dimension(450,250));
        setLocationRelativeTo(dialogPeople);
        setVisible(false);
    }

    private void initComponents(){
        addLabels();
        addButtons();
        addFields();
    }

    private void addButtons(){
        saveBt = new JButton("Guardar");
        saveBt.setBounds(100,150,110,30);
        saveBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editPerson();
                hideDialog();
            }
        });
        add(saveBt);

        cancelBt = new JButton("Cancelar");
        cancelBt.setBounds(220,150,110,30);
        cancelBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideDialog();
            }
        });
        add(cancelBt);
    }

    private void addFields(){
        nameField = new JTextField(20);
        nameField.setBounds(190,60,100,25);
        add(nameField);

        codeField = new JTextField(20);
        codeField.setBounds(190,100,100,25);
        add(codeField);
    }

    private void addLabels(){
        labelNam = new JLabel("Nombre: ");
        labelNam.setBounds(130,60,100,25);
        add(labelNam);

        labelCode = new JLabel("Codigo: ");
        labelCode.setBounds(130,100,100,25);
        add(labelCode);
    }

    public boolean setSelectedPerson(){
        boolean selected = false;
        JLabel jLabel = new JLabel();
        jLabel.setBounds(180,20,150,30);
        if (dialogPeople!=null&&dialogPeople.getSelectedPerson()!=null) {
            nameField.setText(dialogPeople.getSelectedPerson().getName());
            codeField.setText(dialogPeople.getSelectedPerson().getCode());
            selected = true;
        } else {
            dialogPeople.showMessageWarning("NO ha seleccionado ninguna persona para modificar");
        }

        return selected;
    }

    public void editPerson(){
        Person per = dialogPeople.getSelectedPerson();
        if (per!=null) {
            per.setName(nameField.getText());
            per.setCode(codeField.getText());
            dialogPeople.editPerson(per);
            dialogPeople.updatedPerson();
        } else {
            dialogPeople.showMessageWarning("NO ha seleccionado ninguna persona para modificar");
        }
    }

    private void hideDialog(){
        this.setVisible(false);
    }



}
