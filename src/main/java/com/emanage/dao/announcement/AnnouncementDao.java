package com.emanage.dao.announcement;

import com.emanage.model.domain.announcement.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementDao extends JpaRepository<Announcement, Integer> {
}
