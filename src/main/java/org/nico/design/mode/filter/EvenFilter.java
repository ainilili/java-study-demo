package org.nico.design.mode.filter;

import java.util.List;

public class EvenFilter extends AbstractFilter<Integer>{

    @Override
    public void filter(List<Integer> list) {
        for(int index = list.size() -1; index >= 0; index --) {
            if(list.get(index) % 2 != 1) list.remove(index);
        }
    }

}
