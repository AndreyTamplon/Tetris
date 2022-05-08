package model;

import java.util.ArrayList;

public class Model
{
    private final ArrayList<Subscriber> subscribers;

    public Model(ArrayList<Subscriber> subscribers)
    {
        this.subscribers = subscribers;
    }

    public Model()
    {
        this.subscribers = new ArrayList<>();
    }

    public void notifySubscriber(Subscriber subscriber)
    {
        subscriber.update(this);
    }

    protected void notifySubscribers()
    {
        for (final Subscriber subscriber : subscribers)
        {
            notifySubscriber(subscriber);
        }
    }

    public synchronized void subscribe(Subscriber subscriber)
    {
        if (subscriber == null)
        {
            throw new NullPointerException("null is passed in the subscriber field");
        }
        if (!subscribers.contains(subscriber))
        {
            subscribers.add(subscriber);
        }
    }

    public synchronized void unsubscribe(Subscriber subscriber)
    {
        if (subscriber == null)
        {
            throw new NullPointerException("null is passed in the subscriber field");
        }
        if (!subscribers.contains(subscriber))
        {
            throw new IllegalArgumentException("Unknown subscriber: " + subscriber);
        }
        subscribers.remove(subscriber);
    }
}
