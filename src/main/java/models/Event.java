package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    int id;

    @NotNull
    private String name;

    @NotNull
    private String date;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private transient List<EventProperty> properties;

    @ManyToOne
    @JoinColumn(name="fk_timeline")
    private Timeline timeline;

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<EventProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<EventProperty> properties) {
        this.properties = properties;
    }

    public void addProperty(EventProperty property){
        this.properties.add(property);
        property.setEvent(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
