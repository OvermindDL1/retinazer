package com.github.antag99.benchmarks.retinazer;

import org.openjdk.jmh.infra.Blackhole;

import com.github.antag99.benchmarks.retinazer.components.ComponentD;
import com.github.antag99.retinazer.Entity;
import com.github.antag99.retinazer.EntityProcessorSystem;
import com.github.antag99.retinazer.Family;

public class IterationSystemD extends EntityProcessorSystem {
    private Blackhole voidness = new Blackhole();

    public IterationSystemD() {
        super(Family.with(ComponentD.class));
    }

    @Override
    public void process(Entity entity) {
        voidness.consume(entity);
    }
}