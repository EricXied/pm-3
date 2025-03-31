package main.java.dal;

import main.java.model.Clan;
import main.java.model.Race;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClanDAO {

    public static Clan create(
            Connection cxn,
            Race race,
            String clan
    ) throws SQLException {
        String createClanSql = "insert into clan (RaceName, ClanName) values (?, ?)";
        try (PreparedStatement ps = cxn.prepareStatement(createClanSql)) {
            ps.setString(1, race.getName());
            ps.setString(2, clan);
            ps.executeUpdate();
            return new Clan(
                    race,
                    clan
            );

        }
    }

    public static Clan getClanByName(Connection cxn, String clanName, Race race) throws SQLException {
        String selectClanSql = "select * from clan where ClanName = ? and RaceName = ?";
        try (PreparedStatement ps = cxn.prepareStatement(selectClanSql)) {
            ps.setString(1, clanName);
            ps.setString(2, race.getName());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Clan(
                            race,
                            rs.getString("ClanName")
                    );
                } else {
                    return null;
                }
            }
        }
    }
}
