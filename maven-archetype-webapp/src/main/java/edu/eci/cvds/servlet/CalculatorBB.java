package edu.eci.cvds.servlet;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ManagedBean(name = "calculatorBB")
@SessionScoped
public class CalculatorBB {
    public ArrayList<Double> numbers;
    public double mean;
    public double mode;
    public double standardDev;
    public double variance;
    public ArrayList<String> numSubmited;
    public CalculatorBB() {
        numSubmited = new ArrayList<String>();
    }
    public void setNumbers(String list) {
        numbers = new ArrayList<Double>();
        try {
            String[] arrayString = list.split(";");
            for (String stringNum:arrayString) {
                numbers.add(Double.parseDouble(stringNum));
            }
            setNumSubmited(list);
        }catch (Exception e)
        {
            System.out.println("se rompio");
            System.out.println(e.getMessage());
        }
    }
    public void setNumSubmited(String list){
        if(!numSubmited.contains(list)) {
            numSubmited.add(list);
        }
    }

    public double getMean(){
        return mean;
    }

    public ArrayList<String> getNumSubmited() {
        return numSubmited;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getMode() {
        return mode;
    }

    public void setMode(double mode) {
        this.mode = mode;
    }

    public double getStandardDev() {
        return standardDev;
    }

    public void setStandardDev(double standardDev) {
        this.standardDev = standardDev;
    }

    public double getVariance() {
        return variance;
    }

    public void setVariance(double variance) {
        this.variance = variance;
    }

    public double calculateMean(String list){
        setNumbers(list);
        double partialMean = 0;
        double sumMean = 0;
        for (Double num: numbers){
            sumMean += num;
        }
        partialMean = sumMean /(numbers.size()-1);
        setMean(partialMean);
        return partialMean;
    }

    public double calculateStandardDeviation(String list){
        double variance =  calculateVariance(list);
        double partialStandardDev = Math.sqrt(variance);
        setStandardDev(partialStandardDev);
        return partialStandardDev;
    }

    public double calculateVariance(String list){
        double partialVariance = 0;
        double mean = calculateMean(list);
        double varianceSum = 0;
        for (Double num: numbers) {
            varianceSum += Math.pow(num-mean, 2f);
        }
        partialVariance = varianceSum / (numbers.size() -1);
        setVariance(partialVariance);
        return partialVariance;
    }

    public double calculateMode(String list){
        int maxTimes = 0;
        ArrayList<Double> duplicates= new ArrayList<Double>();
        double partialMode = 0;
        for (Double num: numbers) {
            int times = 0;
            for (Double num2: numbers) {
                if(num.equals(num2)){
                    times += 1;
                }
            }
            if(times>maxTimes && times>1){
                partialMode = num;
                maxTimes = times;
            }

        }
        setMode(partialMode);
        return partialMode;
    }

    public void restart(){
        numbers = new ArrayList<Double>();
        numSubmited = new ArrayList<String>();
        setVariance(0);
        setStandardDev(0);
        setMean(0);
        setMode(0);
    }

}
