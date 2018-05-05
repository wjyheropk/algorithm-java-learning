package com.algorithm.graph;

/**
 * <p>
 * Floyd算法的基本思想如下：
 * <p>
 * 从任意节点A到任意节点B的最短路径不外乎2种可能，1是直接从A到B，2是从A经过若干个节点X到B。
 * 所以，我们假设Dis(AB)为节点A到节点B的最短路径的距离，对于每一个节点X，我们检查Dis(AX) + Dis(XB) < Dis(AB)是否成立，
 * 如果成立，证明从A到X再到B的路径比A直接到B的路径短，我们便设置Dis(AB) = Dis(AX) + Dis(XB)，
 * 这样一来，当我们遍历完所有节点X，Dis(AB)中记录的便是A到B的最短路径的距离。
 *
 * @author wangjiayin
 * @since 2017/9/9
 */
public class Floyd {

    /**
     * floyd核心方法
     *
     * @param G 邻接矩阵
     * @param n 节点数
     *
     * @return 计算后的各点之间的最小距离矩阵
     */
    public int[][] floyd(int[][] G, int n) {

        // 初始化距离矩阵
        int[][] Dis = new int[n][n];
        for (int q = 0; q < n; q++) {
            for (int w = 0; w < n; w++) {
                Dis[q][w] = G[q][w];
            }
        }

        // 这里很重要，k层循环要放在最外面
        // 如果把k放在最内层，那么结果将是不正确的，因为这样便过早的把i到j的最短路径确定下来了，而当后面存在更短的路径时，已经不再会更新了。
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (Dis[i][j] > Dis[i][k] + Dis[k][j]) {
                        Dis[i][j] = Dis[i][k] + Dis[k][j];
                    }
                }
            }
        }

        return Dis;

    }

}
