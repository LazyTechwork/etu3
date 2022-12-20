package ru.lazytechwork.algods.utils;

import ru.lazytechwork.algods.utils.sort.InsertionSort;
import ru.lazytechwork.algods.utils.sort.TimSort;

import java.util.Comparator;

public class MSTFinder {

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
        ArrayList<Edge> edges = new ArrayList<>();

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
            INSERTION_SORT.sort(edges, (a, b) -> a.u == b.v ? -1 : a.v == b.u ? 1 : 0);
        }
    }

    private static final TimSort<Edge> TIM_SORT = new TimSort<>();
    private static final InsertionSort<Edge> INSERTION_SORT = new InsertionSort<>();

    /**
     * Находит минимальное остовное дерево по алгоритму Краскала
     * с использованием системы непересекающихся множеств
     *
     * @param edges Массив связей
     * @return минимальное остовное дерево
     */
    public static MinimalSpanningTree mstKruskal(ArrayList<Edge> edges) {
        DSF dsf = new DSF(edges.size()); // СНМ
        MinimalSpanningTree mst = new MinimalSpanningTree();
        INSERTION_SORT.sort(edges, Edge::compareTo); // Сортируем ребра
        for (int i = 0; i < edges.size(); i++) { // Перебираем ребра в порядке неубывания
            Edge e = edges.get(i);
            if (dsf.union(e.u, e.v)) // Если ребра принадлежат разным компонентам
                mst.addEdge(e); // Добавляем ребро в дерево
        }
        return mst;
    }

    /**
     * Преобразование полученного результата в формат,
     * необходимый для курсовой работы
     *
     * @param mst   минимальное остовное дерево
     * @return необходимый вывод для курсовой
     */
    public static String kruskalResult(MinimalSpanningTree mst) {
        ArrayList<Edge> edges = mst.getEdges();
        INSERTION_SORT.sort(edges, Comparator.comparing(e -> e.uKey + " " + e.vKey));
        String[] resultList = new String[edges.size() + 1];
        for (int i = 0, l = edges.size(); i < l; i++) {
            Edge e = edges.get(i);
            resultList[i] = e.uKey + " " + e.vKey;
        }
        resultList[edges.size()] = Integer.toString(mst.getWeight());
        return String.join(System.lineSeparator(), resultList);
    }
}
