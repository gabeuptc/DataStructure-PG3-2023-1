
package co.edu.uptc.presenter;



import co.edu.uptc.pojos.Person;

import java.util.List;


public interface ContractPeople {
    public interface View {
        void setPresenter(ContractPeople.Presenter presenter);
        void start();
        void updatedPeople();
        public void notifyError(String value);
        public void notifyWarning(String value);

    }
    public interface Model{
        void setPresenter(ContractPeople.Presenter presenter);
        void addPerson(Person person);
        String getPerson(int pos);
        public List<Person> getPeople();
        Person getPerson(String attribute);
        void editPerson(Person person);
        String getAuthor();
    }

    public interface Presenter {
        void setView(ContractPeople.View view);
        void setModel(ContractPeople.Model model);

        void addPersonInModel(Person person);
        void notifyPeopleUpdated();
        String getPerson(int pos);
        Person getPerson(String attribute);
        void editPerson(Person person);

        public List<Person> getPeople();
        public void notifyError(String value);
        String getAuthor();


    }
}
