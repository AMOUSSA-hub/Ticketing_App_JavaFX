package com.example;

import java.util.List;
import java.util.Optional;


public interface TicketDao {
    public SupportTicket insert(SupportTicket ticket);

    public List<SupportTicket> findAll();

    public Optional<SupportTicket> findById(long id);

    public List<SupportTicket> searchByTitleOrCustomer(String keyword);

    public void update(SupportTicket ticket);

    public void deleteById(long id);

    public void deleteAll();
}