package co.edu.uptc.views.people;

import co.edu.uptc.pojos.Person;
import co.edu.uptc.views.dashBoard.DashBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditPerson extends JDialog {

    private JLabel personSelected;
    private JLabel labelCode;
    private JLabel labelNam;
    protected JTextField nameField;
    protected JTextField codeField;
    protected JButton saveBt;
    private JButton cancelBt;
    private DialogPeople dialogPeople;

    public EditPerson(DialogPeople dialogPeople){
        super(dialogPeople,true);
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
        saveBt.setBounds(120,150,80,30);
        saveBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editPerson();
                hideDialog();
            }
        });
        add(saveBt);

        cancelBt = new JButton("Cancelar");
        cancelBt.setBounds(210,150,90,30);
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
        nameField.setBounds(180,60,100,25);
        add(nameField);

        codeField = new JTextField(20);
        codeField.setBounds(180,100,100,25);
        add(codeField);
    }

    private void addLabels(){
        personSelected = new JLabel("Cliente: ");
        personSelected.setBounds(120,20,150,30);
        add(personSelected);

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
            jLabel.setText(dialogPeople.getSelectedPerson().getName() + " " + dialogPeople.getSelectedPerson().getCode());
            add(jLabel);
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
        } else {
            dialogPeople.showMessageWarning("NO ha seleccionado ninguna persona para modificar");
        }
    }

    private void hideDialog(){
        this.setVisible(false);
    }



}
