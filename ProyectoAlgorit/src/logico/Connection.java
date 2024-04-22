package logico;

import java.io.Serializable;

//Clase para representar una conexión entre ubicaciones
public class Connection implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Location start;
    Location end;
    int distance;
    int time;

    public Connection(Location start, Location end, int distance, int time) {
        this.start = start;
        this.end = end;
        this.distance = distance;
        this.time = time;
    }

	public Location getStart() {
		return start;
	}

	public void setStart(Location start) {
		this.start = start;
	}

	public Location getEnd() {
		return end;
	}

	public void setEnd(Location end) {
		this.end = end;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
    
}
