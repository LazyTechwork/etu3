package me.lazytechwork.algods.utils.sort;


import me.lazytechwork.algods.utils.ArrayList;
import me.lazytechwork.algods.utils.Stack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;

public class TimSort<T> {
    private static final int GALLOP_SIZE = 7;
    private final InsertionSort<T> INSERTION_SORT_INSTANCE = new InsertionSort<>();

    @Contract(mutates = "param1")
    public void sort(@NotNull ArrayList<T> array, Comparator<T> comparator) {
        Stack<Run> runs = new Stack<>();

        int minRun = getMinRun(array.size());
        int currentIndex = 0;

        Run run;

        while ((run = nextRun(array, currentIndex, minRun, comparator)) != null) {
            runs.push(run);
            currentIndex = run.currentIndex;
        }

        while (runs.getCount() >= 3) {
            Run x = runs.pop();
            Run y = runs.pop();
            Run z = runs.pop();

            if (z.size > x.size + y.size && y.size > x.size) {
                runs.push(z);
                runs.push(y);
                runs.push(x);
                break;
            }

            if (z.size >= x.size + y.size) {
                Run newRun;

                if (z.size > x.size) {
                    runs.push(x);
                    newRun = merge(array, z.startIndex, z.size, y.startIndex, y.size, comparator);
                } else {
                    runs.push(z);
                    newRun = merge(array, x.startIndex, x.size, y.startIndex, y.size, comparator);
                }

                runs.push(newRun);
            } else {
                Run newRun = merge(array, y.startIndex, y.size, x.startIndex, x.size, comparator);
                runs.push(newRun);
                runs.push(z);
            }
        }

        while (runs.getCount() >= 2) {
            Run x = runs.pop();
            Run y = runs.pop();

            Run newRun = merge(array, x.startIndex, x.size, y.startIndex, y.size, comparator);

            runs.push(newRun);
        }
    }

    @Contract(mutates = "param1")
    private void reverseArrayList(@NotNull ArrayList<T> array, int fromIndex, int toIndex) {
        int n = (toIndex - fromIndex) / 2;
        for (int i = 0; i <= n; ++i) {
            T temp = array.get(toIndex - i);
            array.set(toIndex - i, array.get(toIndex + i));
            array.set(toIndex + i, temp);
        }
    }

    @Contract(mutates = "param1")
    private Run merge(@NotNull ArrayList<T> array, int fromIndex1, int size1, int fromIndex2, int size2, Comparator<T> comparator) {
        if (fromIndex1 > fromIndex2) {
            int t = fromIndex1;
            fromIndex1 = fromIndex2;
            fromIndex2 = t;
            t = size1;
            size1 = size2;
            size2 = t;
        }

        ArrayList<T> tempArray = new ArrayList<>(size1);
        for (int i = fromIndex1; i < size1 + fromIndex1; i++)
            tempArray.add(array.get(i));

        int current1 = 0;
        int current2 = fromIndex2;

        boolean lastFromLeft = false;
        int count = 0;

        int until = fromIndex2 + size2 - 1;

        for (int currentArr = fromIndex1; currentArr <= until; ++currentArr) {
            if (current1 == size1) {
                if (lastFromLeft) {
                    lastFromLeft = false;
                    count = 0;
                }

                array.set(currentArr, array.get(current2));
                ++current2;
            } else if (current2 == fromIndex2 + size2) {
                if (!lastFromLeft) {
                    lastFromLeft = true;
                    count = 0;
                }

                array.set(currentArr, tempArray.get(current1));
                ++current1;
            } else {
                if (comparator.compare(tempArray.get(current1), array.get(current2)) > 0) {
                    if (lastFromLeft) {
                        lastFromLeft = false;
                        count = 0;
                    }

                    array.set(currentArr, array.get(current2++));
                } else {
                    if (!lastFromLeft) {
                        lastFromLeft = true;
                        count = 0;
                    }

                    array.set(currentArr, tempArray.get(current1++));
                }
            }
            count++;

            if (count != GALLOP_SIZE) continue;

            currentArr++;

            if (lastFromLeft) {
                if (current2 == fromIndex2 + size2) {
                    count = 0;
                    currentArr--;
                    continue;
                }

                int[] gallopResult = gallop(array, tempArray, currentArr, current1, array.get(current2), comparator);
                currentArr = gallopResult[0];
                current1 = gallopResult[1];
            } else {
                if (current1 == size1) {
                    count = 0;
                    currentArr--;
                    continue;
                }

                int[] gallopResult = gallop(array, array, currentArr, current2, tempArray.get(current1), comparator);
                currentArr = gallopResult[0];
                current2 = gallopResult[1];
            }
            lastFromLeft = !lastFromLeft;
            count = 0;
        }

        return new Run(fromIndex1, size1 + size2, 0);
    }

    @Contract(mutates = "param1")
    private int[] gallop(ArrayList<T> array, ArrayList<T> source, int currentArr, int currentSource, T currentOpposite, Comparator<T> comparator) {
        int startIndex = currentSource;

        int i = 0;
        while (currentSource < source.size() && comparator.compare(source.get(currentSource), currentOpposite) < 0) {
            i++;
            currentSource += 1 << i;
        }

        if (i == 0) {
            currentArr--;
            return new int[]{currentArr, currentSource};
        }

        currentSource -= 1 << i;

        for (int j = 0; j < currentSource - startIndex + 1; j++)
            array.set(currentArr + j, source.get(startIndex + j));

        currentArr += currentSource - startIndex;
        currentSource++;

        return new int[]{currentArr, currentSource};
    }

    @Contract(mutates = "param1")
    private @Nullable Run nextRun(@NotNull ArrayList<T> array, int currentIndex, int minRun, Comparator<T> comparator) {
        int diff = array.size() - currentIndex;

        if (diff < 0 || diff == 0)
            return null;

        if (diff == 1)
            return new Run(currentIndex, 1, currentIndex);

        int startIndex = currentIndex;

        T el1 = array.get(currentIndex++);
        T el2 = array.get(currentIndex++);

        int size = 2;

        boolean invert = comparator.compare(el1, el2) < 0;

        if (invert) {
            for (
                    T prev = el2;
                    currentIndex < array.size()
                            && comparator.compare(prev, array.get(currentIndex)) < 0;
                    prev = array.get(currentIndex++), size++
            )
                ;
            reverseArrayList(array, startIndex, currentIndex - 1);
        } else
            for (
                    T prev = el2;
                    currentIndex < array.size()
                            && comparator.compare(prev, array.get(currentIndex)) >= 0;
                    prev = array.get(currentIndex++), size++
            )
                ;


        if (size < minRun) {
            while (currentIndex++ < array.size() && size++ < minRun) ;

            if (currentIndex - 1 == array.size())
                size++;
            size--;
        }

        currentIndex = startIndex + size;

        INSERTION_SORT_INSTANCE.sort(array, startIndex, startIndex + size - 1, comparator);
        return new Run(startIndex, size, currentIndex);
    }

    private static class Run {
        public int startIndex;
        public int size;
        public int currentIndex;

        public Run(int startIndex, int size, int currentIndex) {
            this.startIndex = startIndex;
            this.size = size;
            this.currentIndex = currentIndex;
        }
    }

    private static int getMinRun(int n) {
        int r = 0;
        while (n >= 64) {
            r |= n & 1;
            n >>= 1;
        }
        return n + r;
    }
}
