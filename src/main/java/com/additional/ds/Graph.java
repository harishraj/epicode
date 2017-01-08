package com.additional.ds;

public class Graph {

    class GNode {

        private GNode adjacent[];
        public int adjacentCount;
        private String vertex;
        public State state;

        public GNode(String vertex, int adjacentLength) {
            this.vertex = vertex;
            adjacentCount = 0;
            adjacent = new GNode[adjacentLength];
        }

        public void addAdjacent(GNode x) {
            if (adjacentCount < adjacent.length) {
                this.adjacent[adjacentCount] = x;
                adjacentCount++;
            } else {
                System.out.print("No more adjacent can be added");
            }
        }

        public GNode[] getAdjacent() {
            return adjacent;
        }
        public String getVertex() {
            return vertex;
        }
    }

    public enum State {
        Unvisited, Visited, Visiting;
    }

}
