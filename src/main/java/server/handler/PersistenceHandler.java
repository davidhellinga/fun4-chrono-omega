package server.handler;

import com.google.gson.Gson;
import dbal.repository.*;
import dbal.specification.AccountSpecification;
import models.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import server.models.EventCreationModel;
import server.utils.HashUtil;
import server.utils.JwtUtils;

import java.util.ArrayList;
import java.util.List;

public class PersistenceHandler {

    private AccountRepository accountRepository = new AccountRepository();
    private EventRepository eventRepository = new EventRepository();
    private EventPropertyRepository eventPropertyRepository = new EventPropertyRepository();
    private TimelineRepository timelineRepository = new TimelineRepository();
    private DateformatRepository dateformatRepository = new DateformatRepository();
    private JwtUtils jwtUtils = new JwtUtils(this);
    private HashUtil hashUtil = new HashUtil();
    private Gson gson = new Gson();

    public List<Account> getAccounts() {
        Session session = accountRepository.openSession();
        List queryResult = session.createCriteria(Account.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        ArrayList<Account> resultList = new ArrayList<>();
        for (Object result : queryResult) {
            resultList.add((Account) result);
        }
        return resultList;
    }

//    public Account getAccountByMail(String email) {
//        Session session = accountRepository.openSession();
//        Criteria query = session.createCriteria(Account.class);
//        query.add(Restrictions.eq("email", email));
//        return (Account) query.uniqueResult();
//    }

    public Boolean newAccount(String email, String password) {
        Account account = new Account();

        account.setEmail(email);
        account.setPassword(hashUtil.Sha256Hash(password));

        if (accountRepository.findOne(AccountSpecification.getByEmail(email)) != null) {
            return false;
        }
        accountRepository.save(account);
        return true;
    }

    public String login(String email, String password) {
        return jwtUtils.login(email, password);
    }

    public String verifyToken(String token) {
        return jwtUtils.isLoggedIn(token);
    }

    public List getTimelines(String email) {
        Session session = timelineRepository.openSession();
        Criteria query = session.createCriteria(Timeline.class);
        query.createAlias("account", "account");
        query.add(Restrictions.eq("account.email", email));
        return query.list();
    }

    //TODO: Dateformats
    public boolean newTimeline(String email, String name) {
        Session session = timelineRepository.openSession();
        Criteria query = session.createCriteria(Timeline.class, "tl");
        query.createAlias("account", "account");
        query.add(Restrictions.eq("title", name));
        query.add(Restrictions.eq("account.email", email));
        if (query.uniqueResult() != null) return false;
        Account account = accountRepository.findOne(AccountSpecification.getByEmail(email));
        Dateformat dateformat = new Dateformat();
        dateformat.setFormat("dd/mm/yyyy");
        dateformat.setMonths(12);
        dateformat.setWeeks(4);
        dateformat.setDays(7);
        dateformat.setHours(24);
        Timeline timeline = new Timeline();
        timeline.setTitle(name);
        timeline.setDateformat(dateformat);
        account.addTimeline(timeline);
        accountRepository.save(account);
        timelineRepository.save(timeline);
        dateformatRepository.save(dateformat);
        return true;
    }

    public Timeline getTimelineById(int id) {
        Session session = timelineRepository.openSession();
        Criteria query = session.createCriteria(Timeline.class);
        query.add(Restrictions.eq("id", id));
        return (Timeline) query.uniqueResult();
    }


    public boolean event(String eventData) {
        try {
            EventCreationModel eventIncoming = gson.fromJson(eventData, EventCreationModel.class);
            eventIncoming.setPersistenceHandler(this);
            if (!eventIncoming.validateEvent()) return false;
            Session session = eventRepository.openSession();
            Criteria query = session.createCriteria(Event.class);
            query.add(Restrictions.eq("id", eventIncoming.getEventId()));
            Event event = eventIncoming.toEvent((Event) query.uniqueResult());
            if (event.getTimeline() == null) {
                getTimelineById(eventIncoming.getTimelineId());
            }
            deleteEvent(event.getId());
            eventRepository.save(event);
            eventPropertyRepository.save(event.getProperties());
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean deleteEvent(int id) {
        try {
            Session eventSession = eventRepository.openSession();
            Criteria eventQuery = eventSession.createCriteria(Event.class);
            eventQuery.add(Restrictions.eq("id", id));

            Event event = (Event) eventQuery.uniqueResult();

            Session propertySession = eventPropertyRepository.openSession();
            Criteria propertyQuery = propertySession.createCriteria(EventProperty.class);
            propertyQuery.add(Restrictions.eq("event", event));

            for (EventProperty property :
                    (List<EventProperty>) propertyQuery.list()) {
                eventPropertyRepository.delete(property);
            }
            eventRepository.delete(event);

            return true;
        } catch (Exception ex) {
            return false;
        }

    }

    public List<EventCreationModel> getEvents(int timelineId) {
        List<EventCreationModel> events = new ArrayList<>();
        Session eventSession = eventRepository.openSession();
        Criteria eventQuery = eventSession.createCriteria(Event.class);
        eventQuery.createAlias("timeline", "timeline");
        eventQuery.add(Restrictions.eq("timeline.id", timelineId));
        for (Event event :
                (List<Event>) eventQuery.list()) {
            Session propertySession = eventPropertyRepository.openSession();
            Criteria propertyQuery = propertySession.createCriteria(EventProperty.class);
            propertyQuery.createAlias("event", "event");
            propertyQuery.add(Restrictions.eq("event.id", event.getId()));
            event.setProperties((List<EventProperty>) propertyQuery.list());
            EventCreationModel ecm = new EventCreationModel(this);
            ecm.fromEvent(event);
            events.add(ecm);

        }
        return events;
    }

    public AccountRepository getAccountRepository() {
        return accountRepository;
    }

    public EventRepository getEventRepository() {
        return eventRepository;
    }

    public EventPropertyRepository getEventPropertyRepository() {
        return eventPropertyRepository;
    }

    public TimelineRepository getTimelineRepository() {
        return timelineRepository;
    }

    public DateformatRepository getDateformatRepository() {
        return dateformatRepository;
    }
}