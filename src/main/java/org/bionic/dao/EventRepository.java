package org.bionic.dao;

import org.bionic.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Event repository by MMaximov
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}