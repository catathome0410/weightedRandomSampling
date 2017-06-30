package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {



    public static void main(String[] args) {
	// write your code here
        SampleHelper sampleHelper = new SampleHelper();
        sampleHelper.generateSamples(3);
        HashMap<Integer, Integer> map = new HashMap<>();   // store generated samples id and number

        for (int i = 0; i < 1000; i++) {
            Sample sample = sampleHelper.weightedRandomSampling();
            map.put(sample.getId(), map.getOrDefault(sample.getId(), 0) + 1);
        }

        sampleHelper.printSamples();
        for (int it : map.keySet()) {
            System.out.println("id: " + it + ", number: " + map.get(it) );
        }
    }


}


class SampleHelper {

    ArrayList<Sample> samples;
    ArrayList<Double> totalWeights;   //   store the sum of the weights uptil current sample
    double totalWeight;

    public SampleHelper () {
        samples = new ArrayList<Sample>();
        totalWeights = new ArrayList<>();
        totalWeight = 0;
    }

    public void generateSamples(int n) {
        samples.clear(); // reset all the sameples
        totalWeights.clear();
        totalWeight = 0;

        for (int i = 0; i < n; i++) {
            Sample tmp = new Sample( (int)(Math.random()*10000) , (int)(Math.random()*1000), Math.random() );
            samples.add(tmp);
            totalWeight += tmp.weight;
            totalWeights.add(totalWeight);
        }
    }

    public Sample weightedRandomSampling() {
        double target = Math.random() *  totalWeight;
        for (int i = 0; i < samples.size(); i++) {
            if (totalWeights.get(i) > target) {
                return samples.get(i);
            }
        }
        return null;
    }

    public void printSamples() {
        for (int i = 0; i < samples.size(); i++) {
            System.out.println("id: " + samples.get(i).getId() + " value: " + samples.get(i).getVal() + " weight: " + samples.get(i).getWeight());
        }
        System.out.println();;
    }

}

class Sample {
    int id;
    int val;
    double weight;

    public Sample() {

    }

    public Sample (int i, int v, double w){
        id = i; val = v; weight = w;
    }

    public int getId() {
        return id;
    }

    public int getVal() {
        return val;
    }

    public double getWeight() {
        return weight;
    }


    public void print() {
        System.out.println("sample id number is: " + this.getId());
        System.out.println("sample value is: " + this.getVal());
        System.out.println("sample weight is: " + this.getWeight());
        System.out.println();
    }
}