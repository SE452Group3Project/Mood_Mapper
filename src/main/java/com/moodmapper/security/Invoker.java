/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moodmapper.security;

import java.lang.reflect.*;

/**
 *
 * @author faithfulokoye
 */
public class Invoker {
/**
 * Get method and invoke it.
 * 
 * @author jbetancourt
 * 
 * @param name of method
 * @param obj Object to invoke the method on
 * @param types parameter types of method
 * @param args to method invocation
 * @return return value
 * @throws Exception for unforseen stuff
 */
public static final <T> Object invokeMethod(final String name, final T obj,
  final Class<?>[] types, final Object... args) throws Exception {

    Method method = obj.getClass().getDeclaredMethod(name, types);
    method.setAccessible(true);
    return method.invoke(null, args);
}

}
