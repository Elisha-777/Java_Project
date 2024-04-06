package Java;

import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CharitableOrganizationDAO {
    private Connection connection;

    public CharitableOrganizationDAO(Connection connection) {
        this.connection = connection;
    }

    // Create a new Charitable Organization
    public boolean addOrganization(CharitableOrganization organization) {
        String sql = "INSERT INTO charitable_organizations (user_id) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, organization.getUserId());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Read a single Charitable Organization by ID
    public CharitableOrganization getOrganizationById(int organizationId) {
        CharitableOrganization organization = null;
        String sql = "SELECT * FROM charitable_organizations WHERE organization_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, organizationId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                organization = new CharitableOrganization(resultSet.getInt("organization_id"), resultSet.getInt("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return organization;
    }

    // Update an existing Charitable Organization
    public boolean updateOrganization(CharitableOrganization organization) {
        String sql = "UPDATE charitable_organizations SET user_id = ? WHERE organization_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, organization.getUserId());
            statement.setInt(2, organization.getOrganizationId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a Charitable Organization
    public boolean deleteOrganization(int organizationId) {
        String sql = "DELETE FROM charitable_organizations WHERE organization_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, organizationId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // List all Charitable Organizations
    public List<CharitableOrganization> getAllOrganizations() {
        List<CharitableOrganization> organizations = new ArrayList<>();
        String sql = "SELECT * FROM charitable_organizations";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CharitableOrganization organization = new CharitableOrganization(resultSet.getInt("organization_id"), resultSet.getInt("user_id"));
                organizations.add(organization);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return organizations;
    }
}
