package logico;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DisjointSet {
    private final Map<Location, Location> parent;
    private final Map<Location, Integer> rank;

    public DisjointSet(Set<Location> locations) {
        this.parent = new HashMap<>();
        this.rank = new HashMap<>();
        for (Location location : locations) {
            parent.put(location, location);
            rank.put(location, 0);
        }
    }

    public Location find(Location location) {
        if (parent.get(location) != location) {
            parent.put(location, find(parent.get(location)));
        }
        return parent.get(location);
    }

    public boolean connected(Location location1, Location location2) {
        return find(location1) == find(location2);
    }

    public void union(Location location1, Location location2) {
        Location root1 = find(location1);
        Location root2 = find(location2);

        if (root1 == root2) {
            return;
        }

        if (rank.get(root1) < rank.get(root2)) {
            parent.put(root1, root2);
        } else if (rank.get(root1) > rank.get(root2)) {
            parent.put(root2, root1);
        } else {
            parent.put(root2, root1);
            rank.put(root1, rank.get(root1) + 1);
        }
    }
}
