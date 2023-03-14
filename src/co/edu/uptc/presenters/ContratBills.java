package co.edu.uptc.presenters;



import co.edu.uptc.pojos.Person;

import java.util.List;


public interface ContratBills {
    public interface View {
        void setPresenter(ContratBills.Presenter presenter);
        void start();
        void updatedPeople();
        public void notifyError(String value);

    }
    public interface Model{
        void setPresenter(ContratBills.Presenter presenter);
        void addPerson(Person person);
        String getPerson(int pos);
        public List<Person> getPeople();
    }

    public interface Presenter {
        void setView(ContratBills.View view);
        void setModel(ContratBills.Model model);

        void addPersonInModel(Person person);
        void notifyPeopleUpdated();
        String getPerson(int pos);

        public List<Person> getPeople();
        public void notifyError(String value);

    }
}
