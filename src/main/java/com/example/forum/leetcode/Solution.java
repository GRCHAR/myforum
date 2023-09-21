package com.example.forum.leetcode;

import com.example.forum.config.InterceptorConfig;

import java.util.*;
import java.util.stream.Collectors;

/** @author genghaoran */
public class Solution {


   public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
   }

  public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
    List<Boolean> ans = new ArrayList<>();
    Map<Integer, List<Integer>> coursesMap = new HashMap<>(numCourses);
    for (int[] prerequisite : prerequisites) {
      if (!coursesMap.containsKey(prerequisite[0])) {
        List<Integer> nextCourses = new ArrayList<>();
        nextCourses.add(prerequisite[1]);
        coursesMap.put(prerequisite[0], nextCourses);
      } else {
        coursesMap.get(prerequisite[0]).add(prerequisite[1]);
      }
    }
    for (int[] query : queries) {

      if (coursesMap.get(query[0]).contains(query[1])) {
        ans.add(true);
      } else {

      }
    }
    return ans;
  }

  public boolean checkValidGrid(int[][] grid) {
    int gridCount = grid.length * grid[0].length;
    int[][] steps = new int[gridCount][2];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        steps[grid[i][j]] = new int[] {i, j};
      }
    }
    if (!Arrays.equals(
        steps[0],
        new int[] {
          0, 0,
        })) {
      return false;
    }
    for (int i = 1; i < gridCount; i++) {
      if ((Math.abs(steps[i][0] - steps[i - 1][0]) == 2
              && Math.abs(steps[i][1] - steps[i - 1][1]) == 1)
          || (Math.abs(steps[i][0] - steps[i - 1][0]) == 1
              && Math.abs(steps[i][1] - steps[i - 1][1]) == 2)) {
        continue;
      } else {
        return false;
      }
    }
    return true;
  }

  public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
    Set<Integer> queenPos = new HashSet<Integer>();
    for (int[] queen : queens) {
      int x = queen[0], y = queen[1];
      queenPos.add(x * 8 + y);
    }

    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    for (int dx = -1; dx <= 1; ++dx) {
      for (int dy = -1; dy <= 1; ++dy) {
        if (dx == 0 && dy == 0) {
          continue;
        }
        int kx = king[0] + dx, ky = king[1] + dy;
        while (kx >= 0 && kx < 8 && ky >= 0 && ky < 8) {
          int pos = kx * 8 + ky;
          if (queenPos.contains(pos)) {
            List<Integer> posList = new ArrayList<Integer>();
            posList.add(kx);
            posList.add(ky);
            ans.add(posList);
            break;
          }
          kx += dx;
          ky += dy;
        }
      }
    }
    return ans;
  }

  public int giveGem(int[] gem, int[][] operations) {
    for (int[] operation : operations) {
      int x = operation[0], y = operation[1];
      int changeNumber = gem[x] / 2;
      gem[x] -= changeNumber;
      gem[y] += changeNumber;
    }
    int minValue = gem[0];
    int maxValue = gem[0];
    for (int gemVal : gem) {
      if (gemVal > maxValue) {
        maxValue = gemVal;
      }
      if (gemVal < minValue) {
        minValue = gemVal;
      }
    }
    return maxValue - minValue;
  }

  public int giveGemStream(int[] gem, int[][] operations) {
      Arrays.stream(operations).forEach(operation -> {
          int x = operation[0], y = operation[1];
          int changeNumber = gem[x] / 2;
          gem[x] -= changeNumber;
          gem[y] += changeNumber;
      });
      return Arrays.stream(gem).max().getAsInt() - Arrays.stream(gem).min().getAsInt();
  }

  public int rob(TreeNode root) {
     List<Integer> dp = new ArrayList<>();
     dp.add(root.val);
     dp.add(Math.max(root.val, root.left.val+root.right.val));
     dfs(root, dp);
     return dp.get(dp.size()-1);
  }

  public void dfs(TreeNode treeNode, List<Integer> dp) {
    dp.add(Math.max(dp.get(dp.size()-1), dp.get(dp.size()-2) + treeNode.val));
     if (treeNode.left != null) {
       dfs(treeNode.left, dp);
     }
     if (treeNode.right != null) {
       dfs(treeNode.right, dp);
     }
  }

  public int totalNodesValue(List<TreeNode> treeNodes) {
     int value = 0;
     for (TreeNode treeNode : treeNodes) {
       value += treeNode.val;
     }
     return value;
  }

    public int minCapability(int[] nums, int k) {
       int lower = Arrays.stream(nums).min().getAsInt();
       int upper = Arrays.stream(nums).max().getAsInt();
       int middle = 0;
       while (lower <= upper) {
           middle = (upper + lower) / 2;
           int count = 0;
           boolean visited = false;
           for (int num : nums) {
               if (middle >= num && !visited) {
                   count++;
                   visited = true;
               } else {
                   visited = false;
               }
           }
           if (count >= k) {
               upper = middle - 1;
           } else {
               lower = middle + 1;
           }
       }
       return lower;
    }

    public int minCount(int[] coins) {
        int count = 0;
        for (int coin : coins) {
            count += coin / 2;
            if (coin % 2 > 0) {
                count += 1;
            }
        }
        return count;
    }


}
