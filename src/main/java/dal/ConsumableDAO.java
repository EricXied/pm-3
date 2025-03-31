package main.java.dal;

import main.java.model.Consumable;
import main.java.model.Item;
import main.java.model.Job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsumableDAO {

    public static Consumable create(
            Connection cxn,
            String itemName,
            int level,
            int maxStackSize,
            int price,
            int requiredLevel,
            String description
    ) throws SQLException {
        String insertConsumableSql = "insert into Consumable (ItemID, Description) values (?, ?)";
        try (PreparedStatement ps = cxn.prepareStatement(insertConsumableSql)) {
            Item item = ItemDAO.create(cxn, itemName, level, maxStackSize, price, requiredLevel);
            ps.setInt(1, item.getItemID());
            ps.setString(2, description);
            ps.executeUpdate();
            return new Consumable(
                    item.getItemID(),
                    itemName,
                    level,
                    maxStackSize,
                    price,
                    requiredLevel,
                    description
            );
        }
    }

    public static Consumable getConsumableById(
            Connection cxn,
            int itemId
    ) throws SQLException {
        String selectConsumableSql = "select * from Consumable where ItemID = ?";
        try (PreparedStatement ps = cxn.prepareStatement(selectConsumableSql)) {
            ps.setInt(1, itemId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Item item = ItemDAO.getItemById(cxn, itemId);
                    return new Consumable(
                            itemId,
                            item.getItemName(),
                            item.getLevel(),
                            item.getMaxStackSize(),
                            item.getPrice(),
                            item.getRequiredLevel(),
                            rs.getString("Description")
                    );
                } else {
                    return null;
                }
            }
        }
    }
}
