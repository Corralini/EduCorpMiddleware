package com.educorp.eduinteractive.ecommerce.cache.Impl;

import java.util.HashMap;
import java.util.Map;

import com.educorp.eduinteractive.ecommerce.cache.Cache;

public class CacheImpl<K,V> implements Cache<K, V>{

	private Map<K,V> cache = null;
	private String name = null;
	
	public CacheImpl(String name) {
		this.name = name;
		cache = new HashMap<K,V>();
	}
	
	@Override
	public void put(K k, V v) {
		
		cache.put(k, v);
		
	}

	@Override
	public V get(K k) {
		
		return cache.get(k);
	}
	
}
