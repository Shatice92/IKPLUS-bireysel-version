package org.hatice.ikplus.repository.commentandnotificationmanagement;

import org.hatice.ikplus.entity.commentandnotificationmanagement.Comment;
import org.hatice.ikplus.entity.commentandnotificationmanagement.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationRepository extends JpaRepository<Notification,Long> {

}