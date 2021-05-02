package Microsoft;

import java.util.*;

/**
 * Kill Process: Description
 * In this problem, each process has a unique PID (process id) and PPID (parent process id).Each process only has one parent process,
 * but may have one or more children processes. This is just like a tree structure. Only one process has PPID that is 0,
 * which means this process has no parent process. All the PIDs will be distinct positive integers. We use two list of
 * integers to represent a list of processes, where the first list contains PID for each process and the second list contains
 * the corresponding PPID. Now given the two lists, and a PID representing a process you want to kill,
 * return a list of PIDs of processes that will be killed in the end. You should assume that when a process is killed,
 * all its children processes will be killed. No order is required for the final answer.
 *
 * The given kill id is guaranteed to be one of the given PIDs.
 * There is at least one PID in the list.
 *
 * Example 1:
 * Input: PID = [1, 3, 10, 5], PPID = [3, 0, 5, 3], killID = 5
 * Output: [5, 10]
 * Explanation: Kill 5 will also kill 10.
 *      3
 *    /   \
 *   1     5
 *        /
 *       10
 *
 * Example 2:
 * Input: PID = [1, 2, 3], PPID = [0, 1, 1], killID = 2
 * Output: [2]
 *
 * Solution: Use BFS and Queue to find the pid which ppid is kill and children pid one by one.
 * runtime complexity: O(n^2)
 *
 * also could create hash table to contain: hm(parent, children)
 */
public class KillProcess {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        // Write your code here
        Map<Integer, List<Integer>> hm = new HashMap<>();

        // put parent number and his children in hash table
        for (int i = 0; i < ppid.size(); i++) {
            if (!hm.containsKey(ppid.get(i))) {
                hm.put(ppid.get(i), new ArrayList<>(Arrays.asList(pid.get(i))));
            } else {
                hm.get(ppid.get(i)).add(pid.get(i));
            }
        }

        List<Integer> ans =  new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(kill);
        ans.add(kill);

        while (!q.isEmpty()) {
            int curr = q.poll();

            if (hm.containsKey(curr)) {
                List<Integer> temp = hm.get(curr);
                for (int i = 0; i < temp.size(); i++) {
                    q.add(temp.get(i));
                    ans.add(temp.get(i));
                }
            }

            // for (int i = 0; i < ppid.size(); i++) {
            //     if (ppid.get(i) == curr) {
            //         q.add(pid.get(i));
            //         ans.add(pid.get(i));
            //     }
            // }
        }

        return ans;
    }
}
