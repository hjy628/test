package cn.chap8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Locale.filter;
import static org.junit.Assert.assertEquals;

/**
 * Created by hjy on 17-2-8.
 */
public class PointTest {
    @Test
    public void testMoveRightBy() throws Exception{
        Point p1 = new Point(5,5);
        Point p2 = p1.moveRightBy(10);

        assertEquals(15,p2.getX());
        assertEquals(5,p2.getY());
    }


    @Test
    public void testFilter() throws Exception{
        List<Integer> numbers = Arrays.asList(1,2,3,4);
/*        List<Integer> even = filter(numbers,i-> i % 2 ==0);
        List<Integer> smallerThanThree = filter(numbers,i -> i < 3);
        assertEquals(Arrays.asList(2,4),even);
        assertEquals(Arrays.asList(1,2),smallerThanThree);*/
    }







}
