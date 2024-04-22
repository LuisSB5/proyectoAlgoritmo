package logico;

import java.io.Serializable;

public class Location implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;

    public Location(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}