import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.LinkedList;
class Astar{
    //Implement wall skips later
    public static int astar(int[][] map){
        int[][] fScore = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++){
            Arrays.fill(fScore[i], 500);
        }
        int[][] hashes = new int[map.length][map[0].length];
        int count = 0;
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                hashes[i][j] = count++;
            }
        }
        class Node implements Comparable<Node>{
            public int x;
            public int y;
            public Node (int x, int y){
                this.x = x;
                this.y = y;
            }
            public int fScore(){
                return fScore[y][x];
            }
            public int compareTo(Node other){
                return this.fScore() - other.fScore();
            }
            public boolean equals(Object obj){
                Node other = (Node) obj;
                return this.x == other.x && this.y == other.y;
            }
            public int hashCode(){
                return hashes[y][x];
            }
            public int h(){
                return map.length + map[0].length - this.x - this.y - 2;
            }
            public String toString() {
                return "(" + x + "," + y + ")"; 
            }
        }
        PriorityQueue<Node> openSet = new PriorityQueue<Node>();
        openSet.add(new Node(0, 0));
        HashMap<Node, Node> cameFrom = new HashMap<Node, Node>();
        int[][] gScore = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++){
            Arrays.fill(gScore[i], 500);
        }
        gScore[0][0] = 0;
        fScore[0][0] = openSet.peek().h();
        while(openSet.size() > 0){
            Node current = openSet.poll();
            if (current.x == map[0].length - 1 && current.y == map.length - 1){
                //rebuildPath
                LinkedList<Node> path = new LinkedList<Node>();
                path.addFirst(current);
                while(cameFrom.containsKey(current)){
                    current = cameFrom.get(current);
                    path.addFirst(current); 
                }
                for (int i = 0; i < path.size(); i++){
                    //System.out.println("(" + path.get(i).x + "," + path.get(i).y + ")");
                }
                //output
                return path.size();
            }
            int tentativeGscore = gScore[current.y][current.x] + 1;
            if (current.x + 1 < map[0].length && map[current.y][current.x + 1] == 0 && tentativeGscore < gScore[current.y][current.x + 1]){
                Node neighbor = new Node(current.x + 1, current.y);
                cameFrom.put(neighbor, current);
                gScore[current.y][current.x + 1] = tentativeGscore;
                fScore[current.y][current.x + 1] = gScore[current.y][current.x + 1] + neighbor.h();
                if (!openSet.contains(neighbor)) openSet.add(neighbor);
            }/*else if (current.x + 1 < map[0].length && !current.removed && tentativeGscore < gScore[current.y][current.x + 1]){
                Node neighbor = new Node(current.x + 1, current.y, true);
                cameFrom.put(neighbor, current);
                gScore[current.y][current.x + 1] = tentativeGscore;
                fScore[current.y][current.x + 1] = gScore[current.y][current.x + 1] + neighbor.h();
                if (!openSet.contains(neighbor)) openSet.add(neighbor);
            } */  
            if (current.y + 1 < map.length && map[current.y + 1][current.x] == 0 && tentativeGscore < gScore[current.y + 1][current.x]) {
                Node neighbor = new Node(current.x, current.y + 1);
                cameFrom.put(neighbor, current);
                gScore[current.y + 1][current.x] = tentativeGscore;
                fScore[current.y + 1][current.x] = gScore[current.y + 1][current.x] + neighbor.h();
                if (!openSet.contains(neighbor)) openSet.add(neighbor);
            }/*else if (current.y + 1 < map.length && !current.removed && tentativeGscore < gScore[current.y + 1][current.x]){
                Node neighbor = new Node(current.x, current.y + 1, true);
                cameFrom.put(neighbor, current);
                gScore[current.y + 1][current.x] = tentativeGscore;
                fScore[current.y + 1][current.x] = gScore[current.y + 1][current.x] + neighbor.h();
                if (!openSet.contains(neighbor)) openSet.add(neighbor);
            }*/
            if(current.y - 1 >= 0 && map[current.y - 1][current.x] == 0 && tentativeGscore < gScore[current.y - 1][current.x]){
                Node neighbor = new Node(current.x, current.y - 1);
                cameFrom.put(neighbor, current);
                gScore[current.y - 1][current.x] = tentativeGscore;
                fScore[current.y - 1][current.x] = gScore[current.y - 1][current.x] + neighbor.h();
                if (!openSet.contains(neighbor)) openSet.add(neighbor);
            }/*else if (current.y - 1 >= 0 && !current.removed && tentativeGscore < gScore[current.y - 1][current.x]){
                Node neighbor = new Node(current.x, current.y - 1, true);
                cameFrom.put(neighbor, current);
                gScore[current.y - 1][current.x] = tentativeGscore;
                fScore[current.y - 1][current.x] = gScore[current.y - 1][current.x] + neighbor.h();
                if (!openSet.contains(neighbor)) openSet.add(neighbor);
            }*/
            if(current.x - 1 >= 0 && map[current.y][current.x - 1] == 0 && tentativeGscore < gScore[current.y][current.x - 1]){
                Node neighbor = new Node(current.x - 1, current.y);
                cameFrom.put(neighbor, current);
                gScore[current.y][current.x - 1] = tentativeGscore;
                fScore[current.y][current.x - 1] = gScore[current.y][current.x - 1] + neighbor.h();
                if (!openSet.contains(neighbor)) openSet.add(neighbor);
            }/*else if (current.x - 1 >= 0 && !current.removed && tentativeGscore < gScore[current.y][current.x - 1]){
                Node neighbor = new Node(current.x - 1, current.y, true);
                cameFrom.put(neighbor, current);
                gScore[current.y][current.x - 1] = tentativeGscore;
                fScore[current.y][current.x - 1] = gScore[current.y][current.x - 1] + neighbor.h();
                if (!openSet.contains(neighbor)) openSet.add(neighbor);
            }*/
        }
        return 10000;
    }
    public static int[][] copy(int[][] map){
        int[][] ret = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                ret[i][j] = map[i][j];
            }
        }
        return ret;
    }
    public static int solution(int[][] map){
        int min = 10000;
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                if (map[i][j] == 1){
                    int[][] copy = copy(map);
                    copy[i][j] = 0;
                    min = Math.min(min, astar(copy));
                }
            }
        }
        return min;

    }
    public static void main(String[] args){
        int[][] input = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //0
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //5
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //10
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //15
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}  //19
      //x  //0            //5            //10           //15        //19 
        }; 
        int[][] input2 = {
            {0, 1, 1, 0}, 
            {0, 0, 0, 1}, 
            {1, 1, 0, 0}, 
            {1, 1, 1, 0}}; 
        int[][] input3 = {
            {0, 0, 0, 0, 0, 0}, 
            {1, 1, 1, 1, 1, 0}, 
            {0, 0, 0, 0, 0, 0}, 
            {0, 1, 1, 1, 1, 1}, 
            {0, 1, 1, 1, 1, 1}, 
            {0, 0, 0, 0, 0, 0}};   
        int[][] input4 = {
                {0, 0, 0, 0, 0, 0}, 
                {1, 1, 1, 1, 1, 0}, 
                {0, 0, 0, 0, 0, 0}, 
                {0, 0, 0, 0, 0, 0}};   
        System.out.println(solution(input));
    }
}