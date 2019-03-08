package com.educorp.eduinteractive.ecommerce.cache;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.educorp.eduinteractive.ecommerce.cache.Impl.CacheImpl;

public class CacheManager {
	private static Logger logger = LogManager.getLogger(CacheManager.class);
	private static Map<String, Cache> caches = null;
	
	public CacheManager() {
		caches = new HashMap <String, Cache>();
	}

	public static <K,V> Cache<K, V> getCache(String nombre, Class<K> keyClass, Class<V> valueClass) {
				
		return getCache(nombre,keyClass, valueClass, true /* arriesgado */);
	}

	public static <K,V> Cache<K,V> createCache (String nombre, Class<K> keyClass, Class<V> valueClass){
		Cache <K,V> newCache =  new CacheImpl<K,V>(nombre);
		caches.put(nombre, newCache);
		if(logger.isInfoEnabled()) {
			logger.info("Cache {} <{},{}> created.", nombre, keyClass, valueClass);
		}
		return newCache;
	}
	
	public static <K,V> Cache<K, V> getCache(String nombre,
			Class<K> keyClass,
			Class<V> valueClass,
			boolean autoCreate) {
		Cache<K,V> cache =  (Cache<K,V>) caches.get(nombre);
		if(cache == null) {
			if(autoCreate) {
				cache = createCache(nombre, keyClass, valueClass);
			}
		}
		return cache;
	}
}
