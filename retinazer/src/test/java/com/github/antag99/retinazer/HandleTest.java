package com.github.antag99.retinazer;

import static org.junit.Assert.*;

import org.junit.Test;

public class HandleTest {
    @Test
    public void testCreate() {
        Engine engine = EngineConfig.create().finish();
        Handle entity = engine.createEntity();
        entity.create(FlagComponentA.class);
        assertNotNull(engine.getMapper(FlagComponentA.class).get(entity.getEntity()));
        engine.update();
        assertNotNull(engine.getMapper(FlagComponentA.class).get(entity.getEntity()));
    }

    @Test
    public void testAdd() {
        Engine engine = EngineConfig.create().finish();
        Handle entity = engine.createEntity();
        entity.add(new FlagComponentA());
        assertNotNull(engine.getMapper(FlagComponentA.class).get(entity.getEntity()));
        engine.update();
        assertNotNull(engine.getMapper(FlagComponentA.class).get(entity.getEntity()));
    }

    @Test
    public void testRemove() {
        Engine engine = EngineConfig.create().finish();
        Handle entity = engine.createEntity();
        entity.add(new FlagComponentA());
        entity.remove(FlagComponentA.class);
        assertNotNull(engine.getMapper(FlagComponentA.class).get(entity.getEntity()));
        engine.update();
        assertNull(engine.getMapper(FlagComponentA.class).get(entity.getEntity()));
        engine.update();
        assertNull(engine.getMapper(FlagComponentA.class).get(entity.getEntity()));
    }

    @Test
    public void testDuplicate() {
        Engine engine = EngineConfig.create().finish();
        Handle entity = engine.createEntity();
        Handle reference = entity.duplicate();
        assertEquals(entity.getEngine(), reference.getEngine());
        assertNotNull(reference.getEngine());
        assertEquals(0, entity.getEntity());
        assertEquals(0, reference.getEntity());
    }

    @Test
    public void testDestroy() {
        Engine engine = EngineConfig.create().finish();
        Handle entity = engine.createEntity().duplicate();
        entity.destroy();
        engine.update();
        assertFalse(engine.getEntities().contains(entity.getEntity()));
    }
}