package main.java.dal;

import main.java.model.GearBonus;
import main.java.model.Item;
import main.java.model.Statistics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GearBonusDAO {

    public static GearBonus create(
            Connection cxn,
            Item item,
            Statistics statistics,
            int value
    ) throws SQLException {
        String insertGearBonusSql = "insert into GearBonus (ItemID,StatsName, Value) values (?,?,?)";
        try (PreparedStatement ps = cxn.prepareStatement(insertGearBonusSql)) {
            ps.setInt(1, item.getItemID());
            ps.setString(2, statistics.getName());
            ps.setInt(3, value);
            ps.executeUpdate();
            return new GearBonus(item, statistics, value);
        }

    }

    public static GearBonus getGearBonusByIdAndStatsName(
            Connection cxn,
            int itemID,
            String statsName
    ) throws SQLException {
        String getGearBonusSql = "select * from GearBonus where ItemID = ? and StatsName = ?";
        try (PreparedStatement ps = cxn.prepareStatement(getGearBonusSql)) {
            ps.setInt(1, itemID);
            ps.setString(2, statsName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Statistics statistics = Statistics.valueOf(statsName);
                    Item item = ItemDAO.getItemById(cxn, itemID);
                    return new GearBonus(
                            item,
                            statistics,
                            rs.getInt("Value")
                    );
                } else {
                    return null;
                }
            }
        }
    }
}
