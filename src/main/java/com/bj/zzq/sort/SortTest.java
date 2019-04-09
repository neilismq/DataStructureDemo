package com.bj.zzq.sort;


import java.sql.*;
import java.util.Date;
import java.util.Random;

public class SortTest {
    private static Connection conn;

    public static void main(String[] args) {
        long startAll = System.nanoTime();
        InsertSort insertSort = new InsertSort();
        MergeSort mergeSort = new MergeSort();
        ShellSort shellSort = new ShellSort();
        QuickSort quickSort = new QuickSort();
        CardinalSort cardinalSort = new CardinalSort();
        cardinalSort.setCardinalNum(10);
        QuickSortWithNoSmall3 quickSortWithNoSmall3 = new QuickSortWithNoSmall3();
        QuickSortWithSmall3Hand quickSortWithSmall3Hand = new QuickSortWithSmall3Hand();
        QuickSortWithSmall9Insert quickSortWithSmall9Insert = new QuickSortWithSmall9Insert();

        Random random = new Random();
        for (int i = 10; i < 100000000; i = i * 10) {
            for (int k = 0; k < 100; k++) {
                Integer[] origin = new Integer[i];
                long createDataStart = System.nanoTime();
                for (int j = 0; j < i; j++) {
                    origin[j] = random.nextInt(Integer.MAX_VALUE);
                }
                long createDataEnd = System.nanoTime();
                System.out.println("造" + i + "个数据共花费" + (createDataEnd - createDataStart) + "纳秒");
                doSomething(cardinalSort, 8, origin);
                doSomething(insertSort, 2, origin);
                doSomething(shellSort, 3, origin);
                doSomething(mergeSort, 1, origin);
                doSomething(quickSort, 7, origin);
                doSomething(quickSortWithNoSmall3, 6, origin);
                doSomething(quickSortWithSmall3Hand, 4, origin);
                doSomething(quickSortWithSmall9Insert, 5, origin);
            }
        }

        long endAll = System.nanoTime();
        System.out.println("共花费" + (endAll - startAll) + "纳秒");

    }

    private static void doSomething(Sort sort, int algorithmType, Integer[] origin) {
        try {
            sort.setTarget(copyArray(origin));
            long start = System.nanoTime();
            sort.sort();
            long end = System.nanoTime();
            long cost = end - start;
            Connection conn = getConnection();
            String sql = "insert into sort_example(data_count,algorithm_id,cost,create_time) values(?,?,?,?)";
            PreparedStatement prepareStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setInt(1, origin.length);
            prepareStatement.setInt(2, algorithmType);
            prepareStatement.setString(3, cost + "");
            prepareStatement.setTimestamp(4, new Timestamp(new Date().getTime()));
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8";
                String username = "root";
                String password = "admin";
                conn = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static Integer[] copyArray(Integer[] array) {
        Integer[] integers = new Integer[array.length];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = array[i];
        }
        return integers;
    }
}
