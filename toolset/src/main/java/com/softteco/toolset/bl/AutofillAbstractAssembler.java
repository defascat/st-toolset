package com.softteco.toolset.bl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author serge
 */
public abstract class AutofillAbstractAssembler<E, D> extends AbstractAssembler<E, D> {

    @Override
    public void assemble(D dto, E entity) {
        if (entity == null) {
            return;
        }
        
        final Class entityClass = entity.getClass();
        for (Field each : getDtoClass().getFields()) {
            Method method = null;
            try {
                if (each.getType().equals(boolean.class)) {
                    method = entityClass.getMethod("is" + each.getName().substring(0, 1).toUpperCase() + each.getName().substring(1));
                } else {
                    method = entityClass.getMethod("get" + each.getName().substring(0, 1).toUpperCase() + each.getName().substring(1));
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace(System.out);
            }

            try {
                each.set(dto, method.invoke(entity));
            } catch (IllegalAccessException e) {
                e.printStackTrace(System.out);
            } catch (InvocationTargetException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    public void disassemble(E entity, D dto) {
        final Class entityClass = entity.getClass();
        for (Field each : getDtoClass().getFields()) {
            Method method = null;
            try {
                method = entityClass.getMethod("set" + each.getName().substring(0, 1).toUpperCase() + each.getName().substring(1), each.getType());
            } catch (NoSuchMethodException e) {
                e.printStackTrace(System.out);
            }

            try {
                method.invoke(entity, each.get(dto));
            } catch (IllegalAccessException e) {
                e.printStackTrace(System.out);
            } catch (InvocationTargetException e) {
                e.printStackTrace(System.out);
            }
        }
    }
}