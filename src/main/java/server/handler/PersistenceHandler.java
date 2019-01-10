package server.handler;

import dbal.repository.*;
import models.Account;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class PersistenceHandler {

    private AccountRepository accountRepository = new AccountRepository();
    private EventRepository eventRepository = new EventRepository();
    private EventPropertyRepository eventPropertyRepository = new EventPropertyRepository();
    private TimelineRepository timelineRepository = new TimelineRepository();
    private DateformatRepository dateformatRepository = new DateformatRepository();

    public List<Account> GetAccounts() {
        Session session = accountRepository.openSession();
        List queryResult = session.createCriteria(Account.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        ArrayList<Account> resultList = new ArrayList<>();
        for (Object result : queryResult) {
            resultList.add((Account) result);
        }
        return resultList;
    }

    //ADD HIBERNATE FUNCTIONS HERE


}

/*
    public void SubmitEntry(List<String> problemWords, List<String> translationWords, String title, String problemLanguage, String translationLanguage, String personEmail) {

        WordList wordList = new WordList();
        List<WordEntry> listEntries = new ArrayList<>();
        for (int i = 0; i < problemWords.size(); i++){
            if(!problemWords.get(i).trim().equals("") && !problemWords.get(i).isEmpty()) {
                WordEntry wordEntry = new WordEntry();
                wordEntry.setProblem(problemWords.get(i));
                wordEntry.setTranslation(translationWords.get(i));
                listEntries.add(wordEntry);
            }
        }
        wordList.setListEntries(listEntries);
        wordList.setTitle(title);

        Person person = new Person();
        person.setEmail(personEmail.toLowerCase());
        person.addWordList(wordList);

        wordList.setProblemLanguage(problemLanguage);
        wordList.setTranslationLanguage(translationLanguage);

        for (WordEntry entry: listEntries) {
            wordEntryRepository.save(entry);
        }

        wordListRepository.save(wordList);
        personRepository.save(person);
    }

    public List<WordList> GetLists() {
        Session session = wordListRepository.openSession();
        List queryResult = session.createCriteria(WordList.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        ArrayList<WordList> resultList = new ArrayList<>();
        for (Object result : queryResult) {
            WordList wordList = (WordList) result;
            resultList.add(wordList);
        }
        return resultList;
    }

    public WordList GetListById(int id) {
        return wordListRepository.findOne(id);
    }

    public List<WordList> GetListsByEmail(String email) {
        Session session = personRepository.openSession();
        Criteria query = session.createCriteria(Person.class);
        query.add(Restrictions.eq("email", email));
        Person person = (Person) query.uniqueResult();
        return person.getWordList();
    }

    public void SubmitResultEntry(int wordListId, int score, int total, String email) {
        Result result = new Result();
        result.setScore(score);
        result.setTotal(total);
        result.setWordListId(wordListId);
        resultRepository.save(result);

        Session session = personRepository.openSession();
        Transaction tx = session.beginTransaction();
        Criteria query = session.createCriteria(Person.class);
        query.add(Restrictions.eq("email", email.toLowerCase()));
        Person person = (Person) query.uniqueResult();
        List<Result> results = person.getResultList();
        results.add(result);
        person.setResultList(results);
        tx.commit();
        session.close();

    }

    public List<RequestResult> GetResultsByEmail(String email) {
        Session session = personRepository.openSession();
        Criteria query = session.createCriteria(Person.class);
        query.add(Restrictions.eq("email", email.toLowerCase()));
        Person person = (Person) query.uniqueResult();
        List<RequestResult> requestResults = new ArrayList<>();

        for (Result result : person.getResultList()){
            RequestResult requestResult = new RequestResult();
            requestResult.setScore(result.getScore());
            requestResult.setTotal(result.getTotal());
            requestResult.setTitle(wordListRepository.findOne(result.getWordListId()).getTitle());
            requestResults.add(requestResult);
        }
        return requestResults;
    }
 */
