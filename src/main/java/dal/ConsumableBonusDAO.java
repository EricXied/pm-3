package main.java.dal;

import main.java.model.Consumable;
import main.java.model.ConsumableBonus;
import main.java.model.Item;
import main.java.model.Statistics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsumableBonusDAO {
    public static ConsumableBonus create(
            Connection cxn,
            Consumable consumable,
            Statistics statistics,
            int value,
            int bounsCap,
            float bonusPercentage
    ) throws SQLException {
        String insertConsumableBonusSql = "insert into ConsumableBonus (ItemID, StatsName, Value, BounsCap, BonusPercentage) values (?,?,?,?,?)";
        try (PreparedStatement ps = cxn.prepareStatement(insertConsumableBonusSql)) {
            ps.setInt(1, consumable.getItemID());
            ps.setString(2, statistics.getName());
            ps.setInt(3, value);
            ps.setInt(4, bounsCap);
            ps.setFloat(5, bonusPercentage);
            ps.executeUpdate();
            return new ConsumableBonus(
                    consumable,
                    statistics,
                    value,
                    bounsCap,
                    bonusPercentage
            );
        }
    }

    public static ConsumableBonus getConsumableBonusByItemIDAndStatsName(Connection cxn, int itemID, String statsName) throws SQLException {

        String selectConsumableBonusSql = "select * from ConsumableBonus where ItemID = ? and StatsName = ?";
        try (PreparedStatement ps = cxn.prepareStatement(selectConsumableBonusSql)) {
            ps.setInt(1, itemID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Statistics statistics = Statistics.valueOf(statsName);
                    Consumable consumable = ConsumableDAO.getConsumableById(cxn, itemID);
                    return new ConsumableBonus(
                            consumable,
                            statistics,
                            rs.getInt("Value"),
                            rs.getInt("BonusCap"),
                            rs.getFloat("BonusPercentage")
                    );
                } else {
                    return null;
                }
            }
        }
    }
}
