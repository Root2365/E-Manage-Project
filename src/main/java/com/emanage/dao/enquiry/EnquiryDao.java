package com.emanage.dao.enquiry;

import com.emanage.model.domain.enquiry.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnquiryDao extends JpaRepository<Enquiry, Integer> {
}
