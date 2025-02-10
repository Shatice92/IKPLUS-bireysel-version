package org.hatice.ikplus.Repository.companymanagement;

import org.hatice.ikplus.entity.companymanagement.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}