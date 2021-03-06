package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Timeline")
public class Timeline {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    int id;

    @OneToMany(mappedBy = "timeline",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private transient List<Event> events;

    @NotNull
    private String title;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Dateformat dateformat;

    @ManyToOne
    @JoinColumn(name="fk_account")
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Dateformat getDateformat() {
        return dateformat;
    }

    public void setDateformat(Dateformat dateformat) {
        this.dateformat = dateformat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void addEvent(Event event){
        this.events.add(event);
        event.setTimeline(this);
    }
}
