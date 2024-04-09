package Java;

/**
 *
 * @author ELISHA KALYAN
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SurplusFoodItemDAOImpl implements ISurplusFoodItemDAO {
    private Connection connection;

    // Constructor that takes a database connection
    public SurplusFoodItemDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<SurplusFoodItem> listAllSurplusFoodItemsForDonation() throws Exception {
        List<SurplusFoodItem> items = new ArrayList<>();
        String sql = "SELECT * FROM surplus_food WHERE is_donation = TRUE AND quantity > 0";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                items.add(new SurplusFoodItem(
                        rs.getInt("id"),
                        rs.getInt("retailer_id"),
                        rs.getString("item_name"),
                        rs.getInt("quantity"),
                        rs.getDate("expiration_date"),
                        rs.getBoolean("is_donation"),
                        rs.getBigDecimal("discounted_price")));
            }
        }
        return items;
    }

    @Override
    public boolean claimSurplusFoodItem(int surplusId) throws Exception {
        String sql = "UPDATE surplus_food SET quantity = 0 WHERE id = ? AND quantity > 0";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, surplusId);
            return statement.executeUpdate() > 0;
        }
    }
}

