package study.model.impl;

import lombok.Builder;
import study.model.Ticket;

@Builder
public class TicketImpl implements Ticket {

    private long id;
    private long userId;
    private long eventId;
    private Ticket.Category category;
    private int place;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public long getEventId() {
        return eventId;
    }

    @Override
    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    @Override
    public Ticket.Category getCategory() {
        return category;
    }

    @Override
    public void setCategory(Ticket.Category category) {
        this.category = category;
    }

    @Override
    public int getPlace() {
        return place;
    }

    @Override
    public void setPlace(int place) {
        this.place = place;
    }
}
