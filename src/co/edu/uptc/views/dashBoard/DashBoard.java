package co.edu.uptc.views.dashBoard;

import co.edu.uptc.pojos.Person;
import co.edu.uptc.presenter.ContratBills;
import co.edu.uptc.views.people.DialogPeople;
import co.edu.uptc.views.people.PanelButtom;
import co.edu.uptc.views.people.PanelInput;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DashBoard extends JFrame implements ContratBills.View {
    ContratBills.Presenter presenter;

    private DialogPeople dialogPeople;

    private PanelButtom panelButtom;


    private PanelInput panelInput;
    public DashBoard()  {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(900 ,900));
        setLocationRelativeTo(null);
        setLayout(null);
        addComponents();
    }

    private void addComponents(){
        addButtonPeople();
        addButtonOther();
        addButtonClose();
    }

    private void addButtonPeople(){
        JButton jButtonPeople = new JButton("Personas");
        jButtonPeople.setBounds(100,100,120,30);
        add(jButtonPeople);
        jButtonPeople.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDiaplogPeople();
            }
        });
    }


    private void showDiaplogPeople(){
        dialogPeople = new DialogPeople(this);
        dialogPeople.updatedPerson();
        dialogPeople.setVisible(true);
    }

    public void closeDialogPerson(){
        if (dialogPeople!=null)
            dialogPeople.setVisible(false);
    }


    private DashBoard getInstance(){
        return this;
    }

    private void addButtonOther(){
        JButton jButtonOther = new JButton("Otros");
        jButtonOther.setBounds(100,150,120,30);
        add(jButtonOther);
        jButtonOther.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(getInstance(),"Sin definir");
            }
        });
    }

    private void addButtonClose(){
        JButton jButtonClose = new JButton("Close");
        jButtonClose.setBounds(100,200,120,30);
        add(jButtonClose);
        jButtonClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }


    public void addPerson(Person person){
        presenter.addPersonInModel(person);
    }

    public Person getPerson(String attribute){
        return presenter.getPerson(attribute);
    }

    public void editPerson(Person person){
        presenter.editPerson(person);
    }

    @Override
    public void setPresenter(ContratBills.Presenter presenter) {
     this.presenter = presenter;
    }

    @Override
    public void start() {
        this.setVisible(true);
    }

    @Override
    public void updatedPeople() {
        if (dialogPeople!=null)
      dialogPeople.updatedPerson();
    }

    @Override
    public void notifyError(String value) {
        JOptionPane.showMessageDialog(this,value,"",JOptionPane.ERROR_MESSAGE);

    }

    @Override
    public void notifyWarning(String value) {
        JOptionPane.showMessageDialog(this,value,"",JOptionPane.WARNING_MESSAGE);
    }

    public List<Person> getPeople() {
        return presenter.getPeople();
    }

    public String getAuthorModel() {
        return presenter.getAuthor();
    }
}
