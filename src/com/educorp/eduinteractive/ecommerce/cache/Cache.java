package com.educorp.eduinteractive.ecommerce.cache;

public interface Cache<K, V> {

	public void put(K k, V v);
	
	public V get(K k);
	
}
