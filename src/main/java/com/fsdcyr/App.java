package com.fsdcyr;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Set<Integer> sets1 = Sets.newHashSet(1,2,3);
        Set<Integer> sets2 = Sets.newHashSet(1,2);

        System.out.println(Sets.difference(sets1, sets2));
    }
}
