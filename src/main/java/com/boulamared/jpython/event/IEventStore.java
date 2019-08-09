package com.boulamared.jpython.event;

import java.util.List;

/**
 * <h1>IEventStore</h1>
 * <p>
 * This is the eventStore interface
 * </p>
 * @author BOULAMMO
 *
 */

public interface IEventStore {

	IEventStore add(Event event);

	IEventStore add(Event... events);

	Event get(String id);

	Event get(Long createdAt);

	void remove(String id);

	void removeLast();

	List<Event> getAll();

}
