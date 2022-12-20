package ru.lazytechwork.algods.utils;

import org.jetbrains.annotations.NotNull;
import ru.lazytechwork.algods.utils.sort.TimSort;

import java.util.Comparator;

public class MSTFinder {
    public static class Edge implements Comparable<Edge> {
        int u, v, w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(@NotNull Edge edge) {
            if (w != edge.w) return w < edge.w ? -1 : 1;
            return 0;
        }
    }

    /**
     * Класс системы непересекающихся множеств
     */
    private static class DSF {
        int[] set; // номер множества
        int[] rnk; // ранг

        public DSF(int size) {
            set = new int[size];
            rnk = new int[size];
            for (int i = 0; i < size; i++)
                set[i] = i;
        }

        /**
         * Возвращает множество, которому принадлежит x
         *
         * @param x X
         * @return множество, которому принадлежит X
         */
        int set(int x) {
            return x == set[x] ? x : (set[x] = set(set[x]));
        }

        /**
         * Если u и v лежат в разных множествах, то сливаем их и возвращаем true
         *
         * @param u
         * @param v
         * @return true, если множества слиты, false иначе
         */
        boolean union(int u, int v) {
            if ((u = set(u)) == (v = set(v))) return false;
            if (rnk[u] < rnk[v]) {
                set[u] = v;
            } else {
                set[v] = u;
                if (rnk[u] == rnk[v]) rnk[u]++;
            }
            return true;
        }
    }

    public static class MinimalSpanningTree {
        ArrayList<Edge> edges;

        public int getWeight() {
            int weight = 0;
            for (int i = 0, l = edges.size(); i < l; i++)
                weight += edges.get(i).w;
            return weight;
        }

        public ArrayList<Edge> getEdges() {
            return edges;
        }

        public void addEdge(Edge e) {
            edges.add(e);
            linkEdges();
        }

        /**
         * Пытается связать дерево (отсортировать вершины в правильном порядке)
         */
        public void linkEdges() {
            TIM_SORT.sort(edges, (a, b) -> a.u == b.v ? -1 : a.v == b.u ? 1 : 0);
        }
    }

    private static final TimSort<Edge> TIM_SORT = new TimSort<>();

    /**
     * Находит минимальное остовное дерево по алгоритму Краскала
     * с использованием системы непересекающихся множеств
     *
     * @param edges Массив связей
     * @return минимальное остовное дерево
     */
    MinimalSpanningTree mstKruskal(ArrayList<Edge> edges) {
        DSF dsf = new DSF(edges.size()); // СНМ
        MinimalSpanningTree mst = new MinimalSpanningTree();
        TIM_SORT.sort(edges, Edge::compareTo); // Сортируем ребра
        for (int i = 0; i < edges.size(); i++) { // Перебираем ребра в порядке неубывания
            Edge e = edges.get(i);
            if (dsf.union(e.u, e.v)) // Если ребра принадлежат разным компонентам
                mst.addEdge(e); // Добавляем ребро в дерево
        }
        return mst;
    }
}
