package main.java.dal;

import main.java.model.CharacterJob;
import main.java.model.Characters;
import main.java.model.Job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CharacterJobDAO {

    public static CharacterJob createCharacterJob(
            Connection cxn,
            Job job,
            Characters characters,
            int level,
            int xp,
            boolean lock
    ) throws SQLException {
        String insertCharacterJobSql = "INSERT INTO CharacterJob (JobName, CharacterID, Level, XP, Lock)VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = cxn.prepareStatement(insertCharacterJobSql)) {
            ps.setString(1, job.getName());
            ps.setInt(2, characters.getCharacterID());
            ps.setInt(3, level);
            ps.setInt(4, xp);
            ps.setBoolean(5, lock);
            ps.executeUpdate();
            return new CharacterJob(
                    job,
                    characters,
                    level, xp,
                    lock
            );
        }
    }

    public static CharacterJob getCharacterJobByIdAndJobName(
            Connection cxn,
            int characterId,
            String jobName
    ) throws SQLException {
        String selectCharacterJobSql = "SELECT * FROM CharacterJob WHERE CharacterID = ? AND JobName = ?";
        try (PreparedStatement ps = cxn.prepareStatement(selectCharacterJobSql)) {
            ps.setInt(1, characterId);
            ps.setString(2, jobName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Characters character = CharacterDAO.getCharacterByID(cxn, characterId);
                    Job job = Job.valueOf(rs.getString("JobName"));
                    return new CharacterJob(
                            job,
                            character,
                            rs.getInt("Level"),
                            rs.getInt("XP"),
                            rs.getBoolean("Lock")
                    );
                } else {
                    return null;

                }
            }
        }
    }
}
