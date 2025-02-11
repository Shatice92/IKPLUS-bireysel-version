package org.hatice.ikplus.repository.commentandnotificationrepository;

import org.hatice.ikplus.entity.commentandnotificationmanagement.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {

}