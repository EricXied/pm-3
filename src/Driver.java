
import main.java.dal.*;
import main.java.model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Driver {
    public static void main(String[] args) {
        try {
            resetSchema();
            insertRecords();
        } catch (SQLException e) {
            System.out.print("SQL Exception: ");
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static void insertRecords() throws SQLException {
        try (
                Connection cxn = ConnectionManager.getConnection()
        ) {
            // INSERT objects from our model.
            Date date = new Date();


        }
    }

    private static void resetSchema() throws SQLException {
        try (
                Connection cxn = ConnectionManager.getSchemalessConnection()
        ) {
            cxn.createStatement().executeUpdate(
                    "drop schema if exists PM3;"
            );
            cxn.createStatement().executeUpdate("create schema PM3;");
            cxn.createStatement().executeUpdate("use PM3;");
        }

        try (
                Connection cxn = ConnectionManager.getConnection()
        ) {
            cxn.createStatement().executeUpdate("""
                    CREATE TABLE Player (
                        PlayerID INT AUTO_INCREMENT PRIMARY KEY,
                        FullName VARCHAR(255) NOT NULL,
                        Email VARCHAR(255) UNIQUE NOT NULL,
                        AccountCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
                    );              
                                              """
            );

            cxn.createStatement().executeUpdate("""
                    CREATE TABLE Race (
                        RaceName VARCHAR(100) PRIMARY KEY
                    );"""
            );

            cxn.createStatement().executeUpdate("""
                    CREATE TABLE Clan (
                          RaceName VARCHAR(100) NOT NULL,
                          ClanName VARCHAR(100) NOT NULL,
                          PRIMARY KEY (RaceName, ClanName),
                          FOREIGN KEY (RaceName) REFERENCES Race(RaceName)
                            ON DELETE CASCADE ON UPDATE CASCADE,
                      	CONSTRAINT unique_race_clan UNIQUE (ClanName, RaceName)
                      );
                      """
            );

            cxn.createStatement().executeUpdate("""
                                       
                     CREATE TABLE Job (
                        JobName VARCHAR(100) PRIMARY KEY
                    );
                    """
            );

            cxn.createStatement().executeUpdate("""
                    CREATE TABLE Characters (
                        CharacterID INT AUTO_INCREMENT PRIMARY KEY,
                        PlayerID INT NOT NULL,
                        FirstName VARCHAR(100) NOT NULL,
                        LastName VARCHAR(100) NOT NULL,
                        RaceName VARCHAR(100),
                        ClanName VARCHAR(100),
                    -- ON DELETE CASCADE (Cascade Delete): Deleting a Player should delete all their Characters
                        FOREIGN KEY (PlayerID) REFERENCES Player(PlayerID)
                          ON DELETE CASCADE ON UPDATE CASCADE,
                        FOREIGN KEY (RaceName) REFERENCES Race(RaceName)
                          ON DELETE SET NULL ON UPDATE CASCADE,
                        FOREIGN KEY (RaceName, ClanName) REFERENCES Clan(RaceName, ClanName)
                          ON DELETE SET NULL ON UPDATE CASCADE
                    );"""
            );

            cxn.createStatement().executeUpdate("""
                    CREATE TABLE CharacterJob (
                        JobName VARCHAR(100) NOT NULL,
                        CharacterID INT NOT NULL,
                        `Level` INT NOT NULL DEFAULT 1,
                        XP INT NOT NULL DEFAULT 0,
                        `Lock` BOOLEAN NOT NULL DEFAULT FALSE,
                        PRIMARY KEY (JobName, CharacterID),
                        FOREIGN KEY (JobName) REFERENCES Job(JobName)
                          ON DELETE CASCADE ON UPDATE CASCADE,
                    -- Deleting a Character should delete its related data
                        FOREIGN KEY (CharacterID) REFERENCES Characters(CharacterID)
                          ON DELETE CASCADE ON UPDATE CASCADE
                    );"""
            );
            cxn.createStatement().executeUpdate("""
                    CREATE TABLE Currency (
                          CurrencyName VARCHAR(100) PRIMARY KEY,
                          Cap INT,
                          WeeklyCap INT
                      );
                      """
            );

            cxn.createStatement().executeUpdate("""
                    CREATE TABLE CharacterCurrency (
                            CurrencyName VARCHAR(100) NOT NULL,
                            CharacterID INT NOT NULL,
                            TotalAmount INT NOT NULL,
                            WeeklyAmount INT NOT NULL,
                            PRIMARY KEY (CurrencyName, CharacterID),
                            FOREIGN KEY (CurrencyName) REFERENCES Currency(CurrencyName)
                              ON DELETE CASCADE ON UPDATE CASCADE,
                            FOREIGN KEY (CharacterID) REFERENCES Characters(CharacterID)
                            ON DELETE CASCADE ON UPDATE CASCADE
                        );
                      """
            );

            cxn.createStatement().executeUpdate("""           
                    CREATE TABLE Statistics (
                     StatsName VARCHAR(100) PRIMARY KEY
                                   );          
                      """
            );

            cxn.createStatement().executeUpdate("""
                    CREATE TABLE CharacterStats (
                        StatsName VARCHAR(100) NOT NULL,
                        CharacterID INT NOT NULL,
                        `Value` INT NOT NULL DEFAULT 0,
                        PRIMARY KEY (StatsName, CharacterID),
                        FOREIGN KEY (StatsName) REFERENCES Statistics(StatsName)
                          ON DELETE CASCADE ON UPDATE CASCADE,
                        FOREIGN KEY (CharacterID) REFERENCES Characters(CharacterID)
                          ON DELETE CASCADE ON UPDATE CASCADE
                    );
                      """
            );



            cxn.createStatement().executeUpdate("""
                    CREATE TABLE Slot (
                        SlotName VARCHAR(100) PRIMARY KEY
                    );
                                        
                      """
            );

            cxn.createStatement().executeUpdate("""
                    CREATE TABLE Item (
                        ItemID INT AUTO_INCREMENT PRIMARY KEY,
                        ItemName VARCHAR(255) NOT NULL,
                        `Level` INT NOT NULL DEFAULT 1,
                        MaxStackSize INT NOT NULL,
                        Price DECIMAL(10,2),
                        RequiredLevel INT
                    );
                      """
            );

            cxn.createStatement().executeUpdate("""
                    CREATE TABLE Gear (
                        ItemID INT PRIMARY KEY,
                        SlotName VARCHAR(100) NOT NULL,
                    -- Deleting an Item should delete its related bonuses
                    -- When an Item is deleted, any associated bonuses (GearBonus, ConsumableBonus)\s
                    -- should also be deleted since they are directly linked to that item.
                        FOREIGN KEY (ItemID) REFERENCES Item(ItemID)
                          ON DELETE CASCADE ON UPDATE CASCADE,
                        FOREIGN KEY (SlotName) REFERENCES Slot(SlotName)
                          ON DELETE CASCADE ON UPDATE CASCADE
                    );
                      """
            );

            cxn.createStatement().executeUpdate("""
                    CREATE TABLE Weapon (
                        ItemID INT PRIMARY KEY,
                        JobName VARCHAR(100) NOT NULL,
                        Damage INT NOT NULL,
                        FOREIGN KEY (ItemID) REFERENCES Item(ItemID)
                          ON DELETE CASCADE ON UPDATE CASCADE,
                        FOREIGN KEY (JobName) REFERENCES Job(JobName)
                          ON DELETE CASCADE ON UPDATE CASCADE
                    );
                                        
                      """
            );

            cxn.createStatement().executeUpdate("""
                    CREATE TABLE Consumable (
                        ItemID INT PRIMARY KEY,
                        `Description` TEXT NOT NULL,
                        FOREIGN KEY (ItemID) REFERENCES Item(ItemID)
                          ON DELETE CASCADE ON UPDATE CASCADE
                    );
                                        
                      """
            );

            cxn.createStatement().executeUpdate("""
                    CREATE TABLE GearBonus (
                        ItemID INT NOT NULL,
                        StatsName VARCHAR(100) NOT NULL,
                        `Value` INT NOT NULL,
                        PRIMARY KEY (ItemID, StatsName),
                        FOREIGN KEY (ItemID) REFERENCES Item(ItemID)
                          ON DELETE CASCADE ON UPDATE CASCADE,
                        FOREIGN KEY (StatsName) REFERENCES Statistics(StatsName)
                          ON DELETE CASCADE ON UPDATE CASCADE
                    );
                      """
            );

            cxn.createStatement().executeUpdate("""
                    CREATE TABLE ConsumableBonus (
                        ItemID INT NOT NULL,
                        StatsName VARCHAR(100) NOT NULL,
                        `Value` INT NOT NULL,
                        BonusCap INT NOT NULL,
                        BonusPercentage DECIMAL(5,2) NOT NULL,
                        PRIMARY KEY (ItemID, StatsName),
                        FOREIGN KEY (ItemID) REFERENCES Consumable(ItemID)
                          ON DELETE CASCADE ON UPDATE CASCADE,
                        FOREIGN KEY (StatsName) REFERENCES Statistics(StatsName)
                          ON DELETE CASCADE ON UPDATE CASCADE
                    );
                                        
                                          """
            );

            cxn.createStatement().executeUpdate("""
                    CREATE TABLE CharacterEquipment (
                        CharacterID INT NOT NULL,
                        SlotName VARCHAR(100) NOT NULL,
                        ItemID INT,
                        PRIMARY KEY (CharacterID, SlotName),
                        FOREIGN KEY (CharacterID) REFERENCES Characters(CharacterID)
                          ON DELETE CASCADE ON UPDATE CASCADE,
                        FOREIGN KEY (SlotName) REFERENCES Slot(SlotName)
                          ON DELETE CASCADE ON UPDATE CASCADE,
                    -- ON DELETE SET NULL (Set Foreign Key to NULL): If an equipped item is deleted, clear the equipment slot instead of deleting the entire row
                    -- The CharacterEquipment table stores a character’s currently equipped items. If an Item is deleted,\s
                    -- we should not delete the entire CharacterEquipment record (which contains important slot data).\s
                    -- Instead, we just set ItemID to NULL, meaning that the equipment slot is now empty.
                        FOREIGN KEY (ItemID) REFERENCES Item(ItemID)
                          ON DELETE SET NULL ON UPDATE CASCADE
                    );
                                          """
            );

            cxn.createStatement().executeUpdate("""

                    CREATE TABLE CharacterInventory (
                        SlotNumber INT NOT NULL,
                        CharacterID INT NOT NULL,
                        ItemID INT,
                        Quantity INT NOT NULL,
                        PRIMARY KEY (SlotNumber, CharacterID),
                        FOREIGN KEY (CharacterID) REFERENCES Characters(CharacterID)
                          ON DELETE CASCADE ON UPDATE CASCADE,
                    -- If an item is deleted, update CharacterInventory to reflect that the slot is now empty
                        FOREIGN KEY (ItemID) REFERENCES Item(ItemID)
                          ON DELETE SET NULL ON UPDATE CASCADE
                    );
                                          """
            );
        }
    }
}
