package com.cs.shopper.client.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import com.cs.shopper.client.entities.GWTSerializable;

public class TripleHashMap<A,B,C,D> extends GWTSerializable {
	private HashMap<A, HashMap<B, HashMap<C,D>>> hashmap;
	public TripleHashMap(){
		hashmap = new HashMap<A, HashMap<B, HashMap<C,D>>>();
	}
	
	public TripleHashMap(HashMap<A, HashMap<B, HashMap<C,D>>> hashmap){
		this.hashmap = hashmap;
	}
	public boolean contains(A top){
		return get().containsKey(top);
	}
	public boolean contains(A top, B middle){
		return contains(top)&&get(top).containsKey(middle);
	}
	public boolean contains(A top, B middle, C bottom){
		return contains(top,middle)&&get(top,middle).containsKey(bottom);
	}
	public void put(A top){
		put(top,new HashMap<B, HashMap<C, D>>());
	}
	public void put(A top, HashMap<B, HashMap<C, D>> rest){
		if(!contains(top)||get(top).isEmpty())
			get().put(top, rest);
	}
	public void put(A top, B middle){
		put(top);
		if(!contains(top,middle)){
			get(top).put(middle, new HashMap<C,D>());
		}
	}
	@SuppressWarnings("unchecked")
	public void put(A top, B middle, C bottom){
		put(top, middle);
		if(!contains(top,middle,bottom)){
			get(top,middle).put(bottom, (D)new Object());
		}
	}
	public void put(A top, B middle, C bottom, D value){
		put(top, middle);
		if(!contains(top,middle,bottom)){
			get(top,middle).put(bottom,value);
		}
	}
	public void put(A top, B middle,
			HashMap<C, D> bottom) {
		put(top,middle);
		if(get(top,middle).isEmpty())
			get(top).put(middle, bottom);
		
	}
	public HashMap<A, HashMap<B, HashMap<C, D>>> get(){
		return hashmap;
	}
	public HashMap<B, HashMap<C, D>> get(A top){
		return hashmap.get(top);
	}
	public HashMap<C, D> get(A top, B middle){
		return get(top).get(middle);
	}
	public D get(A top, B middle, C bottom){
		return get(top,middle).get(bottom);
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return hashmap.isEmpty();
	}

	public Set<A> topKeys() {
		return hashmap.keySet();
	}
	public Set<B> middleKeys(A top) {
		return get(top).keySet();
	}
	public Set<C> bottomKeys(A top, B middle) {
		return get(top,middle).keySet();
	}
	public Collection<D> bottomVals(A top, B middle) {
		return get(top,middle).values();
	}

	public void setAll(
			HashMap<A, HashMap<B, HashMap<C, D>>> hashmap) {
		this.hashmap = hashmap;		
	}



}
