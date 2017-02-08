package hw3.hash;

import java.util.HashSet;
import java.util.Set;

public class HashTableVisualizer {

    public static void main(String[] args) {
        /* scale: StdDraw scale
           N:     number of items
           M:     number of buckets */

        double scale = 0.5;
        int N = 200;
        int M = 10;

        HashTableDrawingUtility.setScale(scale);
        Set<Oomage> oomies = new HashSet<Oomage>();
        for (int i = 0; i < N; i += 1) {
            oomies.add(ComplexOomage.randomComplexOomage());
        }
        visualize(oomies, M, scale);
    }

    public static void visualize(Set<Oomage> set, int M, double scale) {
        HashTableDrawingUtility.drawLabels(M);

        /* TODO: Create a visualization of the given hash table. Use
           du.xCoord and du.yCoord to figure out where to draw
           Oomages.
         */

        /* When done with visualizer, be sure to try 
           scale = 0.5, N = 2000, M = 100. */
        int[] bucketPos = new int[M];
        for (int i = 0; i < bucketPos.length; i++) {
            bucketPos[i] = 0;
        }
        for (Oomage o : set) {
            int hashcode = (o.hashCode() & 0x7FFFFFFF) % M;
            o.draw(HashTableDrawingUtility.xCoord(bucketPos[hashcode]), HashTableDrawingUtility.yCoord(hashcode, M), scale);
            bucketPos[hashcode]++;
        }
    }
} 
