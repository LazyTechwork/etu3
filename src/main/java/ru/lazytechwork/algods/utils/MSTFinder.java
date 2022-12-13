package ru.lazytechwork.algods.utils;

import org.jetbrains.annotations.NotNull;
import ru.lazytechwork.algods.utils.sort.TimSort;

public class MSTFinder {
    private static final TimSort<Edge> TIM_SORT = new TimSort<>();

    int mstKruskal(ArrayList<Edge> edges) {
        DSF dsf = new DSF(edges.size()); // СНМ
        TIM_SORT.sort(edges, Edge::compareTo); // Сортируем ребра
        int ret = 0; // результат
        for (int i = 0; i < edges.size(); i++) { // перебираем ребра в порядке возрастания
            Edge e = edges.get(i);
            if (dsf.union(e.u, e.v)) // если ребра принадлежат разным компонентам
                ret += e.w; // добавляем вес ребра к стоимости MST
        }
        return ret;
    }

    private static class Edge implements Comparable<Edge> {
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
         * @param x
         * @return
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
            if ((u = set(u)) == (v = set(v)))
                return false;
            if (rnk[u] < rnk[v]) {
                set[u] = v;
            } else {
                set[v] = u;
                if (rnk[u] == rnk[v])
                    rnk[u]++;
            }
            return true;
        }
    }
}
