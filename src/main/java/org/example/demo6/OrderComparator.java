package org.example.demo6;

import java.util.Comparator;

public class OrderComparator implements Comparator<Ordering> {

    public int compare(Ordering o1 , Ordering o2){
        if(o1.isVip() && ! o2.isVip()) return  -1 ;
        if(!o1.isVip() && o2.isVip()) return  1 ;
        return Integer.compare(o1.getId(),o2.getId());


    }}
