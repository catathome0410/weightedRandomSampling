package com.company;

import java.util.ArrayList;

public class Main {



    public static void main(String[] args) {
	// write your code here
        SampleHelper sampleHelper = new SampleHelper();
        sampleHelper.generateSamples(100);
        for (int i = 0; i < 1000; i++) {
            sampleHelper.printWeightedRandomSample();
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

    public void printWeightedRandomSample() {
        Sample sample = this.weightedRandomSampling();
        System.out.println("sample id number is: " + sample.getId());
        System.out.println("sample value is: " + sample.getVal());
        System.out.println("sample weight is: " + sample.getWeight());
        System.out.println();
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
}