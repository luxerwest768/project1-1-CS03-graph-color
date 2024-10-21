# project1-1-CS03-graph-color
 
This project implements 2 algorithms to calculate the **chromatic number** of a graph. The chromatic number is the minimum number of colors required to color the vertices of a graph such that no two adjacent vertices share the same color.

# What is Chromatic Number?
The chromatic number of a graph G, denoted as χ(G), is the minimum number of colors required to color the vertices of a graph G in such a way that no two adjacent vertices share the same color. Formally, it is the smallest positive integer k for which there exists a proper vertex coloring with k colors.

- The chromatic number is an essential parameter that captures the inherent colorability of a graph.
- It provides insights into the structural properties and relationships within the graph.

## Examples:
graph:
        (1)----(2)
        / \     / \
      (3)  (4)-(5)-(6)
       |    /\  | /
      (7)(10)-(9)
       |    
      (8)
This is the adjacency matrix representing the graph:

|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |
|---|---|---|---|---|---|---|---|---|---|---|
| 0 | 0 | 1 | 1 | 1 | 0 | 0 | 0 | 0 | 0 | 0 |
| 1 | 1 | 0 | 0 | 0 | 1 | 1 | 0 | 0 | 0 | 0 |
| 2 | 1 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | 0 |
| 3 | 1 | 0 | 0 | 0 | 1 | 0 | 0 | 0 | 1 | 1 |
| 4 | 0 | 1 | 0 | 1 | 0 | 1 | 0 | 0 | 1 | 0 |
| 5 | 0 | 1 | 0 | 0 | 1 | 0 | 0 | 0 | 1 | 0 |
| 6 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 1 | 0 | 0 |
| 7 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | 0 |
| 8 | 0 | 0 | 0 | 1 | 1 | 1 | 0 | 0 | 0 | 1 |
| 9 | 0 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 1 | 0 |
    
# Algorithm to find Chromatic Number of a graph.

## 1.Using Backtracking Algorithm (exact algorithm)
### Follow the given steps

1. Translate the graph.txt into a graph matrix.
2. Create a recursive function that takes graph, current vertex, colour set and current color.
3. If the current vertex is last vertex of the graph, print the chromatic number of the graph.
4. Assign a color to a vertex from range 1 to n.
    4.1. For every assigned color, check the configuration is safe (there is no neighbor vertices that have the same colour) and recursively call the function with the next vertex and the graph.
    4.2. If any recursive function return true then break the loop and return true.
    4.3. If recursive function false, assign the current vertex with different colour and recursively call the function again.

### Examples:
![Graph Example](./images/Screenshot 2024-10-21 172130.png)
![Graph Example](./images/Screenshot 2024-10-21 172646.png)
![Graph Example](./images/Screenshot 2024-10-21 172656.png)
![Graph Example](./images/Screenshot 2024-10-21 173552.png)




