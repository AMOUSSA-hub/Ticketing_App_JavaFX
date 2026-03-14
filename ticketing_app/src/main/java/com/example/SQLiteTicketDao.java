package com.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SQLiteTicketDao implements TicketDao {

    // Méthode pour convertir une ligne SQL en SupportTicket
        private SupportTicket mapRow(ResultSet rs) throws SQLException {
            return new SupportTicket(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("customer_name"),
                    rs.getString("priority"),
                    LocalDate.parse(rs.getString("created_at")),
                    rs.getString("description"),
                    rs.getInt("urgent") == 1,
                    rs.getString("status")
            );
        }

        
    @Override
    public SupportTicket insert(SupportTicket ticket) {
        String sql = "INSERT INTO support_tickets (title, customer_name, priority, created_at, description, urgent, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, ticket.getTitle());
            pstmt.setString(2, ticket.getCustomerName());
            pstmt.setString(3, ticket.getPriority());
            pstmt.setString(4, ticket.getCreatedAt().toString());
            pstmt.setString(5, ticket.getDescription());
            pstmt.setInt(6, ticket.isUrgent() ? 1 : 0);
            pstmt.setString(7, ticket.getStatus());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating ticket failed, no rows affected.");
            }
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long id = generatedKeys.getLong(1);
                    return ticket.withId(id);
                } else {
                    throw new SQLException("Creating ticket failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    
    }

    @Override
    public List<SupportTicket> findAll() {
        String sql = "SELECT * FROM support_tickets ORDER BY id DESC";
        List<SupportTicket> tickets = new ArrayList<>();
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tickets.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public Optional<SupportTicket> findById(long id) {
        String sql = "SELECT * FROM support_tickets WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<SupportTicket> searchByTitleOrCustomer(String keyword) {
        String sql = "SELECT * FROM support_tickets WHERE title LIKE ? OR customer_name LIKE ? ORDER BY id DESC";
        List<SupportTicket> tickets = new ArrayList<>();
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String likeKeyword = "%" + keyword + "%";
            pstmt.setString(1, likeKeyword);
            pstmt.setString(2, likeKeyword);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    tickets.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public void update(SupportTicket ticket) {
        String sql = "UPDATE support_tickets SET title = ?, customer_name = ?, priority = ?, created_at = ?, description = ?, urgent = ?, status = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ticket.getTitle());
            pstmt.setString(2, ticket.getCustomerName());
            pstmt.setString(3, ticket.getPriority());
            pstmt.setString(4, ticket.getCreatedAt().toString());
            pstmt.setString(5, ticket.getDescription());
            pstmt.setInt(6, ticket.isUrgent() ? 1 : 0);
            pstmt.setString(7, ticket.getStatus());
            pstmt.setLong(8, ticket.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(long id) {
        String sql = "DELETE FROM support_tickets WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
    }
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM support_tickets";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}   


