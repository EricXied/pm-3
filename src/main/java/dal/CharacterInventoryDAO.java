package main.java.dal;

import main.java.model.CharacterInventory;
import main.java.model.Characters;
import main.java.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CharacterInventoryDAO {

    public static CharacterInventory create(
            Connection cxn,
            int slotNumber,
            Characters characters,
            Item item,
            int quantity
    ) throws SQLException {
        String insertCharacterInventorySql = "insert into CharacterInventory(SlotNumber, CharacterID, ItemID, Quantity) values(?,?,?,?)";
        try (PreparedStatement ps = cxn.prepareStatement(insertCharacterInventorySql)) {
            ps.setInt(1, slotNumber);
            ps.setInt(2, characters.getCharacterID());
            ps.setInt(3, item.getItemID());
            ps.setInt(4, quantity);
            ps.executeUpdate();
            return new CharacterInventory(slotNumber, characters, item, quantity);
        }
    }

    public static CharacterInventory getInventoryByIdAndSlotNumber(
            Connection cxn,
            int characterId,
            int slotNumber
    ) throws SQLException {
        String selectCharacterInventorySQL = "select * from CharacterInventory where CharacterID = ? and SlotNumber = ?";
        try (PreparedStatement ps = cxn.prepareStatement(selectCharacterInventorySQL)) {
            ps.setInt(1, characterId);
            ps.setInt(2, slotNumber);
            try (ResultSet rs = ps.executeQuery()) {
                Characters characters = CharacterDAO.getCharacterByID(cxn, characterId);
                Item item = ItemDAO.getItemById(cxn, rs.getInt("ItemID"));
                return new CharacterInventory(
                        slotNumber,
                        characters,
                        item,
                        rs.getInt("Quantity")
                );
            }
        }
    }
}
