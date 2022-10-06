package de.bielefeld.umweltamt.aui.module.objektpanels;

/**
 * Interface for object panels that have their own models that can be saved to
 * the database.
 */
public interface ObjectPanel {

    /**
     * Get panel name/title
     * @return Name as String
     */
    public String getName();

    /**
     * Save the panel data to the database.
     * @return True if saved successfully, else false
     */
    public boolean savePanelData();
}
