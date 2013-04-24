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

package com.gs.collections.impl.memory.list;

import java.util.ArrayList;

import com.gs.collections.api.block.function.Function0;
import com.gs.collections.api.block.procedure.primitive.IntProcedure;
import com.gs.collections.api.list.primitive.IntList;
import com.gs.collections.impl.MemoryTests;
import com.gs.collections.impl.list.mutable.primitive.IntArrayList;
import com.gs.collections.impl.memory.MemoryTestBench;
import com.gs.collections.impl.memory.TestDataFactory;
import gnu.trove.list.array.TIntArrayList;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class IntListMemoryTest
{
    @Test
    @Category(MemoryTests.class)
    public void memoryForScaledLists()
    {
        for (int size = 0; size < 1000001; size += 25000)
        {
            this.memoryForScaledLists(size);
        }
    }

    public void memoryForScaledLists(int size)
    {
        MemoryTestBench.on(TIntArrayList.class)
                .printContainerMemoryUsage("IntList", size, new TIntArrayListFactory(size));
        MemoryTestBench.on(IntArrayList.class)
                .printContainerMemoryUsage("IntList", size, new IntArrayListFactory(size));
        MemoryTestBench.on(ArrayList.class)
                .printContainerMemoryUsage("IntList", size, new IntegerArrayListFactory(size));
    }

    public static class IntArrayListFactory implements Function0<IntArrayList>
    {
        private final IntList data;

        public IntArrayListFactory(int size)
        {
            this.data = TestDataFactory.create(size);
        }

        public IntArrayList value()
        {
            final IntArrayList list = new IntArrayList();
            this.data.forEach(new IntProcedure()
            {
                public void value(int each)
                {
                    list.add(each);
                }
            });
            return list;
        }
    }

    public static class TIntArrayListFactory implements Function0<TIntArrayList>
    {
        private final IntList data;

        public TIntArrayListFactory(int size)
        {
            this.data = TestDataFactory.create(size);
        }

        public TIntArrayList value()
        {
            final TIntArrayList list = new TIntArrayList();
            this.data.forEach(new IntProcedure()
            {
                public void value(int each)
                {
                    list.add(each);
                }
            });
            return list;
        }
    }

    public static class IntegerArrayListFactory implements Function0<ArrayList<Integer>>
    {
        private final IntList data;

        public IntegerArrayListFactory(int size)
        {
            this.data = TestDataFactory.create(size);
        }

        public ArrayList<Integer> value()
        {
            final ArrayList<Integer> list = new ArrayList<Integer>();
            this.data.forEach(new IntProcedure()
            {
                public void value(int each)
                {
                    list.add(each);
                }
            });
            return list;
        }
    }
}
