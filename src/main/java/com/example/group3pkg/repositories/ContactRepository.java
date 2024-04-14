package com.example.group3pkg.repositories;

import com.example.group3pkg.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
