package main.java.dal;

import main.java.model.CharacterEquipment;
import main.java.model.Characters;
import main.java.model.Item;
import main.java.model.Slot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CharacterEquipmentDAO {

    public static CharacterEquipment create(
            Connection cxn,
            Characters characters,
            Slot slot,
            Item item
    ) throws SQLException {
        String insertCharacterEquipmentSql = "INSERT INTO CharacterEquipment (CharacterID, SlotName, ItemID) VALUES (?, ?, ?) ";
        try (PreparedStatement ps = cxn.prepareStatement(insertCharacterEquipmentSql)) {
            ps.setInt(1, characters.getCharacterID());
            ps.setString(2, slot.getName());
            ps.setInt(3, item.getItemID());
            ps.executeUpdate();
            return new CharacterEquipment(characters, slot, item);
        }
    }

    public static CharacterEquipment getEquipmentByIDAndSlot(
            Connection cxn,
            int characterID,
            String slotName
    ) throws SQLException {
        String getEquipmentByIDAndSlotSql = "SELECT * FROM CharacterEquipment WHERE CharacterID = ? AND SlotName = ?";
        try (PreparedStatement ps = cxn.prepareStatement(getEquipmentByIDAndSlotSql)) {

            ps.setInt(1, characterID);
            ps.setString(2, slotName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Slot slot = Slot.valueOf(slotName);
                Item item = ItemDAO.getItemById(cxn, rs.getInt("ItemID"));
                Characters character = CharacterDAO.getCharacterByID(cxn, characterID);
                return new CharacterEquipment(
                        character,
                        slot,
                        item
                );
            } else {
                return null;
            }

        }
    }
}
