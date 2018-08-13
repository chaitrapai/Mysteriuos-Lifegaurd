/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package LifeGaurd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ChaitraDinesh
 */
public class optimize {

    /**
     * @param args the command line arguments
     * @param list
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the path of the Ratings Input file:");
        String inputFile1=sc.nextLine().trim();
        File inputfile = new File( inputFile1 );
        String line = "";
        FileWriter fw;
        BufferedWriter out;
        
        
        
        //String inputfile=
       // "C:\\Users\\ChaitraDinesh\\Documents\\Summer Assignment\\CMU MSSM Summer Programming Assignments\\11"
        // + ".in",line="";
        FileReader file_rdr=new FileReader(inputfile);
        BufferedReader buf_rdr = new BufferedReader(file_rdr);
        int num_lifeguard=Integer.parseInt(buf_rdr.readLine());       
        ArrayList<LifeGuard> lifeGaurds=new ArrayList<>();
        
        while ((line = buf_rdr.readLine()) != null) {
            String[] ratings = line.split(" ");
            lifeGaurds.add(new LifeGuard(Double.parseDouble(ratings[0]), Double.parseDouble( ratings[1])));
        }
        
        //sorting by start date
        Collections.sort(lifeGaurds, new LifeGuard());
        int answer =(int) getTotalShift(lifeGaurds);
        System.out.println("The answer is "+answer);
        
        System.out.println("Enter the path of the output file:");
        String outputfile=sc.nextLine().trim();
        fw = new FileWriter(outputfile);
        out = new BufferedWriter(fw);
        out.write(Integer.toString(answer));
        out.newLine();
        out.flush();
        }
        
        //remove the least optimal interval
        private static void removeMinimalImpactGuard(List<LifeGuard> swimIntervals) {
        double minImpact = Integer.MAX_VALUE;
        double previousEnd = Integer.MIN_VALUE;
        LifeGuard minSwimInterval = null;
        int swimIntervalSize = swimIntervals.size();
        for (int i = 0; i < swimIntervalSize; i++) {
            double nextEnd = i == swimIntervalSize - 1 ? Integer.MAX_VALUE : swimIntervals.get(i + 1).getEndTime();
            LifeGuard swimInterval = swimIntervals.get(i);
            LifeGuard nextInterval = i == swimIntervalSize - 1 ? null : swimIntervals.get(i + 1);
            double nextBegin = nextInterval == null ? Integer.MAX_VALUE : nextInterval.getStartTime();
            double currentImpact;
            if (nextEnd <= swimInterval.getEndTime()) {
                swimIntervals.remove(nextInterval);
                return;
            }
            currentImpact = swimInterval.getEndTime() - swimInterval.getStartTime();
            if (previousEnd > swimInterval.getStartTime()) {
                currentImpact = currentImpact - (previousEnd - swimInterval.getStartTime());
            }
            if (nextBegin < swimInterval.getEndTime()) {
                currentImpact = currentImpact - (swimInterval.getEndTime() - nextBegin);
            }
            if (currentImpact < minImpact) {
                minSwimInterval = swimInterval;
                minImpact = currentImpact;
            }
            previousEnd = swimInterval.getEndTime();
        }
        swimIntervals.remove(minSwimInterval);
     }
        //
    public static double getTotalShift(ArrayList<LifeGuard> swimIntervals) {
       
        removeMinimalImpactGuard(swimIntervals);
        return getTotalShiftAfterFiring(swimIntervals);
        
    }
    
    //find total time intervals after firing
    private static double getTotalShiftAfterFiring(ArrayList<LifeGuard> swimIntervals) {
        double sum = 0;
        int swimIntervalSize = swimIntervals.size();
        for (int i = 0; i < swimIntervalSize; i++) {
            LifeGuard currentInterval = swimIntervals.get(i);
            sum += currentInterval.getEndTime() - currentInterval.getStartTime();
            LifeGuard nextInterval = i == swimIntervalSize - 1 ? null : swimIntervals.get(i + 1);
            while (nextInterval != null && nextInterval.getEndTime() <= currentInterval.getEndTime()) {
                i++;
                nextInterval = i == swimIntervalSize - 1 ? null : swimIntervals.get(i + 1);
            }
            if (nextInterval != null && currentInterval.getEndTime() > nextInterval.getStartTime()) {
                sum -= currentInterval.getEndTime() - nextInterval.getStartTime();
            }
        }
        return sum;
    }
    }
  
    
        

