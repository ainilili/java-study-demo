package org.nico.design.mode.filter;

import java.util.List;

public class NegativeFilter extends AbstractFilter<Integer>{

    @Override
    public void filter(List<Integer> list) {
        for(int index = list.size() -1; index >= 0; index --) {
            if(list.get(index) < 0) list.remove(index);
        }
    }

}
