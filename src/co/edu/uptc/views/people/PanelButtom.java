package co.edu.uptc.views.people;

import co.edu.uptc.pojos.Person;
import co.edu.uptc.views.dashBoard.DashBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelButtom extends JPanel {

    private JButton buttonAdd;
    private JButton buttonClose;
    private JButton buttonEdit;
    private EditPerson editPerson;
    private DialogPeople dialogPeople;
    public PanelButtom(DialogPeople dialogPeople) {
        this.dialogPeople = dialogPeople;
        editPerson = new EditPerson(dialogPeople);
        setBackground(Color.yellow);
        setPreferredSize(new Dimension(0,50));
        addComponents();
    }

    private void addComponents(){
        addButtomAdd();
        addButtonClose();
        addEditButton();
    }


    private void addButtonClose(){
        buttonClose = new JButton("CERRAR");
        add(buttonClose);
        buttonClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeDialog();
            }
        });
    }

    private void addEditButton(){
        buttonEdit = new JButton("EDITAR");
        add(buttonEdit);
        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editPerson.setSelectedPerson()) {
                    showEditDialog();
                }
            }
        });
    }

    private void addButtomAdd(){
        buttonAdd = new JButton("ADICIONAR");
        add(buttonAdd);
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogPeople.addPerson();
            }
        });
    }

    private void closeDialog(){
        dialogPeople.close();

    }

    private void showEditDialog(){
        editPerson.setVisible(true);
    }



}
