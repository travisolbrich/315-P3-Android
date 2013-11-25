/**
 * 
 */
package olbrich.csce315.birdbuddy.models;

import java.util.List;


/**
 * @author Travis
 * Represents a bird
 */
public class Bird {

    private Long identifier;
	
	private boolean favorite;
	
	private String name;
	
	private String description;
	
	private List<Season> migrations;

    public Bird() {}

    public Bird(Long identifier) {
        this.identifier = identifier;
    }

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public List<Season> getMigrations() {
		return migrations;
	}

	public void setMigrations(List<Season> migrations) {
		this.migrations = migrations;
	}

}
