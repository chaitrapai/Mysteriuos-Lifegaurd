/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package LifeGaurd;

import com.sun.org.apache.xpath.internal.operations.Equals;
import java.util.Comparator;

/**
 *
 * @author ChaitraDinesh
 */
public class LifeGuard implements Comparator<Object>{
    double startTime;
    double endTime;
    
    public LifeGuard(double startTime, double endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    public LifeGuard() {
        this.startTime = 0;
        this.endTime = 0;
    }
    
    public double getStartTime() {
            return startTime;
        }

        public double getEndTime() {
            return endTime;
        }

        public double getDuration()
        {
            return endTime - startTime;
        }
        
        public boolean equals(Object o) {
    // self check
    if (this == o)
        return true;
    // null check
    if (o == null)
        return false;
    // type check and cast
    if (getClass() != o.getClass())
        return false;
    LifeGuard lg = (LifeGuard) o;
    // field comparison
    return (lg.getStartTime() == startTime
            && lg.getEndTime() == endTime);
}
    @Override
    public int compare(Object t, Object t1) {
        
        int ret = 0;
        LifeGuard l = (LifeGuard) t;
        LifeGuard l1 = (LifeGuard) t1;
        if (l.equals(l1))
        {
            return ret;
        }
        ret = Double.compare(l.getStartTime(), l1.getStartTime());
        if (0 == ret)
        {
            return Double.compare(l.getEndTime(), l1.getEndTime());
        }
        
        return ret;
    }
   
}
