package com.algorithm.graph;

import java.util.Comparator;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * <p>
 * 最短路径—Dijkstra算法:
 * <p>
 * 算法思想：设G=(V,E)是一个带权有向图，把图中顶点集合V分成两组，
 * 第一组为已求出最短路径的顶点集合（用S表示，初始时S中只有一个源点，以后每求得一条最短路径 , 就将加入到集合S中，直到全部顶点都加入到S中，算法就结束了），
 * 第二组为其余未确定最短路径的顶点集合（用U表示），按最短路径长度的递增次序依次把第二组的顶点加入S中。
 * 在加入的过程中，总保持从源点v到S中各顶点的最短路径长度不大于从源点v到U中任何顶点的最短路径长度。
 * 此外，每个顶点对应一个距离，S中的顶点的距离就是从v到此顶点的最短路径长度，U中的顶点的距离，是从v到此顶点只包括S中的顶点为中间顶点的当前最短路径长度。
 *
 * @author wangjiayin@baidu.com
 * @since 2017/9/8
 */
public class Dijkstra {

    public static int max = Integer.MAX_VALUE;
    public static String start;
    public static List<String> points;
    public static List<Edge> graph;
    public static List<Edge> paths;
    public static List<String> groupA;
    public static List<String> groupB;

    public static void init() {
        start = "A";
        points = Lists.newArrayList("A", "B", "C", "D", "E", "F");
        // 图
        graph = Lists.newArrayList();
        graph.add(new Edge("A", "B", 1));
        graph.add(new Edge("A", "C", 12));
        graph.add(new Edge("B", "C", 9));
        graph.add(new Edge("B", "D", 3));
        graph.add(new Edge("C", "E", 5));
        graph.add(new Edge("D", "C", 4));
        graph.add(new Edge("D", "E", 13));
        graph.add(new Edge("D", "F", 15));
        graph.add(new Edge("E", "F", 4));
        // A到其他各点的初始距离
        paths = Lists.newArrayList();
        paths.add(new Edge("A", "A", 0));
        paths.add(new Edge("A", "B", 1));
        paths.add(new Edge("A", "C", 12));
        paths.add(new Edge("A", "D", max));
        paths.add(new Edge("A", "E", max));
        paths.add(new Edge("A", "F", max));
        // 已经计算完毕的点集合
        groupA = Lists.newArrayList();
        // 待计算完毕的点集合
        groupB = points;
    }

    public static String chooseNextPoint() {
        if (groupB.size() == 0) {
            return null;
        }
        return paths.stream()
                .filter(edge -> !groupA.contains(edge.end))
                .min(Comparator.comparingInt(o -> o.dist)).get().end;
    }

    public static void main(String[] args) {

        init();
        String current = chooseNextPoint();
        while (current != null) {

            // 由于current的加入，是否能让start到各点的距离变短？
            final String finalCurrent = current;
            int distFromStartToCurrent =
                    paths.stream().filter(e -> e.end.equals(finalCurrent)).findFirst().get().dist;

            // 以current为基准
            graph.stream()
                    // 过滤current的出边，且到达节点不在计算过的点集合（groupA）内
                    .filter(edge -> edge.start.equals(finalCurrent))
                    .filter(edge -> !groupA.contains(edge.end))
                    .forEach(edge -> {

                        String p = edge.end;
                        int distFromCurrentToP = edge.dist;
                        Edge pathFromStartToP = paths.stream().filter(e -> e.end.equals(p)).findFirst().get();

                        // 如果由于current的加入，使得start到p的距离变短，那么更新path
                        if (distFromStartToCurrent + distFromCurrentToP < pathFromStartToP.dist) {
                            pathFromStartToP.dist = distFromStartToCurrent + distFromCurrentToP;
                        }

                    });

            groupA.add(current);
            groupB.remove(current);
            current = chooseNextPoint();
        }

        paths.forEach(System.out::println);

    }

}

class Edge {
    public String start;
    public String end;
    public int dist;

    public Edge(String start, String end, int dist) {
        this.start = start;
        this.end = end;
        this.dist = dist;
    }

    @Override
    public String toString() {
        return "{" + "start='" + start + '\'' + ", end='" + end + '\'' + ", dist=" + dist + '}';
    }
}