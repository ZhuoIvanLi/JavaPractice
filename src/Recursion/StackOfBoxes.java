package Recursion;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 8.13 Stack of Boxes: a stack of n boxes, with width w1, height h1, depth d1. Larger boxes at bottom. compute the height
 * of tallest possible stack
 */
public class StackOfBoxes {
    class BoxComparator implements Comparator<Box> {
        @Override
        public int compare(Box x, Box y) {
            return y.height - x.height;
        }
    }

    int createStack(ArrayList<Box> boxes){
        Collections.sort(boxes, new BoxComparator()); // sort the boxes
        int maxHeight = 0;
        int[] m = new int[boxes.size()];
        for(int i = 0; i < boxes.size(); i++) {
            int height = createStack(boxes, i, m);
            maxHeight = Math.max(maxHeight, height);
        }

        return maxHeight;
    }

    int createStack(ArrayList<Box> boxes, int bIndex, int[] m){
        if(bIndex < boxes.size() && m[bIndex] > 0){
            return m[bIndex];
        }

        Box bottom = boxes.get(bIndex);
        int maxHeight = 0;
        for(int i = bIndex + 1; i < boxes.size(); i++) {
            if(boxes.get(i).canBeAbove(bottom)){
                int height = createStack(boxes, i + 1, m);
                maxHeight = Math.max(maxHeight, height);
            }
        }

        maxHeight += bottom.height;
        m[bIndex] = maxHeight;
        return maxHeight;
    }

    //solution 2: check every box include or not include by using DP to find the best solution. Bagging issue.
    int createStack2(ArrayList<Box> boxes) {
        Collections.sort(boxes, new BoxComparator()); // sort the boxes
        int[] m = new int[boxes.size()];
        return createStack(boxes, null, 0, m);
    }

    int createStack2(ArrayList<Box> boxes, Box bottom, int offset, int[] m) {
        if(offset >= boxes.size()) return 0; // Base case

        // Height wit bottom
        Box newBottom = boxes.get(offset);
        int heightWithBottom = 0;
        if(bottom == null || newBottom.canBeAbove(bottom)){
            if(m[offset] == 0){
                m[offset] = createStack2(boxes, newBottom, offset + 1, m);
                m[offset] += newBottom.height;
            }
        }

        // without bottom
        int heightWithoutBottom = createStack2(boxes, bottom, offset + 1, m);

        // return better of two options
        return Math.max(heightWithoutBottom, heightWithBottom);
    }

}
