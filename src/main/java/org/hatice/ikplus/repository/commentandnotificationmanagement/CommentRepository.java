package org.hatice.ikplus.repository.commentandnotificationmanagement;

import org.hatice.ikplus.entity.commentandnotificationmanagement.Comment;
import org.hatice.ikplus.entity.shiftandbreakmanagement.Break;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepository extends JpaRepository<Comment,Long> {
}