/*
 * Copyright 2012 Goldman Sachs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gs.collections.impl;

import java.util.List;
import java.util.Random;

import com.gs.collections.impl.list.Interval;
import com.gs.collections.impl.list.mutable.FastList;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FastListAddAllTest
{
    @Test
    @Category(PerformanceTests.class)
    public void runFastListAddAll()
    {
        this.runIntegerFastListAddAll("integer");
        this.runLongFastListAddAll("long");
        this.runIntegerFastListAddAll("integer");
        this.runStringFastListAddAll("string");
        this.runIntegerFastListAddAll("integer");
        this.runLongFastListAddAll("long");
        this.runIntegerFastListAddAll("integer");
        this.runStringFastListAddAll("string");
    }

    private void runIntegerFastListAddAll(String type)
    {
        System.currentTimeMillis();
        Random r = new Random(123412123);
        Integer[] ints = new Integer[100000];
        for (int i = 0; i < ints.length; i++)
        {
            ints[i] = r.nextInt();
        }
        this.runFastListAddAll(type, ints);
    }

    private void runLongFastListAddAll(String type)
    {
        System.currentTimeMillis();
        Random r = new Random(123412123);
        Long[] longs = new Long[100000];
        for (int i = 0; i < longs.length; i++)
        {
            longs[i] = r.nextLong();
        }
        this.runFastListAddAll(type, longs);
    }

    private void runStringFastListAddAll(String type)
    {
        System.currentTimeMillis();
        Random r = new Random(123412123);
        String[] strings = new String[100000];
        for (int i = 0; i < strings.length; i++)
        {
            strings[i] = String.valueOf(r.nextLong());
        }
        this.runFastListAddAll(type, strings);
    }

    private void runFastListAddAll(String type, Object[] objects)
    {
        FastList<Object> listToAddAll = FastList.newListWith(objects);
        for (int i = 0; i < 100; i++)
        {
            this.runFastListAddAll(listToAddAll, 100);
        }
        for (int i = 0; i < 100; i++)
        {
            this.runFastListAddAll(listToAddAll, 100);
        }
        long now1 = System.currentTimeMillis();
        for (int i = 0; i < 100; i++)
        {
            this.runFastListAddAll(listToAddAll, 1000);
        }
        long time1 = System.currentTimeMillis() - now1;
        System.out.println("FastList, list size 100,000, " + type + " addAll/msec: " + 100000 / time1);
        long now2 = System.currentTimeMillis();
        this.runFastListAddAll(FastList.newList(Interval.oneTo(100)), 100000000);
        long time2 = System.currentTimeMillis() - now2;
        System.out.println("FastList, list size 100, addAll/msec: " + 100000000 / time2);
        long now3 = System.currentTimeMillis();
        this.runFastListAddAll(FastList.newListWith(Integer.valueOf(1)), 1000000000);
        long time3 = System.currentTimeMillis() - now3;
        System.out.println("FastList, list size 1, addAll/msec: " + 1000000000 / time3);
        long now4 = System.currentTimeMillis();
        this.runFastListAddAll(FastList.newList(), 10000000000L);
        long time4 = System.currentTimeMillis() - now4;
        System.out.println("FastList, list size (empty), addAll/msec: " + 10000000000L / time4);
    }

    public void runFastListAddAll(List<?> objects, long runs)
    {
        for (long l = 0; l < runs; l++)
        {
            FastList<Object> list = FastList.newList();
            list.addAll(objects);
        }
    }
}
