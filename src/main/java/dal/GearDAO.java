package main.java.dal;

import main.java.model.Gear;
import main.java.model.Item;
import main.java.model.Slot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GearDAO {

    public static Gear create(
            Connection cxn,
            String itemName,
            int level,
            int maxStackSize,
            int price,
            int requiredLevel,
            Slot slot
    ) throws SQLException {
        String insertGearSQL = "insert into gear (ItemID, SlotName) values(?,?)";
        try (PreparedStatement ps = cxn.prepareStatement(insertGearSQL)) {
            Item item = ItemDAO.create(cxn, itemName, level, maxStackSize, price, requiredLevel);
            ps.setInt(1, item.getItemID());
            ps.setString(2, slot.getName());
            ps.executeUpdate();
            return new Gear(
                    item.getItemID(),
                    itemName,
                    level,
                    maxStackSize,
                    price,
                    requiredLevel,
                    slot
            );
        }
    }

    public static Gear getGearByid(
            Connection cxn,
            int itemId
    ) throws SQLException {
        String getGearSql = "select * from gear where ItemID = ?";
        try (PreparedStatement ps = cxn.prepareStatement(getGearSql)) {
            ps.setInt(1, itemId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Item item = ItemDAO.getItemById(cxn, itemId);
                    Slot slot = Slot.valueOf(rs.getString("SlotName"));
                    return new Gear(
                            itemId,
                            item.getItemName(),
                            item.getLevel(),
                            item.getMaxStackSize(),
                            item.getPrice(),
                            item.getRequiredLevel(),
                            slot
                    );
                } else {
                    return null;
                }
            }
        }
    }
}
