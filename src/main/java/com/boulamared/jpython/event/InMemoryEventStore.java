package com.boulamared.jpython.event;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

/**
 * <h1>InMemory EventStore</h1>
 * This is an implementation of the IEventStore interface, used to store the EventStore in the memory
 * @author BOULAMMO
 * @version 1.0
 */

@Service
public class InMemoryEventStore implements IEventStore {

	private List<Event> events = new ArrayList<Event>();

	@Override
	public IEventStore add(Event event) {
		this.events.add(event);
		return this;
	}

	@Override
	public IEventStore add(Event... events) {
		Stream.of(events).forEach(event -> {
			this.events.add(event);
		});
		return this;
	}

	/**
	 * @return Event
	 * @param  id
	 */
	@Override
	public Event get(String id) {
		return this.events.stream().filter(event -> event.getId().equalsIgnoreCase(id)).findFirst().get();
	}

	/**
	 * @return Event
	 * @param createdAt
	 */
	@Override
	public Event get(Long createdAt) {
		return this.events.stream().filter(event -> event.getCreatedAt().equals(createdAt)).findFirst().get();
	}

	/**
	 * @return 
	 * @param id
	 */
	@Override
	public void remove(String id) {
		this.events.remove(this.get(id));
	}

	/**
	 * @return
	 */
	@Override
	public void removeLast() {
		this.events.remove(this.events.size() - 1);
	}

	/**
	 * @return List
	 * @param
	 */
	@Override
	public List<Event> getAll() {
		return this.events;
	}

}
